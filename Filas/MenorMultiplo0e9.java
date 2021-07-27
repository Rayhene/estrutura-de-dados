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
    public Node anterior;

    public Node(String item) {
        this.valor = item;
    }

    public String getValor() {
        return valor;
    }
}

class Queue {
    private Node inicio, fim;
    private int tamanho;

    public void enqueue(String item) {
        Node node = new Node(item);

        if (inicio == null) {
            this.inicio = node;
            this.fim = node;
        } else {
            fim.proximo = node;
            fim = node;
        }

        tamanho++;
    }

    public String dequeue() {
        String itemRemovido = null;

        if (inicio != null) {
            itemRemovido = inicio.valor;
            inicio = inicio.proximo;

            if (inicio == null) {
                fim = null;
            }
            tamanho--;
        }
        return itemRemovido;
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

    public String getInicio() {
        return inicio.getValor();
    }
}

class Lista{
    Node inicio, fim = null;
    private int tamanho;

    public void inserir(String info) {
        Node node = new Node(info);

        if (inicio == null) {
            inicio = fim = node;
            inicio.anterior = null;
            fim.proximo = null;
            tamanho++;
        } else {
            fim.proximo = node;
            node.anterior = fim;
            fim = node;
            fim.proximo = null;
            tamanho++;
        }
    }

    public String elementoNaPosicao(Integer i) {
        String r = null;
        Node p = inicio;
        int k = 0;
        while (p != null && k < i) {
            k++;
            p = p.proximo;
        }
        if (k == i && p != null) {
            r = p.valor;
        }
        return r;
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

    public int getTamanho() {
        return tamanho;
    }
}


class Result {
    static Queue fila = new Queue();
    static Lista lista = new Lista();

    public static void gerarNumeros()
    {
        fila.enqueue("9");

        for (int i = 0; i <= 10000; i++) {
            String s1 = fila.getInicio();
            fila.dequeue();
            lista.inserir(s1);
            fila.enqueue(s1 + "0");
            fila.enqueue(s1 + "9");

        }
    }

    public static String solve(int n) {
        if(n <= 0){
            return "";
        }

        gerarNumeros();

        for (int i = 0; i < lista.getTamanho(); i++){
            if (Integer.parseInt(lista.elementoNaPosicao(i)) % n == 0) {
                return lista.elementoNaPosicao(i);
            }
        }
        return "";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String solution = Result.solve(n);

        bufferedWriter.write(solution);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
