package com.team4.leave_application.Service;

import java.util.Date;
import java.util.List;

public interface HolidayService {
    public List<Date> findHolidayBetween(Date startDate, Date endDate);
    public int calLeaveDays(Date start_date, Date end_date);
}
