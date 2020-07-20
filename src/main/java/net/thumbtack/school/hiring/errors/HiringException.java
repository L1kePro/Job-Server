package net.thumbtack.school.hiring.errors;

public class HiringException extends Exception{

    private HiringErrorCode hiringErrorCode;

    public HiringException(HiringErrorCode hiringErrorCode) {
        super(hiringErrorCode.getErrorString());
        this.hiringErrorCode = hiringErrorCode;
    }

    public HiringErrorCode getErrorCode() {
       return hiringErrorCode;
    }
}
