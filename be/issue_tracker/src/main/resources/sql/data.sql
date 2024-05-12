insert into USERS (id, password, name, created_at)
values ('test2', 'test123', '테스트유저2', '2024-05-08-00:00:00');

insert into MILESTONE (name, description, created_at, deadline)
VALUES ('feat_1', '피쳐원', '2024-05-08-00:00:00', '2024-05-18-00:00:00');

insert into ISSUE (user_id, title, comment, created_at, milestone_id)
VALUES ('test2', '제목', '내용', '2024-05-08-00:00:00', 1);

insert into ISSUE (user_id, title, comment, created_at)
VALUES ('test2', '제목2', '내용2', '2024-05-08-00:00:00');

insert into LABEL (name, description, created_at, color)
VALUES ('todo', '오늘 할 일', '2024-05-08-00:00:00', '#3DDCFF');

insert into LABEL (name, description, created_at, color)
VALUES ('todo2', '내일 할 일', '2024-05-08-00:00:00', '#4DDCFF');

insert into ISSUE_LABEL (issue_id, label_id)
VALUES (1, 1);
insert into ISSUE_LABEL (issue_id, label_id)
VALUES (1, 2);

insert into ISSUE_LABEL (issue_id, label_id)
VALUES (2, 1);