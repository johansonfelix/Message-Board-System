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
import com.company.*;

@WebServlet(name = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //This should be a reference to the main object or we should use a static method
        BusinessLayer temp = new BusinessLayer();

        /*
        if (user == null){
            //Error message
        }*/

        //Read input
        //String filePath = "C:\\Users\\Arturo\\Desktop\\RandomImages\\Guti.png";
        //filePath = Post.getAttDestFilePath();
        //File downloadFile = new File(filePath);
        //FileInputStream inStream = new FileInputStream(downloadFile);
        String PostID = request.getParameter("PostID");
        String AttachmentD = request.getParameter("AttachmentID");
        FileInputStream inStream = temp.getStream(PostID);

        //Obtain servlet context
        ServletContext context = getServletContext();

        //Get MIME type
        //String mimeType = context.getMimeType(filePath);
        String mimeType = context.getMimeType(temp.getMIME(PostID));
        if (mimeType == null){
            mimeType = "application/octet-stream";
        }
        System.out.print("MIME type: "+mimeType);

        //Modify response
        response.setContentType(mimeType);

        //Force download
        String headerKey = "Content-Disposition";
        //String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        String headerValue = String.format("attachment; filename=\"%s\"", temp.getN(PostID));
        response.setHeader(headerKey, headerValue);

        //Obtain response outStream
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead  = -1;
        while ((bytesRead = inStream.read(buffer))!= -1){
            outStream.write(buffer, 0, bytesRead);
        }
        inStream.close();
        outStream.close();

        //Avoid caching
        response.setIntHeader("Expires",0);


    }
}
