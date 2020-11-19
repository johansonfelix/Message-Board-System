package PersistenceLayer;

import BusinessLayer.Attachment;

import java.io.IOException;
import java.sql.SQLException;


public interface AttachmentDAO {

    byte[] getAttachment(int postID) throws SQLException, IOException;
    String getAttachmentMIME(int postID) throws SQLException, IOException;
    boolean insertAttachment(Attachment attachment) throws IOException;
    boolean updateAttachment(Attachment attachment) throws IOException;
    boolean deleteAttachment(int postID) throws IOException;
}
