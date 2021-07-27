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
    public String valor;
    public Node proximo;

    public Node(String item) {
        this.valor = item;
    }
}

class Fila{
    private Node inicio, fim;
    private int tamanho;

    public void inserir(String item) {
        Node node = new Node(item);

        if(inicio == null) {
            this.inicio = node;
            this.fim = node;
        } else {
            fim.proximo = node;
            fim = node;
        }

        tamanho++;
    }

    public String remover() {
        String itemRemovido = null;

        if (inicio != null) {
            itemRemovido = inicio.valor;
            inicio = inicio.proximo;

            if(inicio == null ) {
                fim = null;
            }
            tamanho--;
        }
        return itemRemovido;
    }

    public void ordenarFila() {
        Node atual, p;
        String temp;

        for(atual = inicio; atual.proximo != null; atual = atual.proximo) {
            for(p = atual.proximo; p != null; p = p.proximo) {
                if(atual.valor.compareToIgnoreCase(p.valor) >= 0) {
                    temp = atual.valor;
                    atual.valor = p.valor;
                    p.valor = temp;
                }
            }
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node p = inicio;
        while (p != null) {
            s.append(p.valor).append(" ");
            p = p.proximo;
        }
        return s.toString();
    }
}

class Result {

    public static String separarEmFilas(String nomes){

        String[] arrOfStr = nomes.split("[\\W]");

        String stringRetorno;

        Fila fila1 = new Fila();
        Fila fila2 = new Fila();

        for(int i = 0; i <= arrOfStr.length-1; i++){

            if(Integer.parseInt(arrOfStr[i+1]) <= 10){
                fila1.inserir(arrOfStr[i]);
            }else{
                fila2.inserir(arrOfStr[i]);
            }
            i += 1;
        }

        fila1.ordenarFila();
        fila2.ordenarFila();

        stringRetorno = (fila1.toString() + "\n" + fila2.toString());

        return stringRetorno;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String inputValue = bufferedReader.readLine();

        String FilaDeSupermercado = Result.separarEmFilas(inputValue);

        bufferedWriter.write(FilaDeSupermercado);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}