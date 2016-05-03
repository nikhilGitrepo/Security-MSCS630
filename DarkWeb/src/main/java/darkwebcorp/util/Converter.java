package darkwebcorp.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * file: Converter.java
 * author: Nikhil
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: 02-May-2016
 * version: 1.0
 * 
 * Util class that contains helper methods
 *   - all the method are static
 *  - static because the code is generic, call when required and release
 *  - So we need only one instance of these methods running in JVM
 *  - Contains generic util methods required by DarkWeb and AES
 */
public class Converter {
  
  /**
   * 
   * convertToAscii
   *
   * Method Information
   * Convert to input ASCII array from regular text equivalent .
   * 
   * Parameters:
   *   @param message
   *   @return: String
   * 
   * Return value: ASCII .
   */
  public static String convertToAscii(String message){
    StringBuilder builder = new StringBuilder();
    
    for (int j = 0; j < message.length(); j++) {
      int val = (int) message.charAt(j);
      builder.append(val);
    }
    
    return builder.toString();
  }
  
  /**
   * 
   * convertFromAscii
   *
   * Method Information
   * Convert to regular text equivalent from input ASCII array.
   * 
   * Parameters:
   *   @param asciiArray
   *   @return: String
   * 
   * Return value: regular string.
   */
  public static String convertFromAscii(String[] asciiArray){
    String normalText = "";
    
    for (int i = 0; i < asciiArray.length; i++) {
      normalText += (char) Integer.parseInt(asciiArray[i]);
    }
    
    return normalText;    
  }
  
  /**
   * 
   * byteArrayToString
   *
   * Method Information
   * Convert the serialized inputs that is byte array into String for 
   * further manipulation with the String.
   * 
   * Parameters:
   *   @param message
   *   @return: String
   * 
   * Return value: regular string.
   */
  public static String byteArrayToString(byte[] message){
    
    StringBuilder result = new StringBuilder();
      for(int i = 0; i < message.length; i++)
      {
        result.append( (char)message[i] );
      }
    return result.toString();
  }

  /**
   * 
   * StringToByteArray
   *
   * Method Information
   *  get the message in the form of byte array
   * Parameters:
   *   @param message
   *   @return: byte[]
   * 
   * Return value: byte array equivalent to input message.
   */
  public static byte[] StringToByteArray(String message){
    byte[] result = new byte[message.length()];
    
    if(null != message && !message.isEmpty())
      result = message.getBytes();
      
    return result;
  }
  
  /**
   * 
   * stripMessage
   *
   * Method Information
   *  Uses google gson library to convert into json format message in json
   *  object and fetch required message from payload.
   *  
   * Parameters:
   *   @param message
   *   @return: String
   * 
   * Return value: retrieve message from complete payload.
   */
  public static String stripMessage(String message){

    JsonObject obj = new JsonParser().parse(message).getAsJsonObject();
    return obj.getAsJsonObject().get("message").getAsString();
    
  }

  /**
   * 
   * attachStrippedContent
   *
   * Method Information
   * 
   * Parameters:
   *   @param pText
   *   @param payload
   *   @return: String
   * 
   * Return value: complete payload.
   */
  public static String attachStrippedContent(String pText, String payload) {
    JsonObject obj = new JsonParser().parse(payload).getAsJsonObject();
    obj.getAsJsonObject().remove("message");
    obj.getAsJsonObject().addProperty("message", pText);
    return obj.getAsJsonObject().toString();
  }

  /**
   * 
   * convertToHex
   *
   * Method Information
   *  Regular text to HEX called before input AES encryption and decryption
   *  method implementation
   *  
   * Parameters:
   *   @param message
   *   @return: String
   * 
   * Return value: Regular text to HEX.
   */
  public static String convertToHex(String message) {
    
    StringBuilder result = new StringBuilder();
    char[] res = Hex.encodeHex(message.getBytes());
    result.append(res);
    return result.toString();
  }
  
  /**
   * 
   * convertHexToText
   *
   * Method Information
   *  convert a HEX string to regular text using decodeHEX implementation
   *  provided by apache binary codec
   *  
   * Parameters:
   *   @param strHex
   *   @return: String
   * 
   * Return value: regular text.
   */
  public static String convertHexToText(String strHex) {
    
    byte[] bytes = null;
    String result = "";

    try {
    
      bytes = Hex.decodeHex(strHex.toCharArray());
      result = new String(bytes, "UTF-8");

    } catch (DecoderException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return result;
    
  }

  /**
   * 
   * convertByteArrayToHex
   *
   * Method Information
   * Converts the message byte array using the encodeHEX implementation
   * provided by apache binary codec
   * 
   * Parameters:
   *   @param messageArr
   *   @return: String
   * 
   * Return value: HEX string.
   */
  public static String convertByteArrayToHex(byte[] messageArr) {
    StringBuilder result = new StringBuilder();
    char[] res = Hex.encodeHex(messageArr);
    result.append(res);
    return result.toString();
  }

  /**
   * 
   * createBlocks
   *
   * Method Information
   * The plain text in split into blocks of 16 text chars.
   * Uses REGEX pattern to split into blocks of 16 chars
   * 
   * Parameters:
   *   @param message
   *   @return: String[]
   * 
   * Return value: regular text blocks of 16 chars.
   */
  public static String[] createBlocks(String message) {
    return message.split("(?<=\\G.{16})");
  }

  /**
   * 
   * createBlocksOf32
   *
   * Method Information
   * The cipher text in split into blocks of 32 HEX chars.
   * Uses REGEX pattern to split
   * 
   * Parameters:
   *   @param cText
   *   @return: String[]
   * 
   * Return value: 32 HEX chars Cipher text blocks.
   */
  public static String[] createBlocksOf32(String cText) {
    return cText.split("(?<=\\G.{32})");
  }

  /**
   * 
   * stripBadChars
   *
   * Method Information
   * Utils method called to strip off no meaning text
   * 
   * Parameters:
   *   @param pText
   *   @return: String
   * 
   * Return value: actual text leaving out the padded strings.
   */
  public static String stripBadChars(String pText) {
    Pattern commaPattern = Pattern.compile("\\~") ;
    return commaPattern.split(pText)[0];
  }  

}
