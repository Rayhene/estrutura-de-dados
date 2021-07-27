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

class LinkedList {
    Node inicio, fim;
    private int tamanho;


    public Node getInicioNode() {
        return inicio;
    }

    public void setInicioNode(Node node) {
        inicio = node;
    }

    void inverter(Node node) {
        Node anterior = null, atual = node, proximo;
        while (atual != null) {
            proximo = atual.proximo;
            atual.proximo = anterior;
            anterior = atual;
            atual = proximo;
        }
        node = anterior;
        setInicioNode(node);
    }

   public void inserir(String s) {
        Node node = new Node(s);
        if (inicio == null) {
            inicio = node;
        } else {
            fim.proximo = node;
        }
        fim = node;
        tamanho++;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node p = inicio;
        while (p != null) {
            s.append(p.info).append(" ");
            p = p.proximo;
        }
        return s.toString();
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

class Result{
  public static String inverterNPosicoes(String numeros, int n) {
        String[] arrOfStr = numeros.split(" ");
        LinkedList list = new LinkedList();

        int i;

        if(n > arrOfStr.length){ // se n foi maior que o tamanho da lista printa ela sem nodificações
            for(i = 0; i <= arrOfStr.length-1; i++){
                list.inserir(arrOfStr[i]);
            }
            return list.toString();
        }else if(n <= 0){ // se n for negativo inverte toda a lista
            for(i = 0; i <= arrOfStr.length-1; i++){
                list.inserir(arrOfStr[i]);
            }
            list.inverter(list.getInicioNode());
            return list.toString();
        }else{ //se não for nenhuma dessas opções, ele cria uma sublista a partir de n e depois insere os valores na lista final
            for(i = 0; i < n; i++){
                list.inserir(arrOfStr[i]);
            }
            LinkedList subList = new LinkedList();

            for(i = n; i < arrOfStr.length; i++){
                subList.inserir(arrOfStr[i]);
            }

            subList.inverter(subList.getInicioNode());

            i = 0;
            while(i < subList.getTamanho()){
                list.inserir(subList.elementoNaPosicao(i));
                i++;
            }

            return list.toString();

        }

    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String numeros = bufferedReader.readLine();

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.inverterNPosicoes(numeros, n);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
