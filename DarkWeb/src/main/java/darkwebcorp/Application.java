package darkwebcorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * file: Application.java
 * author: Marist User
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: May 2, 2016
 * version: 1.0
 * 
 * Application class is where the DarkWeb app is launched without web.xml
 * configuration.
 * Has a main method that calls run() method.
 * The run() method returns an ApplicationContext and this application then 
 * retrieves all the beans that were created either by dark web or were 
 * automatically added.
 */
@SpringBootApplication
public class  Application {
  
  /**
   * 
   * main
   *
   * Method Information
   * 
   * The main() method uses Spring Boot’s SpringApplication.run()
   * method to launch an application.
   * 
   * Parameters:
   *   @param args: void
   * 
   * Return value: void
   */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
