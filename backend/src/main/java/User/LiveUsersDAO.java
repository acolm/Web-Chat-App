package User;

import Connector.MongoDBConnector;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class LiveUsersDAO {
    private static LiveUsersDAO ourInstance = null;
    private static MongoDBConnector mongoDBConnector = MongoDBConnector.getInstance();
    private static ArrayList<String> liveusers;

    public static LiveUsersDAO getInstance() {
        if (ourInstance == null) {
            ourInstance = new LiveUsersDAO();
        }
        return ourInstance;
    }

    public static boolean addLiveUser(String username) {
        Document newUserDoc = new Document("Username",username);
        try {
            mongoDBConnector.getLiveUsers().insertOne(newUserDoc);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean removeLiveUser(String username) {
        MongoCollection<Document> liveUsers = mongoDBConnector.getLiveUsers();
        BasicDBObject query = new BasicDBObject();
        query.put("Username", username);

        DeleteResult results = liveUsers.deleteMany(query);
        if (results.getDeletedCount() != 0) {
            return true;
        }
        return false;

    }

    public static boolean removeAllLiveUsers() {
        MongoCollection<Document> liveUsers = mongoDBConnector.getLiveUsers();
        liveUsers.drop();
        return true;

    }

    public static ArrayList<String> getLiveUsers() {
        MongoCollection usersDoc = mongoDBConnector.getLiveUsers();
        MongoCursor<Document> mongoCursor = usersDoc.find().iterator();
        liveusers = new ArrayList<>();

        try {
            while (mongoCursor.hasNext()) {
                Document doc = mongoCursor.next();
                String uname = doc.getString("Username");
                liveusers.add(uname);
            }
        } finally {
            mongoCursor.close();
        }
        Set<String> s = new LinkedHashSet<>(liveusers);
        liveusers = new ArrayList<>(s);
        return liveusers;
    }

    private LiveUsersDAO() {
    }
}
