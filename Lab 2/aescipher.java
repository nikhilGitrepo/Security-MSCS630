/**
 * 
 * @author Nikhil
 *
 */
public class aescipher {

 private static String[][] keyHex = new String[4][4];

 private static String[][] W_Matrix = new String[4][44];

 public static void main(String[] args) {
  String input = "5468617473206D79204B756E67204675";
  input = input.toLowerCase();

  generateKeyMatrix(input);
  generateWMatrix();

  System.out.println("done");
 }

 private static String computeXOR(String val1, String val2) {

  String result = "";

  String bin1 = hexToBin(val1);
  String bin2 = hexToBin(val2);

  if ((bin1.length() - bin2.length()) < 0) {
   bin1 = pad(bin1, (bin1.length() - bin2.length()));
  } else if ((bin1.length() - bin2.length()) > 0) {
   bin2 = pad(bin2, (bin1.length() - bin2.length()));
  }

  for (int i = 0; i < bin1.length(); i++) {
   result = result + (bin1.charAt(i) ^ bin2.charAt(i));
  }

  String hexResult = Integer.toHexString(Integer.valueOf(result, 2));
  return hexResult.length() == 4 ? ("0" + hexResult) : hexResult;
  
 }

 private static String pad(String var, int numOfBits) {
  for (int i = 0; i < numOfBits; i++) {
   var = '0' + var;
  }
  return var;
 }

 private static String hexToBin(String hex) {
  int realNum = Integer.parseInt(hex, 16);
  String binary = Integer.toBinaryString(realNum);
  return binary;
 }

 private static void generateWMatrix() {

  for (int i = 0; i < 4; i = i + 1) {
    for (int j = 0; j < 4; j++) {
     W_Matrix[i][j] = keyHex[i][j];
    }
  }
  
  for (int i = 0; i < 4; i = i + 1) {
   for (int j = 4; j < 44; j++) {
    if (j < 4) {
     W_Matrix[i][j] = keyHex[i][j];
    } else {
     if (j % 4 != 0) {
      W_Matrix[i][j] = computeXOR(W_Matrix[i][j - 4], W_Matrix[i][j - 1]);
     } else {

     }
    }
   }
  }
  
 }

 private static void generateKeyMatrix(String input) {

  int row = 0;
  for (int i = 0; i < (input.length() - 1); i = i + 8) {
   int col = 0;
   for (int j = i; j < (i + 8); j = j + 2) {
    keyHex[row][col] = input.substring(j, (j + 2));
    ++col;
   }
   ++row;
  }
 }

}