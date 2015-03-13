package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class FileEncryptor {
	protected static final String ALGORITHM = "RSA";
	static PrivateKey privKey;
	static PublicKey pubKey;
	private String algo;
	private File file;

	public FileEncryptor() {
		KeyPairGenerator keyGen;
		KeyPair key = null;
		try {
			keyGen = KeyPairGenerator.getInstance(ALGORITHM);
			SecureRandom random;
			random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(1024, random);
			key = keyGen.generateKeyPair();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		privKey = key.getPrivate();
		pubKey = key.getPublic();
	}

	public String encrypt(PublicKey key, String fname) throws Exception {
		File filename = new File(fname);
		FileInputStream fis = new FileInputStream(filename);
		BufferedInputStream bis = new BufferedInputStream(fis);
		file = new File(filename.getParent() + "\\"
				+ filename.getName().split("\\.")[0] + "enc."
				+ filename.getName().split("\\.")[1]);
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		Cipher encrypt = Cipher.getInstance(ALGORITHM);
		encrypt.init(Cipher.ENCRYPT_MODE, key);
		CipherOutputStream cout = new CipherOutputStream(bos, encrypt);

		byte[] buf = new byte[1024];
		int read;
		while ((read = bis.read(buf)) != -1)
			cout.write(buf, 0, read); // writing encrypted data
		// closing streams
		fis.close();
		cout.flush();
		cout.close();
		return filename.toString();
	}

	public String decrypt(PrivateKey key) throws Exception {

		FileInputStream fis = new FileInputStream(file);
		file = new File(file.getParent() + "\\"
				+ file.getName().split("\\.")[0] + "dec."
				+ file.getName().split("\\.")[1]);
		FileOutputStream fos = new FileOutputStream(file);
		Cipher decrypt = Cipher.getInstance(ALGORITHM);
		decrypt.init(Cipher.DECRYPT_MODE, key);
		CipherInputStream cin = new CipherInputStream(fis, decrypt);

		byte[] buf = new byte[1024];
		int read = 0;
		while ((read = cin.read(buf)) != -1)
			// reading encrypted data
			fos.write(buf, 0, read); // writing decrypted data
		// closing streams
		cin.close();
		fos.flush();
		fos.close();
		return file.toString();
	}

	public static PrivateKey getPrivKey() {
		return privKey;
	}

	public static void setPrivKey(PrivateKey privKey) {
		FileEncryptor.privKey = privKey;
	}

	public static PublicKey getPubKey() {
		return pubKey;
	}

	public static void setPubKey(PublicKey pubKey) {
		FileEncryptor.pubKey = pubKey;
	}

}