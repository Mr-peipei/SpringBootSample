INSERT INTO employee(id, name, age)
VALUES('1', 'Tom', 30);


--ユーザーマスタ
INSERT INTO m_user (
 user_id
 ,password
 ,user_name
 ,birthday
 ,age
 ,gender
 ,department_id
 ,role
 ,introduction
 ,follows
 ,follower
) VALUES
('system@co.jp', '$2a$10$9sDoLVretuseW8I0KG872uRYEa0A7bI0YXL5fYxF0KyDLo.gEDGHO', 'システム管理者', '2000-01-01', 21, 1, 1, 'ROLE_ADMIN', '自己紹介サンプルです。', 0, 0)
,('user@co.jp', '$2a$10$9sDoLVretuseW8I0KG872uRYEa0A7bI0YXL5fYxF0KyDLo.gEDGHO', 'ユーザー1', '2000-01-01', 21, 2, 2, 'ROLE_GENERAL','自己紹介サンプルです。', 0, 0)
;

--部署マスタ
INSERT INTO m_department (
 department_id
 ,department_name
) VALUES
(1, 'システム管理部')
,(2, '営業部')
;

--給料テーブル
INSERT INTO t_salary (
 user_id
 ,year_month
 ,salary
) VALUES
('user@co.jp', '2020/11', 280000)
,('user@co.jp', '2020/12', 290000)
,('user@co.jp', '2021/1', 300000)
;

--ツイートテーブル
INSERT INTO a_tweet (
  user_id
 ,tweet_Date
 ,tweet
) VALUES
('system@co.jp', '2000-12-01', 'サンプルツイートナウ')
,('user@co.jp', '2000-12-02', 'とりあえず適当にツイートナウ')
,('system@co.jp', '1999-03-01', 'ツイートいっぱいしてるなう')
,('user@co.jp', '1999-02-01', 'Youtubeいっぱいみてるなう')
,('system@co.jp', '1999-01-01', 'お眠りしたいなう')
;

