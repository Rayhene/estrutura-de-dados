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

    public boolean isEmpty(){
        return inicio == null;
    }

    public void inverterFila(Queue fila){

        if(fila.isEmpty()){
            return;
        }

        String s = fila.inicio.getValor();
        fila.dequeue();
        inverterFila(fila);
        fila.enqueue(s);
    }
}

    class Result {

    public static Queue invertQueue(String input) {

        String[] arrOfStr = input.split(" ");
        Queue fila = new Queue();

        for(int i = 0; i <= arrOfStr.length-1; i++){
            fila.enqueue(arrOfStr[i]);
        }
        System.out.println(fila.toString());
        fila.inverterFila(fila);
        return fila;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
                String input = bufferedReader.readLine();
                Queue queue = Result.invertQueue(input.trim());
                String result = "";
                while(!queue.isEmpty())
                    result += queue.dequeue() + " ";
                bufferedWriter.write(result.trim());
                bufferedWriter.newLine();
            }
        }
    }
}