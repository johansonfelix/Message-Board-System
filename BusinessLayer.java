package com.company;

import javafx.geometry.Pos;

import java.time.LocalDate;
import java.util.*;

public class BusinessLayer {

    HashMap<String, LinkedList<Post>> messageBoard = new HashMap<>();

    public HashMap<String, LinkedList<Post>> createPost(String postID, String message, String user, LocalDate date){
        if(messageBoard.containsKey(user))
            messageBoard.get(user).add(new Post(postID,message,user, date));
        else
        {
            LinkedList<Post> posts = new LinkedList<>();
            posts.add(new Post(postID,message,user, date));
            messageBoard.put(user, posts);
        }
        return messageBoard;
    }

    public HashMap<String, LinkedList<Post>> deletePost(String user, String postID){
        if(messageBoard.containsKey(user)){

            for(String userID : messageBoard.keySet()){

                if(userID.equalsIgnoreCase(user)){
                    messageBoard.get(user).removeIf(post -> post.getPostID().equalsIgnoreCase(postID));
                }
            }

        }


        return messageBoard;
    }

    public HashMap<String, LinkedList<Post>> updatePost(String postID, String message, String user, LocalDate date){
        if(messageBoard.containsKey(user)){

            for(String userID : messageBoard.keySet()){

                if(userID.equalsIgnoreCase(user)){
                    for (Post post : messageBoard.get(user)){
                        if(post.getPostID().equalsIgnoreCase(postID)){
                            post.setMessage(message);
                            post.setDate(date);
                            post.setUpdated(true);
                        }
                    }
                }
            }

        }

        return messageBoard;
    }

    public LinkedList<Post> searchPosts(String user, String dateRange, String[] hashtags){

        return new LinkedList<>();
    }

    public LinkedList<Post> viewRecentPosts(){
        //insert code to read number of posts from config file with default 10
        int numOfPosts = 10; //default
        if(messageBoard.isEmpty())
            return null;

        LinkedList<Post> allPosts = new LinkedList<>();
        for(String user : messageBoard.keySet()){
            allPosts.addAll(messageBoard.get(user));
        }


        allPosts.sort(Comparator.comparing(Post::getDate));


        LinkedList<Post> posts = new LinkedList<>();



        if(allPosts.size()<10){
                numOfPosts = allPosts.size();
            for(int i = 0 ; i< numOfPosts; i++) {

                posts.add(allPosts.removeLast());
            }
        }
        else {

            for (int i = 0; i < numOfPosts; i++) {
                posts.add(allPosts.removeLast());
            }
        }

        return posts;

    }
}


