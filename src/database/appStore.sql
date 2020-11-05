--drop database appStore
--go
create database appStore
go
use appStore

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
---CREATING TABLES-----------------------------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE app_details(
app_ID INT NOT NULL IDENTITY(1,1) UNIQUE,
name VARCHAR(50) NOT NULL,
descript varchar(50) NOT NULL, 
version FLOAT NOT NULL,
category VARCHAR(50) NOT NULL,
avg_rating FLOAT, 
PRIMARY KEY(app_ID)
)
go

CREATE TABLE user_details(
user_ID INT NOT NULL IDENTITY (1,1) UNIQUE, 
name VARCHAR(50) NOT NULL,
date_of_birth DATE NOT NULL,
password VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL, 
PRIMARY KEY(user_ID)
)

go

CREATE TABLE ratings(
app_ID INT NOT NULL, 
user_ID INT NOT NULL,
rating FLOAT NOT NULL, 
FOREIGN KEY(app_ID) REFERENCES app_details(app_ID) ON DELETE CASCADE, 
FOREIGN KEY(user_ID) REFERENCES user_details(user_ID) ON DELETE CASCADE,
PRIMARY KEY(app_ID, user_ID)
)

go

CREATE TABLE reviews(
app_ID INT NOT NULL, 
user_ID INT NOT NULL,
review VARCHAR(100) NOT NULL, 
FOREIGN KEY(app_ID) REFERENCES app_details(app_ID) ON DELETE CASCADE,
FOREIGN KEY(user_ID) REFERENCES user_details(user_ID) ON DELETE CASCADE, 
PRIMARY KEY(app_ID, user_ID)
)

go

CREATE TABLE user_apps(
app_ID INT NOT NULL, 
user_ID INT NOT NULL, 
FOREIGN KEY(app_ID) REFERENCES app_details(app_ID) ON DELETE CASCADE,
FOREIGN KEY(user_ID) REFERENCES user_details(user_ID) ON DELETE CASCADE, 
PRIMARY KEY(app_ID, user_ID)
)

go

CREATE TABLE cards(
ID INT NOT NULL IDENTITY (1,1) UNIQUE,
user_ID INT NOT NULL,
card_No INT NOT NULL,
expiry_year INT NOT NULL,
FOREIGN KEY(user_ID) REFERENCES user_details(user_ID) ON DELETE CASCADE
)

go

CREATE TABLE payment_methods(
ID INT NOT NULL IDENTITY (1,1) UNIQUE,
user_ID INT NOT NULL,
method VARCHAR(50),
FOREIGN KEY(user_ID) REFERENCES user_details(user_ID) ON DELETE CASCADE
)

go

--PROCEDURE FOR ADD USER
CREATE PROCEDURE add_user @name varchar(50),@email varchar(50),@password varchar(50),@date_of_birth DATE
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

go

--PROCEDURE FOR REMOVE USER
CREATE PROCEDURE remove_user @user_id INT
as
Begin
	if (@user_id is not null)
		if exists(select user_ID from user_details where user_details.user_ID = @user_id)
			begin
			delete from user_details
			where user_details.user_ID = @user_id
			end
			else
			RAISERROR('User not Exists!',16,1)
	else
	RAISERROR('Invalid user ID!',16,1)
END

go

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
	RAISERROR('Invalid username',16,1)
END

go

--PROCEDURES FOR METHODS IN USERINTERFACE

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

go
--PROCEDURE FOR checkUSERExists
create procedure check_User_Exist @userId INT, @flag INT output
as
Begin
	if(@userId is not null and exists(select * from user_details where @userID = user_details.user_ID))
	begin
		set @flag = 1
	end
	else
	begin
	set @flag = 0
	RAISERROR('User Not Exists',16,1)
	end
END
go
-- TO GET APP ID, THIS ACTS AS A HELPER FUNCTION

Create procedure getAppId @appname varchar(50),@version int,@appId int output
As
Begin
	set @appId=(select app_ID
	from app_details
	where name=@appname and version=@version)
End

go
-- TO GET USER ID, THIS ACTS AS A HELPER FUNCTION
Create procedure getUserId @email varchar(50), @userId int output
As
Begin
	set @userId=(select user_details.user_ID 
	from user_details
	where user_details.email = @email)
End

go
--PROCEDURE FOR ADDINSTALLED APP FOR USER
create procedure add_Installed_App @appId INT ,@userID INT, @ver INT
as
Begin
if(@appId is not null and exists(select * from app_details where @appId = app_details.app_ID and @ver = app_details.version))
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

go
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

go
--PROCEDURE FOR AUTHENTICATE_USER
create procedure authenticate_user @user_id INT, @password varchar(50), @authenticate INT output
as
BEGIN
if(@user_id is not null and @password is not null and exists(select * from user_details where user_details.user_ID = @user_id and @password = user_details.password))
	begin
		set @authenticate = 1
	end
	else
	begin
	set @authenticate = 0
	RAISERROR('Password Incorrect!!',16,1)
	end
END

go
--PROCEDURE FOR ADD CARD
create procedure add_card @user_id INT, @card_no INT, @expiry_year INT
as
BEGIN
if(@user_id is not null)
	if(@card_no is not null)
		if(@expiry_year is not null)
		begin
			insert into cards(user_ID, card_No, expiry_year) values (@user_id, @card_no, @expiry_year)
		end
		else
		RAISERROR('Invalid Expiry Date',16,1)
	else
	RAISERROR('Invalid Card Number',16,1)
else
RAISERROR('Invalid User ID',16,1)
END

go

--PROCEDURE FOR checkAppInstall
create procedure check_App_Install @app_id INT, @user_id INT, @installed INT output
as
BEGIN
if exists(select * from user_apps where user_apps.app_ID = @app_id and user_apps.user_ID = @user_id)
	set @installed = 1
