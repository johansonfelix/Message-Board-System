import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import BusinessLayer.*;

@WebServlet(name = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageBoardModel mbm = MessageBoardModel.MessageBoardModel();
        HttpSession session = request.getSession(false);
        if(session.getAttribute("user")!= null) {
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
        //User is null
        else{
            PrintWriter out = response.getWriter();
            out.println("<html><head>");
            out.println("<script type=\"text/javascript\">");
            out.println("alert(\"Error! You have to log in first\");</script>");
            out.println("</head><body></body></html>");
        }



    }
}
