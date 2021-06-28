--グループ開発DB
--axizuser
--axiz

--データベースの作成
CREATE DATABASE group_d;


--テーブル情報リセット
delete from calendar;
delete from category;
delete from food;
delete from good_table;
delete from process;
delete from recipe;
delete from recipe_and_category;
delete from user_info;
delete from post_recipe;
delete from made_recipe;

drop table calendar;
drop table category;
drop table food;
drop table good_table;
drop table process;
drop table recipe;
drop table recipe_and_category;
drop table user_info;
drop table post_recipe;
drop table made_recipe;


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


--仮のデータ代入(10名)
INSERT INTO user_info (login_name, user_name, password, role_id) 
VALUES 
('sato', '佐藤', 'axiz',1),
('suzuki', '鈴木', 'bxiz',1),
('takahashi', '高橋', 'cxiz',2),
('tanaka', '田中', 'dxiz', 2),
('nakama', '名嘉眞', 'nakama',2),
('nagamine', '長嶺', 'nagamine',2),
('nagayama', '永山', 'nagayama',2),
('maeshiro', '眞榮城', 'maeshiro', 2),
('shimozato', '下里', 'shimozato',2),
('maekawa', '前川', 'maekawa', 2);


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
(1, 'オーツミルクで全粒粉入りパンケーキ', 'pancake.png', 25, 'とても簡単に出来ます。', '2021-06-02 12:48:35'),
(2, 'ごぼうの代わりに！じゃがいもと人参のきんぴら', 'kinpira.png', 15, 'じゃがいもがおいしいです。', '2021-06-05 16:00:35'),
(3, '甘辛ガーリックで！じゃがいもと豚肉のガーリック炒め', 'garlic.png', 10, 'にんにくが効いてておいしいです。', '2021-06-08 12:48:35'),
(4, '夏バテに最適な冷やしミニトマト', 'tomato.png', 3, '', '2021-06-11 18:48:35'),
(5, 'するする食べれる油そば風そうめん', 'somen.png', 20, '夏バテにもいいです。', '2021-06-13 09:48:35'),
(6, 'カリとろ 豆腐と長芋のふんわり揚げ', 'karitoro.png', 25, '新触感です。', '2021-06-14 12:48:35'),
(7, '小松菜と桜えびの和え物', 'ebi.png', 5, '', '2021-06-15 12:30:35'),
(8, 'ミニトマトと卵の簡単スープ', 'tomatosupu.png', 10, 'すぐできます。', '2021-06-21 12:48:35'),
(9, '落とし卵のみそ汁', 'niratamago.png', 10, '疲労回復にいいです。','2021-06-23 16:48:35'),
(10, 'レンジで作るチーズココアボールクッキー', 'cocoa.png', 15, 'おいしいくて、簡単です(^^)', '2021-06-24 19:48:35'),
(10, 'ザーサイとにんじんの中華風炒り豆腐', 'zasai.png', 20, '食べたことないです', '2021-06-26 12:48:35'),
(10, 'すぐ作れるニラとツナの塩コショウ炒め', 'tunanira.png', 10, '良い感じです', '2021-06-26 17:48:35');


--投稿したレシピ
create table post_recipe(
user_id INT NOT NULL,
recipe_id INT NOT NULL,
dt timestamp NOT NULL,
PRIMARY KEY(user_id, recipe_id, dt)
);


--論理名を指定
COMMENT ON TABLE post_recipe IS '投稿レシピ';
COMMENT ON COLUMN post_recipe.user_id IS 'ユーザーID';
COMMENT ON COLUMN post_recipe.recipe_id IS 'レシピID';
COMMENT ON COLUMN post_recipe.dt IS '投稿した日付';

insert into post_recipe values
 (1, 1, '2021-06-02'), 
 (2, 2, '2021-06-05'),  
 (3, 3, '2021-06-08'),  
 (4, 4, '2021-06-11'),  
 (5, 5, '2021-06-13'),  
 (6, 6, '2021-06-14'),
 (7, 7, '2021-06-15'), 
 (8, 8, '2021-06-21'),  
 (1, 9, '2021-06-23'),  
 (2, 10, '2021-06-24'),  
 (3, 11, '2021-06-26'),  
 (4, 12, '2021-06-26')
