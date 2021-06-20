--グループ開発DB
--axizuser
--axiz

--データベースの作成
CREATE DATABASE group_d;

--ユーザー情報のテーブル作成
CREATE TABLE user_info(
user_id SERIAL PRIMARY KEY,
login_name VARCHAR(20),
user_name VARCHAR(20),
password VARCHAR(10),
role_id INT
);

--論理名を指定
COMMENT ON TABLE  user_info IS 'ユーザー情報';
COMMENT ON COLUMN user_info.user_id IS 'ユーザーID';
COMMENT ON COLUMN user_info.login_name IS 'ログイン名';
COMMENT ON COLUMN user_info.user_name IS '名前';
COMMENT ON COLUMN user_info.password IS 'PASS';
COMMENT ON COLUMN user_info.role_id IS '権限ID';

select * from user_info;

--レシピ情報のテーブル作成
CREATE TABLE recipe(
recipe_id SERIAL PRIMARY KEY,
user_id INT,
recipe_title VARCHAR(50),
complete_image VARCHAR(200),
cooking_time INT,
overview VARCHAR(200),
good_total INT,
create_datetime TIMESTAMP,
update_datetime TIMESTAMP
);

--論理名を指定
COMMENT ON TABLE recipe IS 'レシピ情報';
COMMENT ON COLUMN recipe.recipe_id IS 'レシピID';
COMMENT ON COLUMN recipe.user_id IS 'ユーザーID';
COMMENT ON COLUMN recipe.recipe_title IS 'レシピタイトル';
COMMENT ON COLUMN recipe.complete_image IS '完成画像';
COMMENT ON COLUMN recipe.cooking_time IS '調理時間';
COMMENT ON COLUMN recipe.overview IS 'コメント';
COMMENT ON COLUMN recipe.good_total IS 'いいね数';
COMMENT ON COLUMN recipe.create_datetime IS '投稿日';
COMMENT ON COLUMN recipe.update_datetime IS '更新日';

select * from recipe;

--カレンダーのテーブル作成
CREATE TABLE calendar(
calendar_id SERIAL,
user_id INT,
recipe_id INT,
made_datetime TIMESTAMP
);

--論理名を指定
COMMENT ON TABLE calendar IS 'カレンダー';
COMMENT ON COLUMN calendar.calendar_id IS 'カレンダーID';
COMMENT ON COLUMN calendar.user_id IS 'ユーザーID';
COMMENT ON COLUMN calendar.recipe_id IS 'レシピID';
COMMENT ON COLUMN calendar.made_datetime IS '作った日付';

select * from calendar;

--カテゴリのテーブル作成
CREATE TABLE category(
category_id INT PRIMARY KEY,
category_name VARCHAR(50)
);

--論理名を指定
COMMENT ON TABLE category IS 'カテゴリ';
COMMENT ON COLUMN category.category_id IS 'カテゴリID';
COMMENT ON COLUMN category.category_name IS 'カテゴリ名';

select * from category;

--材料のテーブル作成
CREATE TABLE food(
food_id SERIAL,
recipe_id INT,
display_order_food INT,
food_name VARCHAR(50),
amount VARCHAR(50)
);

--論理名を指定
COMMENT ON TABLE food IS '材料';
COMMENT ON COLUMN food.recipe_id IS 'レシピID';
COMMENT ON COLUMN food.food_id IS '材料ID';
COMMENT ON COLUMN food.display_order_food IS '表示順_材料';
COMMENT ON COLUMN food.food_name IS '材料名';
COMMENT ON COLUMN food.amount IS '分量';

--手順のテーブル作成
CREATE TABLE process(
process_id SERIAL,
recipe_id INT,
display_order_process INT,
process_description VARCHAR(50)
);

--論理名を指定
COMMENT ON TABLE process IS '手順';
COMMENT ON COLUMN process.process_id IS '手順ID';
COMMENT ON COLUMN process.recipe_id IS 'レシピID';
COMMENT ON COLUMN process.display_order_process IS '表示順_手順';
COMMENT ON COLUMN process.process_description IS '工程説明';

select * from process;

--レシピとカテゴリのテーブル作成
CREATE TABLE recipe_and_category(
recipe_category_id SERIAL,
recipe_id INT,
category_id INT
);

--論理名を指定
COMMENT ON TABLE recipe_and_category IS 'レシピとカテゴリ';
COMMENT ON COLUMN recipe_and_category.recipe_category_id IS 'レシピとカテゴリID';
COMMENT ON COLUMN recipe_and_category.recipe_id IS 'レシピID';
COMMENT ON COLUMN recipe_and_category.category_id IS 'カテゴリID';


/* 永山の試行錯誤
select date_part('year', now()) as 年,
select to_char(now(),'YYYY/MM/DD');

CREATE TABLE test(
time_stamp TIMESTAMP,
time_date date
);

select * from test;


INSERT INTO test VALUES
(now(), date_part('year',now()));

SELECT
    DATE_PART('YEAR', time_stamp) AS year,
    DATE_PART('MONTH', time_stamp) AS month,
    DATE_PART('DAY', time_stamp) AS day
FROM test;

--user_info に PK追加
ALTER TABLE user_info ADD CONSTRAINT user_id PRIMARY KEY(user_id);
ALTER TABLE recipe ADD CONSTRAINT recipe_id PRIMARY KEY(recipe_id);
ALTER TABLE category ADD CONSTRAINT category_id PRIMARY KEY(category_id);

create view test_view as
select  
from test


--role
drop table role;
drop table user_info;
drop table recipe
drop table calendar;
drop table category;
drop table food;
drop table process
select * from user_info;

*/
