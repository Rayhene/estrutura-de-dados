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

    private static int i = 2;
    private static int soma;

    public static boolean ehPrimo(int n) {
    // Write your code here
        if(n == 1 || n == 0)  return false;

        if(i < n){
            if(n % i == 0){
                //i = 2;
                return false;
            }else{
                i++;
                if(ehPrimo(n)){
                    return true;
                }
                return false;
            }
        }
        return true;
    }


    public static int somaDosDigitos(int n) {
    // Write your code here
        if (n < 10) {
            if(ehPrimo(n)){
                soma += n;
            }
        } else {
            somaDosDigitos(n / 10);
            somaDosDigitos(n % 10);
        }

        i = 2;
        return soma;
    }
    

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.somaDosDigitos(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