;


--つくったレシピ
create table made_recipe(
user_id INT NOT NULL,
recipe_id INT NOT NULL,
dt timestamp NOT NULL,
PRIMARY KEY(user_id, recipe_id, dt)
);


--材料のテーブル作成
CREATE TABLE food(
food_id SERIAL,
recipe_id INT NOT NULL,
display_order_food INT NOT NULL,
food_name VARCHAR(50) NOT NULL,
amount VARCHAR(50) NOT NULL
);


insert into made_recipe values
 (1, 1, '2021-06-02'), 
 (1, 2, '2021-06-02'),  
 (1, 3, '2021-06-08'),  
 (5, 4, '2021-06-11'),  
 (5, 5, '2021-06-13'),  
 (5, 6, '2021-06-14'),
 (1, 7, '2021-06-15'), 
 (1, 8, '2021-06-18'),  
 (1, 9, '2021-06-20'),  
 (5, 10, '2021-06-24'),  
 (5, 11, '2021-06-25'),  
 (5, 12, '2021-06-26');

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
(1,1,'薄力粉','80g'),
(1,2,'全粒粉','80g'),
(1,3,'ベーキングパウダー','8g'),
(1,4,'砂糖','40g'),
(1,5,'塩','小さじ1/4'),
(1,6,'卵（Mサイズ）','1個'),
(1,7,'オーツミルク（無糖）','110ml'),
(1,8,'メープルシロップ','大さじ2'),
(1,9,'いちご','2個'),
(1,10,'オレンジ（スライス）','3枚'),

(2,1,'じゃがいも','1個'),
(2,2,'にんじん','1/2個'),
(2,3,'(A)酒','大さじ2'),
(2,4,'(A)みりん','大さじ1'),
(2,5,'(A)砂糖','大さじ1'),
(2,6,'(A)顆粒和風だし','小さじ1'),
(2,7,'(A)醤油','大さじ2'),
(2,8,'サラダ油','適量'),

(3,1,'じゃがいも','1個'),
(3,2,'豚バラ肉','50g'),
(3,3,'塩コショウ','少々'),
(3,4,'にんにく','1/2片'),
(3,5,'(A)しょうゆ','大さじ1/2'),
(3,6,'(A)みりん','大さじ1/2'),
(3,7,'(A)酒','大さじ1/2'),
(3,8,'(A)砂糖','大さじ1/4'),
(3,9,'(A)酢','大さじ1/4'),
(3,10,'パセリ','適量'),

(4,1,'ミニトマト','7個'),
(4,2,'青じそ','1枚'),
(4,3,'めんつゆ(ストレート)','1/8カップ'),


(5,1,'そうめん','50g'),
(5,2,'お湯(ゆで用)','500ml'),
(5,3,'牛豚合いびき肉','40g'),
(5,4,'ごま油','小さじ1/2'),
(5,5,'(A)めんつゆ(2倍濃縮)','大さじ1/2'),
(5,6,'(A)オイスターソース','大さじ1/2'),
(5,7,'(A)しょうゆ','大さじ1/2'),
(5,8,'(A)ごま油','大さじ1/2'),
(5,9,'(A)酢','大さじ1/4'),
(5,10,'半熟卵','1個'),
(5,11,'小ねぎ(小口切り)','適量'),
(5,12,'水菜','5g'),
(5,13,'ラー油','小さじ1'),


(6,1,'絹ごし豆腐','100g'),
(6,2,'長芋','75g'),
(6,3,'にんじん','25g'),
(6,4,'乾燥ひじき','1.5g'),
(6,5,'水(戻す用)','適量'),
(6,6,'溶き卵(Mサイズ)','1/4個分'),
(6,7,'(A)片栗粉','大さじ1/2'),
(6,8,'(A)顆粒和風だし','小さじ1/4'),
(6,9,'(A)塩コショウ','小さじ1/4'),
(6,10,'揚げ油','適量'),
(6,11,'すりおろし生姜','小さじ1'),
(6,12,'大葉','1枚'),


