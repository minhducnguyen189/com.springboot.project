package com.springboot.project.cipher.api;

import com.springboot.project.cipher.api.model.DataRequest;
import com.springboot.project.cipher.api.model.HmacDataRequest;
import com.springboot.project.cipher.api.model.MatchDataRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface CipherControllerApi {
	
	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encode/base64", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> encodeData(@RequestBody DataRequest inputData);
	
	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decode/base64", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> decodeData(@RequestBody DataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encode/url", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> encodeUrl(@RequestBody DataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decode/url", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> decodeUrl(@RequestBody DataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/encrypt", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> encryptData(@RequestBody DataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/decrypt", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> decryptData(@RequestBody DataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/sha256", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> hashSHA256(@RequestBody DataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/sha256/check", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Boolean> checkMatchSha256(@RequestBody MatchDataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/bcrypt", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> hashBcrypt(@RequestBody DataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hash/bcrypt/check", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Boolean> checkMatchBcrypt(@RequestBody MatchDataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hmac", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> hmac(@RequestHeader(name = "x-nonce") String nonce,
								@RequestHeader(name = "x-uri-calculate") String urlCalculate,
								@RequestHeader(name = "x-timestamp") String timestamp,
								@RequestBody HmacDataRequest inputData);

	@RequestMapping(method = RequestMethod.POST, path = "v1/cipher/hmac/check", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Boolean> checkMatchHmac(@RequestHeader(name = "x-nonce") String nonce,
								@RequestHeader(name = "x-uri-calculate") String urlCalculate,
								@RequestHeader(name = "x-timestamp") String timestamp,
								@RequestHeader(name = "x-hmac") String hmac,
								@RequestBody HmacDataRequest inputData);
}