else
	set @installed = 0
END

go
--PROCEDURE FOR SETPAYMENTMETHOD
create procedure set_payment_method @user_id INT, @method varchar(50)
as
BEGIN
if(@user_id is not null)
	if(@method is not null)
		insert into payment_methods(user_ID,method) values(@user_id, @method)
	else
	RAISERROR('Method is Invalid',16,1)
else
RAISERROR('User ID is Invalid',16,1)
END

go
--PROCEDURE FOR CHANGE CARD DETAILS
create procedure change_Card_Details @user_id INT, @card_no INT, @newExpYear INT 
as
BEGIN
if exists(select * from cards where @user_id = cards.card_No and @user_id = cards.user_ID)
	begin
		update cards
		set cards.expiry_year = @newExpYear
		where @card_no = cards.card_No and @user_id = cards.user_ID 
	end
else
RAISERROR('Card Not Found!',16,1)
END

go
--PROCEDURE FOR CHECKEMAILEXISTS
create procedure check_email_exists @email varchar(50), @flag INT output
as
BEGIN
if(@email is not null)
	if exists(select * from user_details where user_details.email = @email)
		set @flag = 1
	else
		set @flag = 0
else
RAISERROR('Invalid email formt',16,1)
END

go
--PROCEDURE FOR REMOVE CARD DETAILS
create procedure remove_card_details @user_id INT, @card_no INT
as
Begin
if exists(select * from cards  where cards.card_No = @card_no and cards.user_ID = @user_id)
	delete from cards
	where cards.card_No = @card_no and
	@user_id = cards.user_ID
else
RAISERROR('Card or User not exists',16,1)
End

go
--PROCEDURE FOR CHECKUSERCARD
create procedure check_user_card @user_id INT, @card_no INT, @flag INT output
as
Begin
if exists(select * from cards  where cards.card_No = @card_no and cards.user_ID = @user_id)
	set @flag = 1
else
begin
 set @flag = 0
 RAISERROR('Card or User not exists',16,1)
end
End

go

--PROCEDURES FOR APPINTERFACE
--PROCEDURE FOR GETAPPDETAILS
create procedure getAppDetails @app_id INT
as 
begin
	select * from app_details where @app_id = app_details.app_ID
end

go
--PROCEDURE FOR ALLAPPS
create procedure getAllApps
as
begin
	select app_details.app_ID from app_details
end

go
--PROCEDURE FOR GETAPPSINCATEGORY
create procedure getAppsInCategory @category varchar(50)
as
begin
	select app_details.app_ID from app_details where @category = app_details.category
end

go

--PROCEDURE FOR GETAPPCONTENT
create procedure getAppContent @app_id INT
as
begin
	select app_details.descript from app_details where @app_id = app_details.app_ID
end

go

--PROCEDURE FOR ADDRATING
create procedure addRating @app_id INT, @user_id INT, @rating INT
as
begin
	declare
	@avgRating FLOAT
	if exists(select ratings.rating from ratings where ratings.app_ID = @app_id and ratings.user_ID = @user_id)
	begin
		update ratings
		set rating = @rating
		where ratings.app_ID = @app_id and ratings.user_ID = @user_id
		select @avgRating = AVG(ratings.rating) from ratings where @app_id = ratings.app_ID
		update app_details
		set avg_rating = @avgRating
		where app_details.app_ID = @app_id
	end
	else
	begin
		insert into ratings(app_ID, user_ID, rating) values(@app_id, @user_id, @rating)
		select @avgRating = AVG(ratings.rating) from ratings where @app_id = ratings.app_ID
		update app_details
		set avg_rating = @avgRating
		where app_details.app_ID = @app_id
	end
end

go

--PROCEDURE FOR ADDCOMMENT
create procedure addComment @app_id INT, @user_id INT, @comment varchar(100)
as
begin
	if exists(select comments.comment from comments where @user_id = comments.user_ID and @app_id = comments.app_ID)
	begin
		update comments
		set comments.comment = @comment
		where comments.app_ID = @app_id and comments.user_ID = @user_id
	end
	else
	begin
		insert into comments(app_ID, user_ID, comment) values(@app_id, @user_id, @comment)
	end
end

go

--PROCEDURE FOR ADDING APP
create procedure add_App @name VARCHAR(50) ,@version FLOAT ,@category VARCHAR(50), @description varchar(50)
as
Begin
if(@name is not null and not exists(select * from app_details where @name = app_details.name))
begin 
	if(@version is not null)
	begin 
		if(@category is not null)
		begin 
			 insert into app_details(name,version,category,avg_rating, descript) values(@name, @version, @category, NULL,@description)
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

go
--PROCEDURE FOR REMOVE APP
create procedure remove_App @app_id INT
as
begin
	delete from app_details where app_details.app_ID = @app_id
end

go
--PROCEDURE FOR CHECKAPPEXISTS
create procedure checkAppExits @app_id INT, @flag INT OUTPUT
as
begin
	if exists(select app_details.app_ID from app_details where app_ID = @app_id)
		set @flag = 1
	else
		set @flag = 0 
end 

go

--PROCEDURE FOR UPDATEAPP
create procedure updateApp @name VARCHAR(50) ,@version FLOAT ,@category VARCHAR(50), @content varchar(50)
as
begin
	if exists(select app_details.app_ID from app_details
				where @name = app_details.name
				and @version = app_details.version
				and @category = app_details.category)
	begin
		update app_details
		set descript = @content
		where @name = app_details.name
		and @version = app_details.version
		and @category = app_details.category
	end
	else
	RAISERROR('APP not Exists!',16,1)
end

