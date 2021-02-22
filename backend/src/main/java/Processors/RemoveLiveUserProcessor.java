package Processors;

import Responses.RemoveLiveUserResponse;
import User.LiveUsersDAO;

import java.time.LocalDateTime;

public class RemoveLiveUserProcessor {

    public RemoveLiveUserResponse process(String userName) {

        RemoveLiveUserResponse.RemoveLiveUserResponseBuilder removeLiveUserResponseBuilder = new RemoveLiveUserResponse.RemoveLiveUserResponseBuilder();

        removeLiveUserResponseBuilder.setResponseTime(LocalDateTime.now().toString());

        if (LiveUsersDAO.getInstance().removeLiveUser(userName)) {
            removeLiveUserResponseBuilder.setResponseCode("200 OK");
            removeLiveUserResponseBuilder.setSuccess(true);
            removeLiveUserResponseBuilder.setUser(userName);
        } else {
            removeLiveUserResponseBuilder.setResponseCode("500 ERROR");
            removeLiveUserResponseBuilder.setSuccess(false);
            removeLiveUserResponseBuilder.setUser(null);
        }
        return removeLiveUserResponseBuilder.build();
    }
}