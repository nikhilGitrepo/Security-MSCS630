package darkwebcorp.security;

/**
 * file: AEScipher.java author: Nikhil Hiremath course: MSCS_630L_231_16S
 * assignment: Lab_2 due date: 15-Mar-2016 version: 1.0
 * 
 * This file contains the declaration of the AES abstract data The Class
 * AEScipher.java is where the Core computational logic for AES encryption is
 * done.
 * 
 */
public class AEScipher {

  private static final String[][] S_BOX = {
      { "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01",
        "67", "2B", "FE", "D7", "AB", "76" },
      { "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4",
          "A2", "AF", "9C", "A4", "72", "C0" },
      { "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5",
            "E5", "F1", "71", "D8", "31", "15" },
      { "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12",
              "80", "E2", "EB", "27", "B2", "75" },
      { "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B",
                "D6", "B3", "29", "E3", "2F", "84" },
      { "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB",
                  "BE", "39", "4A", "4C", "58", "CF" },
      { "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9",
                    "02", "7F", "50", "3C", "9F", "A8" },
      { "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6",
                      "DA", "21", "10", "FF", "F3", "D2" },
      { "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7",
                        "7E", "3D", "64", "5D", "19", "73" },
      { "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE",
                          "B8", "14", "DE", "5E", "0B", "DB" },
      { "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3",
                            "AC", "62", "91", "95", "E4", "79" },
      { "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56",
                              "F4", "EA", "65", "7A", "AE", "08" },
      { "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD",
                                "74", "1F", "4B", "BD", "8B", "8A" },
      { "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35",
                                  "57", "B9", "86", "C1", "1D", "9E" },
      { "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E",
                                    "87", "E9", "CE", "55", "28", "DF" },
      { "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99",
                                      "2D", "0F", "B0", "54", "BB", "16" } };

  private static final String[][] R_CON = {
      { "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B",
        "36", "6C", "D8", "AB", "4D", "9A" },
      { "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3",
          "7D", "FA", "EF", "C5", "91", "39" },
      { "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94",
            "33", "66", "CC", "83", "1D", "3A" },
      { "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20",
              "40", "80", "1B", "36", "6C", "D8" },
      { "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35",
                "6A", "D4", "B3", "7D", "FA", "EF" },
      { "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F",
                  "25", "4A", "94", "33", "66", "CC" },
      { "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04",
                    "08", "10", "20", "40", "80", "1B" },
      { "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63",
                      "C6", "97", "35", "6A", "D4", "B3" },
      { "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD",
                        "61", "C2", "9F", "25", "4A", "94" },
      { "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D",
                          "01", "02", "04", "08", "10", "20" },
      { "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F",
                            "5E", "BC", "63", "C6", "97", "35" },
      { "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72",
                              "E4", "D3", "BD", "61", "C2", "9F" },
      { "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74",
                                "E8", "CB", "8D", "01", "02", "04" },
      { "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB",
                                  "4D", "9A", "2F", "5E", "BC", "63" },
      { "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5",
                                    "91", "39", "72", "E4", "D3", "BD" },
      { "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83",
                                      "1D", "3A", "74", "E8", "CB", "8D" } };