(7,1,'小松菜','1/4束'),
(7,2,'桜えび','100g'),
(7,3,'酒','大さじ1/4'),
(7,4,'しょうゆ','小さじ1'),


(8,1,'ミニトマト','3個'),
(8,2,'にら','5g'),
(8,3,'卵','1/2個'),
(8,4,'ごま油','適量'),
(8,5,'(A)丸鶏がらスープの素(顆粒)','小さじ1/2'),
(8,6,'(A)水','900ml'),
(8,7,'(A)酒','適量'),
(8,8,'(A)塩','適量'),
(8,9,'(A)こしょう','適量'),


(9,1,'卵','1個'),
(9,2,'にら','1/4束'),
(9,3,'だし汁','800ml'),
(9,4,'みそ','大さじ1/2'),


(10,1,'薄力粉','20g'),
(10,2,'ココア(無糖)','2.5g'),
(10,3,'砂糖','大さじ1'),
(10,4,'バター(無塩)','10g'),
(10,5,'粉チーズ','大さじ1'),


(11,1,'木綿豆腐','1/4丁'),
(11,2,'ザーサイ','適量'),
(11,3,'にんじん','1/4本'),
(11,4,'長ネギ','2.5cm'),
(11,5,'しょうが(みじん切り)','小さじ1'),
(11,6,'ごま油','小さじ1'),
(11,7,'酒','小さじ1'),
(11,8,'鶏がらスープの素(顆粒','小さじ1'),
(11,9,'塩','小さじ1'),
(11,10,'ごま(白)','適量'),
(11,11,'三つ葉','適量'),


(12,1,'ニラ','50g'),
(12,2,'ツナ油漬け(汁ごと)','45g'),
(12,3,'塩コショウ','適量'),
(12,4,'ごま油','大さじ1/2');


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
(1,1,'いちごはヘタを取り、薄切りにします。オレンジは皮付きのまま薄い輪切りにします。'),
(1,2,'ボウルに粉類、砂糖、塩を入れ泡立て器で混ぜ合わせます。'),
(1,3,'卵、オーツミルクを加え粉っぽさがなくなるまで混ぜ合わせます。'),
(1,4,'フライパンを弱火で加熱し、温まったら3の1/4量を流し入れ3分程焼きます。'),
(1,5,'プツプツと気泡が出てきたら裏返し3分程焼きます。'),
(1,6,'両面に焼き色が付き火が通ったら火から下ろします。同様に3枚焼きます。'),
(1,7,'皿に盛り付け、フルーツを飾り、メープルシロップをかけて完成です。'),

(2,1,'じゃがいもは細切りにします。'),
(2,2,'人参は皮をむき、細切りにします。'),
(2,3,'小鍋を中火で熱し、サラダ油を入れます。'),
(2,4,'人参を炒めます。'),
(2,5,'じゃがいもを炒めます。'),
(2,6,'じゃがいもに軽く火が通ったら、(A)の調味料を入れます。'),
(2,7,'汁気が無くなるまで炒めたら完成です。'),

(3,1,'じゃがいもは皮付きのまま8等分に切ります。'),
(3,2,'濡れたペーパーとラップにくるみ500Wレンジで3分加熱し、竹串がすっと入る柔らかさにします。'),
(3,3,'ニンニクは薄切りにします。'),
(3,4,'豚肉は塩こしょうを振り下味をつけます。'),
(3,5,'フライパンに豚肉とニンニクを入れ、弱火でじっくりと脂と香りを出していきます。'),
(3,6,'油に香りが移ったらじゃがいもを入れ、焼き色をつけるようにして焼いていきます。'),
(3,7,'(A)を加え、全体に絡ませるようにして炒め煮てパセリを振りかけたら完成です。'),


