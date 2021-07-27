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

class Node{
    int info;
    Node proximo;

    public Node(int info) {
        this.info = info;
    }
}

class DynamicList {
    private Node inicio, fim;
    static int contador;

    public void inserir(int i) {
        Node node = new Node(i);
        if (inicio == null) {
            inicio = node;
        } else {
            fim.proximo = node;
        }
        fim = node;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        Node p = inicio;
        while (p != null) {

            if(p.proximo == null){
                s.append(p.info).append(" ");
                p = null;
            }else{
                s.append(p.info).append(" -> ");
                p = p.proximo;
            }
        }
        return s.toString();
    }

    public int fibonacci(int n){

        int valor;

        if (n == 1 || n == 2) {
            valor = 1;
        }else{
            valor = fibonacci(n - 1) + fibonacci(n - 2);
        }

        if(n > contador){
            contador++;
            this.inserir(valor);
        }

        return valor;

    }
}

class Result{
    public static String fibonacci(int n) {

        DynamicList lista = new DynamicList();

        if(n == 2){
            return "0 -> 1";
        }

        if(n <= 0 || n == 1){
            return "0";
        }

        lista.inserir(0);
        lista.inserir(1);

        n -= 1;

        lista.fibonacci(n);

        return lista.toString();
    }
}



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.fibonacci(n);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}