package command;

import connectionProvider.ConnectionProvider;
import model.Login;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCommand {

    public Login execute() {
        Login l = new Login();
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement stmt = connection
                    .prepareStatement("select * from Register where Username =  ? and Password = ? and Usertype = ?");
            
            		stmt.setString(1, l.getUsername());
            		stmt.setString(2, l.getPassword());
            		stmt.setString(3, l.getUsertype());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //l.setUser(rs.getInt(1));
                l.setUsername(rs.getString(2));
                //l.setRegistereddate(rs.getDate(4).toString());
                l.setUsertype(rs.getString(6));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

}

