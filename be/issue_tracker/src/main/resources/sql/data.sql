/* USERS */
insert into USERS (id, password, name)
values ('test2', 'test123', '테스트유저2');

insert into ISSUE (user_id, title, comment, created_at)
VALUES ('test2', '제목', '내용', '2024-05-08-00:00:00');

/* MILESTONE */
insert into MILESTONE (name, description, deadline)
VALUES ('feat_1', '피쳐원', '2024-05-18 00:00:00');

/* ISSUE */
insert into ISSUE (user_id, title, milestone_id)
VALUES ('test2', '제목', 1),
       ('test2', '제목3', 1);

insert into ISSUE (user_id, title)
VALUES ('test2', '제목2'),
       ('test2', '제목 4'),
       ('test2', '제목 4'),
       ('test2', '제목 4'),
       ('test2', '제목 4'),
       ('test2', '제목 4'),
       ('test2', '제목 4'),
       ('test2', '제목 4');

/* COMMENT */
insert into COMMENT (user_id, issue_id, content)
VALUES ('test2', 1, '내용'),
       ('test2', 2, '서울 3대 돈까스 맛집..명성에 맞게 늘 웨이팅이 있음 (테이블링 가능)!육즙 가득이고 고기 부드럽고 튀김 고소하고 완전 맛있음..단점이라면 비싼 가격.. 안심돈까스 17,000원 등심돈까스 18,000원 ㅠ'),
       ('test2', 3, '내용33'),
       ('test2', 4, '내용44'),
       ('test2', 5, '내용44'),
       ('test2', 6, '내용44'),
       ('test2', 7, '내용44'),
       ('test2', 8, '내용87888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888'),
       ('test2', 9, '내용44'),
       ('test2', 10, '내용44');


/* LABEL*/
insert into LABEL (name, description, color)
VALUES ('todo', '오늘 할 일',  '#C3FF93'),
       ('일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일일', '내일 할 일', '#FFDB5C'),
       ('todo3', '내일 할 일', '#FFAF61'),
       ('todo4', '내일 할 일', '#FF70AB'),
       ('todo5', '내일 할 일', '#CAF4FF');


/* ISSUE_LABEL */
insert into ISSUE_LABEL (issue_id, label_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (4, 5),
       (10, 2);