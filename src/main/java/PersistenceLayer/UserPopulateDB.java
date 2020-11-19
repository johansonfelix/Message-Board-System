package PersistenceLayer;

import BusinessLayer.MessageBoardModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.jws.soap.SOAPBinding;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;

public class UserPopulateDB {

    private static UserPopulateDB single_instance = null;

    private UserPopulateDB() {
        populateDB();
    }

    /*
Method to create instance of UserPopulateDB once
 */
    public static UserPopulateDB UserPopulateDB() {
        if (single_instance == null) {
            single_instance = new UserPopulateDB();
        }
        return single_instance;
    }


/*
Method to populate users in DB from users.xml file
 */
    private static void populateDB(){
        try {

            Connection con = DBConnection.getConnection();
            Statement st=con.createStatement();
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            Document document = docBuilder.parse (UserPopulateDB.class.getResource("../configurations/users.xml").getPath());
            document.getDocumentElement().normalize();
            System.out.println ("Initial element within document is " + document.getDocumentElement().getNodeName());
            NodeList listOfUsers = document.getElementsByTagName("user");
            for(int s=0; s<listOfUsers.getLength(); s++){
                Node firstUserNode = listOfUsers.item(s);

                if(firstUserNode.getNodeType() == Node.ELEMENT_NODE){
                    Element firstUserElement = (Element)firstUserNode;
                    NodeList usernameList = firstUserElement.getElementsByTagName("username");
                    Element usernameElement =(Element)usernameList.item(0);

                    NodeList textFNList = usernameElement.getChildNodes();
                    String username=((Node)textFNList.item(0)).getNodeValue().trim();

                    NodeList emailList = firstUserElement.getElementsByTagName("email");
                    Element emailElement =(Element)emailList.item(0);

                    NodeList textLNList = emailElement.getChildNodes();
                    String email= ((Node)textLNList.item(0)).getNodeValue().trim();

                    NodeList passwordList = firstUserElement.getElementsByTagName("password");
                    Element passwordElement =(Element)passwordList.item(0);

                    NodeList textPNList = passwordElement.getChildNodes();
                    String password= ((Node)textPNList.item(0)).getNodeValue().trim();

                    int i=st.executeUpdate("insert into users(username,email,password) values('"+username+"','"+email+"','"+password+"')");
                }
            }
            System.out.println("Data is successfully inserted!");
        }catch (Exception err) {
            System.out.println("User Data can't be loaded into DB: " + err.getMessage ());
        }
    }
}
