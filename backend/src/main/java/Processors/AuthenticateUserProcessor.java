package Processors;

import Auth.Authenticator;
import Responses.AuthResponse;
import User.UserDTO;

import java.time.LocalDateTime;

public class AuthenticateUserProcessor implements UserProcessor {

    public AuthResponse process(UserDTO user) {

        String uname = user.getUsername();
        AuthResponse.AuthResponseBuilder authResponseBuilder = new AuthResponse.AuthResponseBuilder();
        authResponseBuilder.setResponseTime(LocalDateTime.now().toString());
        authResponseBuilder.setUserName(uname);


        int authLevel = Authenticator.authenticate(user);
        boolean adminAuth = user.isAdmin();
        if (authLevel == 0 && !adminAuth ) {
            authResponseBuilder.setSuccess(true);
            authResponseBuilder.setResponseCode("200 OK");
            authResponseBuilder.setResponse("Successful Authentication !");
            return authResponseBuilder.build();
        } else if (authLevel == 1) {
            authResponseBuilder.setSuccess(true);
            authResponseBuilder.setResponseCode("200 OK");
            authResponseBuilder.setResponse("Successful Admin Authentication !");
            return authResponseBuilder.build();
        }
        authResponseBuilder.setResponseCode("400 Error");
        authResponseBuilder.setSuccess(false);
        authResponseBuilder.setResponse("Authentication Failed !");
        return authResponseBuilder.build();
    }
}