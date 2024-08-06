-- product
create table if not exists products (

    id              bigint          not null auto_increment primary key     comment '상품 ID',
    brand_id        bigint          not null                                comment '브랜드 ID',
    category_id     bigint          not null                                comment '카테고리 ID',
    price           bigint          not null                                comment '가격',
    deleted         char(1)         not null default 'N'                    comment '삭제 여부',
    created_at      datetime(6)     not null default current_timestamp      comment '생성 일시',
    updated_at      datetime(6)         null default current_timestamp      comment '수정 일시'
);
create index idx_products_brand_id on products (brand_id);
create index idx_products_category_id on products (category_id);

-- brand
create table if not exists brands (

    id              bigint       not null auto_increment primary key    comment '브랜드 ID',
    name            varchar(255) not null                               comment '브랜드 이름',
    deleted         char(1)      not null default 'N'                   comment '삭제 여부',
    created_at      datetime(6)  not null default current_timestamp     comment '생성 일시',
    updated_at      datetime(6)      null default current_timestamp     comment '수정 일시'
);

-- category
create table if not exists categories (

    id              bigint       not null auto_increment primary key    comment '카테고리 ID',
    name            varchar(255) not null                               comment '카테고리 이름',
    deleted         char(1)      not null default 'N'                   comment '삭제 여부',
    created_at      datetime(6)  not null default current_timestamp     comment '생성 일시',
    updated_at      datetime(6)      null default current_timestamp     comment '수정 일시',

    unique (name)
);