package darkwebcorp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import darkwebcorp.dto.UserPreferences;
import darkwebcorp.dto.Users;

/**
 * 
 * file: RedirectController.java
 * author: Nikhil
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: 02-May-2016
 * version: 1.0
 * 
 * This is spring controller to handle the new user request
 * 
 * Operations:
 * - The user data is persisted and used across application
 * - Provide redirection to the chat.html
 * - Send the required user data to the chat.html
 * 
 */
@Controller
public class RedirectController {

  private Map<String, UserPreferences> users = new HashMap<>();
  
  /**
   * 
   * chatPage
   *
   * Method Information
   * @RequestMapping()
   *   - HTTP request mapping - the form data is sent as HTTP post request
   * 
   * Parameters:
   *   @param userPref
   *   @param model
   *   @return: String
   * 
   * Return value: the view which is usually name of the HTML
   *   Again this can be manipulated to redirect to another controller
   */
  @RequestMapping(value="/redirectToChat.view", method=RequestMethod.POST)
  public String chatPage(@ModelAttribute UserPreferences userPref,
      Model model ){
    System.out.println(userPref);
    
    if(!users.containsKey(userPref.getUser())){
      users.put(userPref.getUser(), userPref);
      Users.setUsers(users);
    }
    
    model.addAttribute("userPref", userPref );
    // returning the chat to redirect to chat.html page
    return "chat";
  }
  
}
