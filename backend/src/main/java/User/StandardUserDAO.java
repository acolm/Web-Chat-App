package User;

import Connector.MongoDBConnector;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.HashMap;

public class StandardUserDAO implements UserDAO {
    private static StandardUserDAO ourInstance = null;
    private static HashMap<String, String> passmap = new HashMap<>();
    private static MongoDBConnector mongoDBConnector = MongoDBConnector.getInstance();


    public static StandardUserDAO getInstance() {
        if (ourInstance == null) {
            ourInstance = new StandardUserDAO();
        }
        return ourInstance;
    }

    public static boolean addUser(StandardUserDTO newUser) {
        Document newUserDoc = new Document("Username",newUser.getUsername()).append("Password", newUser.getPassword());
        passmap = getInstance().getValidUsers();
        if (passmap.get(newUser.getUsername()) != null) {
            return false;
        }
        try {
            mongoDBConnector.getUsers().insertOne(newUserDoc);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public HashMap<String,String> getValidUsers() {
        MongoCollection usersDoc = mongoDBConnector.getUsers();
        MongoCursor<Document> mongoCursor = usersDoc.find().iterator();

        try {
            while (mongoCursor.hasNext()) {
                Document doc = mongoCursor.next();
                String uname = doc.getString("Username"); //data.split("=")[0].split(" ")[1];
                String pwd = doc.getString("Password"); //data.split("=")[1];
                passmap.put(uname,pwd);
            }
        } finally {
            mongoCursor.close();
        }
        return passmap;
    }



    private StandardUserDAO() {
    }
}