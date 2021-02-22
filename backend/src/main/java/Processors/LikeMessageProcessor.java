package Processors;

import Message.MessageDTO;
import Message.MessagesDAO;
import Responses.LikeMessageResponse;

import java.time.LocalDateTime;

public class LikeMessageProcessor {
    public LikeMessageResponse process(MessageDTO message){
        int index = message.getIndex();
        String appUser = message.getAppUser();

        LikeMessageResponse.LikeMessageResponseBuilder responseBuilder = new LikeMessageResponse.LikeMessageResponseBuilder();
        responseBuilder.setResponseTime(LocalDateTime.now().toString());

        if(MessagesDAO.likeMessage(index, appUser))
        {
            responseBuilder.setResponse(appUser + "Liked message at index= "+ index + "Successfully!");
            responseBuilder.setResponseCode("200 OK");
            responseBuilder.setSuccess(true);
            return responseBuilder.build();
        }

        responseBuilder.setResponse("Could not like message! (You may have already liked this message)");
        responseBuilder.setResponseCode("500 Error");
        responseBuilder.setSuccess(false);
        return responseBuilder.build();

    }
}
