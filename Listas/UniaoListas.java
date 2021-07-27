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
    Integer info;
    Node anterior;
    Node proximo;

    public Node(Integer info) {
        this.info = info;
    }
}

class Lista{
    Node inicio, fim = null;
    private int tamanho;

    public void inserir(Integer info) {
        Node node = new Node(info);

        if (inicio == null) {
            inicio = fim = node;
            inicio.anterior = null;
            fim.proximo = null;
            tamanho++;
        } else {
            fim.proximo = node;
            node.anterior = fim;
            fim = node;
            fim.proximo = null;
            tamanho++;
        }
    }
    
    
    public void ordenarLista() {
            Node atual, p;
            int temp;

            for (atual = inicio; atual.proximo != null; atual = atual.proximo) {
                for (p = atual.proximo; p != null; p = p.proximo) {
                    if (atual.info > p.info) {

                        temp = atual.info;
                        atual.info = p.info;
                        p.info = temp;

                    }
                }
            }
        }
    
    
    public Integer elementoNaPosicao(Integer i) {
            Integer r = 0;
            Node p = inicio;
            int k = 0;
            while (p != null && k < i) {
                k++;
                p = p.proximo;
            }
            if (k == i && p != null) {
                r = p.info;
            }
            return r;
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

 public static String unirListas(String lista1, String lista2) {

            String[] arrOfStr1 = lista1.split(" ");
            String[] arrOfStr2 = lista2.split(" ");
            Lista lista = new Lista();

            //insere o primeiro elemento pra ter como checar se tem repetido antes de inserir
            lista.inserir(Integer.parseInt(arrOfStr1[0]));

            //insere os valores da primeira string na lista verificando se já tem um valor igual lá
            //começando por 1 porque já tem um elemento lá
            validarElemento(arrOfStr1, lista, 1);

	    //insere os valores da segunda string na lista verificando se já tem um valor igual lá			
            validarElemento(arrOfStr2, lista, 0);

            lista.ordenarLista();

            return lista.toString();

        }

    private static void validarElemento(String[] arrOfStr, Lista lista, int k) {
        boolean isEqual = false;
        for (int i = k; i <= arrOfStr.length - 1; i++) {
            for (int j = 0; j <= lista.getTamanho() - 1; j++) {

                if (lista.elementoNaPosicao(j).equals(Integer.parseInt(arrOfStr[i]))) {
                    isEqual = true;
                    break;
                }
            }

            if (!isEqual) {
                lista.inserir(Integer.parseInt(arrOfStr[i]));
            }
            isEqual = false;

        }

    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String lista1 = bufferedReader.readLine();

        String lista2 = bufferedReader.readLine();

        String result = Result.unirListas(lista1, lista2);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}