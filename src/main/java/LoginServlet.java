import BusinessLayer.MessageBoardModel;
import BusinessLayer.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        MessageBoardModel.MessageBoardModel();
        User user = MessageBoardModel.LoginProcess(email, password);
        String nextPage;

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getUsername());
            session.setMaxInactiveInterval(120);
            nextPage = "PostsServlet";
            System.out.println("authentication successful");
            response.sendRedirect(nextPage);

        } else {
            String message = "Invalid email/password";
            request.setAttribute("message", message);
            nextPage = "login.jsp";
            System.out.println("authentication fail");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
            requestDispatcher.forward(request, response);
        }




    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session=request.getSession(false);
        if(session.getAttribute("user")!= null) {
            response.sendRedirect("PostsServlet");
        }
        else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
