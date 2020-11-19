package PersistenceLayer;

import BusinessLayer.Attachment;
import BusinessLayer.Post;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.Base64;
import java.util.LinkedList;

public class PostDAOImpl implements PostDAO {


    @Override
    public LinkedList<Post> getAllPosts() throws IOException {
        Connection connection = DBConnection.getConnection();
        try{
            String query = "SELECT * FROM post;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            LinkedList<Post> posts = new LinkedList<>();

            while(rs.next()){
                Post post = extractPostFromResultSet(rs);
                posts.add(post);
            }

            return posts;

        }
        catch (SQLException ex){

            System.out.println("getAllPosts() SQL Error: "+ex.getMessage());
        }
        catch (IOException ex){
            System.out.println("getAllPosts() IO Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("getAllPosts() Error: Connection can't close - "+ex.getMessage());
            }
        }

        return null;
    }

    private Post extractPostFromResultSet(ResultSet rs) throws SQLException, IOException {

        int postID = rs.getInt(1);
        String postTitle = rs.getString(2);
        String username = rs.getString(3);
        Timestamp date_created = rs.getTimestamp(4);
        Timestamp date_modified = rs.getTimestamp(5);
        String message = rs.getString(6);

        Attachment attachment = getAttachment(postID);

        boolean updated =  !(date_modified == null);
        return new Post(postID,postTitle,username, date_created,date_modified,message,attachment,updated);


    }

    private Attachment getAttachment(int postID) throws IOException {
        Connection connection = DBConnection.getConnection();

        try {
            String query = "SELECT * FROM attachment WHERE postID=" + postID;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return extractAttachmentFromResultSet(rs);
            }
        }
        catch (SQLException ex){

            System.out.println("getAttachment() Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("getAttachment() Error: Connection can't close - "+ex.getMessage());
            }

        }

        return null;
    }

    private Attachment extractAttachmentFromResultSet(ResultSet rs) throws SQLException, IOException {
        int postID = rs.getInt(1);
        String original_file_name = rs.getString(2);
        long file_size = rs.getLong(3);
        String mediatype = rs.getString(4);
        Blob blob = rs.getBlob(5);

        InputStream inputStream = blob.getBinaryStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] attachmentBytes = outputStream.toByteArray();
        String base64Attachment = Base64.getEncoder().encodeToString(attachmentBytes);
        inputStream.close();
        outputStream.close();


        return new Attachment(postID, original_file_name,file_size,mediatype,attachmentBytes, base64Attachment);

    }

    @Override
    public boolean createPost(Post post) throws IOException {

        Connection connection = DBConnection.getConnection();
        try{
            String query = "INSERT INTO post (postID, postTitle, username, date_created, message) VALUES(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1,post.getPostID());
            ps.setString(2, post.getPostTitle());
            ps.setString(3, post.getUser());
            ps.setTimestamp(4, post.getDate_created());
            ps.setString(5, post.getMessage());

            int i = ps.executeUpdate();

            connection.close();

            return i != 0;


        }
        catch (SQLException ex){

            System.out.println("createPost() Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("createPost() Error: Connection can't close - "+ex.getMessage());
            }
        }

        return false;

    }

    @Override
    public boolean updatePost(Post post) throws IOException {
        Connection connection = DBConnection.getConnection();

        try{
            String query = "UPDATE post SET postTitle =?, last_modified_date= ?, message = ? WHERE postID =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, post.getPostTitle());
            ps.setTimestamp(2,post.getDate_modified());
            ps.setString(3, post.getMessage());
            ps.setInt(4,post.getPostID());

            int i = ps.executeUpdate();

            connection.close();

            return i!=0;


        }
        catch (SQLException ex){

            System.out.println("updatePost() Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("updatePost() Error: Connection can't close - "+ex.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean deletePost(int postID) throws IOException {
        Connection connection = DBConnection.getConnection();

        try{
            String query = "DELETE FROM post WHERE postID="+postID;
            PreparedStatement ps = connection.prepareStatement(query);

            int i = ps.executeUpdate();

            connection.close();
            return i!=0;
        }
        catch (SQLException ex){

            System.out.println("deletePost() Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("deletePost() Error: Connection can't close - "+ex.getMessage());
            }
        }

        return false;
    }

    @Override
    public Post getPost(int postID){
        Post post = null;

        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM post WHERE postID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, postID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                post = extractPostFromResultSet(rs);

            }

        }
        catch (SQLException ex){

            System.out.println("getPost() Error: "+ex.getMessage());
        }
        catch (IOException ex){
            System.out.println("getPost() Error: Connection can't close - "+ex.getMessage());
        }


        return post;
    }




}
