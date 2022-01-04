package com.springboot.project.root.application.controller;

import com.springboot.project.cipher.api.CipherApi;
import com.springboot.project.cipher.api.model.DataRequest;
import com.springboot.project.cipher.api.model.MatchDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CipherControllerApi {

	@Autowired(required = false)
	private CipherApi cipherApi;

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encode/base64", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> encodeData(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.encodeData(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decode/base64", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> decodeData(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.decodeData(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encode/url", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> encodeUrl(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.encodeUrl(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decode/url", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> decodeUrl(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.decodeUrl(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, path = "v1/cipher/encrypt/key", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createEncryptKey() {
		return new ResponseEntity<>(cipherApi.createEncryptKey(), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encrypt", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> encryptData(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.encryptData(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decrypt", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> decryptData(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.decryptData(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/sha256", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> hashSHA256(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.hashSHA256(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/sha256/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> checkMatchSha256(@RequestBody MatchDataRequest inputData) {
		return new ResponseEntity<>(cipherApi.checkMatchSha256(inputData), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/sha512", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> hashSHA512(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.hashSHA512(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/sha512/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> checkMatchSha512(@RequestBody MatchDataRequest inputData) {
		return new ResponseEntity<>(cipherApi.checkMatchSha512(inputData), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/bcrypt", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> hashBcrypt(@RequestBody DataRequest inputData) {
		return new ResponseEntity<>(cipherApi.hashBcrypt(inputData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/bcrypt/check", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> checkMatchBcrypt(@RequestBody MatchDataRequest inputData) {
		return new ResponseEntity<>(cipherApi.checkMatchBcrypt(inputData), HttpStatus.OK);
	}

}
