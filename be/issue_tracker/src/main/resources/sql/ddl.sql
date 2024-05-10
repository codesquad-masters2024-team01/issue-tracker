drop table if exists ISSUE_LABEL, ISSUE_MILESTONE, LABEL, MILESTONE, COMMENT, ISSUE, USERS;

create table USERS
(
    id          varchar(255) primary key,
    password    varchar(255),
    name        varchar(255)                       not null,
    created_at  timestamp                          not null default current_timestamp,
    profile_img varchar(255),
    type        enum ('NORMAL', 'GITHUB', 'APPLE') not null default 'NORMAL'
);

create table MILESTONE
(
    id          bigint auto_increment primary key,
    name        varchar(255) not null,
    description text         not null,
    created_at  timestamp    not null default current_timestamp,
    deadline    timestamp
);

create table ISSUE
(
    id               bigint auto_increment primary key,
    user_id          varchar(255)           not null,
    milestone_id     bigint,
    title            varchar(255)           not null,
    created_at       timestamp              not null default current_timestamp,
    last_modified_at timestamp              not null default current_timestamp,
    status           enum ('OPEN', 'CLOSE') not null default 'OPEN',
    foreign key (user_id) references USERS (id),
    foreign key (milestone_id) references MILESTONE (id)
);

create table LABEL
(
    id          bigint auto_increment primary key,
    name        varchar(255) not null,
    description text         not null,
    created_at  timestamp    not null default current_timestamp,
    color       varchar(7)   not null default '#ffffff'
);

create table COMMENT
(
    id               bigint auto_increment primary key,
    user_id          varchar(255) not null,
    issue_id         bigint       not null,
    created_at       timestamp    not null default current_timestamp,
    last_modified_at timestamp    not null default current_timestamp,
    foreign key (user_id) references USERS (id),
    foreign key (issue_id) references ISSUE (id)
);

create table USER_LIKES_COMMENT
(
    user_id    varchar(255) not null,
    comment_id bigint       not null,
    primary key (user_id, comment_id),
    foreign key (user_id) references USERS (id),
    foreign key (comment_id) references COMMENT (id)
);

create table ISSUE_LABEL
(
    id       bigint auto_increment primary key,
    issue_id bigint,
    label_id bigint,
    foreign key (issue_id) references ISSUE (id),
    foreign key (label_id) references LABEL (id),
    unique (issue_id, label_id)
);

create table ASSIGNEE
(
    id       bigint auto_increment primary key,
    issue_id bigint,
    user_id  varchar(255) not null,
    foreign key (issue_id) references ISSUE (id),
    foreign key (user_id) references LABEL (id),
    unique (issue_id, user_id)
);

create table COMMENT_FILE
(
    id         bigint auto_increment primary key,
    comment_id bigint,
    file_url   varchar(255) not null,
    foreign key (comment_id) references COMMENT (id)
);