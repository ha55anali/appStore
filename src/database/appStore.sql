drop database appStore
create database appStore
use appStore

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
---CREATING TABLES-----------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE app_details(
app_ID INT NOT NULL IDENTITY(1,1) UNIQUE,
name VARCHAR(50) NOT NULL, 
version FLOAT NOT NULL,
category VARCHAR(50) NOT NULL,
avg_rating FLOAT, 
PRIMARY KEY(app_ID, version)
)

CREATE TABLE user_details(
user_ID INT NOT NULL IDENTITY (1,1) UNIQUE, 
name VARCHAR(50) NOT NULL,
date_of_birth DATE NOT NULL,
password VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL, 
PRIMARY KEY(user_ID)
)

CREATE TABLE ratings(
app_ID INT NOT NULL, 
user_ID INT NOT NULL,
rating FLOAT NOT NULL, 
FOREIGN KEY(app_ID) REFERENCES app_details(app_ID), 
FOREIGN KEY(user_ID) REFERENCES user_details(user_ID),
PRIMARY KEY(app_ID, user_ID)
)

CREATE TABLE reviews(
app_ID INT NOT NULL, 
user_ID INT NOT NULL,
review VARCHAR(100) NOT NULL, 
FOREIGN KEY(app_ID) REFERENCES app_details(app_ID),
FOREIGN KEY(user_ID) REFERENCES user_details(user_ID), 
PRIMARY KEY(app_ID, user_ID)
)

CREATE TABLE user_apps(
app_ID INT NOT NULL, 
user_ID INT NOT NULL, 
FOREIGN KEY(app_ID) REFERENCES app_details(app_ID),
FOREIGN KEY(user_ID) REFERENCES user_details(user_ID), 
PRIMARY KEY(app_ID, user_ID)
)

