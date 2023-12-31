package com.team4.leave_application.Service;

import java.util.Date;
import java.util.List;

import com.team4.leave_application.Model.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface HolidayService {
    public List<Date> findHolidayBetween(Date startDate, Date endDate);
    public int calLeaveDays(Date start_date, Date end_date);
    public Page<Holiday> findAllHoliday(PageRequest pageRequest);
    public List<Holiday> findHolidayByDate(Date date);
    public Holiday createHoliday(Holiday holiday);
    public Holiday editHoliday(Holiday holiday);
    public void deleteHoliday(Holiday holiday);
    public Holiday findHoliday(int holidayId);
}
