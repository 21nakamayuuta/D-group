--グループ開発DB
--axizuser
--axiz

--データベースの作成
CREATE DATABASE group_d;

--ユーザー情報のテーブル作成
CREATE TABLE user_info(
user_id SERIAL PRIMARY KEY,
login_name VARCHAR(20) NOT NULL,
user_name VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
role_id INT NOT NULL
);

--論理名を指定
COMMENT ON TABLE  user_info IS 'ユーザー情報';
COMMENT ON COLUMN user_info.user_id IS 'ユーザーID';
COMMENT ON COLUMN user_info.login_name IS 'ログイン名';
COMMENT ON COLUMN user_info.user_name IS '名前';
COMMENT ON COLUMN user_info.password IS 'PASS';
COMMENT ON COLUMN user_info.role_id IS '権限ID';


--ユーザーID（user_id）1からの自動連番に設定
ALTER SEQUENCE user_info_user_id_seq RESTART 1;

--仮のデータ代入
INSERT INTO user_info (login_name, user_name, password, role_id) 
VALUES 
('sato', '佐藤', 'axiz',1),
('suzuki', '鈴木', 'bxiz',1),
('takahashi', '高橋', 'cxiz',2),
('tanaka', '田中', 'dxiz', 2);

select * from user_info;

--レシピ情報のテーブル作成
CREATE TABLE recipe(
recipe_id SERIAL PRIMARY KEY,
user_id INT NOT NULL,
recipe_title VARCHAR(50) NOT NULL,
complete_image VARCHAR(200) NOT NULL,
cooking_time INT NOT NULL,
overview VARCHAR(200),
--good_total INT NOT NULL, --good_tableを作成したため、このカラムは必要ないと判断した
create_datetime TIMESTAMP NOT NULL,
update_datetime TIMESTAMP 
);
-- recipe テーブルの update_datetimeカラム NOT NULL制約を取り消し
ALTER TABLE recipe ALTER COLUMN update_datetime DROP NOT NULL;

--論理名を指定
COMMENT ON TABLE recipe IS 'レシピ情報';
COMMENT ON COLUMN recipe.recipe_id IS 'レシピID';
COMMENT ON COLUMN recipe.user_id IS 'ユーザーID';
COMMENT ON COLUMN recipe.recipe_title IS 'レシピタイトル';
COMMENT ON COLUMN recipe.complete_image IS '完成画像';
COMMENT ON COLUMN recipe.cooking_time IS '調理時間';
COMMENT ON COLUMN recipe.overview IS 'コメント';
--COMMENT ON COLUMN recipe.good_total IS 'いいね数';
COMMENT ON COLUMN recipe.create_datetime IS '投稿日';
COMMENT ON COLUMN recipe.update_datetime IS '更新日';

--レシピ（recipe_id）1からの自動連番に設定
ALTER SEQUENCE recipe_recipe_id_seq RESTART 1;

--仮のデータ代入
INSERT INTO recipe (user_id, recipe_title, complete_image, cooking_time, overview, create_datetime) 
VALUES 
(1, 'オーツミルクで全粒粉入りパンケーキ', 'webapp/image', 10, 'とても簡単に出来ます。', now()),
(2, 'ごぼうの代わりに！じゃがいもと人参のきんぴら', 'webapp/image/aaa', 15, 'じゃがいもがおいしいです。', now())
(3, 'ごぼうの代わりに！じゃがいもと人参のきんぴら', 'webapp/image/aaa', 15, 'じゃがいもがおいしいです。', now())
(1, 'すぐできる☆サバ缶カレー', 'すぐできる☆サバ缶カレー.jpq', 15, '煮込み時間少ない時短サバ缶カレー☆15分スピード料理', now())
(2, '簡単妊活ご飯☆鯖缶トマトカレー', '簡単妊活ご飯☆鯖缶トマトカレー.jpg', 15, '妊活にいいと言われる鯖缶、大豆、トマトの栄養をたっぷり採れます！美味しく食べて元気になりましょう☆', now())
(3, 'おうち飲茶✣豚バラ肉の豆鼓蒸し', 'おうち飲茶✣豚バラ肉の豆鼓蒸し.jpg', 15, '飲茶の定番「豚スペアリブの豆鼓蒸し」をバラ肉で。手が込んでるようで実は簡単です。', now());