  private static final String[][] INV_S_BOX = {
      { "52", "09", "6A", "D5", "30", "36", "A5", "38", "BF", "40",
        "A3", "9E", "81", "F3", "D7", "FB" },
      { "7C", "E3", "39", "82", "9B", "2F", "FF", "87", "34", "8E",
          "43", "44", "C4", "DE", "E9", "CB" },
      { "54", "7B", "94", "32", "A6", "C2", "23", "3D", "EE", "4C",
            "95", "0B", "42", "FA", "C3", "4E" },
      { "08", "2E", "A1", "66", "28", "D9", "24", "B2", "76", "5B",
              "A2", "49", "6D", "8B", "D1", "25" },
      { "72", "F8", "F6", "64", "86", "68", "98", "16", "D4", "A4",
                "5C", "CC", "5D", "65", "B6", "92" },
      { "6C", "70", "48", "50", "FD", "ED", "B9", "DA", "5E", "15",
                  "46", "57", "A7", "8D", "9D", "84" },
      { "90", "D8", "AB", "00", "8C", "BC", "D3", "0A", "F7", "E4",
                    "58", "05", "B8", "B3", "45", "06" },
      { "D0", "2C", "1E", "8F", "CA", "3F", "0F", "02", "C1", "AF",
                      "BD", "03", "01", "13", "8A", "6B" },
      { "3A", "91", "11", "41", "4F", "67", "DC", "EA", "97", "F2",
                        "CF", "CE", "F0", "B4", "E6", "73" },
      { "96", "AC", "74", "22", "E7", "AD", "35", "85", "E2", "F9",
                          "37", "E8", "1C", "75", "DF", "6E" },
      { "47", "F1", "1A", "71", "1D", "29", "C5", "89", "6F", "B7",
                            "62", "0E", "AA", "18", "BE", "1B" },
      { "FC", "56", "3E", "4B", "C6", "D2", "79", "20", "9A", "DB",
                              "C0", "FE", "78", "CD", "5A", "F4" },
      { "1F", "DD", "A8", "33", "88", "07", "C7", "31", "B1", "12",
                                "10", "59", "27", "80", "EC", "5F" },
      { "60", "51", "7F", "A9", "19", "B5", "4A", "0D", "2D", "E5",
                                  "7A", "9F", "93", "C9", "9C", "EF" },
      { "A0", "E0", "3B", "4D", "AE", "2A", "F5", "B0", "C8", "EB",
                                    "BB", "3C", "83", "53", "99", "61" },
      { "17", "2B", "04", "7E", "BA", "77", "D6", "26", "E1", "69",
                                      "14", "63", "55", "21", "0C", "7D" } };

  private static String[][] W_Matrix = null;

  private static final String[][] FIXED_STATE_MATRIX = { 
      { "02", "03", "01", "01" }, 
      { "01", "02", "03", "01" },
      { "01", "01", "02", "03" }, 
      { "03", "01", "01", "02" } };

  private static final String[][] INVERSE_FIXED_STATE_MATRIX = { 
      { "0E", "0B", "0D", "09" },
      { "09", "0E", "0B", "0D" }, 
      { "0D", "09", "0E", "0B" }, 
      { "0B", "0D", "09", "0E" } };

  private static final String HEX_ONE_B = "1B";

  private static final String HEX_TWO = "02";

  private static final Object HEX_THREE = "03";

  private static final String HEX_ZERO = "00";

  private static final Object HEX_ONE = "01";

  private static final String HEX_NINE = "09";

  private static final Object HEX_B = "0B";

  private static final String HEX_D = "0D";

  private static final Object HEX_E = "0E";

  /**
   * 
   * generateWMatrix
   *
   * Method Information The method 'generateWMatrix' is responsible for
   * generating the W_Matrix using which the round keys are derived. Every 5th
   * column will indicate a 'Round'.
   * 
   * Suppose the iteration is j=4, Current round, i = j/4 if (j mod 4 != 0 )
   * w(j) = w(j - 4) XOR w(j - 1)
   * 
   * if (j mod 4 == 0 ) w(j) = w(j - 4) XOR Wnew Here 'Wnew' is to be computed
   * using history W_Matrix, S_BOX and R_CON values. * Parameters::
   * 
   * Return value: void
   */
  private static void generateWMatrix(String[][] keyHexMatrix,int Nk) {
	  if(Nk == 4){
		  W_Matrix = new String[4][44];
	  }else if(Nk == 6 ){
		  W_Matrix = new String[4][52];
	  }else if(Nk == 8){
		  W_Matrix = new String[4][60];
	  }
	  
    for (int i = 0; i < 4; i = i + 1) {
      for (int j = 0; j < Nk; j++) {
        W_Matrix[i][j] = keyHexMatrix[i][j];
      }
    }

    String[][] w_new = null;
    int columns = 0;
    if(Nk == 4){
    	columns = 44;
    }else if(Nk == 6){
    	columns = 52;
    }else if(Nk == 8){
    	columns = 60;
    } 
    for (int j = Nk; j < columns; j++) {
      if (j % Nk != 0) {
        for (int i = 0; i < 4; i++) {
          W_Matrix[i][j] = computeXOR(W_Matrix[i][j - Nk], W_Matrix[i][j - 1]);
        }
      } else {
        w_new = new String[1][4];

        for (int i = 0; i < 4; i++) {
          w_new[0][i] = W_Matrix[i][j - 1];
        }
        // Left-Shift ; S_BOX ; R_CON are all together done in the below
        // lines of
        // code

        w_new[0][0] = computeXOR(aesRcon(j,Nk), aesSBox(W_Matrix[1][j - 1], false));

        w_new[0][1] = aesSBox(W_Matrix[2][j - 1], false);

        w_new[0][2] = aesSBox(W_Matrix[3][j - 1], false);

        // identify the first row element shifted to the bottom index
        w_new[0][3] = aesSBox(W_Matrix[0][j - 1], false);

        // Compute XOR of Wnew and (j-4) element of W_MATRIX for i-th
        // row
        // w(j) = w(j - 4) XOR Wnew
        // Assign the result to W_MATRIX
        for (int i = 0; i < 4; i++) {
          W_Matrix[i][j] = computeXOR(W_Matrix[i][j - Nk], w_new[0][i]);
        }
      }
    }

  }

