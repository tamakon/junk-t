create table images
(
    id         serial primary key comment 'システム上のID',
    tag        varchar(200) not null unique comment '画像を識別するための名前',
    created_at datetime comment '作成日時',
    updated_at datetime comment '更新日時'
)