Team-members:

Hamza Ben Jemaa 40048648

Johanson Felix 40071581

Rania Azab 40041630

Arturo Santamaria 40025561

# Message-Board-System

An online discussion application where people can hold conversations in the form of posted messages. 

Login/Logout Features enabled

Only registered users can post on the message board.

Users can edit messages

Users can search for messages

Users can attach downloadable files to their messages

Users can specify tags for their posted messages

To-be released in the future:

  *Register form
  
  *Profile page
  
  *Users can check notifications
  
  *Scroll by popular topics 
  
  *Advertisements to generate revenue
  
  *Private messages.
  
  # Installation Guide
  
  1. Create Java Project and include Servlet Specification
  2. Once Project is created then copy and replace the src folder in your project with the 'src' folder in the repository.
  3. Go through all files and import/download all neccessary libraries/artifacts/etc. that are needed (your compiler should tell you).
  4. Create mysql database called messageboard system and 3 tables (users, post, attachment):
      create database test;
      use test;
      create table Post (
postID int NOT NULL,
postTitle varchar(255),
username varchar(255) NOT NULL,
date_created timestamp NOT NULL,
last_modified_date timestamp NULL default NULL,
message varchar(2000),
primary key (postID)
);

create table Attachment (
postID int NOT NULL unique,
original_file_name varchar(255),
file_size long,
mediatype varchar(10),
attachment mediumblob,
foreign key (postID) references Post(postID)
);

create table users(
username varchar(50),
email varchar(50),
password varchar(50),
primary key (username));


  5. Do not populate any of the tables manually.
  6. Modify the db.properties file to adjust any database credentials for db access through the app.
  7. Relative paths should work from any machine as the conexts are set.
  8. Ensure that the tomcat is configured before running.
  9. Try running the app.

-Common fixes to my errors:
 -Make sure jdbc driver connector is installed and is up to date : com.mysql.cj.jdbc.Driver (I used the 8.0.22 version)
 -Make sure javax.servlet.jsp api is installed and updated
 -Make sure javax.servlet:jstl is installed and updated
 -Make sure taglibs:standard library is installed and updated.
 
 the configurations folder is in the WEB-INF/classes folder. It has db.properties and 
 
 I used intellij compiler.
  
  
