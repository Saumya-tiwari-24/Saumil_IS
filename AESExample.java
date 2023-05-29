import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
public class AESExample {
private static final String AES_ALGORITHM = "AES";
private static final int KEY_LENGTH = 128;
public static String encrypt(String plaintext, String secretKey) throws Exception {
byte[] keyBytes = fixKeyLength(secretKey.getBytes(StandardCharsets.UTF_8));
SecretKeySpec keySpec = new SecretKeySpec(keyBytes, AES_ALGORITHM);
Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
cipher.init(Cipher.ENCRYPT_MODE, keySpec);
byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
return Base64.getEncoder().encodeToString(encryptedBytes);
}
public static String decrypt(String ciphertext, String secretKey) throws Exception {
byte[] keyBytes = fixKeyLength(secretKey.getBytes(StandardCharsets.UTF_8));
SecretKeySpec keySpec = new SecretKeySpec(keyBytes, AES_ALGORITHM);
Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
cipher.init(Cipher.DECRYPT_MODE, keySpec);
byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
byte[] decryptedBytes = cipher.doFinal(decodedBytes);
return new String(decryptedBytes, StandardCharsets.UTF_8);
}
private static byte[] fixKeyLength(byte[] keyBytes) {
int validLength = (KEY_LENGTH / 8);
if (keyBytes.length == validLength) {
return keyBytes;
} else if (keyBytes.length < validLength) {
byte[] newKeyBytes = new byte[validLength];
System.arraycopy(keyBytes, 0, newKeyBytes, 0, keyBytes.length);
return newKeyBytes;
} else {
byte[] newKeyBytes = new byte[validLength];
System.arraycopy(keyBytes, 0, newKeyBytes, 0, validLength);
return newKeyBytes;
}
}
public static void main(String[] args) {
try {
String plaintext = "Hello, world!";
String secretKey = "ThisIsASecretKey123";
String encryptedText = encrypt(plaintext, secretKey);
System.out.println("Encrypted: " + encryptedText);
String decryptedText = decrypt(encryptedText, secretKey);
System.out.println("Decrypted: " + decryptedText);
} catch (Exception e) {
e.printStackTrace();
}
}
}





