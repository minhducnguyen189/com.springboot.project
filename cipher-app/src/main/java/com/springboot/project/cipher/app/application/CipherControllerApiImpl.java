package com.springboot.project.cipher.app.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.cipher.api.CipherApi;
import com.springboot.project.cipher.api.model.DataRequest;
import com.springboot.project.cipher.api.model.HmacDataRequest;
import com.springboot.project.cipher.api.model.MatchDataRequest;
import com.springboot.project.cipher.app.exception.CipherException;
import com.springboot.project.cipher.app.model.HmacMessage;
import com.springboot.project.cipher.app.service.CipherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor(onConstructor =@__(@Autowired))
public class CipherControllerApiImpl implements CipherApi {
	
	private final CipherService cipherService;
	private final ObjectMapper objectMapper;
	
	public String encodeData(DataRequest inputData) {
		return cipherService.encodeBase64(inputData.getData());
	}

	public String decodeData(DataRequest inputData) {
		return cipherService.decodeBase64(inputData.getData());
	}

	public String encodeUrl(DataRequest inputData) {
		return cipherService.encodeUrl(inputData.getData());
	}

	public String decodeUrl(DataRequest inputData) {
		return cipherService.decodeUrl(inputData.getData());
	}

	public String createEncryptKey() {
		return cipherService.createEncryptKey();
	}

	public String encryptData(DataRequest inputData) {
		return cipherService.encryptData(inputData.getData());
	}

	public String decryptData(DataRequest inputData) {
		return cipherService.decryptData(inputData.getData());
	}

	public String hashSHA256(DataRequest inputData) {
		return cipherService.hashSHA256(inputData.getData());
	}

	public Boolean checkMatchSha256(MatchDataRequest inputData) {
		return cipherService.isSHA256Match(inputData.getRawData(), inputData.getHashedData());
	}

	public String hashBcrypt(DataRequest inputData) {
		return cipherService.bcrypt(inputData.getData());
	}

	public Boolean checkMatchBcrypt(MatchDataRequest inputData) {
		return cipherService.isBcryptMatch(inputData.getRawData(), inputData.getHashedData());
	}

	public String hmac(String nonce, String urlCalculate, String timestamp, HmacDataRequest inputData) {
		String hmacMessageString = this.buildHmacMessage(nonce, urlCalculate, timestamp, inputData);
		return cipherService.hmac(hmacMessageString);
	}

	public Boolean checkMatchHmac(String nonce, String urlCalculate, String timestamp, String hmac, HmacDataRequest inputData) {
		String hmacMessageString = this.buildHmacMessage(nonce, urlCalculate, timestamp, inputData);
		return cipherService.isHmacMatch(hmacMessageString, hmac);
	}


	private String buildHmacMessage(String nonce, String urlCalculate, String timestamp, HmacDataRequest inputData) {
		HmacMessage hmacMessage = new HmacMessage();
		hmacMessage.setNonce(nonce);
		hmacMessage.setTimestamp(urlCalculate);
		hmacMessage.setTimestamp(timestamp);
		hmacMessage.setHmacDataRequest(inputData);
		try {
			return objectMapper.writeValueAsString(hmacMessage);
		} catch (JsonProcessingException ex) {
			throw new CipherException("can not parse hmac message: ", ex);
		}
	}


}