(4,1,'ミニトマトは、ヘタを取り、保存袋に入れて冷凍庫でひと晩凍らせます。'),
(4,2,'青じそは千切りにします。'),
(4,3,'ボウルに水をはり、ミニトマトを入れて皮をむき、水気を切ります。'),
(4,4,'器に盛り、めんつゆをかけ、青じそを添えます。'),
(4,5,'ミニトマトが完全に溶けないうちにいただきます。'),


(5,1,'(A)の材料を混ぜます。'),
(5,2,'フライパンにごま油を中火で熱し、牛豚合びき肉を炒めます。'),
(5,3,'火が通ったら1のうち大さじ1を加えて味が全体になじむまで炒め、火からおろします。'),
(5,4,'鍋にお湯を沸かし、そうめんをパッケージの表記通りにゆでます。'),
(5,5,'しっかりと流水で洗い、水を切ります。'),
(5,6,'そうめんと(A)の残りを和えます。'),
(5,7,'器に盛り、のこりの材料をのせて完成です。'),


(6,1,'長芋はすりおろします。'),
(6,2,'にんじんは細切りにします。'),
(6,3,'木綿豆腐はキッチンペーパーに包みます。'),
(6,4,'ボウルに入れ、その上にさらにボウルをのせて冷蔵庫に入れ、1時間程水気を切ります。'),
(6,5,'水を張った別のボウルに、乾燥ひじきを入れ戻し、流水で洗い水気を切ります。'),
(6,6,'別のボウルに木綿豆腐をちぎって入れ、長芋、人参、ひじき、溶き卵、(A)を入れ、混ぜ合わせます。'),
(6,7,'揚げ油をフライパンの底から3cm程の高さまで入れ、170℃に熱します。'),
(6,8,'混ぜ合わせた材料を形を整え、中に火が通るまで5分程揚げ、取り出して油を切ります。'),
(6,9,'器に揚げたものを盛り付け、しょうがと大葉を添えて完成です。'),


(7,1,'小松菜は熱湯でゆでて4cmの長さに切ります。'),
(7,2,'桜えびは水(大さじ1)につけて戻します。'),
(7,3,'戻し汁は取っておきます。'),
(7,4,'小松菜と桜えびの水気を切り、戻し汁、酒、しょうゆで和えます。'),


(8,1,'ミニトマトはヘタを取って半分に切り、にらは3～4cmの長さに切ります。'),
(8,2,'鍋にごま油を熱してミニトマトを弱火で炒め、(A)を加えて煮ます(約10分)。'),
(8,3,'酒、塩、こしょうで味を調え、切ったにらと溶き卵を加えて仕上げます。'),


(9,1,'にらは2cm幅に切ります。'),
(9,2,'鍋にだし汁を入れて中火にかけ、にらを加えて5分程煮ます。'),
(9,3,'みそを溶き入れ、卵を割り入れて白身がかたまり、黄身が半熟になるまで加熱し、器に盛ります。'),


(10,1,'ボウルにバターを入れ、なめらかになるように泡立て器で混ぜます。'),
(10,2,'砂糖を加え、さらに混ぜ、薄力粉、ココアをふるい入れます。'),
(10,3,'ゴムベラで粉っぽさがなくなるまで混ぜたら、粉チーズの半量を加えます。'),
(10,4,'ボール型に丸めます。これを9個作ります。'),
(10,5,'オーブンシートの上に並べ、電子レンジ(600W)で1分半加熱します。'),
(10,6,'冷めたら残りの粉チーズをまぶします。'),


(11,1,'豆腐は軽く水切りし、粗く潰しておきます。'),
(11,2,'ザーサイ、にんじんは千切り、長ねぎはみじん切りにします。'),
(11,3,'フライパンにごま油をひき、切った長ねぎ、しょうがを入れて香りを出します。'),
(11,4,'炒めたものに豆腐、ザーサイ、にんじん、酒、鶏がらスープの素、塩を加えてサッと炒めます。'),
(11,5,'仕上げにごまをふり、三つ葉を添えます。'),


