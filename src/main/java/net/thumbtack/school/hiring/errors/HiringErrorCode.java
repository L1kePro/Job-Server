package net.thumbtack.school.hiring.errors;

public enum HiringErrorCode {
    NOT_LOGGED_IN("NOT_LOGGED_IN"),
    EXIST_LOGIN("LOGIN_ALREADY_EXIST"),
    WRONG_LOGIN("WRONG_LOGIN: Cannot be empty or less 3 char"),
    WRONG_FIRSTNAME("WRONG_FIRSTNAME: Cannot be empty or less 3 char"),
    WRONG_PASSWORD("WRONG_PASSWORD: Cannot be empty or less 3 char");

    private String errorString;

    HiringErrorCode(String message) {
        this.errorString = message;
    }
    public String getErrorString() {
        return errorString;
    }
}
