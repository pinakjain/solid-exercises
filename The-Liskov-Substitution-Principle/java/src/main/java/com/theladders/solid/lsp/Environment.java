package com.theladders.solid.lsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Environment 
{
  public static final String KEY_EMAIL_DOMAIN = "emaildomain";
  protected static HashMap<Object, Object> envMap; 
  
  public Environment(HashMap<Object, Object> envMap)
  {
	  this.envMap = envMap;
  }

  /**
   * Convenience method that returns the admin email address for this ladder.
   *
   * @return email address or "" if either the user or domain is not defined
   */

  public String getAdminEmail()
  {
    String user = (String) getEnvDataForKey("admin");
    String domain = (String) getEnvDataForKey(KEY_EMAIL_DOMAIN);

    return user.length() > 0 && domain.length() > 0 ? user + "@" + domain : "";
  }
  
  public void putEnvData(Object key, Object value){
	  envMap.put(key, value);
  }

  public Object getEnvDataForKey(Object key)
  {
    Object val = envMap.get(key);
    return (val != null) ? val.toString().trim() : "";
  }
  
  public HashMap<Object, Object> getEnvMap(){
	  return this.envMap;
  }
  
  public Collection<Object> getValues(){
	  return envMap.values();
  }
  
  public Set<Object> getKeySet(){
	  return envMap.keySet();
  }
  
  public  Set<Entry<Object, Object>> getEntrySet(){
	  return envMap.entrySet();
  }
}
