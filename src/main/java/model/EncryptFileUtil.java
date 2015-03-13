package model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class EncryptFileUtil  {
	static Cipher ecipher;
	static boolean flag = false;
	public static String asymKeyAlgorithm = "RSA";
	public static String asymAlgorithm = "RSA";
	public static int asymKeyAlgorithmStrength = 1024;
	public static String signatureAlgorithm = "SHA1WithRSAEncryption";

	Base64 b64 = new Base64();
	public static String symKeyAlgorithm = "RIJNDAEL";
	public static String symAlgorithm = "RIJNDAEL";
	// public static String symAlgorithm = "BLOWFISH" ;
	// public static String symAlgorithm = "TWOFISH" ;
	public static int symAlgorithmStrength = 128;
	static String message = null;

	public static String encryptMsg(String msg) {

		try {

			// Encode the string into bytes using utf-8
			byte[] utf8 = msg.getBytes("UTF8");
			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);
			String encr = new sun.misc.BASE64Encoder().encode(enc);

			return encr;

		} catch (javax.crypto.BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} // catch (java.io.IOException e) {
		// }
		return null;
	}

	public static byte[] encrypt(byte[] toEncrypt, SecretKey key)
			throws GeneralSecurityException {

		Cipher cipher = Cipher.getInstance(symAlgorithm);
		System.out.println("got cipher, blocksize = " + cipher.getBlockSize());
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] result = cipher.doFinal(toEncrypt);
		return result;
	}

	public static byte[] encrypt(byte[] toEncrypt, PublicKey key)
			throws GeneralSecurityException {

		Cipher cipher = Cipher.getInstance(asymAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] result = cipher.doFinal(toEncrypt);
		return result;
	}

}
