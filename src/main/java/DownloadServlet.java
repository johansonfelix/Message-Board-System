import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import BusinessLayer.*;

@WebServlet(name = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageBoardModel mbm = MessageBoardModel.MessageBoardModel();

        /*
        if (user == null){
            //Error message
        }*/
        int PostID = Integer.parseInt(request.getParameter("PostID"));
        String AttachmentD = request.getParameter("AttachmentID");
        byte[] attArray = new byte[0];
        String mimeType = null;
        String fileName = null;
        //Get
        try {
            attArray = mbm.getAttachment(PostID);
            mimeType = mbm.getAttachmentMIME(PostID);
            fileName = mbm.getAttachmentName(PostID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Set default mime type
        if (mimeType == null){
            mimeType = "application/octet-stream";
        }

        //Modify response
        response.setContentType(mimeType);

        //Force download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", fileName);
        response.setHeader(headerKey, headerValue);

        //Obtain response outStream
        OutputStream outStream = response.getOutputStream();
        outStream.write(attArray);
        outStream.close();

        //Avoid caching
        response.setIntHeader("Expires",0);


    }
}
