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
    String info;
    Node proximo;

    public Node(String info) {
        this.info = info;
    }
}

class ListaCircular{

    private Node inicio, fim;
    private int tamanho;

    public void inserir(String info) {
        Node node = new Node(info);
        if (inicio == null) {
            inicio = node;
        } else {
            fim.proximo = node;
        }
        fim = node;
        fim.proximo = inicio;
        tamanho++;
    }

    public String elementoNaPosicao(int i) {
        String s = null;
        Node p = inicio;
        int j=0;
        while (p != null && j<i) {
            j++;
            p = p.proximo;
        }
        if (j==i && p != null) {
            s = p.info;
        }
        return s;
    }

    public int getTamanho() {
        return tamanho;
    }
}

class Result {

    static ListaCircular rodaGigante = new ListaCircular();

    public static void preencherRodaGigante(String nomes) {
        String[] arrOfStr = nomes.split(" ");

        for(int i = 0; i <= arrOfStr.length-1; i++){
            rodaGigante.inserir(arrOfStr[i]);
        }
    }

    public static String girarRodaGigante(int numeroDeGiros) {
        if(rodaGigante.getTamanho() != 0){
            return rodaGigante.elementoNaPosicao(numeroDeGiros);
        }else{
            return null;
        }
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String nomes = bufferedReader.readLine();

        Result.preencherRodaGigante(nomes);

        int numeroDeGiros = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.girarRodaGigante(numeroDeGiros);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
