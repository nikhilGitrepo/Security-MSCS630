package darkwebcorp.dto;

/**
 * 
 * file: Message.java
 * author: Nikhil
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: 02-May-2016
 * version: 1.0
 * 
 * The message payload POJO used across application
 */
public class Message {

  /*
   * message identification - for tracking and authentication
   */
  private int messageID;
  
  /*
   * Desired message
   */
  private String message;
  
  /*
   * Sender of receivers name
   */
  private String name;

  /**
   * Message Default constructor
   */
  public Message() {
  }

  /**
   * Parameterized Constructor - used when all the 
   * related data is available
   * 
   * @param messageID
   * @param message
   * @param name
   */
  public Message(int messageID, String message, String name) {
    super();
    this.messageID = messageID;
    this.message = message;
    this.name = name;
  }

  public int getMessageID() {
    return messageID;
  }

  public void setMessageID(int messageID) {
    this.messageID = messageID;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Message [messageID=" + messageID + ", message=" +
  message + ", name=" + name + "]";
  }
  
}
