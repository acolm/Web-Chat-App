package Processors;

import Message.MessageDTO;
import Message.MessagesDAO;
import Responses.DisplayMessageResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DisplayMessagesProcessor {

    public DisplayMessageResponse process() {

        DisplayMessageResponse.DisplayMessageResponseBuilder responseBuilder = new DisplayMessageResponse.DisplayMessageResponseBuilder();
        responseBuilder.setResponseTime(LocalDateTime.now().toString());
        try {
            responseBuilder.setMessages(MessagesDAO.getInstance().displayMessages());
            responseBuilder.setResponseCode("200 OK");
            responseBuilder.setSuccess(true);
        } catch (Exception e) {
            ArrayList<String> errors = new ArrayList<String>();
            errors.add(e.toString());
            responseBuilder.setMessages(errors);
            responseBuilder.setSuccess(false);
            responseBuilder.setResponseCode("500 Error");
        }

        return responseBuilder.build();

    }
}
