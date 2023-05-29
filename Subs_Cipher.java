public class Subs_Cipher {
public static void main(String[] args) {
String str = "HelloWorld";
StringBuilder andResult = new StringBuilder();
StringBuilder xorResult = new StringBuilder();
for (char c : str.toCharArray()) {
// Perform AND operation
char andChar = (char) (c & 127);
andResult.append(andChar);
// Perform XOR operation
char xorChar = (char) (c ^ 127);
xorResult.append(xorChar);
}
// Display results
System.out.println("XOR Result: " + xorResult.toString());
System.out.println("Original String: " + str);
System.out.println("AND Result: " + andResult.toString());
}
}