package Responses;

import java.util.ArrayList;

public class LikeMessageResponse {
    private final String response;
    private final String responseTime;
    private final String responseCode;
    private final boolean success;

    public LikeMessageResponse(String response,String responseTime, String responseCode, boolean success) {
        this.response = response;
        this.responseTime = responseTime;
        this.responseCode = responseCode;
        this.success = success;
    }

    public static class LikeMessageResponseBuilder {

        private String response;
        private String responseTime;
        private String responseCode;
        private boolean success;

        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }


        public LikeMessageResponse build() {
            return new LikeMessageResponse(response,responseTime, responseCode, success);
        }

    }
}
