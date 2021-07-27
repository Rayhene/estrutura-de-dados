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
    public String valor;
    public Node proximo;

    public Node(String s) {
        this.valor = s;
    }
}

class Stack {

    private Node topo;
    private int qtd;

    public void push(String s) {
        Node node = new Node(s);
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
            Node p = topo;
            while (p != null) {
                s += p.valor;
                p = p.proximo;
            }
        } else {
            s += "null";
        }
        return s;
    }


}


class Result {


    public static boolean isOperator(String c){

        if(c.equals("+") || c.equals("-")  || c.equals("*")  || c.equals("/"))
            return  true;
        else return false;
    }

    public static String converterNotacao(String inputValue) {

        Stack pilha = new Stack();

        String[] arrOfString = inputValue.split("");

        for (int i = 0; i < arrOfString.length; i++){
            if(!isOperator(arrOfString[i])){
                pilha.push(arrOfString[i]);
            }else{
                String s1 = pilha.pop();
                String s2 = pilha.pop();
                String expressao = arrOfString[i] + s2 + s1;
                pilha.push(expressao);
            }
        }

        return pilha.toString();

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String inputValue = bufferedReader.readLine();

        String result = Result.converterNotacao(inputValue);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}