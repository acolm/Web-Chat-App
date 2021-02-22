package Responses;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayMessageResponse {

    private final String responseTime;
    private final String responseCode;
    private final ArrayList<String> messages;
    private final boolean success;

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public DisplayMessageResponse(String responseTime, String responseCode, ArrayList<String> messages, boolean success) {
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.messages = messages;
        this.success = success;
    }


    public static class DisplayMessageResponseBuilder {

        private String responseTime;
        private String responseCode;
        private ArrayList<String> messages;
        private boolean success;

        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public void setMessages(ArrayList<String> messages) {
            this.messages = messages;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }


       public DisplayMessageResponse build() {
            return new DisplayMessageResponse(responseTime, responseCode, messages, success);
       }

    }
}
