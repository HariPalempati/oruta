package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class EncryptFile1Util {
	static boolean flag = false;
	private static String filename;
	private static String password;
	private static FileInputStream inFile;
	private static FileOutputStream outFile;
	private static FileOutputStream outFile1;

	public static File encryptFile(String to, String attach) {
		File file = null;
		try {
			System.out.println(attach);
			filename = attach;
			System.out.println(filename + "filename");
			String password = to;
			System.out.println("to password  " + password);
			inFile = new FileInputStream(filename);
			file = new File(filename + ".des");
			outFile1 = new FileOutputStream(filename + ".des");
			outFile = new FileOutputStream(file);
			System.out.println(filename.length());
			PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");
			SecretKey passwordKey = keyFactory.generateSecret(keySpec);

			// PBE = hashing + symmetric encryption. A 64 bit random
			// number (the salt) is added to the password and hashed
			// using a Message Digest Algorithm (MD5 in this example.).
			// The number of times the password is hashed is determined
			// by the interation count. Adding a random number and
			// hashing multiple times enlarges the key space.

			byte[] salt = new byte[8];
			Random rnd = new Random();
			rnd.nextBytes(salt);
			int iterations = 100;

			// Create the parameter spec for this salt and interation count

			PBEParameterSpec parameterSpec = new PBEParameterSpec(salt,
					iterations);

			// Create the cipher and initialize it for encryption.

			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.ENCRYPT_MODE, passwordKey, parameterSpec);

			// Need to write the salt to the (encrypted) file. The
			// salt is needed when reconstructing the key for decryption.
			outFile.write(salt);
			outFile1.write(salt);
			// Read the file and encrypt its bytes.

			byte[] input = new byte[64];
			int bytesRead;
			while ((bytesRead = inFile.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if (output != null)
					outFile.write(output);
				outFile1.write(output);

			}

			byte[] output = cipher.doFinal();
			if (output != null)
				outFile.write(output);
			outFile1.write(output);
			inFile.close();
			outFile.flush();
			outFile1.flush();
			if (outFile != null && outFile != null) {
				flag = true;
				outFile.close();
				outFile1.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

}
