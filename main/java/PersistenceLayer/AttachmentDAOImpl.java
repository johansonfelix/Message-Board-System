package PersistenceLayer;

import BusinessLayer.Attachment;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;

public class AttachmentDAOImpl implements AttachmentDAO {
    @Override
    public byte[] getAttachment(int postID) throws SQLException {
        //DB Connection
        Connection connection = DBConnection.getConnection();

        try{
            String query = "SELECT * FROM attachment WHERE postID="+postID;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Attachment attachment = extractAttachmentFromResultSet(rs);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = null;

                try{
                    out = new ObjectOutputStream(bos);
                    out.writeObject(attachment);
                    out.flush();
                    byte[] bytes = bos.toByteArray();
                    return bytes;
                }
                catch (IOException ex){
                    System.out.println("getAttachment() Error: "+ex.getMessage());
                }
                finally {
                    try{
                        bos.close();
                    }
                    catch (IOException ex){
                        System.out.println("getAttachment() Error: "+ex.getMessage());
                    }

                }


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

    private Attachment extractAttachmentFromResultSet(ResultSet rs) throws SQLException {

        int postID = rs.getInt(1);
        String original_file_name = rs.getString(2);
        long file_size = rs.getLong(3);
        String mediatype = rs.getString(4);
        Blob blob = rs.getBlob(5);
        int blobLength = (int) blob.length();
        byte[] blobAsBytes = blob.getBytes(1, blobLength);
        blob.free();

        return new Attachment(postID, original_file_name, file_size, mediatype, blobAsBytes);
    }

    @Override
    public boolean insertAttachment(Attachment attachment) {
        Connection connection = DBConnection.getConnection();
        try{
            String query = "INSERT INTO attachment (postID, original_file_name , file_size , mediatype ,attachment) VALUES(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1,attachment.getPostID());
            ps.setString(2, attachment.getOriginal_file_name());
            ps.setLong(3, attachment.getFile_size());
            ps.setString(4, attachment.getMediaType());
            ps.setBlob(5, new SerialBlob(attachment.getAttachment()));

            int i = ps.executeUpdate();

            connection.close();

            return i != 0;


        }
        catch (SQLException ex){

            System.out.println("insertAttachment() Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("insertAttachment() Error: Connection can't close - "+ex.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean updateAttachment(Attachment attachment) {
        Connection connection = DBConnection.getConnection();

        try{
            String query = "UPDATE attachment SET original_file_name =?,file_size  =?, mediatype=?, attachment  = ? WHERE postID =?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, attachment.getOriginal_file_name());
            ps.setLong(2, attachment.getFile_size());
            ps.setString(3, attachment.getMediaType());
            ps.setBlob(4, new SerialBlob(attachment.getAttachment()));
            ps.setInt(5,attachment.getPostID());

            int i = ps.executeUpdate();

            connection.close();

            return i!=0;


        }
        catch (SQLException ex){

            System.out.println("updateAttachment() Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("updateAttachment() Error: Connection can't close - "+ex.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean deleteAttachment(int postID) {
        Connection connection = DBConnection.getConnection();

        try{
            String query = "DELETE FROM attachment WHERE postID="+postID;
            PreparedStatement ps = connection.prepareStatement(query);

            int i = ps.executeUpdate();

            connection.close();
            return i!=0;
        }
        catch (SQLException ex){

            System.out.println("deleteAttachment() Error: "+ex.getMessage());
        }
        finally{
            try{
                if(connection!=null)
                    connection.close();

            }
            catch (SQLException ex){
                System.out.println("deleteAttachment() Error: Connection can't close - "+ex.getMessage());
            }
        }

        return false;
    }
}