  /**
   * 
   * generateKeyMatrix
   *
   * Method Information This method is used by the primary method that takes
   * the input and generates a matrix from the input for manipulation
   * 
   * Parameters:
   * 
   * @param input
   * @return: keyHexMatrix
   * 
   * Return value: The result is saved in 'keyHex' class variable
   */
  private static String[][] generateKeyMatrix(String input) {
	  String[][] keyHexMatrix = null;
	  if(input.length() == 32){
		  keyHexMatrix = new String[4][4];
	  }else if(input.length() == 48 ){
		  keyHexMatrix = new String[4][6];
	  }else if(input.length() == 64 ){
		  keyHexMatrix = new String[4][8];
	  }
    int col = 0;
    for (int i = 0; i < (input.length() - 1); i = i + 8) {
      int row = 0;
      for (int j = i; j < (i + 8); j = j + 2) {
        keyHexMatrix[row][col] = input.substring(j, (j + 2));
        ++row;
      }
      ++col;
    }
    return keyHexMatrix;
  }

  /**
   * computeXOR
   *
   * Method Information
   * 
   * This is an important method that computes XOR of the binary input strings
   * Parameters:
   * 
   * @param val1
   * @param val2
   * @return: result
   * 
   * Return value: Bitwise XORed value of val1 and val2. 0110 1001 --
   *          val1 0100 1011 -- val2 ------------------ 0010 0010 -- result
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

    String hexResult = Integer.toHexString(Integer.valueOf(result, 2)).toUpperCase();
    return hexResult.length() == 1 ? ("0" + hexResult) : hexResult;

  }

  /**
   * 
   * pad()
   *
   * Method Information A helper method to pad an binary input string with
   * zeros as necessary: mentioned as 'numOfBits' Parameters:
   * 
   * @param var
   *            : binary input string
   * @param numOfBits
   * @return: var
   * 
   * Return value: padded string. var = 110 1001 numOfBits = 1;
   * return var = 0110 1001
   * 
   */
  private static String pad(String var, int numOfBits) {
    for (int i = 0; i < numOfBits; i++) {
      var = '0' + var;
    }
    return var;
  }

  /**
   * 
   * hexToBin
   *
   * Method Information:
   * This is a private helper method to convert the HEX
   * String to Binary Equivalent. Integer.toBinaryString(realNum)
   * 
   * Parameters:
   * 
   * @param hex
   * @return: String
   * 
   *   Return value: binary
   * 
   *          hex = 69 binary = 0110 1001
   * 
   */
  private static String hexToBin(String hex) {
    int realNum = Integer.parseInt(hex, 16);
    String binary = Integer.toBinaryString(realNum);
    return binary;
  }

