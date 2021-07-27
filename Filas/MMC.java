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
    public int valor;
    public Node proximo;

    public Node(int item) {
        this.valor = item;
    }

    public int getValor() {
        return valor;
    }
}

class Queue {
    private Node inicio, fim;
    private int tamanho;

    public void enqueue(int item) {
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

    public int dequeue() {
        int itemRemovido = 0;

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

}


class Result {

    private static boolean ehPrimo(int numero) {
        for (int j = 2; j < numero; j++) {
            if (numero % j == 0)
                return false;
        }
        return true;
    }
    public static int mmc(int number1, int number2) {

        int mmc = 1;
        int i;

        if(number1 == 0 || number2 == 0) {
            System.out.println(0);
        }

        int maior = number1 > number2? number1 : number2;

        Queue fila = new Queue();

        for(i = 2; i <= maior; i++){
            if(!ehPrimo(i)){
                continue;
            }else{
                while(number1 % i == 0 || number2 % i == 0){
                    if(number1 % i == 0){
                        number1 = number1/i;
                    }

                    if (number2 % i == 0){
                        number2 = number2/i;
                    }
                    fila.enqueue(i);
                }
            }
        }

        while(!fila.isEmpty()){
            mmc *= fila.dequeue();
        }

        return mmc;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int inputValue1 = Integer.parseInt(bufferedReader.readLine().trim());

        int inputValue2 = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.mmc(inputValue1, inputValue2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}