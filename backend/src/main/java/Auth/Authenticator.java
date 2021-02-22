package Auth;


import java.util.HashMap;

import User.StandardUserDAO;
import User.UserDTO;

public class Authenticator {

    public static int authenticate(UserDTO user) {
        if (authenticateStandard(user)) {
            return 0;
        }
        return -1;
    }
    private static boolean authenticateStandard(UserDTO user) {

        HashMap<String,String> users = StandardUserDAO.getInstance().getValidUsers();

        if (users.get(user.getUsername()) != null) {
            return users.get(user.getUsername()).equals(user.getPassword());
        }
        return false;
    }



}