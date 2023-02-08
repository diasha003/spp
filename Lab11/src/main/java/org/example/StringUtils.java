package org.example;

import java.util.Arrays;

public class StringUtils {

    public static String repeat(String pattern, int repeat){

      if (repeat < 0){
          throw new IllegalArgumentException();
      }

      if (pattern == null){
          throw new NullPointerException();
      }

      String result = "";
      for(int i=0 ; i<repeat; i++){
          result += pattern.substring(0,pattern.length());
      }
      return result;

    }

    public static String repeat(String str, String separator, int repeat){

        if (repeat < 0){
            throw new IllegalArgumentException();
        }

        if (str == null || separator == null){
            throw new NullPointerException();
        }

        String[] array = new String[repeat];
        Arrays.fill(array, str);
        //System.out.println(String.join(separator ,array));
        return String.join(separator ,array);
    }

    public static String keep(String str, String pattern){

        if(pattern == null && str==null){
            throw new NullPointerException();
        }

        if(str == null){
            return null;
        }

        if(pattern == null){
            return "";
        }

        String result = "";
        for (char value : str.toCharArray()){
            if(pattern.indexOf(value) != -1){
                result += value;
            }
        }
        return result;
    }

}
