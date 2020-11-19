import BusinessLayer.*;
import org.apache.commons.io.IOUtils;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;

@WebServlet(name = "PostsServlet")
@MultipartConfig
public class PostsServlet extends HttpServlet {




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


       HttpSession session = request.getSession(false);
        if(session.getAttribute("user")!= null) {
            String action = request.getParameter("ACTION");

            if ("POST".equals(action)) {
                String title = request.getParameter("title");
                String msg = request.getParameter("message");

                String user = (String) session.getAttribute("user");
                Attachment attachment = null;
                Part filePart = request.getPart("file");

                if(filePart.getSize()>0) {
                    InputStream fileContent = filePart.getInputStream();
                    byte[] bytes = IOUtils.toByteArray(fileContent);
                    attachment = new Attachment(filePart.getSubmittedFileName(),filePart.getSize(),filePart.getContentType(),bytes);
                }

                MessageBoardModel.createPost(title, user, Timestamp.from(Instant.now()),msg, attachment);
                request.setAttribute("messages", MessageBoardModel.viewRecentPosts());

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                requestDispatcher.forward(request, response);




            }
            if ("SEARCH".equals(action)) {
                //insert code to search and return Linkedlist with search results to front end
            }
            if ("UPDATE".equals(action)) {

                String title = request.getParameter("updated-title");
                String msg = request.getParameter("updated-message");
                int postID = Integer.parseInt(request.getParameter("postID"));
                Timestamp date_created = Timestamp.valueOf(request.getParameter("date_created"));
                String user = (String) session.getAttribute("user");
                Attachment attachment = null;
                Part filePart = request.getPart("updated-file");
                if(filePart.getSize()>0) {
                    InputStream fileContent = filePart.getInputStream();
                    byte[] bytes = IOUtils.toByteArray(fileContent);
                    attachment = new Attachment(postID,filePart.getSubmittedFileName(),filePart.getSize(),filePart.getContentType(),bytes);
                }

                MessageBoardModel.updatePost(postID,title, user, date_created,msg, attachment);
                request.setAttribute("messages", MessageBoardModel.viewRecentPosts());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                requestDispatcher.forward(request, response);
            }
            if ("DELETE".equals(action)) {

                int postID = Integer.parseInt(request.getParameter("postID"));
                MessageBoardModel.deletePost(postID);
                request.setAttribute("messages", MessageBoardModel.viewRecentPosts());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                requestDispatcher.forward(request, response);
            }


        }
        else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession(false);
        if(session.getAttribute("user")!= null) {
            request.setAttribute("messages", MessageBoardModel.viewRecentPosts());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            requestDispatcher.forward(request, response);
        }
        else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request,response);
        }

    }
}
