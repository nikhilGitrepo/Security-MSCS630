/**
 * The Class aescipher.java is where the 
 * Core computational logic for AES encryption is done in this class.
 * 
 * @author Nikhil Hiremath
 *
 */
public class aescipher {

 private static final String[][] S_BOX = {
   { "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76" },
   { "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0" },
   { "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15" },
   { "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75" },
   { "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84" },
   { "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF" },
   { "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8" },
   { "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2" },
   { "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73" },
   { "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB" },
   { "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79" },
   { "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08" },
   { "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A" },
   { "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E" },
   { "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF" },
   { "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16" } };

 private static final String[][] R_CON = {
   { "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A" },
   { "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39" },
   { "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A" },
   { "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C", "D8" },
   { "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF" },
   { "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC" },
   { "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B" },
   { "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3" },
   { "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94" },
   { "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20" },
   { "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35" },
   { "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F" },
   { "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04" },
   { "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63" },
   { "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD" },
   { "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D" } };

 private static String[][] keyHex = new String[4][4];

 public static String[][] W_Matrix = new String[4][44];

 /**
  * The method 'generateWMatrix' is responsible for generating the W_Matrix
  * using which the round keys are derived. Every 5th column will indicate a
  * 'Round'.
  * 
  * Suppose the iteration is j=4, Current round, i = j/4 
  * if (j mod 4 != 0 )
  *  w(j) = w(j - 4) XOR w(j - 1) 
  *  
  * if (j mod 4 == 0 ) 
  *  w(j) = w(j - 4) XOR Wnew
  * Here 'Wnew' is to be computed using history W_Matrix, S_BOX and R_CON
  * values.
  * 
  * 
  */
 public static void generateWMatrix() {

  for (int i = 0; i < 4; i = i + 1) {
   for (int j = 0; j < 4; j++) {
    W_Matrix[i][j] = keyHex[i][j];
   }
  }

  String[][] w_new = null;
  for (int j = 4; j < 44; j++) {
   if (j % 4 != 0) {
    for (int i = 0; i < 4; i++) {
     W_Matrix[i][j] = computeXOR(W_Matrix[i][j - 4], W_Matrix[i][j - 1]);
    }
   } else {
    w_new = new String[1][4];

    for(int i=0;i<4;i++){
     w_new[0][i] = W_Matrix[i][j - 1];
    }
    // Left-Shift ; S_BOX ; R_CON are all together done in the below lines of code

    w_new[0][0] = computeXOR(aesRcon(j), aesSBox(W_Matrix[1][j - 1]));

    w_new[0][1] = aesSBox(W_Matrix[2][j - 1]);

    w_new[0][2] = aesSBox(W_Matrix[3][j - 1]);

    //identify the first row element shifted to the bottom index
    w_new[0][3] = aesSBox(W_Matrix[0][j - 1]);

    //Compute XOR of Wnew and (j-4) element of W_MATRIX for i-th row
    // w(j) = w(j - 4) XOR Wnew
    //Assign the result to W_MATRIX
    for (int i = 0; i < 4; i++) {
     W_Matrix[i][j] = computeXOR(W_Matrix[i][j - 4], w_new[0][i]);
    }
   }
  }

 }

 /**
  * This method is used by the primary method that takes the input and
  * generates a matrix from the input for manipulation
  * 
  * @param input
  *            The result is saved in 'keyHex' class variable
  */
 public static void generateKeyMatrix(String input) {

  int col = 0;
  for (int i = 0; i < (input.length() - 1); i = i + 8) {
   int row = 0;
   for (int j = i; j < (i + 8); j = j + 2) {
    keyHex[row][col] = input.substring(j, (j + 2));
    ++row;
   }
   ++col;
  }
 }

 /**
  * This is an important method that computes XOR of the binary input strings
  * 
  * @param val1
  * @param val2
  * @return
  */
 private static String computeXOR(String val1, String val2) {

  String result = "";

  String bin1 = hexToBin(val1);
  String bin2 = hexToBin(val2);

  if ((bin1.length() - bin2.length()) < 0) {
   bin1 = pad(bin1, (bin2.length() - bin1.length()));
  } else if ((bin1.length() - bin2.length()) > 0) {
   bin2 = pad(bin2, (bin1.length() - bin2.length()));
  }

  for (int i = 0; i < bin1.length(); i++) {
   result = result + (bin1.charAt(i) ^ bin2.charAt(i));
  }

  String hexResult = Integer.toHexString(Integer.valueOf(result, 2));
  return hexResult.length() == 1 ? ("0" + hexResult) : hexResult;

 }

 /**
  * A helper method to pad an binary input string with zeros as necessary:
  * mentioned as 'numOfBits'
  * 
  * @param var
  *            : binary input string
  * @param numOfBits
  * @return padded string
  */
 private static String pad(String var, int numOfBits) {
  for (int i = 0; i < numOfBits; i++) {
   var = '0' + var;
  }
  return var;
 }

 /**
  * This is a private helper method to convert the HEX String to Binary
  * Equivalent
  * 
  * @param hex
  *            string
  * @return Integer.toBinaryString(realNum)
  */
 private static String hexToBin(String hex) {
  int realNum = Integer.parseInt(hex, 16);
  String binary = Integer.toBinaryString(realNum);
  return binary;
 }

 /**
  * This method will return a the value corresponding to the HEX input from
  * the S_BOX matrix. This introduces linearity in the code
  * 
  * @param inHex
  * @return S_BOX[][]
  */
 private static String aesSBox(String inHex) {
  return S_BOX[Integer.parseInt(inHex.split("")[0], 16)][Integer.parseInt(inHex.split("")[1], 16)];
 }

 /**
  * This method will return a the value corresponding to the integer input
  * from the R_CON matrix. It is called 'If the colum index j is a multiple
  * of 4'
  * 
  * @param j
  *            : indicates the current Round
  * @return R_CON[0][j/4]
  */
 private static String aesRcon(int j) {
  return R_CON[0][j / 4];
 }

 /**
  * This method prints the values stored in the
  * W_MATRIX on console
  */
 public static void printWMatrix() {
  for (int i = 0; i < 4; i = i + 1) {
   for (int j = 0; j < 44; j++) {
    System.out.print(aescipher.W_Matrix[i][j] + " | ");
   }
   System.out.println();
   for (int j = 0; j < 44; j++) {
    System.out.print("-----");
   }
   System.out.println();
  }
 }

 /**
  * This method prints the Round Keys
  * from W_MATRIX in the form of 11 Round Key sets
  */
 public static String aesRoundKeys(String input) {
 
 //Call the helper method to generate Key Matrix
 generateKeyMatrix(input);

 //Call the helper method to generate W_Matrix
 generateWMatrix();

 //Append the final Output to StringBuffer
 StringBuffer result = new StringBuffer();
  for (int j = 0; j < 44; j = j + 1) {
   for (int i = 0; i < 4; i= i+1) {
   result.append(aescipher.W_Matrix[i][j]);
   }
   if(j>2 && (j+1)%4 == 0){
   result.append("\n");
   }
  }
  
  return result.toString();

 }

}