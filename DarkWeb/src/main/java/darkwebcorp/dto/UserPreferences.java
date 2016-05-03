package darkwebcorp.dto;

/**
 * 
 * file: UserPreferences.java
 * author: Nikhil
 * course: MSCS_630L_231_16S
 * assignment: Final Project - DarkWeb
 * due date: 02-May-2016
 * version: 1.0
 * 
 * This file maintains the user preferences for example,
 * user ->              Nikhil
 * encryptionScheme ->  AES
 * securityLevel ->     256
 * secureKey ->         Safe and Secured
 * 
 */
public class UserPreferences {
  
  private String user;
  
  private String encryptionScheme;
  
  private String securityLevel;
  
  private String secureKey;

  /**
   * Parameterized Constructor - used when all the 
   * related data is available
   * 
   * @param user
   * @param encryptionScheme
   * @param securityLevel
   * @param secureKey
   */
  public UserPreferences(String user, String encryptionScheme, 
		  String securityLevel, String secureKey) {
    super();
    this.user = user;
    this.encryptionScheme = encryptionScheme;
    this.securityLevel = securityLevel;
    this.secureKey = secureKey;
  }

  public UserPreferences() {
    super();
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getEncryptionScheme() {
    return encryptionScheme;
  }

  public void setEncryptionScheme(String encryptionScheme) {
    this.encryptionScheme = encryptionScheme;
  }

  public String getSecurityLevel() {
    return securityLevel;
  }

  public void setSecurityLevel(String securityLevel) {
    this.securityLevel = securityLevel;
  }

  public String getSecureKey() {
    return secureKey;
  }

  public void setSecureKey(String secureKey) {
    this.secureKey = secureKey;
  }

  @Override
  public String toString() {
    return "UserPreferences [user=" + user + ", encryptionScheme=" 
  + encryptionScheme + ", securityLevel="
        
  + securityLevel + ", secureKey=" + secureKey + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + 
    		((encryptionScheme == null) ? 0 : encryptionScheme.hashCode());
    result = prime * result + 
    		((secureKey == null) ? 0 : secureKey.hashCode());
    result = prime * result + 
    		((securityLevel == null) ? 0 : securityLevel.hashCode());
    result = prime * result + 
    		((user == null) ? 0 : user.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserPreferences other = (UserPreferences) obj;
    if (encryptionScheme == null) {
      if (other.encryptionScheme != null)
        return false;
    } else if (!encryptionScheme.equals(other.encryptionScheme))
      return false;
    if (secureKey == null) {
      if (other.secureKey != null)
        return false;
    } else if (!secureKey.equals(other.secureKey))
      return false;
    if (securityLevel == null) {
      if (other.securityLevel != null)
        return false;
    } else if (!securityLevel.equals(other.securityLevel))
      return false;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    return true;
  }
  
}
