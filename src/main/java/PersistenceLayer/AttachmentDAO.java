package PersistenceLayer;

import BusinessLayer.Attachment;

import java.io.IOException;
import java.sql.SQLException;


public interface AttachmentDAO {

    byte[] getAttachment(int postID) throws SQLException, IOException;
    String getAttachmentMIME(int postID) throws SQLException, IOException;
    String getAttachmentName(int postID) throws IOException;
    void insertAttachment(Attachment attachment) throws IOException;
    void updateAttachment(Attachment attachment) throws IOException;
    void deleteAttachment(int postID) throws IOException;
}
