import sun.awt.image.ImageWatched;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.LinkedList;

public class Test {

    public static LinkedList<Post> searchPosts(LinkedList<Post> posts , String user, LocalDateTime startDate, LocalDateTime endDate, LinkedList<String> hashtag) {

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

    public static void main(String args[]){
        System.out.println("test");
        LocalDateTime t1 = LocalDateTime.of(2020, Month.NOVEMBER,19,14,0,0);
        LocalDateTime t2 = LocalDateTime.of(2020, Month.NOVEMBER,19,13,0,0);
        LocalDateTime t3 = LocalDateTime.of(2020, Month.NOVEMBER,19,1,0,0);

            //    public Post(int postID, String postTitle, String user, LocalDateTime date_created,
            //    LocalDateTime date_modified, String message, boolean updated) {
        Post p1 = new Post(1, "title Rania", "rania",t1,t1,"Hi, I am Rania #welcome",false);
        Post p2 = new Post(2, "title New Student", "Bob",t2,t2,"Hi, I am new student #welcome #excited",false);
        Post p3 = new Post(3, "title Mike", "Mike",t3,t3,"Hi, I am Mike #new",false);

        LinkedList<Post> postList = new LinkedList<>();
        postList.add(p1);
        postList.add(p2);
        postList.add(p3);


        LinkedList<String> hashtags = new LinkedList<>();
        hashtags.add("welcome");
        hashtags.add("excited");
        //hashtags.add("meshmawgood");

        LinkedList<Post> result = searchPosts(postList,"Bob",t3,t2, hashtags);

        for(Post p : result){
            System.out.println(p.toString());
        }


    }
}
