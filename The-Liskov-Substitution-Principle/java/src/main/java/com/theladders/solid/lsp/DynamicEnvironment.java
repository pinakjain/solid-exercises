package com.theladders.solid.lsp;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A wrapper that allows some properties to be overriden on a per-request basis.
 *
 * @author Zhi-Da Zhong &lt;zz@theladders.com&gt;
 */

public class DynamicEnvironment
{
  private final Environment         base;
  private final HashMap<Object, Object> specificEnv;
  private final Map<String, String> keyMap; // map insecure prop names to secure ones

  public DynamicEnvironment(Environment base, Map<String, String> propKeyMap, HashMap<Object, Object> specificEnv)
  {
    this.base = base;
    this.keyMap = propKeyMap;
    this.specificEnv = specificEnv;
  }

 
  public Collection<Object> values()
  {
    // TODO remove masked values
    // TODO join local instance values
    return base.getValues();
  }

  /**
   * This method uses a mapped version of the given key to access first its own Map then its
   * underlying Map.
   *
   * @param key
   *          An environment key like "home"
   * @return The value for the given key after mapping (e.g. "home" might be mapped to "secureHome")
   */

  public Object get(Object key)
  {
    String realKey = keyMap.get(key);
    Object value = specificEnv.get(realKey != null ? realKey : key);
    if (value == null)
    {
      return base.getEnvDataForKey(realKey != null ? realKey : key);
    }
    return value;
  }

  public Set<Map.Entry<Object, Object>> entrySet()
  {
    Set<Map.Entry<Object, Object>> entrySet = new HashSet<>(specificEnv.entrySet());
    entrySet.addAll(base.getEntrySet());
    return Collections.unmodifiableSet(entrySet);
  }

  public Set<Object> keySet()
  {
    Set<Object> keySet = new HashSet<>(specificEnv.keySet());
    keySet.addAll(keyMap.keySet());
    keySet.addAll(base.getKeySet());
    return Collections.unmodifiableSet(keySet);
  }
  
  public Map<String, String> getKeyMap(){
	  return this.keyMap;
  }
}
