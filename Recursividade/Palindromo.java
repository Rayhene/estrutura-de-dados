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
    
    public static String ajeitarString(String s) {
        String string = s.toLowerCase();
        string = string.replaceAll("\\p{Punct}", "");
        string = string.replaceAll("\\s", "");
        return string;
    }
    
     public static boolean checarChar(char c1, char c2) {
        return c1 == c2;
    }

    public static boolean checkPalindromo(String s) {
        s = ajeitarString(s);
        int size = s.length();
        int i = 0;

        if (size == 1) {
            System.out.println("A string eh um palindromo");
            return true;
        }

        if (checarChar(s.charAt(i), s.charAt(size - i - 1))) {
            i++;
            checarChar(s.charAt(i), s.charAt(size - i - 1));
            System.out.println("A string eh um palindromo");
            return true;
        }else {
            System.out.println("A string nao eh um palindromo");
            return false;
        }


    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        boolean result = Result.checkPalindromo(s);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
