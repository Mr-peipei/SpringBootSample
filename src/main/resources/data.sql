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
 ,tweet_Id
) VALUES
('system@co.jp', '2000-12-01', 'サンプルツイートナウ','1')
,('user@co.jp', '2000-12-02', 'とりあえず適当にツイートナウ','2')
,('system@co.jp', '1999-03-01', 'ツイートいっぱいしてるなう','3')
,('user@co.jp', '1999-02-01', 'Youtubeいっぱいみてるなう','4')
,('system@co.jp', '1999-01-18', 'お眠りしたいなう','5')
,('system@co.jp', '1999-01-17', 'お眠りしたいなう','6')
,('system@co.jp', '1999-01-16', 'お眠りしたいなう','7')
,('system@co.jp', '1999-01-15', 'お眠りしたいなう','8')
,('system@co.jp', '1999-01-14', 'お眠りしたいなう','9')
,('system@co.jp', '1999-01-13', 'お眠りしたいなう','10')
,('system@co.jp', '1999-01-12', 'お眠りしたいなう','11')
,('system@co.jp', '1999-01-11', 'お眠りしたいなう','12')
,('system@co.jp', '1999-01-10', 'お眠りしたいなう','13')
,('system@co.jp', '1999-01-09', 'お眠りしたいなう','14')
,('system@co.jp', '1999-01-08', 'お眠りしたいなう','15')
,('system@co.jp', '1999-01-07', 'お眠りしたいなう','16')
,('system@co.jp', '1999-01-06', 'お眠りしたいなう','17')
,('system@co.jp', '1999-01-05', 'お眠りしたいなう','18')
,('system@co.jp', '1999-01-04', 'お眠りしたいなう','19')
,('system@co.jp', '1999-01-03', 'お眠りしたいなう','20')
,('system@co.jp', '1999-01-02', 'お眠りしたいなう','21')
;

--ツイートテーブル
-- INSERT INTO a_follow (
--                      user_id
--                     ,follow_Date
--                     ,follow
-- ) VALUES
-- ('system@co.jp', '2000-12-01', 'user@co.jp')
-- ;
--
-- INSERT INTO a_follower (
--                       user_id
--                      ,follower_Date
--                      ,follower
-- ) VALUES
--     ('user@co.jp', '2000-12-01', 'system@co.jp')
-- ;
