package com.team4.leave_application.Controller.Exception;

public class HolidayNotFound extends Exception {
    private static final long serialVersionUID = 1L;

    public HolidayNotFound() {}

    public HolidayNotFound(String msg) {
        super(msg);
    }
}
