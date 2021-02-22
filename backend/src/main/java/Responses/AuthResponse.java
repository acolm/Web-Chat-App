package Responses;

public class AuthResponse implements Response {

    private final String username;
    private final String responseTime;
    private final String responseCode;
    private final boolean success;
    private final String response;

    public AuthResponse(String responseTime, String responseCode,String username, boolean success, String response) {
        this.username = username;
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.success = success;
        this.response = response;
    }

    public static class AuthResponseBuilder {

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

        public AuthResponse build() {
            return new AuthResponse(responseTime, responseCode, userName, success, response);
        }
    }
}