(12,1,'ニラは5cm幅に切ります。'),
(12,2,'フライパンにごま油を入れ中火で加熱し、ニラ、ツナ油漬けを入れ2分程炒めます。'),
(12,3,'ニラがしんなりしてきたら中火のまま、塩こしょうを入れ炒め合わせます。'),
(12,4,'全体に味がなじんだら火から下ろし、器に盛り付けて完成です。');


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
(2,2),
(2,5),
(3,1),
(4,1),
(4,5),
(5,3),
(6,2),
(7,2),
(7,5),
(8,1),
(9,2),
(9,2),
(10,4),
(11,3),
(12,2);


--いいねテーブル作成
CREATE TABLE good_table(
good_id SERIAL,
recipe_id INT NOT NULL,
user_id INT NOT NULL,
dt timestamp NOT NULL
);

--論理名を指定
COMMENT ON TABLE good_table IS 'いいねテーブル';
COMMENT ON COLUMN good_table.good_id IS 'いいねID';
COMMENT ON COLUMN good_table.recipe_id IS 'レシピID';
COMMENT ON COLUMN good_table.user_id IS 'ユーザーID';
COMMENT ON COLUMN good_table.dt IS 'いいねを押した日付';

--いいねテーブル（good_id）1からの自動連番に設定
ALTER SEQUENCE good_table_good_id_seq RESTART 1;

