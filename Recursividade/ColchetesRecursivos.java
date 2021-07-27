import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static boolean isTrue(String stringValue) {
    
         if (stringValue == null || stringValue.length() == 0) {
            return true;
        }

        if(stringValue.length() == 1 ) {
            return false;
        }

        stringValue = stringValue.replaceAll("[^\\[\\]]", "");


        if (stringValue.contains("[]")) {
            return isTrue(stringValue.replaceFirst("\\[\\]", ""));
        }else {
            return false;
        }

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String inputValue = bufferedReader.readLine();

        boolean result = Result.isTrue(inputValue);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
