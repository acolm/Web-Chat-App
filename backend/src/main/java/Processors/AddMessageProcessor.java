package Processors;

import Message.MessageDTO;
import Message.MessagesDAO;
import Responses.AddMessageResponse;

import java.time.LocalDateTime;

public class AddMessageProcessor {

    public AddMessageResponse process(MessageDTO newMessage) {

        String messageID = newMessage.getMessageID();

        AddMessageResponse.AddMessageResponseBuilder responseBuilder = new AddMessageResponse.AddMessageResponseBuilder();
        responseBuilder.setMessageID(messageID);
        responseBuilder.setResponseTime(LocalDateTime.now().toString());

        if (MessagesDAO.addMessage(newMessage)) {
            responseBuilder.setResponse("Message Added Successfully !");
            responseBuilder.setResponseCode("200 OK");
            responseBuilder.setSuccess(true);
        } else {
            responseBuilder.setResponse("Could not add Message !");
            responseBuilder.setResponseCode("500 Error");
            responseBuilder.setSuccess(false);
        }

        return responseBuilder.build();

    }
}
