package com.springboot.project.cipher.app.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.cipher.api.model.DataRequest;
import com.springboot.project.cipher.api.model.HmacDataRequest;
import com.springboot.project.cipher.api.model.MatchDataRequest;
import com.springboot.project.cipher.app.exception.CipherException;
import com.springboot.project.cipher.app.model.HmacMessage;
import com.springboot.project.cipher.app.service.CipherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.cipher.api.CipherControllerApi;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor(onConstructor =@__(@Autowired))
public class CipherControllerApiImpl implements CipherControllerApi {
	
	private final CipherService cipherService;
	private final ObjectMapper objectMapper;
	
	public ResponseEntity<String> encodeData(DataRequest inputData) {
		return new ResponseEntity<String>(cipherService.encodeBase64(inputData.getData()), HttpStatus.CREATED);
	}

	public ResponseEntity<String> decodeData(DataRequest inputData) {
		return new ResponseEntity<String>(cipherService.decodeBase64(inputData.getData()), HttpStatus.CREATED);
	}

	public ResponseEntity<String> encodeUrl(DataRequest inputData) {
		return new ResponseEntity<String>(cipherService.encodeUrl(inputData.getData()), HttpStatus.CREATED);
	}

	public ResponseEntity<String> decodeUrl(DataRequest inputData) {
		return new ResponseEntity<String>(cipherService.decodeUrl(inputData.getData()), HttpStatus.CREATED);
	}

	public ResponseEntity<String> encryptData(DataRequest inputData) {
		return new ResponseEntity<String>(cipherService.encryptData(inputData.getData()), HttpStatus.CREATED);
	}

	public ResponseEntity<String> decryptData(DataRequest inputData) {
		return new ResponseEntity<String>(cipherService.decryptData(inputData.getData()), HttpStatus.CREATED);
	}

	public ResponseEntity<String> hashSHA256(DataRequest inputData) {
		return new ResponseEntity<String>(cipherService.hashSHA256(inputData.getData()), HttpStatus.CREATED);
	}

	public ResponseEntity<Boolean> checkMatchSha256(MatchDataRequest inputData) {
		return new ResponseEntity<Boolean>(cipherService
				.isSHA256Match(inputData.getRawData(), inputData.getHashedData()), HttpStatus.OK);
	}

	public ResponseEntity<String> hashBcrypt(DataRequest inputData) {
		return new ResponseEntity<String>(cipherService.bcrypt(inputData.getData()), HttpStatus.CREATED);
	}

	public ResponseEntity<Boolean> checkMatchBcrypt(MatchDataRequest inputData) {
		return new ResponseEntity<Boolean>(cipherService
				.isBcryptMatch(inputData.getRawData(), inputData.getHashedData()), HttpStatus.OK);
	}

	public ResponseEntity<String> hmac(String nonce, String urlCalculate, String timestamp, HmacDataRequest inputData) {
		String hmacMessageString = this.buildHmacMessage(nonce, urlCalculate, timestamp, inputData);
		return new ResponseEntity<String>(cipherService.hmac(hmacMessageString), HttpStatus.CREATED);
	}

	public ResponseEntity<Boolean> checkMatchHmac(String nonce, String urlCalculate, String timestamp, String hmac, HmacDataRequest inputData) {
		String hmacMessageString = this.buildHmacMessage(nonce, urlCalculate, timestamp, inputData);
		return new ResponseEntity<Boolean>(cipherService.isHmacMatch(hmacMessageString, hmac), HttpStatus.OK);
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