select * from recipe;

--カレンダーのテーブル作成
CREATE TABLE calendar(
calendar_id SERIAL,
user_id INT NOT NULL,
recipe_id INT NOT NULL,
made_datetime TIMESTAMP NOT NULL
);

--論理名を指定
COMMENT ON TABLE calendar IS 'カレンダー';
COMMENT ON COLUMN calendar.calendar_id IS 'カレンダーID';
COMMENT ON COLUMN calendar.user_id IS 'ユーザーID';
COMMENT ON COLUMN calendar.recipe_id IS 'レシピID';
COMMENT ON COLUMN calendar.made_datetime IS '作った日付';

--カレンダー（calendar_id）1からの自動連番に設定
ALTER SEQUENCE calendar_calendar_id_seq RESTART 1;

--仮のデータ代入
INSERT INTO calendar (user_id, recipe_id, made_datetime) 
VALUES 
(1, 1, now()),
(1, 2, now());

select * from calendar;


--カテゴリのテーブル作成
CREATE TABLE category(
category_id INT PRIMARY KEY,
category_name VARCHAR(50) NOT NULL
);

--論理名を指定
COMMENT ON TABLE category IS 'カテゴリ';
COMMENT ON COLUMN category.category_id IS 'カテゴリID';
COMMENT ON COLUMN category.category_name IS 'カテゴリ名';

INSERT INTO category (category_id, category_name) 
VALUES 
(1, '洋食'),
(2, '和食'),
(3, '中華'),
(4, 'デザート'),
(5, '付け合わせ');

select * from category;

--材料のテーブル作成
CREATE TABLE food(
food_id SERIAL,
recipe_id INT NOT NULL,
display_order_food INT NOT NULL,
food_name VARCHAR(50) NOT NULL,
amount VARCHAR(50) NOT NULL
);


--論理名を指定
COMMENT ON TABLE food IS '材料';
COMMENT ON COLUMN food.food_id IS '材料ID';
COMMENT ON COLUMN food.recipe_id IS 'レシピID';
COMMENT ON COLUMN food.display_order_food IS '表示順_材料';
COMMENT ON COLUMN food.food_name IS '材料名';
COMMENT ON COLUMN food.amount IS '分量';

--材料（food_id）1からの自動連番に設定
ALTER SEQUENCE food_food_id_seq RESTART 1;

INSERT INTO food (recipe_id, display_order_food, food_name, amount) 
VALUES
(1, 1, '薄力粉', '80g'),
(1, 2, '全粒粉', '80g'),
(1, 3, 'ベーキングパウダー', '8g'),
(1, 4, '砂糖', '40g'),
(1, 5, '塩', '小さじ1/4'),
(1, 6, '卵（Mサイズ）', '1個'),
(1, 7, 'オーツミルク（無糖）', '110ml'),
(1, 8, 'メープルシロップ', '大さじ2'),
(1, 9, 'いちご', '2個'),
(1, 10, 'オレンジ（スライス）', '3枚'),
(2, 1, 'じゃがいも', '1個'),
(2, 2, 'にんじん', '1/2個'),
(2, 3, '（A）酒', '大さじ2'),
(2, 4, '（A）みりん', '大さじ1'),
(2, 5, '（A）砂糖', '大さじ1'),
(2, 6, '（A）顆粒和風だし', '小さじ1'),
(2, 7, '（A）醬油', '大さじ2'),
(2, 8, 'サラダ油', '適量');

select * from food;

--手順のテーブル作成
CREATE TABLE process(
process_id SERIAL,
recipe_id INT NOT NULL,
display_order_process INT NOT NULL,
process_description VARCHAR(50) NOT NULL
);

