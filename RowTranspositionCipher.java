import java.util.Scanner;

public class RowTranspositionCipher {
    public static void main(String[] args) {
        System.out.println("The Plaintext: helloworld");
        System.out.println("Key: 3142");

        Scanner sc = new Scanner(System.in);
        String plaintext = "helloworld";
        System.out.println("Plaintext: " + plaintext);
        
        System.out.println("Encryption:");
        String encryptedText = encrypt(plaintext);
        System.out.println("Encrypted Text: " + encryptedText);
        
        System.out.println("Decryption:");
        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
    
    public static String encrypt(String plaintext) {
        String key = "3142"; // Key for row transposition
        
        // Remove spaces and convert to upper case
        plaintext = plaintext.replaceAll("\\s+", "").toUpperCase();
        int keyLength = key.length();
        int textLength = plaintext.length();
        
        // Calculate number of rows needed to fit the plaintext
        int numRows = (int) Math.ceil((double) textLength / keyLength);
        
        // Create a 2D character array to store the plaintext
        char[][] matrix = new char[numRows][keyLength];
        int k = 0;
        
        // Fill the matrix with the plaintext characters
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (k < textLength) {
                    matrix[i][j] = plaintext.charAt(k++);
                } else {
                    matrix[i][j] = 'X'; // Add padding character
                }
            }
        }
        
        // Create a StringBuilder to store the encrypted text
        StringBuilder ciphertext = new StringBuilder();
        
        // Read the matrix column-wise using the key order
        for (int i = 0; i < keyLength; i++) {
            int index = Character.getNumericValue(key.charAt(i)) - 1;
            for (int j = 0; j < numRows; j++) {
                ciphertext.append(matrix[j][index]);
            }
        }
        
        return ciphertext.toString();
    }
    
    public static String decrypt(String ciphertext) {
        String key = "3142"; // Key for row transposition
        
        int keyLength = key.length();
        int textLength = ciphertext.length();
        
        // Calculate number of rows needed to fit the ciphertext
        int numRows = (int) Math.ceil((double) textLength / keyLength);
        
        // Create a 2D character array to store the ciphertext
        char[][] matrix = new char[numRows][keyLength];
        int k = 0;
        
        // Fill the matrix with the ciphertext characters
        for (int i = 0; i < keyLength; i++) {
            int index = Character.getNumericValue(key.charAt(i)) - 1;
            for (int j = 0; j < numRows; j++) {
                matrix[j][index] = ciphertext.charAt(k++);
            }
        }
        
        // Create a StringBuilder to store the decrypted text
        StringBuilder plaintext = new StringBuilder();
        
        // Read the matrix row-wise
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (matrix[i][j] != 'X') {
                    plaintext.append(matrix[i][j]);
                }
            }
        }
        
        return plaintext.toString();
    }
}
