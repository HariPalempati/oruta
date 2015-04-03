package command;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import connectionprovider.ConnectionProvider;

public class UploadCommand {

	public void execute(String fileName, InputStream fis, long l) {

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			IOUtils.copy(fis, output);
			byte[] filecontent = output.toByteArray();
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement stmt = connection
					.prepareStatement("INSERT INTO Upload VALUES (?, ?)");
			stmt.setString(1, Username);
			stmt.setBytes(2, FileName);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}