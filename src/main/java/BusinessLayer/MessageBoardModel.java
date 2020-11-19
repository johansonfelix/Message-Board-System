package BusinessLayer;

import PersistenceLayer.*;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Properties;
/*
BUSINESS LAYER
 */
public class MessageBoardModel{

    /*
    DATA MEMBERS
    single_instance: used to implement Business layer as a singleton class.
    messageBoard: stores all messages in the messageboard as a LinkedList of posts
    postDAO: Data Access Object for posts
    attachmentDAO: Data Access Object for attachments
     */
    private static MessageBoardModel single_instance = null;
    private static LinkedList<Post> messageBoard = new LinkedList<>();
    private static final PostDAOImpl postDAO = new PostDAOImpl();
    private static final AttachmentDAO attachmentDAO = new AttachmentDAOImpl();
    static int postIDCounter = 100;


    /*
    Private constructor for Business layer singleton class
     */
    private MessageBoardModel() {

        UserPopulateDB populate_once = UserPopulateDB.UserPopulateDB();
        System.out.println("Business Model Initiated");
    }

    /*
    Method to create instance of BusinessLayer once
     */
    public static MessageBoardModel MessageBoardModel() {
        if (single_instance == null) {
            single_instance = new MessageBoardModel();
        }
        return single_instance;
    }

    /*
    CREATE POST METHOD
    Accepts most of the parameters for a post constructor.
    Creates a post in through postDAO and creates attachment to database through attachment DAO
     */
    public static void createPost( String postTitle, String user, Timestamp date_created, String message, Attachment attachment) throws IOException {

        Post post = new Post(++postIDCounter, postTitle, user, date_created, null, message, attachment, false);

        postDAO.createPost(post);
        if(attachment!=null) {
            attachment.setPostID(post.getPostID());
            attachmentDAO.insertAttachment(attachment);
        }
        messageBoard = postDAO.getAllPosts();

    }

    /*
       DELETE POST METHOD
        Deletes post based on postID.
        It deletes the attachment first from the DB because it is linked to the post. Then it deletes the post.
        */
    public static void deletePost(int postID) throws IOException {

        attachmentDAO.deleteAttachment(postID);
        postDAO.deletePost(postID);
        messageBoard = postDAO.getAllPosts();

    }

    /*
   UPDATE POST METHOD
   Accepts most parameters for a post constructor. Updates post in postDAO and then updates attachment.
    */
    public static void updatePost(int postID, String postTitle, String user, Timestamp date_created, String message, Attachment attachment) throws IOException {
        Post post = new Post(postID, postTitle, user, date_created, Timestamp.from(Instant.now()), message, attachment, true);

        postDAO.updatePost(post);
        if(attachment!=null){
            attachmentDAO.updateAttachment(attachment);
        }

        messageBoard = postDAO.getAllPosts();


    }

    /*
   SEARCH POSTS METHOD
   Searches list of posts and returns matches based on username, date range, hashtags
    */
    public static LinkedList<Post> searchPosts(String user, String dateRange, String[] hashtags) {

        return new LinkedList<>();
    }

    /*
   VIEW RECENT POSTS METHOD
       Returns a LinkedList of the n most recent posts. The value of n is configurable in the config.properties file.
    */
    public static LinkedList<Post> viewRecentPosts() throws IOException {

        int n = getNumberOfPosts();


        messageBoard = postDAO.getAllPosts();
        if (messageBoard.isEmpty())
            return null;

        LinkedList<Post> allPosts = messageBoard;
        allPosts.sort(Comparator.comparing(Post::getDate_created).reversed());
        LinkedList<Post> posts = new LinkedList<>();
        allPosts.stream().limit(n).forEach((posts::add));
        return posts;

    }

    /*
    GET ATTACHMENT METHOD
     Returns a byte array of the attachment for the FileDownloadServlet
  */
    public static byte[] getAttachment(int postID) throws SQLException, IOException {
        return attachmentDAO.getAttachment(postID);
    }

    /*
    GET MIME TYPE METHOD
     Returns mime type a String to the FileDownloadServlet
  */
    public static String getAttachmentMIME(int postID) throws SQLException, IOException {
        return attachmentDAO.getAttachmentMIME(postID);
    }


    private static int getNumberOfPosts() {
        Properties properties = new Properties();

        try {

            properties.load(MessageBoardModel.class.getResourceAsStream("../configurations/config.properties"));


            return Integer.parseInt(properties.getProperty("number"));
        } catch (IOException ex) {
            System.out.println("Can't read config file. Using Default Value 10.");
        }
        return 10;
    }

    public static User LoginProcess(String email, String password) {
        User user = null;
        try {
            user = ProcessLogin.LoginProcess(email, password);
            return user;
        } catch (IOException ex) {
            System.out.println("User Login Error: " + ex.getMessage());
            return null;
        }
    }




}


