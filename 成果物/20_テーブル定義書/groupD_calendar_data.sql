create table post_recipe(
user_id INT NOT NULL,
recipe_id INT NOT NULL,
dt timestamp NOT NULL,
PRIMARY KEY(user_id, recipe_id, dt)
);

insert into post_recipe values
 (1, 2, '2021-5-20'), 
 (1, 2, '2021-5-21'),  
 (1, 1, '2021-5-22'),  
 (2, 2, '2021-5-22'),  
 (1, 2, '2021-5-23'),  
 (2, 1, '2021-5-23');
 
select * from post_recipe;

select * from post_recipe where date_part('year', dt) = 2021 and date_part('month', dt) = 5 and date_part('day', dt) = 23;

--�_�������w��
COMMENT ON TABLE post_recipe IS '���e���V�s';
COMMENT ON COLUMN post_recipe.user_id IS '���[�U�[ID';
COMMENT ON COLUMN post_recipe.recipe_id IS '���V�sID';
COMMENT ON COLUMN post_recipe.dt IS '���e�������t';


create table made_recipe(
user_id INT NOT NULL,
recipe_id INT NOT NULL,
dt timestamp NOT NULL,
PRIMARY KEY(user_id, recipe_id, dt)
);

insert into made_recipe values
 (1, 2, '2021-5-22'), 
 (1, 1, '2021-5-23'),  
 (2, 1, '2021-5-22'),  
 (2, 2, '2021-5-23'),  
 (1, 2, '2021-5-23'),  
 (1, 1, '2021-5-21'),  
 (1, 1, '2021-5-19');
 
select * from made_recipe;

select *, date_part('year', dt) 'year' from made_recipe where date_part('year', dt) = 2021 and date_part('month', dt) = 5 and date_part('day', dt) = 23;

--�_�������w��
COMMENT ON TABLE made_recipe IS '��������V�s';
COMMENT ON COLUMN made_recipe.user_id IS '���[�U�[ID';
COMMENT ON COLUMN made_recipe.recipe_id IS '���V�sID';
COMMENT ON COLUMN made_recipe.dt IS '��������t';

delete from post_recipe;
delete from made_recipe;
drop table post_recipe;
drop table made_recipe;
