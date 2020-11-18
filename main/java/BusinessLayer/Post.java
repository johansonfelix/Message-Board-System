package BusinessLayer;



import java.time.LocalDate;

public class Post {

    private int postID;
    private String postTitle;
    private String user;
    private LocalDate date_created;
    private LocalDate date_modified;
    private String message;
    boolean updated;

    public Post(int postID, String postTitle, String user, LocalDate date_created, LocalDate date_modified, String message, boolean updated) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.user = user;
        this.date_created = date_created;
        this.date_modified = date_modified;
        this.message = message;
        this.updated = updated;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getDate_created() {
        return date_created;
    }

    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
    }

    public LocalDate getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(LocalDate date_modified) {
        this.date_modified = date_modified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", postTitle='" + postTitle + '\'' +
                ", user='" + user + '\'' +
                ", date_created=" + date_created +
                ", date_modified=" + date_modified +
                ", message='" + message + '\'' +
                ", updated=" + updated +
                '}';
    }
}
