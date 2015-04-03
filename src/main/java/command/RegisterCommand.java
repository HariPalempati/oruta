package command;

import connectionProvider.ConnectionProvider;
import model.Register;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RegisterCommand {

	public String execute(Register u) {

		try {
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO Register(FirstName, LastName, EmailId, Location, Username, Password, ConfirmPassword, Usertype) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setString(3, u.getEmailId());
			stmt.setString(4, u.getLocation());
			stmt.setString(5, u.getUsername());
			stmt.setString(6, u.getPassword());
			stmt.setString(7, u.getConfirmPassword());
			stmt.setString(8, u.getUsertype());
			@SuppressWarnings("unused")
			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				return rs.getString("Usertype");
//			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-1";
	}

}
