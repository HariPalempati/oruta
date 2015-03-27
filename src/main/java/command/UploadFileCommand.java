package command;

import connectionProvider.ConnectionProvider;

import javax.security.cert.X509Certificate;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;


public class UploadFileCommand {

    public boolean execute(File f, String prikey, String pubkey) {
        boolean flag = false;
        try {
            int tmp = 0;
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement filedetailspreparedStatement = connection
                    .prepareStatement("insert into filedetails values((select nvl(max(fileid),100)+1 from filedetails),?,sysdate,?,?,?,'position')");

            filedetailspreparedStatement.setString(1, f.getName().replace(
                    ".des", "").trim());
            filedetailspreparedStatement.setInt(2, (int) f.length());
            filedetailspreparedStatement.setString(3, prikey.toString());
            filedetailspreparedStatement.setString(4, pubkey.toString());

            filedetailspreparedStatement.executeUpdate();

            BufferedInputStream bufferedInputStream = null;
            try {
                bufferedInputStream = new BufferedInputStream(
                        new FileInputStream(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            int i = 0;
            int filelength = (int) f.length();
            byte[] buffer = new byte[1024];

            int j = 0;
            try {
                while ((tmp = bufferedInputStream.read(buffer)) > 0) {
                    j = ++i;
                    PreparedStatement preparedStatement1 = connection
                            .prepareStatement("insert into FILEUPLOADDETAILS values((select nvl(max(UPLOADID),100)+1 from FILEUPLOADDETAILS),(select max(fileid) from filedetails),?,?,?,?,?,?)");
                    PreparedStatement preparedStatement2 = connection
                            .prepareStatement("insert into TPAFILEUPLOADDETAILS values((select nvl(max(UPLOADID),100)+1 from TPAFILEUPLOADDETAILS),(select max(fileid) from filedetails),?,?,?,?,?,?)");
                    preparedStatement1.setInt(1, j);
                    preparedStatement2.setInt(1, j);
                    try {
                        preparedStatement1.setBytes(2, buffer);
                        preparedStatement2.setBytes(2, buffer);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                   // X509v1Certificate certificate = new X509v1Certificate(); //It is showing error. Dependency should be added.
                    //X509Certificate cert = certificate.getCertificate();
                    //String[] s = String.valueOf(cert).split("Signature:");
                    preparedStatement1.setInt(3, tmp);
                    //preparedStatement1.setString(4, s[1].trim());
                    preparedStatement1.setInt(5, new Random().nextInt());
                    preparedStatement1.setInt(6, new Random().nextInt());

                    preparedStatement2.setInt(3, tmp);
                    //preparedStatement2.setString(4, s[1].trim());
                    preparedStatement2.setInt(5, new Random().nextInt());
                    preparedStatement2.setInt(6, new Random().nextInt());

                    preparedStatement1.executeUpdate();
                    preparedStatement2.executeUpdate();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.commit();
            return flag = true;
            // preparedStatement.executeUpdate();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flag = false;
    }
}
