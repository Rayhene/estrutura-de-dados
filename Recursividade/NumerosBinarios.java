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

    private static String strFinal = "";
    
    public static void concatenarString(String str){
        strFinal += str + " ";
    }
    
    public static void gerarNumero(int n, String str) {
        if(n > 0) {
            gerarNumero(n-1, str + "0");
            gerarNumero(n-1, str + "1");
        }else{
            concatenarString(str);
        }
    }
    

    public static void solve(int n) {
        String str = "";
        gerarNumero(n, str);
        System.out.println(strFinal);

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.solve(n);

        bufferedReader.close();
    }
}
