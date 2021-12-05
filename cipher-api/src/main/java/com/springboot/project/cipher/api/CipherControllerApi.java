package com.springboot.project.cipher.api;

import com.springboot.project.cipher.api.model.DataRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
}
