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

        if(this.isEmpty()){
            return;
        }
        
        for(atual = inicio; atual.proximo != null; atual = atual.proximo) {
            for(p = atual.proximo; p != null; p = p.proximo) {
                if(Integer.parseInt(atual.valor) > (Integer.parseInt(p.valor)) ) {
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

    public int getTamanho() {
        return tamanho;
    }

    public boolean isEmpty(){
        return inicio == null;
    }
}

class Result {

    public static String brincarComFila(String fila) {

        String[] arrOfStr = fila.split(" ");
        
          if(arrOfStr.length == 0){
            return " ";
        }
        
        Fila filaFinal = new Fila();
        Fila filaImpar = new Fila();


        for(int i = 0; i <= arrOfStr.length-1; i++){

            if(Integer.parseInt(arrOfStr[i]) % 2 == 0){
                filaFinal.inserir(arrOfStr[i]); //a principio vai ser usada pra armazenar os numeros pares
            }else{
                filaImpar.inserir(arrOfStr[i]);
            }
        }
        filaFinal.ordenarFila();

        while(!filaImpar.isEmpty()){
            String s = filaImpar.remover();
            filaFinal.inserir(s);
        }


        return filaFinal.toString();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String fila = bufferedReader.readLine();

        String result = Result.brincarComFila(fila);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}