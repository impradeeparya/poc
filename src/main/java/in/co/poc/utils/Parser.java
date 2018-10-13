package in.co.poc.utils;

import com.google.gson.Gson;

public class Parser {

  private static Gson gson = new Gson();

  public static <T> T fromJson(String json, Class<T> classType) {
    return gson.fromJson(json, classType);
  }

  public static String toJson(Object o) {
    return gson.toJson(o);
  }
}
