package PersistenceLayer;

import BusinessLayer.User;

import java.io.IOException;
import java.sql.*;


public class ProcessLogin {



    public static User LoginProcess(String email , String password ) throws IOException {
        User user = null;
        Connection connection = DBConnection.getConnection();
        try {
            String sql = "SELECT * FROM users WHERE email = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();



            if (result.next()) {
                user = new User();
                user.setUsername(result.getString("username"));
                user.setEmail(email);
            }
            return user;

        } catch (SQLException ex) {
            System.out.println("Login Error: SQL Error: " + ex.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();

            } catch (SQLException ex) {
                System.out.println("Login Error: SQL Error: \"+ex.getMessage())");

            }
        }
        return user;

    }



}