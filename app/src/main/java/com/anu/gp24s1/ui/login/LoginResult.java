package com.anu.gp24s1.ui.login;

public class LoginResult {
    public enum Status {
        SUCCESS,
        FAIL
    }

    private Status status;

    public LoginResult(Status status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return status.equals(Status.SUCCESS);
    }

}