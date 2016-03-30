import java.util.Scanner;

/**
 * The Driver class call the utility methods in aescipher.java
 * to generate 11 Round keys for the application
 * @author Nikhil Hiremath
 *
 */
public class driver {

 /**
  * The Main thread is the point of entry to the driver class
  * The input argument will be a key string like "5468617473206D79204B756E67204675"
  * The output is displayed on console either on Command-line prompt or IDE console
  * using Standard Output 'System.out'
  * @param args
  */
 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  String input = sc.nextLine();
  input = input.toUpperCase();

  String roundKeysHex = aescipher.aesRoundKeys(input);
  System.out.println(roundKeysHex);
 }
 
}
