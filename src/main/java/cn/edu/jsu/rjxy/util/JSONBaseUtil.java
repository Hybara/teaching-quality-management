package cn.edu.jsu.rjxy.util;

import java.util.HashMap;
import java.util.Map;

public class JSONBaseUtil {

  private static int JSON_DATA_MAP_SIZE = 2;

  private JSONBaseUtil() {};

  public static Map<String, Object> structuralResponseMap(Object data, double count) {
    Map<String, Object> responseMap = new HashMap<>(JSON_DATA_MAP_SIZE);
    responseMap.put("data", data);
    responseMap.put("count", count);
    return responseMap;
  }
}
