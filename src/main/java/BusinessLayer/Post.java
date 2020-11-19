package BusinessLayer;

import java.sql.Timestamp;

public class Post {
/*
DATA MEMBERS
 */
    private int postID;
    private String postTitle;
    private String user;
    private Timestamp date_created;
    private Timestamp date_modified;
    private String message;
    private Attachment attachment;
    boolean updated;

    /*
    CONSTRUCTOR
     */
    public Post(int postID, String postTitle, String user, Timestamp date_created, Timestamp date_modified, String message, Attachment attachment, boolean updated) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.user = user;
        this.date_created = date_created;
        this.date_modified = date_modified;
        this.message = message;
        this.attachment = attachment;
        this.updated = updated;

    }

    /*
    GETTERS AND SETTERS
     */

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

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }

    public Timestamp getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Timestamp date_modified) {
        this.date_modified = date_modified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Attachment getAttachment() {
        return attachment;
    }
    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

/*
    TO STRING METHOD
 */

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", postTitle='" + postTitle + '\'' +
                ", user='" + user + '\'' +
                ", date_created=" + date_created +
                ", date_modified=" + date_modified +
                ", message='" + message + '\'' +
                ", attachment=" + attachment.getOriginal_file_name() +
                ", updated=" + updated +
                '}';
    }
}
