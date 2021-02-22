package Connector;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class MongoDBConnector {
    private static MongoDBConnector ourInstance = null;
    private static MongoClient mongoClient;

    public static MongoDBConnector getInstance() {
        if (ourInstance == null) {
            ourInstance = new MongoDBConnector();
        }
        return ourInstance;
    }
    private static void connectMongo() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create();
        }
    }

    private static MongoDatabase getDB() {
        connectMongo();
        return mongoClient.getDatabase("Database");
    }

    public static MongoCollection<Document> getUsers() {
        MongoDatabase db = getDB();
        return db.getCollection("Users");
    }

    public static MongoCollection<Document> getLiveUsers() {
        MongoDatabase db = getDB();
        return db.getCollection("LiveUsers");
    }

    public static MongoCollection<Document> getMessages() {
        MongoDatabase db = getDB();
        return db.getCollection("Messages");
    }

    private MongoDBConnector() {
    }
}
