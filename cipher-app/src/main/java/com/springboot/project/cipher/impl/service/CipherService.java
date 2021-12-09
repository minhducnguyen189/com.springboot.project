package com.springboot.project.cipher.impl.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.springboot.project.cipher.impl.exception.CipherException;
import com.springboot.project.cipher.impl.model.EncryptionConfig;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

@Service
public class CipherService {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String HASH_ALGORITHM = "SHA-256";

    @Autowired
    private EncryptionConfig encryptionConfig;

    public String encodeBase64(String data) {
        return Base64Utils.encodeToString(data.getBytes());
    }

    public String decodeBase64(String data) {
        return new String(Base64Utils.decode(data.getBytes()));
    }

    public String encodeUrl(String urlString) {
        return UriUtils.encode(urlString, DEFAULT_CHARSET);
    }

    public String decodeUrl(String urlString) {
        return UriUtils.decode(urlString, DEFAULT_CHARSET);
    }

    public String encryptData(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            byte[] key = encryptionConfig.getAes().getSecret().getBytes(DEFAULT_CHARSET);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes(DEFAULT_CHARSET));
            return HexBin.encode(encryptedData);
        } catch (Exception ex) {
            throw new CipherException("Can not encrypt Data", ex);
        }
    }

    public String decryptData(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            byte[] key = encryptionConfig.getAes().getSecret().getBytes(DEFAULT_CHARSET);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decodedData = HexBin.decode(data);
            byte[] decryptedData = cipher.doFinal(decodedData);
            return new String(decryptedData, DEFAULT_CHARSET);
        } catch (Exception ex) {
            throw new CipherException("Can not encrypt Data", ex);
        }
    }

    public String hashSHA256(String data) {
        try {
            String dataWithSalt = encryptionConfig.getSha256().getSalt().concat(data);
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = messageDigest.digest(dataWithSalt.getBytes(DEFAULT_CHARSET));
            return HexBin.encode(hash);
        } catch (Exception ex) {
            throw new CipherException("Can not hash Data", ex);
        }
    }

    public boolean isSHA256Match(String data, String hashData) {
        String reHashData = hashSHA256(data);
        return reHashData.equals(hashData);
    }

    public String bcrypt(String data) {
        try {
            byte[] hash = BCrypt.withDefaults().hash(encryptionConfig.getBcrypt().getCost(),
                    encryptionConfig.getBcrypt().getSaltLength16().getBytes(DEFAULT_CHARSET), data.getBytes(DEFAULT_CHARSET));
            return new String(hash, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new CipherException("Bcrypt unsupported encoding");
        }
    }

    public boolean isBcryptMatch(String data, String hashData) {
        try {
            return BCrypt.verifyer().verify(data.getBytes(DEFAULT_CHARSET), hashData.getBytes(DEFAULT_CHARSET)).verified;
        } catch (UnsupportedEncodingException e) {
            throw new CipherException("Bcrypt unsupported encoding");
        }
    }

}
