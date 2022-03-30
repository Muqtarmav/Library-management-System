set foreign_key_checks = 0;

truncate table user;
truncate table book;
truncate table role;
truncate table user_role;

insert into user(id, first_name, last_name, email, mobile, age, user_name, passWord)
values(12, 'muqt', 'tunji', 'muqt@gmail.com', '08342778984', '17', 'muqtyjay', 'muqtar4'),
(2, 'samieg', 'gold', 'gold@gmail.com', '07028294393', '15', 'sarnkidowski', 'gold1234'),
(3, 'yungcash', 'blessing', '@yungcash@gmail.com', '090563883993', '16', 'cashi', 'cash123');


insert into role(role_id, name, description)
values(2, 'user_role', 'this user has access to the site'),
(5, 'admin_role',  'this user has ultimate access to this site');



insert into book(id, title, author, description, page_count, category)
values(10, 'java', 'daitel', 'programming language', 700, 'software engineering'),
(11, 'biology', 'mason jobs', 'human anatomy', 890, 'medicine');


insert into user_role(user_id, role_id)
values(12, 2);

set foreign_key_checks = 1;