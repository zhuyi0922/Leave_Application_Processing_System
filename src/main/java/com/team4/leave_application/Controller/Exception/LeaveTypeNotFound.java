package com.team4.leave_application.Controller.Exception;

public class LeaveTypeNotFound extends Exception{
	 private static final long serialVersionUID = 1L;
	  
	  public LeaveTypeNotFound() {}
	  
	  public LeaveTypeNotFound(String msg) {
	    super(msg);
	  }
}