INSERT INTO good_table (recipe_id, user_id, dt) 
VALUES
(	1	,	1	, now())	,
(	1	,	2	, now())	,
(	1	,	3	, now())	,
(	1	,	4	, now())	,
(	1	,	5	, now())	,
(	1	,	6	, now())	,
(	1	,	7	, now())	,
(	1	,	8	, now())	,
(	1	,	9	, now())	,
(	1	,	10	, now())	,
(	1	,	11	, now())	,
(	1	,	12	, now())	,
(	1	,	13	, now())	,
(	2	,	1	, now())	,
(	2	,	2	, now())	,
(	2	,	3	, now())	,
(	2	,	4	, now())	,
(	2	,	5	, now())	,
(	2	,	6	, now())	,
(	2	,	7	, now())	,
(	2	,	8	, now())	,
(	2	,	9	, now())	,
(	2	,	10	, now())	,
(	2	,	11	, now())	,
(	2	,	12	, now())	,
(	2	,	13	, now())	,
(	2	,	14	, now())	,
(	2	,	15	, now())	,
(	2	,	16	, now())	,
(	2	,	17	, now())	,
(	2	,	18	, now())	,
(	2	,	19	, now())	,
(	2	,	20	, now())	,
(	2	,	21	, now())	,
(	2	,	22	, now())	,
(	2	,	23	, now())	,
(	2	,	24	, now())	,
(	2	,	25	, now())	,
(	2	,	26	, now())	,
(	2	,	27	, now())	,
(	2	,	28	, now())	,
(	2	,	29	, now())	,
(	2	,	30	, now())	,
(	2	,	31	, now())	,
(	2	,	32	, now())	,
(	2	,	33	, now())	,
(	2	,	34	, now())	,
(	2	,	35	, now())	,
(	3	,	1	, now())	,
(	3	,	2	, now())	,
(	3	,	3	, now())	,
(	3	,	4	, now())	,
(	3	,	5	, now())	,
(	3	,	6	, now())	,
(	3	,	7	, now())	,
(	4	,	8	, now())	,
(	4	,	9	, now())	,
(	4	,	10	, now())	,
(	4	,	11	, now())	,
(	4	,	12	, now())	,
(	4	,	13	, now())	,
(	4	,	14	, now())	,
(	4	,	15	, now())	,
(	4	,	16	, now())	,
(	4	,	17	, now())	,
(	4	,	18	, now())	,
(	4	,	19	, now())	,
(	4	,	20	, now())	,
(	4	,	21	, now())	,
(	4	,	22	, now())	,
(	5	,	1	, now())	,
(	5	,	2	, now())	,
(	5	,	3	, now())	,
(	5	,	4	, now())	,
(	6	,	1	, now())	,
(	6	,	2	, now())	,
(	6	,	3	, now())	,
(	6	,	4	, now())	,
(	6	,	5	, now())	,
(	6	,	6	, now())	,
(	6	,	7	, now())	,
(	6	,	8	, now())	,
(	6	,	9	, now())	,
(	6	,	10	, now())	,
(	6	,	11	, now()	)	,
(	6	,	12	, now()	)	,
(	6	,	13	, now()	)	,
(	6	,	14	, now()	)	,
(	6	,	15	, now()	)	,
(	6	,	16	, now()	)	,
(	7	,	1	, now()	)	,
(	7	,	2	, now()	)	,
(	7	,	3	, now()	)	,
(	7	,	4	, now()	)	,
(	7	,	5	, now()	)	,
(	7	,	6	, now()	)	,
(	7	,	7	, now()	)	,
(	7	,	8	, now()	)	,
(	7	,	9	, now()	)	,
(	7	,	10	, now()	)	,
(	7	,	11	, now()	)	,
(	7	,	12	, now()	)	,
(	7	,	13	, now()	)	,
(	8	,	14	, now()	)	,
(	8	,	15	, now()	)	,
(	8	,	16	, now()	)	,
(	8	,	17	, now()	)	,
(	9	,	18	, now()	)	,
(	9	,	19	, now()	)	,
(	9	,	20	, now()	)	,
(	9	,	21	, now()	)	,
(	9	,	22	, now()	)	,
(	9	,	23	, now()	)	,
(	9	,	24	, now()	)	,
(	9	,	25	, now()	)	,
(	9	,	26	, now()	)	,
(	9	,	27	, now()	)	,
(	9	,	28	, now()	)	,
(	9	,	29	, now()	)	,
(	9	,	30	, now()	)	,
(	9	,	31	, now()	)	,
(	9	,	32	, now()	)	,
(	9	,	33	, now()	)	,
(	9	,	34	, now()	)	,
(	9	,	35	, now()	)	,
(	9	,	36	, now()	)	,
(	9	,	37	, now()	)	,
(	9	,	38	, now()	)	,
(	9	,	39	, now()	)	,
(	9	,	40	, now()	)	,
(	9	,	41	, now()	)	,
(	9	,	42	, now()	)	,
(	10	,	43	, now()	)	,
(	10	,	44	, now()	)	,
(	10	,	45	, now()	)	,
(	10	,	46	, now()	)	,
(	11	,	47	, now())	,
(	11	,	48	, now())	,
(	11	,	49	, now())	,
(	11	,	50	, now())	,
(	11	,	51	, now())	,
(	11	,	52	, now())	,
(	11	,	53	, now())	,
(	11	,	54	, now())	,
(	11	,	55	, now())	,
(	11	,	56	, now())	,
(	11	,	57	, now())	,
(	11	,	58	, now())	,
(	11	,	59	, now())	,
(	12	,	60	, now())	,
(	12	,	61	, now())	,
(	12	,	62	, now())	,
(	12	,	63	, now())	,
(	12	,	64	, now())	,
(	12	,	65	, now())	,
(	12	,	66	, now())	,
(	12	,	67	, now())	,
(	12	,	68	, now())	,
(	12	,	69	, now())	,
(	12	,	70	, now())	,
(	12	,	71	, now())	,
(	12	,	72	, now())	,
(	12	,	73	, now())	,
(	12	,	74	, now())	,
(	12	,	75	, now())	,
(	12	,	76	, now())	,
(	12	,	77	, now())	,
(	12	,	78	, now())	,
(	12	,	79	, now())	,
(	12	,	80	, now())	,
(	12	,	81	, now())	,
(	12	,	82	, now())	,
(	12	,	83	, now())	;


select * from user_info;
select * from recipe;
select * from post_recipe;
select * from made_recipe;
select * from food;
select * from process;
select * from recipe_and_category;
select * from category;
select * from recipe_and_category;
select * from good_table;
