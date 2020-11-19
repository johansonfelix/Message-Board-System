package PersistenceLayer;

import BusinessLayer.Post;

import java.io.IOException;
import java.util.LinkedList;

public interface PostDAO {


    LinkedList<Post> getAllPosts() throws IOException;
    boolean createPost(Post post) throws IOException;
    boolean updatePost(Post post) throws IOException;
    boolean deletePost(int postID) throws IOException;
    Post getPost(int postID);

}
