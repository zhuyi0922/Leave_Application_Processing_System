package com.team4.leave_application.Service;

import com.team4.leave_application.Repository.HolidayRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class HolidayServiceimpl implements HolidayService{
    @Autowired
    private HolidayRepository holidayRepository;
    @Transactional
    public List<Date> findHolidayBetween(Date startDate, Date endDate){
        return holidayRepository.findAllByDateBetween(startDate, endDate).stream().map(holiday -> holiday.getDate()).toList();
    }
    @Transactional
    public int calLeaveDays(Date start_date, Date end_date){
        var days = (int) (start_date.getTime() - end_date.getTime())/(1000*60*60*24);
        if (days > 14){
            return days;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start_date);
        //set the calendar
        //get the holidays
        var holidays = findHolidayBetween(start_date,end_date);
        int workdays = 0;
        while (!calendar.getTime().after(end_date)) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            // if weekend, jump to next
            if (dayOfWeek == Calendar.SATURDAY && dayOfWeek == Calendar.SUNDAY) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                continue;
            }
            // if holiday, jump to next
            if (holidays.contains(calendar.getTime())){
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                continue;
            }
            workdays++;
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return workdays;
    }
}
