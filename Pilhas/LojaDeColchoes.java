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

class Node {
    public Integer valor;
    public Node proximo;

    public Node(Integer s) {
        this.valor = s;
    }
}

class Stack {

    private Node topo;
    private int qtd;

    public void push(Integer s) {
        Node node = new Node(s);
        node.proximo = topo;
        topo = node;
        qtd++;
    }

    public Integer pop() {
        Integer s = 0;
        if (!isEmpty()) {
            s = topo.valor;
            topo = topo.proximo;
            qtd--;
        } else {
            throw new RuntimeException("Pilha vazia!");
        }
        return s;
    }

    public Integer top() {
        Integer s = 0;
        if (!isEmpty()) {
            s = topo.valor;
        } else {
            throw new RuntimeException("Pilha vazia!");
        }
        return s;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public String toString() {
        String s = "";
        if (! isEmpty()) {
            Node p = topo;
            while (p != null) {
                s += p.valor + "\n";
                p = p.proximo;
            }
        } else {
            s += "null";
        }
        return s;
    }

    public int getQtd() {
        return qtd;
    }
}

class Result {

    public static String organizar(int capacidade, int numDeColchoes) {

        Stack pilhaCaminhao = new Stack();
        Stack pilhaCarrinho = new Stack();
        Stack pilhaArmazem = new Stack();

        for(int i = numDeColchoes; i >= 1; i--){
            pilhaCaminhao.push(i);
        }

        while(!pilhaCaminhao.isEmpty()){
            //desempilha do caminhao e empilha do carrinho de acordo com a capacidade
            transporteColchoes(capacidade, pilhaCaminhao, pilhaCarrinho);

            //desempilha do carrinho e empilha no armazem de acordo com a quantidade
            transporteColchoes(capacidade, pilhaCarrinho, pilhaArmazem);

        }
        return pilhaArmazem.toString();
    }

    private static void transporteColchoes(int capacidade, Stack pilha1, Stack pilha2) {
        if(pilha1.getQtd() < capacidade){
            while(!pilha1.isEmpty()){
                pilha2.push(pilha1.top());
                pilha1.pop();
            }
        }else{
            for (int i = 1; i <= capacidade; i++){
                pilha2.push(pilha1.top());
                pilha1.pop();
            }
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int capacidade = Integer.parseInt(bufferedReader.readLine().trim());

        int numDeColchoes = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.organizar(capacidade, numDeColchoes);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}