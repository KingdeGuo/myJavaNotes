import java.io.*;
import java.net.*;
import java.util.*;

 public class Options {

  public static void main(String[] args) {
    try {
      URL u = new URL(args[0]);
      HttpURLConnection http = (HttpURLConnection) u.openConnection();
      http.setRequestMethod("OPTIONS");
      Map<String, List<String>> headers = http.getHeaderFields();
      for (Map.Entry<String, List<String>> header : headers.entrySet()) {
        System.out.println(header.getKey() + ": " + join(header.getValue()));
      }
    } catch (MalformedURLException ex) {
      System.err.println(args[0] + " is not a parseable URL");
    } catch (IOException ex) {
      System.err.println(ex);
    }
    System.out.println();       
  }

  private static String join(List<String> list) {
    StringBuilder builder = new StringBuilder();
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      builder.append(iterator.next());
      if (iterator.hasNext()) builder.append(", ");
    }
    return builder.toString();
  }
}