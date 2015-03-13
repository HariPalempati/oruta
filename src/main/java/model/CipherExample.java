package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class CipherExample {
	CryptMsgUtil decrypt;
	String privatekey;
	String publickey;

	public CipherExample(String filename) {
		this.decrypt = new CryptMsgUtil(filename);
		this.privatekey = decrypt.encrypt(filename);
		this.publickey = decrypt.encrypt(privatekey);
	}

	public static void encrypt(String key, InputStream is, OutputStream os)
			throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void decrypt(String key, InputStream is, OutputStream os)
			throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is,
			OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for
		// SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os)
			throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}

	public String encryptFile(String orgFile, String encFile)
			throws FileNotFoundException {

		FileInputStream fis = new FileInputStream(orgFile);
		FileOutputStream fos = new FileOutputStream(encFile);
		try {
			encrypt(privatekey, fis, fos);
			fos.flush();
			fos.close();
			fis.close();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encFile;
	}

	public String decryptFile(String encFile, String decFile)
			throws FileNotFoundException {
		try {

			FileInputStream fis2 = new FileInputStream(encFile);
			FileOutputStream fos2 = new FileOutputStream(decFile);
			decrypt(decrypt.decrypt(publickey), fis2, fos2);
			fos2.flush();
			fos2.close();
			fis2.close();
		} catch (Throwable e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return decFile;
	}

}
