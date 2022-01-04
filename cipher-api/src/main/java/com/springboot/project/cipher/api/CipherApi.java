package com.springboot.project.cipher.api;

import com.springboot.project.cipher.api.model.DataRequest;
import com.springboot.project.cipher.api.model.MatchDataRequest;

public interface CipherApi {
	
	String encodeData(DataRequest inputData);
	
	String decodeData(DataRequest inputData);

	String encodeUrl(DataRequest inputData);

	String decodeUrl(DataRequest inputData);

	String createEncryptKey();

	String encryptData(DataRequest inputData);

	String decryptData(DataRequest inputData);

	String hashSHA256(DataRequest inputData);

	Boolean checkMatchSha256(MatchDataRequest inputData);

	String hashSHA512(DataRequest inputData);

	Boolean checkMatchSha512(MatchDataRequest inputData);

	String hashBcrypt(DataRequest inputData);

	Boolean checkMatchBcrypt(MatchDataRequest inputData);

}
