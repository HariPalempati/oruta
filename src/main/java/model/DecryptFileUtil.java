package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class DecryptFileUtil {

	private static String filename;
	private static String password;
	private static FileInputStream inFile;
	private static FileOutputStream outFile;
	String token = null;
	File file1 = null;

	public File fileDecrypt(File file, String string, String string1) {
		String fname = string;
		try {
			String password = string1;
			System.out.println("password is------->" + password);
			inFile = new FileInputStream(file);
			StringTokenizer st = new StringTokenizer(fname, ".");
			System.out.println(file);
			while (st.hasMoreElements()) {
				token = st.nextToken();
			}
			System.out.println("Token = " + token);
			if (token.equalsIgnoreCase("txt")) {
				System.out.println(file.getName());
				file1 = new File(file.getName() + ".txt");
			} else if (token.equalsIgnoreCase("jpg")) {
				file1 = new File(file.getName() + ".jpeg");
				System.out.println("in else if block" + file1.getName());
			} else if (token.equalsIgnoreCase("doc")) {
				file1 = new File(file.getName() + ".doc");
			} else if (token.equalsIgnoreCase("docx")) {
				file1 = new File(file.getName() + ".docx");
			} else if (token.equalsIgnoreCase("pdf")) {
				file1 = new File(file.getName() + ".pdf");
			} else if (token.equalsIgnoreCase("png")) {
				file1 = new File(file.getName() + ".png");
			} else if (token.equalsIgnoreCase("jpeg")) {
				file1 = new File(file.getName() + ".jpg");
			} else if (token.equalsIgnoreCase("html")) {
				file1 = new File(file.getName() + ".html");
			} else if (token.equalsIgnoreCase("htm")) {
				file1 = new File(file.getName() + ".htm");
			} else {
				System.out.println("attachment cannot be opened");
			}
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");
			SecretKey passwordKey = keyFactory.generateSecret(keySpec);
			byte[] salt = new byte[8];
			inFile.read(salt);
			int iterations = 100;
			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt,
					iterations);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, passwordKey, parameterSpec);
			outFile = new FileOutputStream(file1);
			byte[] input = new byte[64];
			int bytesRead;
			while ((bytesRead = inFile.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				System.out.println(output.length);
				if (output != null)
					outFile.write(output);
			}
			byte[] output = cipher.doFinal();
			if (output != null)
				outFile.write(output);
			outFile.flush();
			inFile.close();
			outFile.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return file1;

	}

}
