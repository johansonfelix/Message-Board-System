import BusinessLayer.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedList;

@WebServlet(name = "FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {



    BusinessLayer msgboard = new BusinessLayer();

    int postIDcounter = 100;





    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("ACTION");

        if("POST".equals(action)){
            String title = request.getParameter("title");
            String msg = request.getParameter("msg");
            //String user =  session username

            //insert code to grab attachment, call attachment bos, check if attachment field is empty, if empty then make bos null
            //name of attachment field in home.jsp is 'datafile'
            /*LinkedList<Post> messages = msgboard.createPost(postIDcounter++,title,user,LocalDate.now(),msg,bos);
            request.setAttribute("messages", messages);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            requestDispatcher.forward(request, response);*/

        }
        if("SEARCH".equals(action)){
            //insert code to search and return Linkedlist with search results to front end
        }
        if("UPDATE".equals(action)){
            String title = request.getParameter("title");
            String msg = request.getParameter("update-message");
            int postID = Integer.parseInt(request.getParameter("postID"));
            LocalDate date_created= LocalDate.parse(request.getParameter("date_created"));
            //String user =  session username
            //name of attachment field is 'datafile;
            //insert code to grab attachment, call the attachment bos, check if attachment field is empty, if empty then make bos=null

            //LinkedList<Post> messages = msgboard.updatePost(postId,title,user,date_created,msg,bos);
            //request.setAttribute("messages", messages);
            //RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            //requestDispatcher.forward(request, response);
        }
        if("DELETE".equals(action)){

            int postID = Integer.parseInt(request.getParameter("postID"));
            LinkedList<Post> messages = msgboard.deletePost(postID);
            //request.setAttribute("messages", messages);
            //RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            //requestDispatcher.forward(request, response);
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File(getServletContext().getRealPath("/")+"/nature.jpg");
        byte[] attachment = Files.readAllBytes(Paths.get(file.toURI()));
        Attachment[] a = new Attachment[11];
        a[0] = new Attachment(1, file.getName(),file.length(),"jpg",attachment);
        a[1] = new Attachment(2, file.getName(),file.length(),"jpg",attachment);
        a[2] = new Attachment(3, file.getName(),file.length(),"jpg",attachment);
        a[3] = new Attachment(4, file.getName(),file.length(),"jpg",attachment);
        a[4] = new Attachment(5, file.getName(),file.length(),"jpg",attachment);
        a[5] = new Attachment(6, file.getName(),file.length(),"jpg",attachment);
        a[6] = new Attachment(7, file.getName(),file.length(),"jpg",attachment);
        a[7] = new Attachment(8, file.getName(),file.length(),"jpg",attachment);
        a[8] = new Attachment(9, file.getName(),file.length(),"jpg",attachment);
        a[9] = new Attachment(10, file.getName(),file.length(),"jpg",attachment);
        a[10] = new Attachment(11, file.getName(),file.length(),"jpg",attachment);



        ByteArrayOutputStream[]bos = new ByteArrayOutputStream[11];
        for(int i = 0; i <=10;i++){
            bos[i] = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos[i]);
            oos.writeObject(a[i]);

        }





        try {
            msgboard.createPost(1, "Zoom University", "Johanson", LocalDate.now(), "I still hate online school!", bos[0]);
            msgboard.createPost(2, "Zoom University", "Mary", LocalDate.parse("2020-02-03"), "I still hate online school!", bos[1]);
            msgboard.createPost(3, "Zoom University", "Johanson", LocalDate.parse("2020-04-05"), "I still hate online school!", bos[2]);
            msgboard.createPost(4, "Zoom University", "Jonas", LocalDate.parse("2020-07-30"), "I still hate online school!", bos[3]);
            msgboard.createPost(5, "Zoom University", "Persephone", LocalDate.parse("2020-10-13"), "I still hate online school!", bos[4]);
            msgboard.createPost(6, "Zoom University", "Marcus", LocalDate.parse("2020-08-12"), "I still hate online school!", bos[5]);
            msgboard.createPost(7, "Zoom University", "Marcus", LocalDate.parse("2020-04-05"), "I still hate online school!", bos[6]);
            msgboard.createPost(8, "Zoom University", "Mary", LocalDate.parse("2020-08-05"), "I still hate online school!", bos[7]);
            msgboard.createPost(9, "Zoom University", "John", LocalDate.parse("2020-01-09"), "I still hate online school!", bos[8]);
            msgboard.createPost(10, "Zoom University", "Paul", LocalDate.parse("2020-07-05"), "I still hate online school!", bos[9]);
            msgboard.createPost(11, "Zoom University", "Felicia", LocalDate.parse("2020-04-22"), "I still hate online school!", bos[10]);

            LinkedList<Post> messages = msgboard.viewRecentPosts();

            request.setAttribute("messages", messages);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            requestDispatcher.forward(request, response);
        }
        catch(ClassNotFoundException ex){
            //
        }
    }
}
