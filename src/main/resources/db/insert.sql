truncate table user;
truncate table book;

insert into user(id, first_name, last_name, email, mobile, age)
values(12, 'muqt', 'tunji', 'muqt@gmail.com', '08342778984', '17'),
(2, 'samieg', 'gold', 'gold@gmail.com', '07028294393', '15'),
(3, 'yungcash', 'blessing', '@yungcash@gmail.com', '090563883993', '16');


insert into book(id, title, author, description, page_count, category)
values(10, 'java', 'daitel', 'programming language', 700, 'software engineering'),
(11, 'biology', 'mason jobs', 'human anatomy', 890, 'medicine');



