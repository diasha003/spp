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

    public static String loose (String str, String remove){

        if(remove == null && str==null){
            throw new NullPointerException();
        }

        if(str == null){
            return null;
        }

        if(remove == null && str != null){
            return str;
        }


        String result = "";
        for (char value : str.toCharArray()){
            if(remove.indexOf(value) == -1){
                result += value;
            }
        }
        return result;
    }

    public static int indexOfDifference(String str1, String str2){

        if(str1 == null && str2==null){
            throw new NullPointerException();
        }

        for(int i=0; i< (str1.length() > str2.length()? str1.length():str2.length()); i++) {

            if (i > (str1.length()-1) || str1.charAt(i) != str2.charAt(i)) {
                return i;
            }
        }
        return -1;

    }


    public static String common(String str1, String str2){

        if(str1 == null && str2==null){
            throw new NullPointerException();
        }

        String result = "";
        for(int i=0; i< str1.length(); i++) {

            if (str1.charAt(i) == str2.charAt(i)) {
                result += str1.charAt(i);
            }
        }


        return result;
    }
}
