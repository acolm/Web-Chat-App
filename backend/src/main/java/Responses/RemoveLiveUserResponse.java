package Responses;

public class RemoveLiveUserResponse {

    private final String responseTime;
    private final String responseCode;
    private final String user;
    private final boolean success;

    public RemoveLiveUserResponse(String responseTime, String responseCode, String user, boolean success) {
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.user = user;
        this.success = success;
    }


    public static class RemoveLiveUserResponseBuilder {

        private String responseCode;
        private String user;
        private String responseTime;
        private boolean success;


        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public RemoveLiveUserResponse build() {
            return new RemoveLiveUserResponse(responseTime, responseCode, user, success);
        }


    }




}