  /**
   * aesSBox
   *
   * Method Information This method will return a the value corresponding to
   * the HEX input from the S_BOX matrix. This introduces linearity in the
   * code
   * 
   * Parameters:
   * 
   * @param inHex
   * @return: S_BOX[][] : String
   * 
   * Return value: 
   * S_BOX[index][index] byte 6E is substituted by
   * entry of S-Box in row 6 and column E, i.e., by 9F
   */
  private static String aesSBox(String inHex, boolean isInverse) {
    if (!isInverse) {
      return S_BOX[Integer.parseInt(inHex.split("")[0], 16)][Integer.parseInt(inHex.split("")[1], 16)];
    } else {
      return INV_S_BOX[Integer.parseInt(inHex.split("")[0], 16)][Integer.parseInt(inHex.split("")[1], 16)];
    }
  }

  /**
   * aesRcon
   *
   * Method Information
   * 
   * This method will return a the value corresponding to the integer input
   * from the R_CON matrix. It is called 'If the colum index j is a multiple
   * of 4'
   * 
   * Parameters:
   * 
   * @param j
   *            : indicates the current Round
   * @return: String
   * 
   *   Return value: R_CON[0][j/4]
   */
  private static String aesRcon(int j,int Nk) {
    return R_CON[0][j / Nk];
  }

  /**
   * 
   * printWMatrix
   *
   * Method Information:
   * This method prints the values stored in the W_MATRIX
   * on console
   * 
   * Parameters:: System.out Print 11 Round keys.
   * 
   * Return value:
   */
  private static void printWMatrix() {
    for (int i = 0; i < 4; i = i + 1) {
      for (int j = 0; j < 44; j++) {
        System.out.print(AEScipher.W_Matrix[i][j] + " | ");
      }
      System.out.println();
      for (int j = 0; j < 44; j++) {
        System.out.print("-----");
      }
      System.out.println();
    }
  }

  /**
   * 
   * generatePlainTextMatrix (String plainText)
   *
   * Method Information Method is used to generate a matrix of plain text
   * hexadecimal input Parameters:
   * 
   * @param plainText
   * @return: outStateHex
   * 
   *   Return value: generates Plain Text Matrix
   */
  private static String[][] generatePlainTextMatrix(String plainText) {
    String[][] plainTextHex = new String[4][4];
    int col = 0;
    for (int i = 0; i < (plainText.length() - 1); i = i + 8) {
      int row = 0;
      for (int j = i; j < (i + 8); j = j + 2) {
        plainTextHex[row][col] = plainText.substring(j, (j + 2));
        ++row;
      }
      ++col;
    }
    return plainTextHex;
  }

  /**
   * 
   * aesStateXOR
   *
   * Method Information
   * 
   * This generates a 4x4 output by taking 2 '4x4' input matrix
   * 
   * Parameters:
   * 
   * @param sHex
   * @param keyHex
   * @return: outStateHex
   * 
   * Return value: 
   * The outStateHex is the result of addition of the
   * round key 'keyHex' with the currentState 'sHex'.
   * 
   */
  private static String[][] aesStateXOR(String[][] sHex, String[][] keyHex) {
    String[][] outStateHex = new String[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        outStateHex[i][j] = computeXOR(sHex[i][j], keyHex[i][j]);
      }
    }

