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
    public Integer valor;
    public NoPilha proximo;

    public NoPilha(Integer s) {
        this.valor = s;
    }
}

class Pilha {

    private NoPilha topo;
    private int qtd;

    public void push(Integer s) {
        NoPilha node = new NoPilha(s);
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
        Integer s = null;
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

}


class Result {

    public static Pilha preencherPilha(List<Integer> lista) {
        Pilha pilha = new Pilha();

        for (int i = 0; i < lista.size(); i++) {
            pilha.push(lista.get(i));
        }
        return pilha;
    }

    public static String fun(List<Integer> lista) {
        Pilha pilha = preencherPilha(lista);

        ordenarPilha(pilha);

        return pilha.toString();
    }
    
    public static void inserirNaOrdem(Pilha pilha, int numero) {
        if (pilha.isEmpty()) {
            pilha.push(numero);
            return;
        }

        if(numero < pilha.top()){
            pilha.push(numero);
            return;
        }

        int temp = pilha.pop();
        inserirNaOrdem(pilha, numero);

        pilha.push(temp);
    }

    public static void ordenarPilha(Pilha pilha) {

        if (!pilha.isEmpty()) {
            int numero = pilha.pop();
            ordenarPilha(pilha);
            inserirNaOrdem(pilha, numero);
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<Integer> numeros = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
                .collect(toList());

        String resultado = Result.fun(numeros);
        System.out.println(resultado);

        bufferedWriter.write(resultado);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}