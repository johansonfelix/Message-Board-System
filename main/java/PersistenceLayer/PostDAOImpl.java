package PersistenceLayer;

import BusinessLayer.Post;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;

public class PostDAOImpl implements PostDAO {
    @Override
    public Post getPost(int postID) throws SQLException {
        //DB Connection
        Connection connection = DBConnection.getConnection();

        try{
            String query = "SELECT * FROM post WHERE postID="+postID;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return extractPostFromResultSet(rs);
            }

        }
        catch (SQLException ex){

            System.out.println("getPost() Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("getPost() Error: Connection can't close - "+ex.getMessage());
            }
        }

        return null;
    }

    @Override
    public LinkedList<Post> getAllPosts() {
        Connection connection = DBConnection.getConnection();
        try{
            String query = "SELECT * from post ";
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

            System.out.println("getAllPosts() Error: "+ex.getMessage());
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

    private Post extractPostFromResultSet(ResultSet rs) throws SQLException{

        int postID = rs.getInt(1);
        String postTitle = rs.getString(2);
        String user = rs.getString(3);
        Date date = rs.getDate(4);
        LocalDate date_created = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        date = rs.getDate(5);
        LocalDate date_modified;
        if(date == null)
            date_modified= null;
        else
            date_modified =Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

        String message = rs.getString(6);
        boolean updated =  !(date_modified == null);
        return new Post(postID,postTitle,user, date_created,date_modified,message,updated);


    }

    @Override
    public boolean createPost(Post post) {

        Connection connection = DBConnection.getConnection();
        try{
            String query = "INSERT INTO post (postID, postTitle, username, date_created, message) VALUES(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1,post.getPostID());
            ps.setString(2, post.getPostTitle());
            ps.setString(3, post.getUser());
            ps.setDate(4, Date.valueOf(post.getDate_created()));
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
    public boolean updatePost(Post post) {
        Connection connection = DBConnection.getConnection();

        try{
            String query = "UPDATE post SET postTitle =?, last_modified_date= ?, message = ? WHERE postID =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, post.getPostTitle());
            ps.setDate(2, Date.valueOf(post.getDate_modified()));
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
    public boolean deletePost(int postID) {
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
}
