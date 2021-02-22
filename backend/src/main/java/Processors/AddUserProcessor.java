package Processors;

import User.StandardUserDTO;
import User.StandardUserDAO;
import Responses.AddUserResponse;
import User.UserDTO;

import java.time.LocalDateTime;

public class AddUserProcessor implements UserProcessor {

    public AddUserResponse process(UserDTO newUser) {

        String uname = newUser.getUsername();
        String upwd = newUser.getPassword();
        AddUserResponse.AddUserResponseBuilder responseBuilder = new AddUserResponse.AddUserResponseBuilder();
        responseBuilder.setResponseTime(LocalDateTime.now().toString());
        responseBuilder.setUserName(uname);

        StandardUserDTO newuser = new StandardUserDTO(uname,upwd);
        if (StandardUserDAO.getInstance().addUser(newuser)) {
            responseBuilder.setResponseCode("200 OK");
            responseBuilder.setSuccess(true);
            responseBuilder.setResponse("User" + " " + uname + " Added Successfully !");
            return responseBuilder.build();
        }

        responseBuilder.setResponse("400 Error");
        responseBuilder.setSuccess(false);
        responseBuilder.setResponse("Unable to add User !");
        return responseBuilder.build();
    }


}