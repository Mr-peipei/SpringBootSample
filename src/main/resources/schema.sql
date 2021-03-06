CREATE TABLE IF NOT EXISTS employee (
  id VARCHAR(50) PRIMARY KEY,
  name VARCHAR(50),
  age INT
);

-- ユーザーマスタ
CREATE TABLE IF NOT EXISTS m_user (
 user_id VARCHAR(50) PRIMARY KEY
 ,password VARCHAR(100)
 ,user_name VARCHAR(50)
 ,birthday DATE
 ,age INT
 ,gender INT
 ,department_id INT
 ,introduction VARCHAR(100)
 ,follows INT
 ,follower INT
 ,role VARCHAR(50)
);

--部署マスタ
CREATE TABLE IF NOT EXISTS m_department (
 department_id INT PRIMARY KEY
 ,department_name VARCHAR(50)
);

--給料テーブル
CREATE TABLE IF NOT EXISTS t_salary (
 user_id VARCHAR(50)
 ,year_month VARCHAR(50)
 ,salary INT
 , PRIMARY KEY(user_id, year_month)
);

--ツイートテーブル
CREATE TABLE IF NOT EXISTS a_tweet (
    user_id VARCHAR(50)
    ,tweet_Date TIMESTAMP
    ,tweet VARCHAR(200)
    ,tweet_Id VARCHAR(200)
    , PRIMARY KEY(user_id, tweet_Date)
);


--フォローテーブル
CREATE TABLE IF NOT EXISTS a_follow (
    user_id VARCHAR(50)
    ,follow_Date TIMESTAMP
    ,follow VARCHAR(200)
    , PRIMARY KEY(user_id, follow_Date)
);

--フォロワーテーブル
CREATE TABLE IF NOT EXISTS a_follower (
    user_id VARCHAR(50)
    ,follower_Date TIMESTAMP
    ,follower VARCHAR(200)
    , PRIMARY KEY(user_id, follower_Date)
);