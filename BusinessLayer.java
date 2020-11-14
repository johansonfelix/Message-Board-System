package com.company;
import javafx.geometry.Pos;

//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class BusinessLayer {

    HashMap<String, LinkedList<Post>> messageBoard = new HashMap<>();

    public HashMap<String, LinkedList<Post>> createPost(String postID, String message, String user, LocalDateTime date, String attDestFilePath){
        if(messageBoard.containsKey(user))
            messageBoard.get(user).add(new Post(postID,message,user, date, attDestFilePath));
        else
        {
            LinkedList<Post> posts = new LinkedList<>();
            posts.add(new Post(postID,message,user, date, attDestFilePath));
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

    public HashMap<String, LinkedList<Post>> updatePost(String postID, String message, String user, LocalDateTime date, String attDestFilePath){
        if(messageBoard.containsKey(user)){

            for(String userID : messageBoard.keySet()){

                if(userID.equalsIgnoreCase(user)){
                    for (Post post : messageBoard.get(user)){
                        if(post.getPostID().equalsIgnoreCase(postID)){
                            post.setMessage(message);
                            post.setDate(date);
                            post.setAttDestFilePath(attDestFilePath);
                            post.setUpdated(true);
                        }
                    }
                }
            }

        }

        return messageBoard;
    }


    public LinkedList<Post> searchPosts(LinkedList<Post> posts ,String user, LocalDateTime startDate, LocalDateTime endDate,  LinkedList<String> hashtag){

        LinkedList<Post> searchResults = new LinkedList<Post>();

            for (Post p : posts) {

                if (user == null || p.getUser().equals(user)) {
                    if (startDate == null || p.getDate().isAfter(startDate)) {
                        if (endDate == null || p.getDate().isBefore(endDate)) {
                            Boolean foundHashTag = true;
                            for (String s : hashtag) {
                                if (!p.getMessage().contains(s)) {
                                    foundHashTag = false;
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


