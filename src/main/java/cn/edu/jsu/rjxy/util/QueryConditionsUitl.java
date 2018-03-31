package cn.edu.jsu.rjxy.util;

public class QueryConditionsUitl {


  public static String constructQueryConditions(String search) {
    if (search!=null) {
      char[] searchArray = search.toCharArray();
      search = "%";
      for (int i=0; i<searchArray.length; i++) {
        search += searchArray[i]+"%";
      }
    }
    return search;
  }
}
