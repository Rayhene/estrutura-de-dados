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

class NoPilha {
    public String valor;
    public NoPilha proximo;

    public NoPilha(String s) {
        this.valor = s;
    }
}

class Pilha {

    private NoPilha topo;
    private int qtd;

    public void push(String s) {
        NoPilha node = new NoPilha(s);
        node.proximo = topo;
        topo = node;
        qtd++;
    }

    public String pop() {
        String s = null;
        if (!isEmpty()) {
            s = topo.valor;
            topo = topo.proximo;
            qtd--;
        } else {
            throw new RuntimeException("Pilha vazia!");
        }
        return s;
    }

    public String top() {
        String s = null;
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
            NoPilha p = topo;
            while (p != null) {
                s += p.valor + " ";
                p = p.proximo;
            }
        } else {
            s += "null";
        }
        return s;
    }

    public int size() {
        return qtd;
    }


    public void print() {
        System.out.println(this);
    }

}


class Result {


    public static Pilha criarPilha(String frase) {
        Pilha pilha = new Pilha();

        String[] inputs = frase.split(" ");
        for (String input : inputs) {
            pilha.push(input);
        }

        return pilha;

    }

    public static void organizarPilhas(Pilha pilhaPar, Pilha pilhaImpar) {

        Pilha tempPilhaImpar = new Pilha();
        Pilha tempPilhaPar = new Pilha();
        Pilha temp = new Pilha();

	//cria uma pilha temporaria com os numeros pares 
        while(!pilhaPar.isEmpty()){
            if(Integer.parseInt(pilhaPar.top()) % 2 == 0){
                tempPilhaPar.push(pilhaPar.top());
                pilhaPar.pop();
            }else{
                pilhaPar.pop();
            }
        }

	//desempilha a tempPilhaPar e empilha a pilha temp
        int tamanho = tempPilhaPar.size();
        for(int i = 0; i < tamanho; i++){
            temp.push(tempPilhaPar.top());
            tempPilhaPar.pop();
        }

	//desempilha pilha temp e empilha a pilhaPar
        for(int i = 0; i < tamanho; i++){
            pilhaPar.push(temp.top());
            temp.pop();
        }

	//cria uma pilha temporaria com os numeros impares
        while(!pilhaImpar.isEmpty()){
            if(Integer.parseInt(pilhaImpar.top()) % 2 != 0){
                tempPilhaImpar.push(pilhaImpar.top());
                pilhaImpar.pop();
            }else{
                pilhaImpar.pop();
            }
        }

	//empilha a pilha temp e desempilha tempPilhaImpar
        tamanho = tempPilhaImpar.size();
        for(int i = 0; i < tamanho; i++){
            temp.push(tempPilhaImpar.top());
            tempPilhaImpar.pop();
        }

	//empilha pilhaImpar e desempilha temp
        for(int i = 0; i < tamanho; i++){
            pilhaImpar.push(temp.top());
            temp.pop();
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        
        String frase = bufferedReader.readLine();

        Pilha pilhaPar = Result.criarPilha(frase);
        Pilha pilhaImpar = Result.criarPilha(frase);

        Result.organizarPilhas(pilhaPar, pilhaImpar);

        bufferedWriter.write(pilhaPar.toString());
        bufferedWriter.newLine();
        bufferedWriter.write(pilhaImpar.toString());

        bufferedReader.close();
        bufferedWriter.close();
    }
}