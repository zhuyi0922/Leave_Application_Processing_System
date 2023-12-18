package com.team4.leave_application.Model;

import jakarta.persistence.*;
import java.time.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "event")
@NamedQuery(name = "Event.findEventByDate",
        query="select e from Event e where (e.startDate BETWEEN ?1 AND ?2) OR (e.endDate BETWEEN ?1 AND ?2)")
public class Event {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "event_msg")
    private String eventMsg;
}
