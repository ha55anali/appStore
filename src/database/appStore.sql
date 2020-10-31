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
PRIMARY KEY(app_ID)
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

--PROCEDURE FOR USER SIGNUP
CREATE PROCEDURE signup @name varchar(50),@email varchar(50),@password varchar(50),@date_of_birth DATE
as
Begin
	if (@email is not null)
	begin
	 if(@password is not null)
	 begin
	  if(@name is not null)
	  begin
	   if(@date_of_birth is not null)
	   begin
		  if not exists(select * from user_details where @email = user_details.email)
		  begin
		  insert into user_details(name,email,password,date_of_birth) values (@name, @email, @password, @date_of_birth)
		  end
		  else
		  RAISERROR('User Account is already present!',16,1)
		  end
	     else
	     RAISERROR('Date of Birth is incorrect!',16,1)
	   end
	   else
	   RAISERROR('Name is incorrect!',16,1)
	 end
	 else
	 RAISERROR('Password is incorrect!',16,1)
	end
	else
	RAISERROR('Email is incorrect!',16,1)
End

-- PROCEDURE FOR USER SIGNIN
create procedure signin @email varchar(50),@password varchar(20)
as
Begin
	if (@email is not null)
	begin
	 if(@password is not null)
	 begin
	    if not exists(select * from user_details where @email=email)
		RAISERROR('User does not exist',16,1)
		else
			begin
			  if not exists(select * from user_details where @email=email and [password]=@password)
			  RAISERROR('Password is incorrect',16,1)
			end
		end
	 else
	 RAISERROR('Invalid password',16,1)
	end
	else
	RAISERROR('Invalid email',16,1)
END

--PROCEDURE FOR ADDING APP
create procedure add_App @name VARCHAR(50) ,@version FLOAT ,@category VARCHAR(50)
as
Begin
if(@name is not null and not exists(select * from app_details where @name = app_details.name))
begin 
	if(@version is not null)
	begin 
		if(@category is not null)
		begin 
			 insert into app_details(name,version,category,avg_rating) values(@name, @version, @category, NULL)
		end
		else
		RAISERROR('Catergory Not Entered',16,1)
	end
	else
	RAISERROR('Version Not Entered',16,1)
end
else
RAISERROR('Application Already Exists',16,1)
end

-- TO GET APP ID, THIS ACTS AS A HELPER FUNCTION
Create procedure getAppId @appname varchar(50),@version int,@appId int output
As
Begin
	set @appId=(select app_ID
	from app_details
	where name=@appname and version=@version)
End

-- TO GET USER ID, THIS ACTS AS A HELPER FUNCTION
Create procedure getUserId @email varchar(50), @userId int output
As
Begin
	set @userId=(select user_details.user_ID 
	from user_details
	where user_details.email = @email)
End

--PROCEDURE FOR getUSERDETAILS
create procedure get_User_Details @userId INT
as
Begin
	if(@userId is not null and exists(select * from user_details where @userID = user_details.user_ID))
	begin
		select * from user_details where @userId = user_details.user_ID	
	end
	else
	RAISERROR('User Not Exists',16,1)
END

--PROCEDURE FOR ADDINSTALLED APP FOR USER
create procedure add_Installed_App @appId INT ,@userID INT
as
Begin
if(@appId is not null and exists(select * from app_details where @appId = app_details.app_ID))
begin 
	if(@userID is not null and exists(select * from user_details where @userID = user_details.user_ID))
	begin 
		insert into user_apps(app_ID,user_ID) values(@appId, @userID)
	end
	else
	RAISERROR('User Not Exists',16,1)
end
else
RAISERROR('Application Not Exists',16,1)
end

--PROCEDURE FOR REMOVEINSTALLED APP FOR USER
create procedure remove_Installed_App @appId INT ,@userID INT
as
Begin
if(@appId is not null and exists(select * from app_details where @appId = app_details.app_ID))
begin 
	if(@userID is not null and exists(select * from user_details where @userID = user_details.user_ID))
	begin 
		if(exists (select * from user_apps where @userID = user_apps.user_ID and @appId = user_apps.app_ID))
		   delete from user_apps where @userID = user_apps.user_ID and @appId = user_apps.app_ID
	end
	else
	RAISERROR('User Not Exists',16,1)
end
else
RAISERROR('Application Not Exists',16,1)
end
