/* USERS */
insert into USERS (id, password, name, created_at)
values ('test2', 'test123', '테스트유저2', '2024-05-08-00:00:00');


/* MILESTONE */
insert into MILESTONE (name, description, created_at, deadline)
VALUES ('feat_1', '피쳐원', '2024-05-08-00:00:00', '2024-05-18-00:00:00');


/* ISSUE */
insert into ISSUE (user_id, title, comment, milestone_id)
VALUES ('test2', '제목', '내용', 1);

insert into ISSUE (user_id, title, comment)
VALUES ('test2', '제목2', '내용2');

insert into ISSUE (user_id, title, comment, milestone_id)
VALUES ('test2', '제목3', '서울 3대 돈까스 맛집..명성에 맞게 늘 웨이팅이 있음 (테이블링 가능)! 육즙 가득이고 고기 부드럽고 튀김 고소하고 완전 맛있음
단점이라면 비싼 가격.. 안심돈까스 17,000원 등심돈까스 18,000원 ㅠ', 1);

insert into ISSUE (user_id, title, comment)
VALUES ('test2', '제목4', '서울 3대 돈까스 맛집..명성에 맞게 늘 웨이팅이 있음 (테이블링 가능)! 육즙 가득이고 고기 부드럽고 튀김 고소하고 완전 맛있음 단점이라면 비싼 가격.. 안심돈까스 17,000원 등심돈까스 18,000원 ㅠ');


/* LABEL*/
insert into LABEL (name, description, created_at, color)
VALUES ('todo', '오늘 할 일', '2024-05-08-00:00:00', '#C3FF93');

insert into LABEL (name, description, created_at, color)
VALUES ('todo2', '내일 할 일', '2024-05-08-00:00:00', '#FFDB5C');

insert into LABEL (name, description, created_at, color)
VALUES ('todo3', '내일 할 일', '2024-05-08-00:00:00', '#FFAF61');

insert into LABEL (name, description, created_at, color)
VALUES ('todo4', '내일 할 일', '2024-05-08-00:00:00', '#FF70AB');

insert into LABEL (name, description, created_at, color)
VALUES ('todo5', '내일 할 일', '2024-05-08-00:00:00', '#CAF4FF');


/* ISSUE_LABEL */
insert into ISSUE_LABEL (issue_id, label_id)
VALUES (1, 1),
       (1, 2);

insert into ISSUE_LABEL (issue_id, label_id)
VALUES (2, 1);

insert into ISSUE_LABEL (issue_id, label_id)
VALUES (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (4, 5);