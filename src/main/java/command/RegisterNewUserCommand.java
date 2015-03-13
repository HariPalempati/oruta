package command;

import connectionProvider.ConnectionProvider;
import model.Users;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterNewUserCommand {

    public int execute(Users users) {

        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement stmt = connection
                    .prepareStatement("insert into USERLOGIN values((select nvl(max(userid),100)+1 from USERLOGIN),?,?,sysdate,'active','user')");

            stmt.setString(1, users.getLoginid());
            stmt.setString(2, users.getPassword());

            return stmt.executeUpdate();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
