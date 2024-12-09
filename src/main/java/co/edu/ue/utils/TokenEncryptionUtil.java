package co.edu.ue.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class TokenEncryptionUtil {

	private static final String ENCRYPTION_ALGORITHM = "AES";
	private static final int KEY_SIZE = 192;
	private static final byte[] SECRET_KEY = generateKeyBytes();

	private static SecretKey generateSecretKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
		keyGenerator.init(KEY_SIZE);
		return keyGenerator.generateKey();
	}

	private static SecretKey getSecretKey(byte[] keyBytes) {
		return new SecretKeySpec(keyBytes, ENCRYPTION_ALGORITHM);
	}

	private static byte[] generateKeyBytes() {
        try {
            SecretKey secretKey = generateSecretKey();
            return secretKey.getEncoded();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar la clave secreta", e);
		}
	}

	public static String encrypt(String data) throws Exception {
        SecretKey secretKey = getSecretKey(SECRET_KEY);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

	public static String decrypt(String encryptedData) throws Exception {
		SecretKey secretKey = getSecretKey(SECRET_KEY);
		Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decodedData = Base64.getDecoder().decode(encryptedData);
		return new String(cipher.doFinal(decodedData));
	}
}
