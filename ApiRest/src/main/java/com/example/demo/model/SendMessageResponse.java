package com.example.demo.model;


public class SendMessageResponse {

    private Long timestamp;
    private Integer status;
    private String response;

    public SendMessageResponse(Long timestamp, Integer status, String response) {
        this.timestamp = timestamp;
        this.status = status;
        this.response = response;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
