package com.company;

import PersistenceLayer.AttachmentDAO;
import PersistenceLayer.AttachmentDAOImpl;
import PersistenceLayer.PostDAOImpl;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class BusinessLayer {

    private LinkedList<Post> messageBoard = new LinkedList<>();
    private PostDAOImpl postDAO = new PostDAOImpl();
    private AttachmentDAO attachmentDAO = new AttachmentDAOImpl();


    public LinkedList<Post> createPost(int postID, String postTitle, String user, LocalDate date_created,String message, ByteArrayOutputStream bos) throws IOException, ClassNotFoundException {

        Post post = new Post(postID,postTitle,user,date_created,null,message,false);

        postDAO.createPost(post);

        byte[] bytes = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bis);
        Attachment new_attachment = (Attachment) in.readObject();
        attachmentDAO.insertAttachment(new_attachment);
        messageBoard = postDAO.getAllPosts();


        return messageBoard;
    }

    public LinkedList<Post> deletePost(int postID){

        attachmentDAO.deleteAttachment(postID);
        postDAO.deletePost(postID);
        messageBoard = postDAO.getAllPosts();

        return messageBoard;
    }

    public LinkedList<Post> updatePost(int postID, String postTitle, String user, LocalDate date_created, String message, ByteArrayOutputStream bos) throws IOException, ClassNotFoundException {
        Post post = new Post(postID,postTitle,user,date_created,LocalDate.now(),message,true);
        postDAO.updatePost(post);


        byte[] bytes = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bis);
        Attachment new_attachment = (Attachment) in.readObject();
        attachmentDAO.updateAttachment(new_attachment);
        messageBoard = postDAO.getAllPosts();

        return messageBoard;
    }

    public LinkedList<Post> searchPosts(LinkedList<Post> posts , String user, LocalDateTime startDate, LocalDateTime endDate, LinkedList<String> hashtag){


        LinkedList<Post> searchResults = new LinkedList<Post>();

        for (Post p : posts) {

            if (user == null || p.getUser().equals(user)) {
                if (startDate == null || p.getDate_created().isAfter(startDate) || p.getDate_created().isEqual(startDate)) {
                    if (endDate == null || p.getDate_created().isBefore(endDate) || p.getDate_created().isEqual(endDate)) {
                        Boolean foundHashTag = true;

                        if(hashtag !=null) {
                            for (String s : hashtag) {
                                if (!p.getMessage().contains(s)) {
                                    foundHashTag = false;
                                }
                            }
                        }
                        if (foundHashTag) {
                            searchResults.add(p);
                        }


                    }
                }
            }
        }

        Collections.reverse(searchResults);
        return searchResults;
    }
    public LinkedList<Post> viewRecentPosts(){
        //insert code to read number of posts from config file with default 10
        int numOfPosts = 10; //default

        messageBoard = postDAO.getAllPosts();
        if(messageBoard.isEmpty())
            return null;

        LinkedList<Post> allPosts = messageBoard;



        allPosts.sort(Comparator.comparing(Post::getDate_created));


        LinkedList<Post> posts = new LinkedList<>();

        allPosts.stream().limit(numOfPosts).forEach((posts::add));


        return posts;

    }



}


