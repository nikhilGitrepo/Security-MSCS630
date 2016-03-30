import java.util.Scanner;

/**
 * file: Driver.java
 * author: Nikhil Hiremath
 * course: MSCS_630L_231_16S
 * assignment: Lab_2
 * due date: 25-Mar-2016
 * version: 1.0
 * 
 * The Driver class call the utility methods in aescipher.java
 * to generate a Cipher Text.
 * Takes Plain Text and Key as inputs and 
 * returns cipher text in the console.
 * 
 */
public class Driver {

  private static final String PATTERN = "^[a-fA-F0-9]*$";
  
 /**
  * The Main thread is the point of entry to the driver class
  * The input argument will be a key string like 
  * "5468617473206D79204B756E67204675"
  * The output is displayed on console either on Command-line prompt 
  * or IDE console
  * using Standard Output 'System.out'
  * @param args
  */
 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);

  String keyHex = "init";
  String pTextHex = "init";
  
  try {
    keyHex = sc.nextLine();
    pTextHex = sc.nextLine();
    
    String cTextHex = "";
    
    if(testInput(keyHex,pTextHex)){
      pTextHex = pTextHex.toUpperCase();
      cTextHex = AEScipher.aes(pTextHex, keyHex);
      System.out.print(cTextHex.toUpperCase());
    }
    
    sc.close();
    
  } catch (Exception e) {
    if(e.getMessage().contains("No line found")){
      System.out.println(
          "Error in the input file, Missing input.. "
          + "either PlainText, Key or both");
    }
  }
  
 }

/**
 * testInput
 *
 * Method Information
 * 
 * Parameters:
 * @param pTextHex 
 * @param keyHex 
 *   @return: boolean
 * 
 * Return value: 
 *   True/False based on whether the either inputs are valid or Invalid.
 */
private static boolean testInput(String keyHex, String pTextHex) {

  if( ((keyHex.isEmpty()) || (keyHex.length() != 32) 
        || !keyHex.matches(PATTERN))){
        System.out.println("Invalid Key input : " + keyHex);
    return false;
  }else if( ((pTextHex.isEmpty()) || (pTextHex.length() != 32) 
          || !pTextHex.matches(PATTERN)) ){
        System.out.println("Invalid Plain Text input : " + pTextHex);
    return false;
  }
  return true;
}
 
}