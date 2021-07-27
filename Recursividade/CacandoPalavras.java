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

    private static int vezes = 0;
    private static int i = 0;

    public static int buscarPalavra(String input) {
    
        
        String[] arrOfStr = input.toLowerCase().split(" ");
        
        if(arrOfStr.length <= 1 ){
            return 0;
        }

        String texto = arrOfStr[0];
        String palavra = arrOfStr[1];
        String constraint;

        if(arrOfStr.length >= 3) constraint = arrOfStr[2];
        else constraint = "*";


        if(i <= texto.length() - palavra.length()){
            if(texto.charAt(i) == palavra.charAt(0)){

                if(texto.substring(i, i + palavra.length()).equals(palavra)){

                    if(texto.substring(i + palavra.length()).length() >= constraint.length()){
                        if(texto.substring(i + palavra.length(), i + palavra.length() + constraint.length()).equals(constraint)){
                            i++;
                            buscarPalavra(input);
                        }else{
                            i++;
                            vezes++;
                            buscarPalavra(input);
                        }
                    }else{
                        i++;
                        vezes++;
                        buscarPalavra(input);
                    }

                }else{
                    i++;
                    buscarPalavra(input);
                }

            }else {
                i++;
                buscarPalavra(input);
            }
        }else{
            return vezes;
        }


        return vezes;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String input = bufferedReader.readLine();

        int result = Result.buscarPalavra(input);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
