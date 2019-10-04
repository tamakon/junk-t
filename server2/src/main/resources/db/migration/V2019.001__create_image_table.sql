create table images
(
    id         int          not null auto_increment comment 'システム上のID',
    name       varchar(200) not null comment '画像を識別するための名前',
    created_at timestamp    not null comment '作成日時',
    updated_at timestamp    not null comment '更新日時',
    primary key (id),
    unique (name)
)