import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MD5Example {
public static String calculateMD5(String text) throws NoSuchAlgorithmException {
// Create an instance of MessageDigest with MD5 algorithm
MessageDigest md = MessageDigest.getInstance("MD5");
// Convert the text to bytes and update the digest
md.update(text.getBytes());
// Get the digest bytes
byte[] digest = md.digest();
// Convert the digest bytes to a hexadecimal string representation
StringBuilder hexString = new StringBuilder();
for (byte b : digest) {
String hex = Integer.toHexString(0xFF & b);
if (hex.length() == 1) {
// Pad single digit hex values with leading zero
hexString.append('0');
}
hexString.append(hex);
}
return hexString.toString();
}
public static void main(String[] args) {
try {
String text = "Hello, world!";
String md5Digest = calculateMD5(text);
System.out.println("Text: " + text);
System.out.println("MD5 Digest: " + md5Digest);
} catch (NoSuchAlgorithmException e) {
e.printStackTrace();
}
}
}
