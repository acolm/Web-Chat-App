package Processors;

import Responses.AddLiveUserResponse;
import Responses.AddMessageResponse;
import User.LiveUsersDAO;

import java.time.LocalDateTime;

public class AddLiveUserProcessor {

    public AddLiveUserResponse process(String userName) {

        AddLiveUserResponse.AddLiveUserResponseBuilder addLiveUserResponseBuilder = new AddLiveUserResponse.AddLiveUserResponseBuilder();

        addLiveUserResponseBuilder.setResponseTime(LocalDateTime.now().toString());

        if (LiveUsersDAO.getInstance().addLiveUser(userName)) {
            addLiveUserResponseBuilder.setResponseCode("200 OK");
            addLiveUserResponseBuilder.setSuccess(true);
            addLiveUserResponseBuilder.setUser(userName);
        } else {
            addLiveUserResponseBuilder.setResponseCode("500 ERROR");
            addLiveUserResponseBuilder.setSuccess(false);
            addLiveUserResponseBuilder.setUser(null);
        }
        return addLiveUserResponseBuilder.build();
    }
}
