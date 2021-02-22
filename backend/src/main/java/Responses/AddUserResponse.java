package Responses;

public class AddUserResponse implements Response {

    private final String responseTime;
    private final String responseCode;
    private final String userName;
    private final boolean success;
    private final String response;

    public AddUserResponse(String responseTime, String responseCode, String userName, boolean success, String response) {
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.userName = userName;
        this.success = success;
        this.response = response;
    }

    public static class AddUserResponseBuilder {

        private String responseTime;
        private String responseCode;
        private String userName;
        private boolean success;
        private String response;


        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public void setResponse(String response) {this.response = response;}

        public AddUserResponse build() {
            return new AddUserResponse(responseTime, responseCode, userName, success, response);
        }


    }



}