--processテーブルのprocess_descriptionの文字数を変更（増やした）
ALTER TABLE process
ALTER COLUMN process_description TYPE VARCHAR(200);

--論理名を指定
COMMENT ON TABLE process IS '手順';
COMMENT ON COLUMN process.process_id IS '手順ID';
COMMENT ON COLUMN process.recipe_id IS 'レシピID';
COMMENT ON COLUMN process.display_order_process IS '表示順_手順';
COMMENT ON COLUMN process.process_description IS '工程説明';

--手順（process_id）1からの自動連番に設定
ALTER SEQUENCE process_process_id_seq RESTART 1;

INSERT INTO process (recipe_id, display_order_process, process_description) 
VALUES
(1, 1, 'いちごはヘタを取り、薄切りにします。オレンジは皮付きのまま薄い輪切りにします。'),
(1, 2, 'ボウルに粉類、砂糖、塩を入れ泡立て器で混ぜ合わせます。'),
(1, 3, '卵、オーツミルクを加え粉っぽさがなくなるまで混ぜ合わせます。'),
(1, 4, 'フライパンを弱火で加熱し、温まったら3の1/4量を流し入れ3分程焼きます。プツプツと気泡が出てきたら裏返し3分程焼き、両面に焼き色が付き火が通ったら火から下ろします。同様に3枚焼きます。'),
(1, 5, '皿に盛り付け、1を飾り、メープルシロップをかけて完成です。'),
(2, 1, 'じゃがいもは細切りにします。'),
(2, 2, '人参は皮をむき、細切りにします。'),
(2, 3, '小鍋を中火で熱し、サラダ油を入れます。'),
(2, 4, '2を炒めます。'),
(2, 5, '1を炒めます。'),
(2, 6, 'じゃがいもに軽く火が通ったら、(A)の調味料を入れます。'),
(2, 7, '汁気が無くなるまで炒めたら完成です。');

select * from process;

--レシピとカテゴリのテーブル作成
CREATE TABLE recipe_and_category(
recipe_category_id SERIAL,
recipe_id INT NOT NULL,
category_id INT NOT NULL
);

--論理名を指定
COMMENT ON TABLE recipe_and_category IS 'レシピとカテゴリ';
COMMENT ON COLUMN recipe_and_category.recipe_category_id IS 'レシピとカテゴリID';
COMMENT ON COLUMN recipe_and_category.recipe_id IS 'レシピID';
COMMENT ON COLUMN recipe_and_category.category_id IS 'カテゴリID';

--レシピとカテゴリ（recipe_and_category_id）1からの自動連番に設定
ALTER SEQUENCE recipe_and_category_recipe_category_id_seq RESTART 1;

INSERT INTO recipe_and_category (recipe_id, category_id) 
VALUES
(1,1),
(1,4),
(2,2);

select * from recipe_and_category;

--いいねテーブル作成
CREATE TABLE good_table(
good_id SERIAL,
recipe_id INT NOT NULL,
user_id INT NOT NULL
);

--論理名を指定
COMMENT ON TABLE good_table IS 'いいねテーブル';
COMMENT ON COLUMN good_table.good_id IS 'いいねID';
COMMENT ON COLUMN good_table.recipe_id IS 'レシピID';
COMMENT ON COLUMN good_table.user_id IS 'ユーザーID';

--いいねテーブル（good_id）1からの自動連番に設定
ALTER SEQUENCE good_table_good_id_seq RESTART 1;

INSERT INTO good_table (recipe_id, user_id) 
VALUES
(1,1),
(1,2),
(2,1),
(2,3),
(2,4);

select * from good_table;

select r.user_id, recipe_title, count(g.recipe_id) いいね
from recipe r 
inner join good_table g
on r.recipe_id = g.recipe_id
where r.user_id = 2
group by recipe_title, r.user_id;

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
drop table process;
drop table recipe_and_category;
select * from user_info;

*/
