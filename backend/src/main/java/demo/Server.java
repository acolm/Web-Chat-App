package demo;

import Message.MessageDTO;
import Message.MessagesDAO;
import Processors.*;
import Responses.DisplayMessageResponse;
import Responses.GetLiveUserResponse;
import User.StandardUserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static spark.Spark.*;

public class Server {
  public static void main(String[] args) {


    port(1234);
      Gson gson = new GsonBuilder().create();
      webSocket("/ws", WebSocketHandler.class);

    post("/addUser", (req, res) -> {
          AddUserProcessor addUserProcessor = new AddUserProcessor();
          StandardUserDTO newUserDTO = gson.fromJson(req.body(), StandardUserDTO.class);
          return gson.toJson(addUserProcessor.process(newUserDTO));
      });

    post("/login", (req,res) -> {
        AuthenticateUserProcessor authenticateProcessor = new AuthenticateUserProcessor();
        StandardUserDTO newUserDTO = gson.fromJson(req.body(), StandardUserDTO.class);
        return gson.toJson(authenticateProcessor.process(newUserDTO));
    });

    post("/addMessage", (req, res) -> {
        AddMessageProcessor addMessageProcessor = new AddMessageProcessor();
        MessageDTO newMessage = gson.fromJson(req.body(), MessageDTO.class);
        return gson.toJson(addMessageProcessor.process(newMessage));
    });

    get("/getAllMessages", (req, res) -> {
        DisplayMessagesProcessor displayMessagesProcessor = new DisplayMessagesProcessor();
        DisplayMessageResponse response = displayMessagesProcessor.process();
        return gson.toJson(response);
    });

      post("/addLiveUser", (req, res) -> {
          AddLiveUserProcessor addUserProcessor = new AddLiveUserProcessor();
          StandardUserDTO newUserDTO = gson.fromJson(req.body(), StandardUserDTO.class);
          return gson.toJson(addUserProcessor.process(newUserDTO.getUsername()));
      });

      post("/removeLiveUser", (req, res) -> {
          RemoveLiveUserProcessor removeLiveUserProcessor = new RemoveLiveUserProcessor();
          StandardUserDTO newUserDTO = gson.fromJson(req.body(), StandardUserDTO.class);
          return gson.toJson(removeLiveUserProcessor.process(newUserDTO.getUsername()));
      });

      get("/getLiveUsers", (req, res) -> {
          GetLiveUsersProcessor getLiveUsersProcessor = new GetLiveUsersProcessor();
          GetLiveUserResponse response = getLiveUsersProcessor.process();
          return gson.toJson(response);
      });

      post("/likeMessage", (req,res)->{
          LikeMessageProcessor likeMessageProcessor = new LikeMessageProcessor();
          MessageDTO messageDTO = gson.fromJson(req.body(),MessageDTO.class);
          return gson.toJson(likeMessageProcessor.process(messageDTO));
      });


  }
}
