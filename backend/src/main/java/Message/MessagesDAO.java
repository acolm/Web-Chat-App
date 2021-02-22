package Message;

import Connector.MongoDBConnector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.DoubleConsumer;
import java.util.stream.Collectors;

public class MessagesDAO {
    private static MessagesDAO ourInstance = null;
    private static MongoDBConnector mongoDBConnector = MongoDBConnector.getInstance();
    private static ArrayList<String> messageStrings;

    public static MessagesDAO getInstance() {
        if (ourInstance == null) {
            ourInstance = new MessagesDAO();
        }
        return ourInstance;
    }

    public static boolean addMessage(MessageDTO newMessage) {
        //Date dateAndTime = new Date(newMessage.getMessageTime());
        //SimpleDateFormat formatDateAndTime = new SimpleDateFormat("MM/dd/yy ',' hh:mm a");
        System.out.println(newMessage.getMessageTime());
        Document newMessageDocument = new Document("User", newMessage.getUser())
                .append("Message", newMessage.getMessage())
                .append("MessageID", newMessage.getMessageID())
                .append("Liked By", newMessage.getLikedBy())
                .append("MessageTime", newMessage.getMessageTime())
                .append("Like Count", newMessage.getNumLikes());
        try {
            mongoDBConnector.getMessages().insertOne(newMessageDocument);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> displayMessages() {
        MongoCollection<Document> messages = mongoDBConnector.getMessages();
        List<String> messagesList = messages.find().into(new ArrayList<>())
                .stream()
                .map(document -> {
                    return document.getString("MessageTime") + "--" + document.getString("User") + ": " + document.getString("Message")  + " | Likes: " + document.getInteger("Like Count");
                })
                .collect(Collectors.toList());

        return new ArrayList<>(messagesList);

    }

    private MessagesDAO() {
    }

    public static boolean likeMessage(int index , String appUser) {
        MongoCollection<Document> messages = mongoDBConnector.getMessages();
        Document doc = new Document();
        System.out.println("Index" +index);
        MongoCursor<Document> cursor = messages.find().iterator();
        try{
            if(index == 0){
                doc = cursor.next();
                ArrayList<String> likedByList = (ArrayList<String>)doc.get("Liked By");
                System.out.println(likedByList);
                System.out.println(likedByList.contains(appUser));
                if(likedByList.contains(appUser)) {
                    System.out.println("User already liked message at Index"+ index);
                    cursor.close();
                    return false;
                }
                else {
                    Document pushValue = new Document("Liked By", appUser);
                    Document incValue = new Document("Like Count", 1);
                    Document operations = new Document("$push", pushValue).append("$inc", incValue);
                    messages.updateOne(doc, operations);
                    System.out.println("Liked message at Index " + index);
                    System.out.println("After User Liked:" + (ArrayList<String>) doc.get("Liked By"));
                }
                cursor.close();
                return true;
            }
            else if(index>0){
                int i = 0;
                boolean found = false;
                while(!found){
                    if((i==index))
                        found = true;
                    else{
                        cursor.next();
                        i++;
                    }
                }
                doc = cursor.next();
                ArrayList<String> likedByList = (ArrayList<String>)doc.get("Liked By");
                System.out.println("Before User Liked:" + likedByList);
                System.out.println(likedByList.contains(appUser));
                if(likedByList.contains(appUser)) {
                    System.out.println("User already liked message at Index"+ index);
                    cursor.close();
                    return false;
                }
                else {
                    Document pushValue = new Document("Liked By", appUser);
                    Document incValue = new Document("Like Count", 1);
                    Document operations = new Document("$push", pushValue).append("$inc", incValue);
                    messages.updateOne(doc, operations);
                    System.out.println("Liked message at Index " + index);
                    System.out.println("After User Liked:" + (ArrayList<String>) doc.get("Liked By"));
                }
                cursor.close();
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
            cursor.close();
            return false;
        }
        System.out.println("failed");
        cursor.close();
        return false;
    }
}
