package com.springboot.project.basicAuth.app.service;

import com.springboot.project.basicAuth.api.model.BasicAuthUserRequest;
import com.springboot.project.basicAuth.app.entity.UserEntity;
import com.springboot.project.basicAuth.app.repository.UserRepository;
import com.springboot.project.cipher.app.service.CipherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class BasicAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CipherService cipherService;

    public String createUserRepository(BasicAuthUserRequest basicAuthUserRequest) {
        String plainPassword = this.cipherService.decryptData(basicAuthUserRequest.getPassword());
        String bcryptPassword = this.cipherService.bcrypt(plainPassword);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(basicAuthUserRequest.getUsername());
        userEntity.setPassword(bcryptPassword);
        userEntity.setCreatedDate(LocalDateTime.now());
        userRepository.save(userEntity);
        return "OK";
    }

    public boolean checkValidBasicAuth(String basicAuthString) {
        if (StringUtils.isNoneBlank(basicAuthString)) {
            String[] plainBasicAuth = this.basicAuthStringHandler(basicAuthString);
            String username = plainBasicAuth[0];
            String password = plainBasicAuth[1];
            Optional<UserEntity> userEntity = this.userRepository.findById(username);
            if (userEntity.isPresent()) {
                return this.cipherService.isBcryptMatch(password, userEntity.get().getPassword());
            }
        }
        return false;
    }

    public String getUsername(String basicAuthString) {
        return this.basicAuthStringHandler(basicAuthString)[0];
    }

    public String getPassword(String basicAuthString) {
        return this.basicAuthStringHandler(basicAuthString)[1];
    }

    private String[] basicAuthStringHandler(String basicAuthString) {
        String encodedAuthString = StringUtils.substring(basicAuthString, "Basic ".length()).trim();
        String plainBasicAuthString = this.cipherService.decodeBase64(encodedAuthString);
        return plainBasicAuthString.split(":");
    }


}
