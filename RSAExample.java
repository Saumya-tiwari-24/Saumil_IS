import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
public class RSAExample {
public static KeyPair generateKeyPair() throws Exception {
SecureRandom secureRandom = new SecureRandom();
KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
keyPairGenerator.initialize(2048, secureRandom);
return keyPairGenerator.generateKeyPair();
}
public static byte[] sign(PrivateKey privateKey, byte[] data) throws Exception {
Signature signature = Signature.getInstance("SHA256withRSA");
signature.initSign(privateKey);
signature.update(data);
return signature.sign();
}
public static boolean verify(PublicKey publicKey, byte[] data, byte[] signatureBytes) throws
Exception {
Signature signature = Signature.getInstance("SHA256withRSA");
signature.initVerify(publicKey);
signature.update(data);
return signature.verify(signatureBytes);
}
public static void main(String[] args) {
try {
// Generate key pair
KeyPair keyPair = generateKeyPair();
PublicKey publicKey = keyPair.getPublic();
PrivateKey privateKey = keyPair.getPrivate();
// Original data
String originalData = "Hello, world!";
byte[] originalDataBytes = originalData.getBytes();
// Sign the data with the private key
byte[] signatureBytes = sign(privateKey, originalDataBytes);
// Verify the signature using the public key
boolean isVerified = verify(publicKey, originalDataBytes, signatureBytes);
System.out.println("Original Data: " + originalData);
System.out.println("Signature Verified: " + isVerified);
} catch (Exception e) {
e.printStackTrace();
}
}
}


