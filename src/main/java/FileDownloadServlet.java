import BusinessLayer.MessageBoardModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;


@WebServlet("/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("PostServlet");
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MessageBoardModel mbm = MessageBoardModel.MessageBoardModel();
        HttpSession session = request.getSession(false);

        if(session.getAttribute("user")!= null) {
            int PostID = Integer.parseInt(request.getParameter("postID"));
            byte[] attArray = MessageBoardModel.getAttachment(PostID);
            String mimeType = MessageBoardModel.getAttachmentMIME(PostID);
            String fileName = MessageBoardModel.getAttachmentName(PostID);



            //Get
            if(attArray!=null) {


                //Set default mime type
                if (mimeType == null) {
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
                response.setIntHeader("Expires", 0);
            }
            //nothing happens here.
            System.out.println("here");
        }


        //User is null
        else{
            System.out.println("hereee");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request,response);
        }

    }
}
