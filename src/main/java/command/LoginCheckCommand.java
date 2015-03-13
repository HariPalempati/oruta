package command;

import connectionProvider.ConnectionProvider;
import model.Users;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCheckCommand {

    /* public Users loginCheck(Users users) {
        return jdbcTemplate.query("select * from userlogin where loginid='"
                + users.getLoginid() + "' and password='" + users.getPassword()
                + "' and logintype='" + users.getLogintype().toLowerCase()
                + "'", new ResultSetExtractor<Users>() {

            public Users extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                Users user = new Users();
                if (rs.next()) {
                    user.setUserid(rs.getInt(1));
                    user.setLoginid(rs.getString(2));
                    user.setRegistereddate(rs.getDate(4).toString());
                    user.setLogintype(rs.getString(6));

                }
                return user;
            }
        });
    }*/

    public Users execute() {
        Users users = new Users();
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement stmt = connection
                    .prepareStatement("select * from userlogin where" +

                            "loginid=" + "'"+ users.getLoginid()  +"'" +
                            "and password=" + "'"+ users.getLoginid() +"'" +
                            "and logintype=" + "'"+ users.getLogintype().toLowerCase() +"'"
                    );

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.setUserid(rs.getInt(1));
                users.setLoginid(rs.getString(2));
                users.setRegistereddate(rs.getDate(4).toString());
                users.setLogintype(rs.getString(6));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
