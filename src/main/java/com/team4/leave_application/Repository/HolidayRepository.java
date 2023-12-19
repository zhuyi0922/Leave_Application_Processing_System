package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    List<Date> findByHolidayDateBetween(Date startDate, Date endDate);
}
