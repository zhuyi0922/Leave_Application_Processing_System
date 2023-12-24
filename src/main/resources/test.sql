insert into `leave_type` (`leave_type_id`, `leave_type_name`, `max_leave_day`, `staff_title`) values('1','annual leave','20','employee');
insert into `leave_type` (`leave_type_id`, `leave_type_name`, `max_leave_day`, `staff_title`) values('2','medical leave','10','employee');
insert into `remain_leave` (`remain_leave_id`, `remain_leave`, `leave_type_id`, `staff_id`) values('1','27','1','1');
insert into `remain_leave` (`remain_leave_id`, `remain_leave`, `leave_type_id`, `staff_id`) values('2','45','2','1');
insert into `role` (`role_id`, `description`, `name`) values('001','employee','employee');
insert into `role` (`role_id`, `description`, `name`) values('002','admin','admin');
insert into `role` (`role_id`, `description`, `name`) values('003','manager','manager');
insert into `staff` (`id`, `birthday`, `email`, `first_name`, `gender`, `last_name`, `manager_id`, `middle_name`, `title`) values('1','2009-06-20','1013626958@qq.com','zhu','male','yi','2',NULL,'employee');
insert into `staff` (`id`, `birthday`, `email`, `first_name`, `gender`, `last_name`, `manager_id`, `middle_name`, `title`) values('2','2012-06-21','zhuy71302@gmail.com','marty','male','zhu','2',NULL,'admin');
insert into `user` (`user_id`, `password`, `username`, `staff_id`) values('1','123','tin','1');
insert into `user` (`user_id`, `password`, `username`, `staff_id`) values('2','123','zhu','2');
insert into `user_role` (`user_id`, `role_id`) values('1','001');
insert into `user_role` (`user_id`, `role_id`) values('2','003');

insert into `user` (`user_id`, `password`, `username`, `staff_id`) values('3','123','esther','3');
insert into `staff` (`id`, `birthday`, `email`, `first_name`, `gender`, `last_name`, `manager_id`, `middle_name`, `title`) values('3','2003-04-13','esther123@gmail.com','esther','female','lam','3',NULL,'admin');