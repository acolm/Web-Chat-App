package Responses;

public class AddMessageResponse {

    private final String responseTime;
    private final String responseCode;
    private final String messageID;
    private final boolean success;
    private final String response;


    public AddMessageResponse(String responseTime, String responseCode, String messageID, boolean success, String response) {
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.messageID = messageID;
        this.success = success;
        this.response = response;
    }
    public boolean isSuccess() {
        return success;
    }



    public static class AddMessageResponseBuilder {

        private String responseCode;
        private String messageID;
        private boolean success;
        private String response;
        private String responseTime;

        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public void setMessageID(String messageID) {
            this.messageID = messageID;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public AddMessageResponse build() {
            return new AddMessageResponse(responseTime, responseCode, messageID, success, response);
        }

    }


}
