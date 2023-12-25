package com.team4.leave_application.Repository;

import com.team4.leave_application.Model.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    List<Holiday> findAllByDateBetween(Date startDate, Date endDate);

	List<Holiday> findByDate(Date date);

    Page<Holiday> findAll(Pageable pageable);

}
