package command;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectionProvider.ConnectionProvider;

import java.sql.Statement;
import java.util.ArrayList;

import model.Upload;

@SuppressWarnings("unused")
public class RetrieveCommand {

	public ArrayList execute(Byte filename) {
		ArrayList ret = new ArrayList();
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT FileName FROM Retrieve WHERE FILENAME=?");
			while (rs.next()) {
				Upload s = new Upload();
				s.setFileName(rs.getByte("FileName"));
				ret.add(s);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
}

//public class RetrieveCommand {
//
//	public InputStream execute(String filename) {
//
//		Connection conn;
//		try {
//			conn = ConnectionProvider.getConnection();
//			PreparedStatement stmt = conn
//					.prepareStatement("SELECT FileName FROM Retrieve WHERE FILENAME=?");
//			stmt.setString(1, filename);
//			ResultSet rs = stmt.executeQuery();
//	        if (rs != null) {
//	            while (rs.next()) {
//	                InputStream is = rs.getBinaryStream("file");
//	                return is;
//	            }
//	            rs.close();
//	        }    
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//}