    return outStateHex;
  }

  /**
   * aesNibbleSub
   *
   * Method Information:
   * 
   * Encryption of each value of the state is replaced with
   * the corresponding SBOX value AES S-Box Lookup Table. Substituting every
   * value in the current state matrix with a corresponding value in the
   * S_BOX.
   * 
   * Parameters:
   * 
   * @param: inStateHex
   * @return: outStateHex
   * 
   * Return value: New State output after substitution.
   */
  private static String[][] aesNibbleSub(String[][] inStateHex, boolean isInverse) {
    String[][] outStateHex = new String[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        outStateHex[i][j] = aesSBox(inStateHex[i][j], isInverse);
      }
    }
    return outStateHex;
  }

  /**
   * aesShiftRow
   *
   * Method Information
   * 
   * Parameters:
   * 
   * @param inStateHex
   * @return: outStateHex
   * 
   * Return value: Shift inStateHex rows based on row index as
   * follows inStateHex: 63 EB 9F A0 C0 2F 93 92 AB 30 AF C7 20 CB 2B A2
   * 
   * outStateHex: 63 EB 9F A0 2F 93 92 C0 AF C7 AB 30 A2 20 CB 2B
   * 
   */
  private static String[][] aesShiftRow(String[][] inStateHex) {
    String[][] outStateHex = new String[4][4];
    for (int i = 0; i < 4; i++) {
      outStateHex[i] = cyclicLeftShift(inStateHex[i], i);
    }
    return outStateHex;
  }

  /**
   * aesInverseShiftRow
   *
   * Method Information
   * 
   * Parameters:
   * 
   * @param inStateHex
   * @return: outStateHex
   * 
   * Return value: Shift inStateHex rows based on row index as
   * follows inStateHex: 63 EB 9F A0 C0 2F 93 92 AB 30 AF C7 20 CB 2B A2
   * 
   * << RIGHT SHIFT >>
   * 
   * outStateHex: 63 EB 9F A0 92 C0 2F 93 AF C7 AB 30 CB 2B A2 20
   * 
   */
  private static String[][] aesInverseShiftRow(String[][] inStateHex) {
    String[][] outStateHex = new String[4][4];
    for (int i = 0; i < 4; i++) {
      outStateHex[i] = cyclicRightShift(inStateHex[i], i);
    }
    return outStateHex;
  }

  /**
   * cyclicLeftShift
   *
   * Method Information
   * 
   * Parameters:
   * 
   * @param strings
   * @param i
   * @return: strings
   * 
   *   Return value: strings = C0 2F 93 92
   * 
   *          i=1
   * 
   *          strings = 2F 93 92 C0
   */
  private static String[] cyclicLeftShift(String[] strings, int i) {
    for (int shift = 0; shift < i; shift++) {
      String temp = strings[0];
      strings[0] = strings[1];
      strings[1] = strings[2];
      strings[2] = strings[3];
      strings[3] = temp;
    }
    return strings;
  }

  /**
   * cyclicLeftShift
   *
   * Method Information
   * 
   * Parameters:
   * 
   * @param strings
   * @param i
   * @return: strings
   * 
   *   Return value: strings = C0 2F 93 92
   * 
   *          i=1
   * 
   *          strings = 92 C0 2F 93
   */
  private static String[] cyclicRightShift(String[] strings, int i) {
    for (int shift = 0; shift < i; shift++) {
      String temp = strings[3];
      strings[3] = strings[2];
      strings[2] = strings[1];
      strings[1] = strings[0];
      strings[0] = temp;
    }
    return strings;
  }

  /**
   * oneBitCyclicleft
   *
   * Method Information
   * 
   * Parameters:
   * 
   * @param strings
   * @return: the number on which to compute the factorial
   * 
   *   Return value: the factorial of n, or 1 if n <= 0.
   */
  private static String oneBitLeftshift(String str) {
    String tmp = hexToBin(str);
    tmp = tmp.length() < 8 ? pad(tmp, 8 - tmp.length()) : tmp;
    tmp = tmp.substring(1, tmp.length()) + "0";
    return Integer.toHexString(Integer.valueOf(tmp, 2)).toUpperCase();
  }

  /**
   * aesMixColumn
   *
   * Method Information:
   * Perform following operation: -
   * cryptographicMultiplication( inStateHex[j][k], fixedMatrix[i][j] )
   * 
   * FIXED_STATE_MATRIX is different for Encryption and Decryption algorithms
   * 
   * for Encryption:
   * 
   * FIXED_STATE_MATRIX = { { "02", "03", "01", "01" }, { "01", "02", "03",
   * "01" }, { "01", "01", "02", "03" }, { "03", "01", "01", "02" } };
   *
   * for Decryption:
   * 
   * INVERSE_FIXED_STATE_MATRIX = { { "0E", "0B", "0D", "09" }, { "09", "0E",
   * "0B", "0D" }, { "0D", "09", "0E", "0B" }, { "0B", "0D", "09", "0E" } };
   * 
   * @param inStateHex
   * @param FIXED_STATE_MATRIX
   * @return: String[][]
   * 
   *   Return value: The
   */
  private static String[][] aesMixColumn(String[][] inStateHex, String[][] FIXED_STATE_MATRIX) {
    String[][] resultMartrix = new String[4][4];
    for (int i = 0; i < 4; i++) {
      String hexXORofProduct = HEX_ZERO;
      for (int k = 0; k < 4; k++) {
        for (int j = 0; j < 4; j++) {

          hexXORofProduct = computeXOR(hexXORofProduct,
              FIXED_STATE_MATRIX[i][j].equals(HEX_ONE) == true ? inStateHex[j][k]
                  : cryptographicMultiplication(FIXED_STATE_MATRIX[i][j], inStateHex[j][k]));

          if (j == 3) {
            resultMartrix[i][k] = hexXORofProduct;
            hexXORofProduct = "00";
          }
        }
      }
    }
    return resultMartrix;
  }

  /**
   * 
   * cryptographicMultiplication
   *
   * Method Information
   * 
   * Parameters:
   * 
   * @param fixedMatrixVal
   * @param inStateHexVal
   * @return: intermediateRes
   * 
   *   Return value:
   * 
   * @param fixedMatrixVal
   * @param inStateHexVal
   * @return: intermediateRes
   */
  private static String cryptographicMultiplication(String fixedMatrixVal, String inStateHexVal) {

    String intermediateRes = "";
    String MSB_str;
    MSB_str = inStateHexVal.substring(0, 1);
    if (fixedMatrixVal.equals(HEX_TWO)) {

      if (Integer.parseInt(MSB_str, 16) > 7) {
        // MSB == 1
        intermediateRes = computeShiftXOR(inStateHexVal);

      } else {
        // MSB == 0
        intermediateRes = hexMultiplication(HEX_TWO, inStateHexVal);

      }
    } else if (fixedMatrixVal.equals(HEX_THREE)) {

      if (Integer.parseInt(MSB_str, 16) > 7) {
        // MSB == 1
        intermediateRes = computeXOR(inStateHexVal, computeShiftXOR(inStateHexVal));

      } else {
        // MSB == 0
        intermediateRes = computeXOR(inStateHexVal, hexMultiplication(HEX_TWO, inStateHexVal));

      }
    } else {
      String mulBy02Result = cryptographicMultiplication("02", inStateHexVal);
      String mulBy04Result = cryptographicMultiplication("02", mulBy02Result);
      String mulBy08Result = cryptographicMultiplication("02", mulBy04Result);
      if (fixedMatrixVal.equals(HEX_NINE)) {
        intermediateRes = computeXOR(inStateHexVal, mulBy08Result);
      } else if (fixedMatrixVal.equals(HEX_B)) {
        intermediateRes = computeXOR(mulBy02Result, mulBy08Result);
        intermediateRes = computeXOR(inStateHexVal, intermediateRes);
      } else if (fixedMatrixVal.equals(HEX_D)) {
        intermediateRes = computeXOR(mulBy04Result, mulBy08Result);
        intermediateRes = computeXOR(inStateHexVal, intermediateRes);
      } else if (fixedMatrixVal.equals(HEX_E)) {
        intermediateRes = computeXOR(mulBy04Result, mulBy08Result);
        intermediateRes = computeXOR(mulBy02Result, intermediateRes);
      }
    }
    return intermediateRes;
  }

  /**
   * hexMultiplication
   *
   * Method Information:
   * 
   * Method to implement the Multiplication of two hexadecimal numbers.
   * 
   * Parameters:
   * 
   * @param hexTwo
   * @param string:
   * @return buff
   * 
   *  Return value: Normal hexadecimal. 02 * 40 = 60 buff = 60
   */
  private static String hexMultiplication(String hex1, String hex2) {
    // Use as radix 2 because it's binary
    int lDec1 = Integer.parseInt(hexToBin(hex1), 2);
    int lDec2 = Integer.parseInt(hexToBin(hex2), 2);

    int decMulResult = lDec1 * lDec2;
    String buff = Integer.toHexString(
        Integer.valueOf(
            decToBin(
                Integer.toString(decMulResult)), 2)).toUpperCase();

    return buff.length() < 2 ? pad(buff, 2 - buff.length()) : buff;
  }

  /**
   * computeShiftXOR
   *
   * Method Information:
   *  In the case where MSB is '1', then Left shift
   * 'inStateStr' by 1-bit XOR shifted String with irreducible HEX number "1B"
   * 
   * Parameters:
   * 
   * @param inStateStr
   * @return: (inStateStr << 1) ^ "1B"
   * 
   *   Return value: the factorial of n, or 1 if n <= 0.
   */
  private static String computeShiftXOR(String inStateStr) {
    inStateStr = oneBitLeftshift(inStateStr);
    return computeXOR(inStateStr, HEX_ONE_B);
  }

  /**
   * 
   * decToBin
   *
   * Method Information 
   * convert Decimal value into Binary or bits Parameters:
   * 
   * @param hex
   * @return: binary
   * 
   *   Return value: 8- bit binary output. hex = 04 binary = 00000100
   * 
   */
  private static String decToBin(String hex) {
    int realNum = Integer.parseInt(hex, 10);
    String binary = Integer.toBinaryString(realNum);
    return binary;
  }

  /**
   * 
   * aes
   *
   * Method Information:
   * 
   * AES Encryption
   * 
   * Main method that will call several helper and utility methods to generate
   * the cipher text by taking two inputs,
   * 
   * Plain text : 32 bit hexadecimal input Key : 32 bit hexadecimal key
   * 
   * - The plain text and key are converted into 4x4 matrix - The key is used
   * to generate W_Matrix - Every 4 columns represents a round key - There
   * will be 11 rounds and every round key is fetched by method calling the
   * method fetchRoundKey(round)
   * 
   * - Round 0 - Mix plainText with KeyHex
   * 
   * - Round 1 to 9 - + Nibble Substitution + Shift Rows + Mix Columns + Add
   * Key
   * 
   * - Round 10 - + Nibble Substitution + Shift Rows + Add Key
   * 
   * Parameters:
   * 
   * @param pTextHex
   * @param keyHex
   * @return: buffer
   * 
   *   Return value: 
   *   Cipher Text after completing the whole AES
   *   encryption process.
   */
  public static String aes(String pTextHex, String keyHex) {
	  StringBuilder buffer = new StringBuilder();

    String[][] plainTextHex = new String[4][4];
    // Call the helper method to generate Plain text Matrix
    plainTextHex = generatePlainTextMatrix(pTextHex);

    String[][] keyHexMatrix = new String[4][4];
    // Call the helper method to generate Key Matrix
    keyHexMatrix = generateKeyMatrix(keyHex);

    int Nk = 0;
    int roundMax = 0;
	  if(keyHex.length() == 32){
		  Nk = 4;
		  roundMax = 11;
	  }else if(keyHex.length() == 48 ){
		  Nk = 6;
		  roundMax = 13;		  
	  }else if(keyHex.length() == 64 ){
		  Nk = 8;
		  roundMax = 15;		  
	  }
    
    // Call the helper method to generate W_Matrix
    generateWMatrix(keyHexMatrix,Nk);

    // Round 0 - Mixing the Input with Round Key 0
    // ( PlainTextMatrix ^ Round_Key_0 )
    String[][] outStateHex = new String[4][4];

    outStateHex = aesStateXOR(plainTextHex, generateKeyMatrix(keyHex));

/*    buffer = new StringBuilder();
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        buffer.append(outStateHex[j][i]);
      }
    }
    System.out.println(buffer.toString());*/
    
    // Loop the next three line for 11 times,
    // Implementation of Round_1 to Round_11 - 10 Rounds
    for (int round = 1; round < roundMax; round++) {
      outStateHex = aesNibbleSub(outStateHex, false);
      outStateHex = aesShiftRow(outStateHex);

      // We don't Mix Column in the last round
      if (round != (roundMax-1)) {
        outStateHex = aesMixColumn(outStateHex, FIXED_STATE_MATRIX);
      }
      outStateHex = aesStateXOR(outStateHex, fetchRoundKey(round,4));
      
/*      buffer = new StringBuilder();
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
          buffer.append(outStateHex[j][i]);
        }
      }
      System.out.println(buffer.toString());
     */ 
    }

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        buffer.append(outStateHex[j][i]);
      }
    }
    return buffer.toString();
  }

  /**
   * fetchRoundKey
   *
   * Method Information
   * 
   * Parameters:
   * 
   * @param round
   *            : int
   * @return: roundKey : String[4][4]
   * 
   * Return value: Fetch the round key from W_Matrix
   */
  private static String[][] fetchRoundKey(int round,int col_range) {
    String[][] roundKey = new String[4][col_range];
    int column = 0;
    for (int i = 0; i < 4; i = i + 1) {
      column = 0;
      for (int j = round * col_range; j < ((round * col_range) + col_range); j = j + 1) {
        roundKey[i][column] = W_Matrix[i][j];
        if (column == (col_range-1)) {
          column = 0;
        } else {
          column++;
        }
      }
    }

    return roundKey;
  }

  /**
   * aesDecrypt
   *
   * Method Information
   * 
   * call several utility methods
   * 
   * generatePlainTextMatrix : For key generation
   * 
   * 
   * aesInverseShiftRow aesNibbleSub aesStateXOR
   * 
   * Parameters:
   * 
   * @param cTextHex
   * @param keyHex
   * @return: String
   * 
   * Return value: 
   */
  public static String aesDecrypt(String cTextHex, String keyHex) {
	StringBuilder buffer = new StringBuilder();

    String[][] plainTextHex = new String[4][4];
    // Call the helper method to generate Plain text Matrix
    plainTextHex = generatePlainTextMatrix(cTextHex);

    String[][] keyHexMatrix = new String[4][4];
    // Call the helper method to generate Key Matrix
    keyHexMatrix = generateKeyMatrix(keyHex);

    int Nk = 0;
    int roundMax = 0;
	  if(keyHex.length() == 32){
		  Nk = 4;
		  roundMax = 11;
	  }else if(keyHex.length() == 48 ){
		  Nk = 6;
		  roundMax = 13;		  
	  }else if(keyHex.length() == 64 ){
		  Nk = 8;
		  roundMax = 15;		  
	  }
    
    // Call the helper method to generate W_Matrix
    generateWMatrix(keyHexMatrix,Nk);

    // Round 0 - Mixing the Input with Round Key 0
    // ( PlainTextMatrix ^ Round_Key_0 )
    String[][] outStateHex = new String[4][4];

    outStateHex = aesStateXOR(plainTextHex, fetchRoundKey(roundMax-1,4));
/*    System.out.println("round[ 0].iinput  "+ printState(plainTextHex));
    System.out.println("round[ 0].ik_sch  "+ printState(fetchRoundKey(roundMax-1,4)));*/
    
    // Loop the next three line for 11 times,
    // Implementation of Round_1 to Round_11 -->> for next 10 Rounds
    for (int round = (roundMax-2); round >= 0; round--) {
      /*System.out.println("round[ "+(round-13)+"].istart  "+ printState(outStateHex));*/
      outStateHex = aesInverseShiftRow(outStateHex);
      
/*      System.out.println("round[ "+(round-13)+"].is_row  "+ printState(outStateHex));*/
      
      outStateHex = aesNibbleSub(outStateHex, true);
      
/*      System.out.println("round[ "+(round-13)+"].is_box  "+ printState(outStateHex));
      
      System.out.println( "round[ "+(round-13)+"].ik_sch  "+ printState(fetchRoundKey(round,4)));*/
      outStateHex = aesStateXOR(outStateHex, fetchRoundKey(round,4));
      
      /*System.out.println( "round[ "+(round-13)+"].ik_add  "+ printState(outStateHex));*/
      
      // We don't Mix Column in the last round
      if (round != 0) {
        // The FIXED STATE input is changed for mixed Column
        outStateHex = aesMixColumn(outStateHex, INVERSE_FIXED_STATE_MATRIX);
      }
/*      System.out.println("round[ "+(round-13)+"].istart  "+ printState(outStateHex));
      System.out.println("------------------------");*/
    }

    return printState(outStateHex);
  }
  
  public static String printState(String[][] outStateHex){
	  StringBuilder buffer = new StringBuilder();
	    for (int i = 0; i < 4; i++) {
	        for (int j = 0; j < 4; j++) {
	          buffer.append(outStateHex[j][i]);
	        }
	      }
	    return buffer.toString();
  }

}