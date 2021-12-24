package com.springboot.project.cipher.app.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.springboot.project.cipher.app.exception.CipherException;
import com.springboot.project.cipher.app.model.EncryptionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class CipherService {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String SECURE_RANDOM_ALGORITHMS = "SHA1PRNG";
    private static final String IV = "94A15E1F08550FFC";

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

    public String createEncryptKey() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
            keyGenerator.init(256, secureRandom);
            SecretKey key = keyGenerator.generateKey();
            return DatatypeConverter.printHexBinary(key.getEncoded());
        } catch (Exception ex) {
            throw new CipherException("Can not generate Secret Key", ex);
        }
    }

    public String encryptData(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            byte[] key = DatatypeConverter.parseHexBinary(encryptionConfig.getAes().getSecret());
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(DEFAULT_CHARSET));
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes(DEFAULT_CHARSET));
            return DatatypeConverter.printHexBinary(encryptedData);
        } catch (Exception ex) {
            throw new CipherException("Can not encrypt Data", ex);
        }
    }

    public String decryptData(String data) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);
            byte[] key = DatatypeConverter.parseHexBinary(encryptionConfig.getAes().getSecret());
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(DEFAULT_CHARSET));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decodedData = DatatypeConverter.parseHexBinary(data);
            byte[] decryptedData = cipher.doFinal(decodedData);
            return new String(decryptedData, DEFAULT_CHARSET);
        } catch (Exception ex) {
            throw new CipherException("Can not decrypt Data", ex);
        }
    }

    public String hashSHA256(String data) {
        try {
            String dataWithSalt = encryptionConfig.getSha256().getSalt().concat(data);
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = messageDigest.digest(dataWithSalt.getBytes(DEFAULT_CHARSET));
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception ex) {
            throw new CipherException("Can not hash Data", ex);
        }
    }

    public boolean isSHA256Match(String data, String hashData) {
        String reHashData = this.hashSHA256(data);
        return reHashData.equals(hashData);
    }

    public String bcrypt(String data) {
        try {
            byte[] hash = BCrypt.withDefaults().hash(encryptionConfig.getBcrypt().getCost(),
                    this.generateBcryptSalt(), data.getBytes(DEFAULT_CHARSET));
            return DatatypeConverter.printHexBinary(hash);
        } catch (UnsupportedEncodingException e) {
            throw new CipherException("Bcrypt unsupported encoding");
        }
    }

    public boolean isBcryptMatch(String data, String hashData) {
        try {
            return BCrypt.verifyer().verify(data.getBytes(DEFAULT_CHARSET), DatatypeConverter.parseHexBinary(hashData)).verified;
        } catch (UnsupportedEncodingException e) {
            throw new CipherException("Bcrypt unsupported encoding");
        }
    }

    public String hmac(String data) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    encryptionConfig.getHmac().getSecret().getBytes(DEFAULT_CHARSET), HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(data.getBytes(DEFAULT_CHARSET));
            return DatatypeConverter.printHexBinary(hmacBytes);
        } catch (Exception ex) {
            throw new CipherException("Can not Hmac Data", ex);
        }
    }

    public boolean isHmacMatch(String data, String hmacData) {
        String reHmacData = this.hmac(data);
        return reHmacData.equals(hmacData);
    }

    private byte[] generateBcryptSalt() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHMS);
            return secureRandom.generateSeed(16);
        } catch (NoSuchAlgorithmException e) {
            throw new CipherException("AlgorithmException unsupported: ", e);
        }
    }

}
