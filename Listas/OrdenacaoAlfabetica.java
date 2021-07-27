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
    Node anterior;
    Node proximo;

    public Node(String info) {
        this.info = info;
    }
}

class Lista{
    Node inicio, fim = null;
    private int tamanho;

    public void inserir(String info) {
        Node node = new Node(info);

        if(inicio == null) {
            inicio = fim = node;
            inicio.anterior = null;
        }
        else {
            fim.proximo = node;
            node.anterior = fim;
            fim = node;
        }
        fim.proximo = null;
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


    public void ordenarLista() {
        Node atual, p;
        String temp;

	//percorre a lista com dois nodes, se o valor que vem antes for maior que o próximo troca o valor
        for(atual = inicio; atual.proximo != null; atual = atual.proximo) {
            for(p = atual.proximo; p != null; p = p.proximo) {
                if(atual.info.compareToIgnoreCase(p.info) >= 0) {
                    temp = atual.info;
                    atual.info = p.info;
                    p.info = temp;
                }
            }
        }
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

    public int getTamanho() {
        return tamanho;
    }

}


class Result {

    public static String ordenarPalavras(String s) {
        String[] arrOfStr = s.split(" ");
        Lista lista = new Lista();

	//insere o primeiro elemento pra ter como comparar antes de inserir os outros elementos
        lista.inserir(arrOfStr[0]);

	//verifica se já tem um elemento igual na lista antes de inserir
        for(int i = 1; i <= arrOfStr.length-1; i++){
            boolean isEqual = false;
            for(int j = 0; j <= lista.getTamanho()-1; j++){
                if(lista.elementoNaPosicao(j).equalsIgnoreCase(arrOfStr[i])){
                    isEqual = true;
                    break;
                }
            }
            if(!isEqual){
                lista.inserir(arrOfStr[i]);
            }
        }
        lista.ordenarLista();
        return lista.toString();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.ordenarPalavras(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}