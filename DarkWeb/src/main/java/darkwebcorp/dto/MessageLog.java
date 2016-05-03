/**
 * 
 */
package darkwebcorp.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * file: MessageLog.java
 * author: Nikhil
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: 02-May-2016
 * version: 1.0
 * 
 * - This file is created to show future scope for this project
 * 
 * - This can be used if all the user messages are persisted in the database.
 * 
 * - If an user request to retrieve all the messages or recent messages, then
 * the MessageLog POJO can populated with messages.
 * 
 * 
 */
public class MessageLog {
  
  List<Message> messageLogs = new ArrayList<>();

  public MessageLog(List<Message> messageLogs) {
    super();
    this.messageLogs = messageLogs;
  }
  
  public MessageLog() {
  }

  public List<Message> getMessageLogs() {
    return messageLogs;
  }

  public void setMessageLogs(List<Message> messageLogs) {
    this.messageLogs = messageLogs;
  }
  
}
