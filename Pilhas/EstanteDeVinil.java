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

class Node<T> {
    public T info;
    public Node<T> next;

    public Node(T e) {
        this.info = e;
    }

}

class Pilha<T> {

    private Node<T> topo;
    private int qtd;

    public void push(T e) {
        Node<T> novo = new Node<T>(e);
        novo.next = topo;
        topo = novo;
        qtd++;
    }

    public T pop() {
        T r = null;
        if (! isEmpty()) {
            r = topo.info;
            topo = topo.next;
            qtd--;
        } else {
            throw new RuntimeException("Pilha vazia!");
        }
        return r;
    }

    public T top() {
        T r = null;
        if (! isEmpty()) {
            r = topo.info;
        } else {
            throw new RuntimeException("Pilha vazia!");
        }
        return r;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public String toString() {
        String r = "topo";
        if (! isEmpty()) {
            Node<T> p = topo;
            while (p != null) {
                r += " -> " + p.info;
                p = p.next;
            }
        } else {
            r += "-> null";
        }
        return r;
    }

}

class Vinil{

    String nome;
    int ano;

    public Vinil(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return nome + "(" + ano + ")";
    }
}

class Result {

    public static String ouvirArtista(Pilha<Vinil> estante) {

        Vinil vinil = estante.top();
        return vinil.toString();
    }


    public static Pilha<Vinil> inserirVinil(Pilha<Vinil> estante, Vinil disco) {

        if(estante.isEmpty()){
            estante.push(disco);
            return estante;
        }

        if(disco.ano < estante.top().ano){
            estante.push(disco);
            return estante;
        }

        Vinil temp = estante.pop();
        inserirVinil(estante, disco);

        estante.push(temp);

        return estante;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String v1 = bufferedReader.readLine();

        int s1 = Integer.parseInt(bufferedReader.readLine().trim());

        String v2 = bufferedReader.readLine();

        int s2 = Integer.parseInt(bufferedReader.readLine().trim());

        String v3 = bufferedReader.readLine();

        int s3 = Integer.parseInt(bufferedReader.readLine().trim());

        String v4 = bufferedReader.readLine();

        int s4 = Integer.parseInt(bufferedReader.readLine().trim());


        Pilha<Vinil> estante = new Pilha<Vinil>();
        Vinil vinil1 = new Vinil(v1,s1);
        Vinil vinil2 = new Vinil(v2,s2);
        Vinil vinil3 = new Vinil(v3,s3);
        Vinil vinil4 = new Vinil(v4,s4);
        estante.push(vinil1);
        estante = Result.inserirVinil(estante, vinil2);
        estante = Result.inserirVinil(estante, vinil3);
        estante = Result.inserirVinil(estante, vinil4);
        System.out.println(Result.ouvirArtista(estante));
        bufferedReader.close();
    }
}