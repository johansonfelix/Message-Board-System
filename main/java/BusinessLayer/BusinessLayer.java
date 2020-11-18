package BusinessLayer;

import PersistenceLayer.AttachmentDAO;
import PersistenceLayer.AttachmentDAOImpl;
import PersistenceLayer.PostDAOImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;

public class BusinessLayer {

    private LinkedList<Post> messageBoard = new LinkedList<>();
    private PostDAOImpl postDAO = new PostDAOImpl();
    private AttachmentDAO attachmentDAO = new AttachmentDAOImpl();


    public LinkedList<Post> createPost(int postID, String postTitle, String user, LocalDate date_created,String message, ByteArrayOutputStream bos) throws IOException, ClassNotFoundException {

        Post post = new Post(postID,postTitle,user,date_created,null,message,false);

        postDAO.createPost(post);

        if(bos!=null) {

            byte[] bytes = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream in = new ObjectInputStream(bis);
            Attachment new_attachment = (Attachment) in.readObject();
            attachmentDAO.insertAttachment(new_attachment);
            messageBoard = postDAO.getAllPosts();
        }

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

    public LinkedList<Post> searchPosts(String user, String dateRange, String[] hashtags){

        return new LinkedList<>();
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


