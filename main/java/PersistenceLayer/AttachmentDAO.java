package PersistenceLayer;

import BusinessLayer.Attachment;

import java.sql.SQLException;

public interface AttachmentDAO {


    byte[] getAttachment(int postID) throws SQLException;
    boolean insertAttachment(Attachment attachment);
    boolean updateAttachment(Attachment attachment);
    boolean deleteAttachment(int postID);
}
