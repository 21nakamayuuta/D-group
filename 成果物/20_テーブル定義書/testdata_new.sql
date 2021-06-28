--�O���[�v�J��DB
--axizuser
--axiz

--�f�[�^�x�[�X�̍쐬
CREATE DATABASE group_d;


--�e�[�u����񃊃Z�b�g
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


--���[�U�[���̃e�[�u���쐬
CREATE TABLE user_info(
user_id SERIAL PRIMARY KEY,
login_name VARCHAR(20) NOT NULL,
user_name VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL,
role_id INT NOT NULL
);

--�_�������w��
COMMENT ON TABLE  user_info IS '���[�U�[���';
COMMENT ON COLUMN user_info.user_id IS '���[�U�[ID';
COMMENT ON COLUMN user_info.login_name IS '���O�C����';
COMMENT ON COLUMN user_info.user_name IS '���O';
COMMENT ON COLUMN user_info.password IS 'PASS';
COMMENT ON COLUMN user_info.role_id IS '����ID';


--���[�U�[ID�iuser_id�j1����̎����A�Ԃɐݒ�
ALTER SEQUENCE user_info_user_id_seq RESTART 1;


--���̃f�[�^���(10��)
INSERT INTO user_info (login_name, user_name, password, role_id) 
VALUES 
('sato', '����', 'axiz',1),
('suzuki', '���', 'bxiz',1),
('takahashi', '����', 'cxiz',2),
('tanaka', '�c��', 'dxiz', 2),
('nakama', '������', 'nakama',2),
('nagamine', '����', 'nagamine',2),
('nagayama', '�i�R', 'nagayama',2),
('maeshiro', '���ď�', 'maeshiro', 2),
('shimozato', '����', 'shimozato',2),
('maekawa', '�O��', 'maekawa', 2);


--���V�s���̃e�[�u���쐬
CREATE TABLE recipe(
recipe_id SERIAL PRIMARY KEY,
user_id INT NOT NULL,
recipe_title VARCHAR(50) NOT NULL,
complete_image VARCHAR(200) NOT NULL,
cooking_time INT NOT NULL,
overview VARCHAR(200),
--good_total INT NOT NULL, --good_table���쐬�������߁A���̃J�����͕K�v�Ȃ��Ɣ��f����
create_datetime TIMESTAMP NOT NULL,
update_datetime TIMESTAMP 
);

-- recipe �e�[�u���� update_datetime�J���� NOT NULL�����������
ALTER TABLE recipe ALTER COLUMN update_datetime DROP NOT NULL;


--�_�������w��
COMMENT ON TABLE recipe IS '���V�s���';
COMMENT ON COLUMN recipe.recipe_id IS '���V�sID';
COMMENT ON COLUMN recipe.user_id IS '���[�U�[ID';
COMMENT ON COLUMN recipe.recipe_title IS '���V�s�^�C�g��';
COMMENT ON COLUMN recipe.complete_image IS '�����摜';
COMMENT ON COLUMN recipe.cooking_time IS '��������';
COMMENT ON COLUMN recipe.overview IS '�R�����g';
--COMMENT ON COLUMN recipe.good_total IS '�����ː�';
COMMENT ON COLUMN recipe.create_datetime IS '���e��';
COMMENT ON COLUMN recipe.update_datetime IS '�X�V��';


--���V�s�irecipe_id�j1����̎����A�Ԃɐݒ�
ALTER SEQUENCE recipe_recipe_id_seq RESTART 1;


--���̃f�[�^���
INSERT INTO recipe (user_id, recipe_title, complete_image, cooking_time, overview, create_datetime) 
VALUES 
(1, '�I�[�c�~���N�őS��������p���P�[�L', 'pancake.png', 25, '�ƂĂ��ȒP�ɏo���܂��B', '2021-06-02 12:48:35'),
(2, '���ڂ��̑���ɁI���Ⴊ�����Ɛl�Q�̂���҂�', 'kinpira.png', 15, '���Ⴊ���������������ł��B', '2021-06-05 16:00:35'),
(3, '�Ðh�K�[���b�N�ŁI���Ⴊ�����Ɠؓ��̃K�[���b�N�u��', 'garlic.png', 10, '�ɂ�ɂ��������ĂĂ��������ł��B', '2021-06-08 12:48:35'),
(4, '�ăo�e�ɍœK�ȗ�₵�~�j�g�}�g', 'tomato.png', 3, '', '2021-06-11 18:48:35'),
(5, '���邷��H�ׂ������Ε������߂�', 'somen.png', 20, '�ăo�e�ɂ������ł��B', '2021-06-13 09:48:35'),
(6, '�J���Ƃ� �����ƒ����̂ӂ���g��', 'karitoro.png', 25, '�V�G���ł��B', '2021-06-14 12:48:35'),
(7, '�����؂ƍ����т̘a����', 'ebi.png', 5, '', '2021-06-15 12:30:35'),
(8, '�~�j�g�}�g�Ɨ��̊ȒP�X�[�v', 'tomatosupu.png', 10, '�����ł��܂��B', '2021-06-21 12:48:35'),
(9, '���Ƃ����݂̂��`', 'niratamago.png', 10, '��J�񕜂ɂ����ł��B','2021-06-23 16:48:35'),
(10, '�����W�ō��`�[�Y�R�R�A�{�[���N�b�L�[', 'cocoa.png', 15, '�����������āA�ȒP�ł�(^^)', '2021-06-24 19:48:35'),
(10, '�U�[�T�C�Ƃɂ񂶂�̒��ؕ��u�蓤��', 'zasai.png', 20, '�H�ׂ����ƂȂ��ł�', '2021-06-26 12:48:35'),
(10, '��������j���ƃc�i�̉��R�V���E�u��', 'tunanira.png', 10, '�ǂ������ł�', '2021-06-26 17:48:35');


--���e�������V�s
create table post_recipe(
user_id INT NOT NULL,
recipe_id INT NOT NULL,
dt timestamp NOT NULL,
PRIMARY KEY(user_id, recipe_id, dt)
);


--�_�������w��
COMMENT ON TABLE post_recipe IS '���e���V�s';
COMMENT ON COLUMN post_recipe.user_id IS '���[�U�[ID';
COMMENT ON COLUMN post_recipe.recipe_id IS '���V�sID';
COMMENT ON COLUMN post_recipe.dt IS '���e�������t';

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


--���������V�s
create table made_recipe(
user_id INT NOT NULL,
recipe_id INT NOT NULL,
dt timestamp NOT NULL,
PRIMARY KEY(user_id, recipe_id, dt)
);


--�ޗ��̃e�[�u���쐬
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

--�_�������w��
COMMENT ON TABLE food IS '�ޗ�';
COMMENT ON COLUMN food.food_id IS '�ޗ�ID';
COMMENT ON COLUMN food.recipe_id IS '���V�sID';
COMMENT ON COLUMN food.display_order_food IS '�\����_�ޗ�';
COMMENT ON COLUMN food.food_name IS '�ޗ���';
COMMENT ON COLUMN food.amount IS '����';




--�ޗ��ifood_id�j1����̎����A�Ԃɐݒ�
ALTER SEQUENCE food_food_id_seq RESTART 1;

INSERT INTO food (recipe_id, display_order_food, food_name, amount) 
VALUES
(1,1,'���͕�','80g'),
(1,2,'�S����','80g'),
(1,3,'�x�[�L���O�p�E�_�[','8g'),
(1,4,'����','40g'),
(1,5,'��','������1/4'),
(1,6,'���iM�T�C�Y�j','1��'),
(1,7,'�I�[�c�~���N�i�����j','110ml'),
(1,8,'���[�v���V���b�v','�傳��2'),
(1,9,'������','2��'),
(1,10,'�I�����W�i�X���C�X�j','3��'),

(2,1,'���Ⴊ����','1��'),
(2,2,'�ɂ񂶂�','1/2��'),
(2,3,'(A)��','�傳��2'),
(2,4,'(A)�݂��','�傳��1'),
(2,5,'(A)����','�傳��1'),
(2,6,'(A)�����a������','������1'),
(2,7,'(A)�ݖ�','�傳��2'),
(2,8,'�T���_��','�K��'),

(3,1,'���Ⴊ����','1��'),
(3,2,'�؃o����','50g'),
(3,3,'���R�V���E','���X'),
(3,4,'�ɂ�ɂ�','1/2��'),
(3,5,'(A)���傤��','�傳��1/2'),
(3,6,'(A)�݂��','�傳��1/2'),
(3,7,'(A)��','�傳��1/2'),
(3,8,'(A)����','�傳��1/4'),
(3,9,'(A)�|','�傳��1/4'),
(3,10,'�p�Z��','�K��'),

(4,1,'�~�j�g�}�g','7��'),
(4,2,'����','1��'),
(4,3,'�߂��(�X�g���[�g)','1/8�J�b�v'),


(5,1,'�����߂�','50g'),
(5,2,'����(��ŗp)','500ml'),
(5,3,'���؍����т���','40g'),
(5,4,'���ܖ�','������1/2'),
(5,5,'(A)�߂��(2�{�Z�k)','�傳��1/2'),
(5,6,'(A)�I�C�X�^�[�\�[�X','�傳��1/2'),
(5,7,'(A)���傤��','�傳��1/2'),
(5,8,'(A)���ܖ�','�傳��1/2'),
(5,9,'(A)�|','�傳��1/4'),
(5,10,'���n��','1��'),
(5,11,'���˂�(�����؂�)','�K��'),
(5,12,'����','5g'),
(5,13,'���[��','������1'),


(6,1,'����������','100g'),
(6,2,'����','75g'),
(6,3,'�ɂ񂶂�','25g'),
(6,4,'�����Ђ���','1.5g'),
(6,5,'��(�߂��p)','�K��'),
(6,6,'�n����(M�T�C�Y)','1/4��'),
(6,7,'(A)�ЌI��','�傳��1/2'),
(6,8,'(A)�����a������','������1/4'),
(6,9,'(A)���R�V���E','������1/4'),
(6,10,'�g����','�K��'),
(6,11,'���肨�낵���I','������1'),
(6,12,'��t','1��'),


(7,1,'������','1/4��'),
(7,2,'������','100g'),
(7,3,'��','�傳��1/4'),
(7,4,'���傤��','������1'),


(8,1,'�~�j�g�}�g','3��'),
(8,2,'�ɂ�','5g'),
(8,3,'��','1/2��'),
(8,4,'���ܖ�','�K��'),
(8,5,'(A)�ی{����X�[�v�̑f(����)','������1/2'),
(8,6,'(A)��','900ml'),
(8,7,'(A)��','�K��'),
(8,8,'(A)��','�K��'),
(8,9,'(A)�����傤','�K��'),


(9,1,'��','1��'),
(9,2,'�ɂ�','1/4��'),
(9,3,'�����`','800ml'),
(9,4,'�݂�','�傳��1/2'),


(10,1,'���͕�','20g'),
(10,2,'�R�R�A(����)','2.5g'),
(10,3,'����','�傳��1'),
(10,4,'�o�^�[(����)','10g'),
(10,5,'���`�[�Y','�傳��1'),


(11,1,'�ؖȓ���','1/4��'),
(11,2,'�U�[�T�C','�K��'),
(11,3,'�ɂ񂶂�','1/4�{'),
(11,4,'���l�M','2.5cm'),
(11,5,'���傤��(�݂���؂�)','������1'),
(11,6,'���ܖ�','������1'),
(11,7,'��','������1'),
(11,8,'�{����X�[�v�̑f(����','������1'),
(11,9,'��','������1'),
(11,10,'����(��)','�K��'),
(11,11,'�O�t','�K��'),


(12,1,'�j��','50g'),
(12,2,'�c�i���Ђ�(�`����)','45g'),
(12,3,'���R�V���E','�K��'),
(12,4,'���ܖ�','�傳��1/2');


--�菇�̃e�[�u���쐬
CREATE TABLE process(
process_id SERIAL,
recipe_id INT NOT NULL,
display_order_process INT NOT NULL,
process_description VARCHAR(50) NOT NULL
);

--process�e�[�u����process_description�̕�������ύX�i���₵���j
ALTER TABLE process
ALTER COLUMN process_description TYPE VARCHAR(200);

--�_�������w��
COMMENT ON TABLE process IS '�菇';
COMMENT ON COLUMN process.process_id IS '�菇ID';
COMMENT ON COLUMN process.recipe_id IS '���V�sID';
COMMENT ON COLUMN process.display_order_process IS '�\����_�菇';
COMMENT ON COLUMN process.process_description IS '�H������';

--�菇�iprocess_id�j1����̎����A�Ԃɐݒ�
ALTER SEQUENCE process_process_id_seq RESTART 1;

INSERT INTO process (recipe_id, display_order_process, process_description) 
VALUES
(1,1,'�������̓w�^�����A���؂�ɂ��܂��B�I�����W�͔�t���̂܂ܔ����֐؂�ɂ��܂��B'),
(1,2,'�{�E���ɕ��ށA�����A�������A���Ċ�ō������킹�܂��B'),
(1,3,'���A�I�[�c�~���N�����������ۂ����Ȃ��Ȃ�܂ō������킹�܂��B'),
(1,4,'�t���C�p������΂ŉ��M���A���܂�����3��1/4�ʂ𗬂�����3�����Ă��܂��B'),
(1,5,'�v�c�v�c�ƋC�A���o�Ă����痠�Ԃ�3�����Ă��܂��B'),
(1,6,'���ʂɏĂ��F���t���΂��ʂ�����΂��牺�낵�܂��B���l��3���Ă��܂��B'),
(1,7,'�M�ɐ���t���A�t���[�c������A���[�v���V���b�v�������Ċ����ł��B'),

(2,1,'���Ⴊ�����͍א؂�ɂ��܂��B'),
(2,2,'�l�Q�͔���ނ��A�א؂�ɂ��܂��B'),
(2,3,'����𒆉΂ŔM���A�T���_�������܂��B'),
(2,4,'�l�Q���u�߂܂��B'),
(2,5,'���Ⴊ�������u�߂܂��B'),
(2,6,'���Ⴊ�����Ɍy���΂��ʂ�����A(A)�̒����������܂��B'),
(2,7,'�`�C�������Ȃ�܂��u�߂��犮���ł��B'),

(3,1,'���Ⴊ�����͔�t���̂܂�8�����ɐ؂�܂��B'),
(3,2,'�G�ꂽ�y�[�p�[�ƃ��b�v�ɂ����500W�����W��3�����M���A�|���������Ɠ���_�炩���ɂ��܂��B'),
(3,3,'�j���j�N�͔��؂�ɂ��܂��B'),
(3,4,'�ؓ��͉������傤��U�艺�������܂��B'),
(3,5,'�t���C�p���ɓؓ��ƃj���j�N�����A��΂ł�������Ǝ��ƍ�����o���Ă����܂��B'),
(3,6,'���ɍ��肪�ڂ����炶�Ⴊ���������A�Ă��F������悤�ɂ��ďĂ��Ă����܂��B'),
(3,7,'(A)�������A�S�̂ɗ��܂���悤�ɂ����u�ߎςăp�Z����U�肩�����犮���ł��B'),


(4,1,'�~�j�g�}�g�́A�w�^�����A�ۑ��܂ɓ���ėⓀ�ɂłЂƔӓ��点�܂��B'),
(4,2,'�����͐�؂�ɂ��܂��B'),
(4,3,'�{�E���ɐ����͂�A�~�j�g�}�g�����Ĕ���ނ��A���C��؂�܂��B'),
(4,4,'��ɐ���A�߂��������A������Y���܂��B'),
(4,5,'�~�j�g�}�g�����S�ɗn���Ȃ������ɂ��������܂��B'),


(5,1,'(A)�̍ޗ��������܂��B'),
(5,2,'�t���C�p���ɂ��ܖ��𒆉΂ŔM���A���؍��т������u�߂܂��B'),
(5,3,'�΂��ʂ�����1�̂����傳��1�������Ė����S�̂ɂȂ��ނ܂��u�߁A�΂��炨�낵�܂��B'),
(5,4,'��ɂ����𕦂����A�����߂���p�b�P�[�W�̕\�L�ʂ�ɂ�ł܂��B'),
(5,5,'��������Ɨ����Ő􂢁A����؂�܂��B'),
(5,6,'�����߂��(A)�̎c���a���܂��B'),
(5,7,'��ɐ���A�̂���̍ޗ����̂��Ċ����ł��B'),


(6,1,'�����͂��肨�낵�܂��B'),
(6,2,'�ɂ񂶂�͍א؂�ɂ��܂��B'),
(6,3,'�ؖȓ����̓L�b�`���y�[�p�[�ɕ�݂܂��B'),
(6,4,'�{�E���ɓ���A���̏�ɂ���Ƀ{�E�����̂��ė①�ɂɓ���A1���Ԓ����C��؂�܂��B'),
(6,5,'���𒣂����ʂ̃{�E���ɁA�����Ђ��������߂��A�����Ő􂢐��C��؂�܂��B'),
(6,6,'�ʂ̃{�E���ɖؖȓ������������ē���A�����A�l�Q�A�Ђ����A�n�����A(A)�����A�������킹�܂��B'),
(6,7,'�g�������t���C�p���̒ꂩ��3cm���̍����܂œ���A170���ɔM���܂��B'),
(6,8,'�������킹���ޗ����`�𐮂��A���ɉ΂��ʂ�܂�5�����g���A���o���Ė���؂�܂��B'),
(6,9,'��ɗg�������̂𐷂�t���A���傤���Ƒ�t��Y���Ċ����ł��B'),


(7,1,'�����؂͔M���ł�ł�4cm�̒����ɐ؂�܂��B'),
(7,2,'�����т͐�(�傳��1)�ɂ��Ė߂��܂��B'),
(7,3,'�߂��`�͎���Ă����܂��B'),
(7,4,'�����؂ƍ����т̐��C��؂�A�߂��`�A���A���傤��Řa���܂��B'),


(8,1,'�~�j�g�}�g�̓w�^������Ĕ����ɐ؂�A�ɂ��3�`4cm�̒����ɐ؂�܂��B'),
(8,2,'��ɂ��ܖ���M���ă~�j�g�}�g����΂��u�߁A(A)�������Ďς܂�(��10��)�B'),
(8,3,'���A���A�����傤�Ŗ��𒲂��A�؂����ɂ�Ɨn�����������Ďd�グ�܂��B'),


(9,1,'�ɂ��2cm���ɐ؂�܂��B'),
(9,2,'��ɂ����`�����Ē��΂ɂ����A�ɂ��������5�����ς܂��B'),
(9,3,'�݂���n������A�����������Ĕ��g�������܂�A���g�����n�ɂȂ�܂ŉ��M���A��ɐ���܂��B'),


(10,1,'�{�E���Ƀo�^�[�����A�Ȃ߂炩�ɂȂ�悤�ɖA���Ċ�ō����܂��B'),
(10,2,'�����������A����ɍ����A���͕��A�R�R�A���ӂ邢����܂��B'),
(10,3,'�S���x���ŕ����ۂ����Ȃ��Ȃ�܂ō�������A���`�[�Y�̔��ʂ������܂��B'),
(10,4,'�{�[���^�Ɋۂ߂܂��B�����9���܂��B'),
(10,5,'�I�[�u���V�[�g�̏�ɕ��ׁA�d�q�����W(600W)��1�������M���܂��B'),
(10,6,'��߂���c��̕��`�[�Y���܂Ԃ��܂��B'),


(11,1,'�����͌y�����؂肵�A�e���ׂ��Ă����܂��B'),
(11,2,'�U�[�T�C�A�ɂ񂶂�͐�؂�A���˂��݂͂���؂�ɂ��܂��B'),
(11,3,'�t���C�p���ɂ��ܖ����Ђ��A�؂������˂��A���傤�������č�����o���܂��B'),
(11,4,'�u�߂����̂ɓ����A�U�[�T�C�A�ɂ񂶂�A���A�{����X�[�v�̑f�A���������ăT�b���u�߂܂��B'),
(11,5,'�d�グ�ɂ��܂��ӂ�A�O�t��Y���܂��B'),


(12,1,'�j����5cm���ɐ؂�܂��B'),
(12,2,'�t���C�p���ɂ��ܖ�����ꒆ�΂ŉ��M���A�j���A�c�i���Ђ������2�����u�߂܂��B'),
(12,3,'�j��������Ȃ肵�Ă����璆�΂̂܂܁A�������傤������u�ߍ��킹�܂��B'),
(12,4,'�S�̂ɖ����Ȃ��񂾂�΂��牺�낵�A��ɐ���t���Ċ����ł��B');


--�J�e�S���̃e�[�u���쐬
CREATE TABLE category(
category_id INT PRIMARY KEY,
category_name VARCHAR(50) NOT NULL
);

--�_�������w��
COMMENT ON TABLE category IS '�J�e�S��';
COMMENT ON COLUMN category.category_id IS '�J�e�S��ID';
COMMENT ON COLUMN category.category_name IS '�J�e�S����';

INSERT INTO category (category_id, category_name) 
VALUES 
(1, '�m�H'),
(2, '�a�H'),
(3, '����'),
(4, '�f�U�[�g'),
(5, '�t�����킹');



--���V�s�ƃJ�e�S���̃e�[�u���쐬
CREATE TABLE recipe_and_category(
recipe_category_id SERIAL,
recipe_id INT NOT NULL,
category_id INT NOT NULL
);

--�_�������w��
COMMENT ON TABLE recipe_and_category IS '���V�s�ƃJ�e�S��';
COMMENT ON COLUMN recipe_and_category.recipe_category_id IS '���V�s�ƃJ�e�S��ID';
COMMENT ON COLUMN recipe_and_category.recipe_id IS '���V�sID';
COMMENT ON COLUMN recipe_and_category.category_id IS '�J�e�S��ID';

--���V�s�ƃJ�e�S���irecipe_and_category_id�j1����̎����A�Ԃɐݒ�
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


--�����˃e�[�u���쐬
CREATE TABLE good_table(
good_id SERIAL,
recipe_id INT NOT NULL,
user_id INT NOT NULL,
dt timestamp NOT NULL
);

--�_�������w��
COMMENT ON TABLE good_table IS '�����˃e�[�u��';
COMMENT ON COLUMN good_table.good_id IS '������ID';
COMMENT ON COLUMN good_table.recipe_id IS '���V�sID';
COMMENT ON COLUMN good_table.user_id IS '���[�U�[ID';
COMMENT ON COLUMN good_table.dt IS '�����˂����������t';

--�����˃e�[�u���igood_id�j1����̎����A�Ԃɐݒ�
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
