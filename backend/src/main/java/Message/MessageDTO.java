package Message;

import java.util.ArrayList;


public class MessageDTO {

    private final String messageID;
    private final String message;
    private final String user;
    private final String appUser;
    private final ArrayList<String> likedBy;
    private final int numLikes;
    private final int i;
    private final String messageTime;


    public MessageDTO(String messageID, String message, String user, ArrayList<String> likedBy, int numLikes, String messageTime, int i, String appUser) {
        this.messageID = messageID;
        this.message = message;
        this.user = user;
        this.likedBy = likedBy;
        this.numLikes = numLikes;
        this.messageTime = messageTime;
        this.i = i;
        this.appUser = appUser;
    }


    public String getMessageID() {
        return messageID;
    }

    public String getMessage() {
        return message;
    }

    public String getAppUser(){return appUser;}

    public String getUser() {
        return user;
    }

    public String getMessageTime() {
        return messageTime;
    }

    // Return String of Users who Liked a message
    /*public ArrayList<String> getLikedBy() {
        String outstring = "";
        if (getNumLikes() != 0) {
            for (int i = 0; i <numLikes; i++) {
                outstring += likedBy.get(i);

            }
        }
        return outstring;
    }*/

    public ArrayList<String> getLikedBy()
    {
        return likedBy;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public int getIndex(){return i;}
}
