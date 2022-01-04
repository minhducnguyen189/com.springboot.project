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
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

@Service
public class CipherService {

    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final String HASH_ALGORITHM_512 = "SHA-512";
    private static final String SECURE_RANDOM_ALGORITHMS = "SHA1PRNG";

    @Autowired
    private EncryptionConfig encryptionConfig;

    public String encodeBase64(String data) {
        return Base64Utils.encodeToString(data.getBytes());
    }

    public String decodeBase64(String data) {
        return new String(Base64Utils.decode(data.getBytes()));
    }

    public String encodeUrl(String urlString) {
        return UriUtils.encode(urlString, StandardCharsets.UTF_8);
    }

    public String decodeUrl(String urlString) {
        return UriUtils.decode(urlString, StandardCharsets.UTF_8);
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
            if (Objects.isNull(encryptionConfig.getAes().getIvSecret())) {
                byte[] ivParameterSpecKey = this.generateIvParameterSpec();
                IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameterSpecKey);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
                byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
                String ivParameterSpecKeyString = DatatypeConverter.printHexBinary(ivParameterSpecKey);
                String encryptedDataString = DatatypeConverter.printHexBinary(encryptedData);
                return ivParameterSpecKeyString.concat(encryptedDataString);
            }
            IvParameterSpec ivParameterSpec = new IvParameterSpec(encryptionConfig
                    .getAes()
                    .getIvSecret()
                    .getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
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
            byte[] dataBytes = DatatypeConverter.parseHexBinary(data);
            if (Objects.isNull(encryptionConfig.getAes().getIvSecret()) && dataBytes.length > 16) {
                byte[] ivParameterSpecKey = this.getIvParameterSpecKey(dataBytes);
                byte[] payload = Arrays.copyOfRange(dataBytes, 16, dataBytes.length);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameterSpecKey);
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
                byte[] decryptedData = cipher.doFinal(payload);
                return new String(decryptedData, StandardCharsets.UTF_8);
            }
            IvParameterSpec ivParameterSpec = new IvParameterSpec(encryptionConfig
                    .getAes()
                    .getIvSecret()
                    .getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decryptedData = cipher.doFinal(dataBytes);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new CipherException("Can not decrypt Data", ex);
        }
    }

    public String hashSHA256(String data) {
        try {
            String dataWithSalt = encryptionConfig.getSha256().getSalt().concat(data);
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = messageDigest.digest(dataWithSalt.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception ex) {
            throw new CipherException("Can not hash Data", ex);
        }
    }

    public boolean isSHA256Match(String data, String hashData) {
        String reHashData = this.hashSHA256(data);
        return reHashData.equals(hashData);
    }

    public String hashSHA512(String data) {
        try {
            String dataWithSalt = encryptionConfig.getSha512().getSalt().concat(data);
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM_512);
            byte[] hash = messageDigest.digest(dataWithSalt.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception ex) {
            throw new CipherException("Can not hash Data", ex);
        }
    }

    public boolean isSHA512Match(String data, String hashData) {
        String reHashData = this.hashSHA512(data);
        return reHashData.equals(hashData);
    }

    public String bcrypt(String data) {
        byte[] hash = BCrypt.withDefaults().hash(encryptionConfig.getBcrypt().getCost(),
                this.generateBcryptSalt(), data.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(hash);
    }

    public boolean isBcryptMatch(String data, String hashData) {
        return BCrypt.verifyer().verify(data.getBytes(StandardCharsets.UTF_8), DatatypeConverter.parseHexBinary(hashData)).verified;
    }

    private byte[] generateBcryptSalt() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM_ALGORITHMS);
            return secureRandom.generateSeed(16);
        } catch (NoSuchAlgorithmException e) {
            throw new CipherException("AlgorithmException unsupported: ", e);
        }
    }

    private byte[] generateIvParameterSpec() {
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    private byte[] getIvParameterSpecKey(byte[] dataBytes) {
        return Arrays.copyOfRange(dataBytes, 0, 16);
    }

}
