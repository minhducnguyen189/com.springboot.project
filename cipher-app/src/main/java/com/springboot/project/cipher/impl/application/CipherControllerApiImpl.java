package com.springboot.project.cipher.impl.application;

import com.springboot.project.cipher.api.model.DataRequest;
import com.springboot.project.cipher.impl.service.CipherService;
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

}
