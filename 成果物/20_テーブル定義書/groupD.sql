--�O���[�v�J��DB
--axizuser
--axiz

--�f�[�^�x�[�X�̍쐬
CREATE DATABASE group_d;

--���[�U�[���̃e�[�u���쐬
CREATE TABLE user_info(
user_id SERIAL PRIMARY KEY,
login_name VARCHAR(20),
user_name VARCHAR(20),
password VARCHAR(10),
role_id INT
);

--�_�������w��
COMMENT ON TABLE  user_info IS '���[�U�[���';
COMMENT ON COLUMN user_info.user_id IS '���[�U�[ID';
COMMENT ON COLUMN user_info.login_name IS '���O�C����';
COMMENT ON COLUMN user_info.user_name IS '���O';
COMMENT ON COLUMN user_info.password IS 'PASS';
COMMENT ON COLUMN user_info.role_id IS '����ID';

select * from user_info;

--���V�s���̃e�[�u���쐬
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

--�_�������w��
COMMENT ON TABLE recipe IS '���V�s���';
COMMENT ON COLUMN recipe.recipe_id IS '���V�sID';
COMMENT ON COLUMN recipe.user_id IS '���[�U�[ID';
COMMENT ON COLUMN recipe.recipe_title IS '���V�s�^�C�g��';
COMMENT ON COLUMN recipe.complete_image IS '�����摜';
COMMENT ON COLUMN recipe.cooking_time IS '��������';
COMMENT ON COLUMN recipe.overview IS '�R�����g';
COMMENT ON COLUMN recipe.good_total IS '�����ː�';
COMMENT ON COLUMN recipe.create_datetime IS '���e��';
COMMENT ON COLUMN recipe.update_datetime IS '�X�V��';

select * from recipe;

--�J�����_�[�̃e�[�u���쐬
CREATE TABLE calendar(
calendar_id SERIAL,
user_id INT,
recipe_id INT,
made_datetime TIMESTAMP
);

--�_�������w��
COMMENT ON TABLE calendar IS '�J�����_�[';
COMMENT ON COLUMN calendar.calendar_id IS '�J�����_�[ID';
COMMENT ON COLUMN calendar.user_id IS '���[�U�[ID';
COMMENT ON COLUMN calendar.recipe_id IS '���V�sID';
COMMENT ON COLUMN calendar.made_datetime IS '��������t';

select * from calendar;

--�J�e�S���̃e�[�u���쐬
CREATE TABLE category(
category_id INT PRIMARY KEY,
category_name VARCHAR(50)
);

--�_�������w��
COMMENT ON TABLE category IS '�J�e�S��';
COMMENT ON COLUMN category.category_id IS '�J�e�S��ID';
COMMENT ON COLUMN category.category_name IS '�J�e�S����';

select * from category;

--�ޗ��̃e�[�u���쐬
CREATE TABLE food(
food_id SERIAL,
recipe_id INT,
display_order_food INT,
food_name VARCHAR(50),
amount VARCHAR(50)
);

--�_�������w��
COMMENT ON TABLE food IS '�ޗ�';
COMMENT ON COLUMN food.recipe_id IS '���V�sID';
COMMENT ON COLUMN food.food_id IS '�ޗ�ID';
COMMENT ON COLUMN food.display_order_food IS '�\����_�ޗ�';
COMMENT ON COLUMN food.food_name IS '�ޗ���';
COMMENT ON COLUMN food.amount IS '����';

--�菇�̃e�[�u���쐬
CREATE TABLE process(
process_id SERIAL,
recipe_id INT,
display_order_process INT,
process_description VARCHAR(50)
);

--�_�������w��
COMMENT ON TABLE process IS '�菇';
COMMENT ON COLUMN process.process_id IS '�菇ID';
COMMENT ON COLUMN process.recipe_id IS '���V�sID';
COMMENT ON COLUMN process.display_order_process IS '�\����_�菇';
COMMENT ON COLUMN process.process_description IS '�H������';

select * from process;

--���V�s�ƃJ�e�S���̃e�[�u���쐬
CREATE TABLE recipe_and_category(
recipe_category_id SERIAL,
recipe_id INT,
category_id INT
);

--�_�������w��
COMMENT ON TABLE recipe_and_category IS '���V�s�ƃJ�e�S��';
COMMENT ON COLUMN recipe_and_category.recipe_category_id IS '���V�s�ƃJ�e�S��ID';
COMMENT ON COLUMN recipe_and_category.recipe_id IS '���V�sID';
COMMENT ON COLUMN recipe_and_category.category_id IS '�J�e�S��ID';


/* �i�R�̎��s����
select date_part('year', now()) as �N,
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

--user_info �� PK�ǉ�
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
