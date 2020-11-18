package PersistenceLayer;

import BusinessLayer.Post;

import java.sql.SQLException;
import java.util.LinkedList;

public interface PostDAO {

    Post getPost(int postID) throws SQLException;
    LinkedList<Post> getAllPosts();
    boolean createPost(Post post);
    boolean updatePost(Post post);
    boolean deletePost(int postID);

}
