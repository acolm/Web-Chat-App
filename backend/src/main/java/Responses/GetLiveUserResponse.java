package Responses;

import java.util.ArrayList;

public class GetLiveUserResponse {

    private final String responseTime;
    private final String responseCode;
    private final ArrayList<String> users;
    private final boolean success;

    public ArrayList<String> getUsers() {
        return this.users;
    }

    public GetLiveUserResponse(String responseTime, String responseCode, ArrayList<String> users, boolean success) {
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.users = users;
        this.success = success;
    }


    public static class GetLiveUserResponseBuilder {

        private String responseTime;
        private String responseCode;
        private ArrayList<String> users;
        private boolean success;

        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public void setUsers(ArrayList<String> users) {
            this.users = users;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }


        public GetLiveUserResponse build() {
            return new GetLiveUserResponse(responseTime, responseCode, users, success);
        }

    }
}
