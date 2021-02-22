package Processors;

import Responses.GetLiveUserResponse;
import User.LiveUsersDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetLiveUsersProcessor {

    public GetLiveUserResponse process() {

        GetLiveUserResponse.GetLiveUserResponseBuilder responseBuilder = new GetLiveUserResponse.GetLiveUserResponseBuilder();
        responseBuilder.setResponseTime(LocalDateTime.now().toString());
        try {
            responseBuilder.setUsers(LiveUsersDAO.getInstance().getLiveUsers());
            responseBuilder.setResponseCode("200 OK");
            responseBuilder.setSuccess(true);
        } catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.toString());
            responseBuilder.setUsers(errors);
            responseBuilder.setSuccess(false);
            responseBuilder.setResponseCode("500 Error");
        }

        return responseBuilder.build();

    }
}
