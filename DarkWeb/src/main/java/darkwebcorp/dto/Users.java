package darkwebcorp.dto;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * file: Users.java
 * author: Nikhil
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: 02-May-2016
 * version: 1.0
 * 
 * Users Model object
 * - used to represent different users
 * - Subscribers and logged in application users
 * - HashMap to represent users
 * 
 */
public class Users {
  
  /*
   * Static variable - The users are accessible globally
   */
  private static Map<String, UserPreferences> users = new HashMap<>();
  
  /*
   * Static variable 'userSubs' - The Subscribers to this application
   * has scope before broadcasting the messages
   */
  private static Map<String, String> userSubs = new HashMap<>();

  public static Map<String, UserPreferences> getUsers() {
    return users;
  }

  public static void setUsers(Map<String, UserPreferences> users) {
    Users.users = users;
  }

  public static Map<String, String> getUserSubs() {
    return userSubs;
  }

  public static void setUserSubs(Map<String, String> userSubs) {
    Users.userSubs = userSubs;
  }
  
}
