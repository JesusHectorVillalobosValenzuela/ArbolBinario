/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.binario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Jesus Hector Villaobos Valenzuela
 */


    class Nodo{
        public int valor;
        public Nodo izq;
        public Nodo der;


    public Nodo(int valor) {
        this.valor = valor;
        this.izq = null;
        this.der = null;
    }
    }


public class ArbolBinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        if (args.length >=1){
            String archivo = args[0];           // Configurar los argumentos antes de correr
                                                // En args[0] va el archivo de texto con la
                                                // lista de los numeros para guardar en el arbol binario.
            Nodo nodoM = CargaArchivo(archivo);
            PreOrder(nodoM);
            //PostOrder(nodoM);
            //InOrder(nodoM);
            //Busqueda(nodoM, 7);
            //Grafo(nodoM);

            //Stack s = new Stack();
            //s = GeneraInOrder(nodoM, s);
            //ImprimeGrafo(s);
        }
    }

    // Este metodo inserta los valores a los nodos
    public static Nodo Insert (Nodo root, Integer n){
        if ( root == null ) {
            root = new Nodo(n);
        } else{
            if ( n < root.valor ) {
                root.izq = Insert(root.izq,n);
            } else{
                root.der = Insert(root.der,n);
            }
        }
        return root;
    }

    // Este metodo lee el archivo de texto donde esta la lista de nodos. El primer valor lo toma como raiz del arból
    public static Nodo CargaArchivo(String archivo){
        Nodo raiz = null;
        try{
            Scanner sc = new Scanner(new File(archivo));
            while (sc.hasNextLine()){
                String linea = sc.nextLine();
                if (linea.isEmpty()){
                    continue;
                }
                int valor = Integer.parseInt(linea);
                raiz = Insert(raiz, valor);
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return raiz;
    }

    // Este metodo reccore el arból de la siguiente manera; la raíz se recorre antes que los recorridos de los subárboles izquierdo y derecho.
    static void PreOrder(Nodo n){
        if (n != null) {
            Despliega(n);
            PreOrder(n.izq);
            PreOrder(n.der);
        }
    }

    // Este metodo reccore el arból de la siguiente manera; la raíz se recorre después de los recorridos por el subárbol izquierdo y el derecho.
    static void PostOrder(Nodo n){
        if (n != null) {
            PostOrder(n.izq);
            PostOrder(n.der);
            Despliega(n);
        }
    }

    // Este metodo recorre el arból de la siguiente manera; la raíz se recorre entre los recorridos de los árboles izquierdo y derecho.
    static void InOrder(Nodo n){
        if (n != null) {
            InOrder(n.izq);
            Despliega(n);
            InOrder(n.der);
                    }
    }

    // Este metodo despliega el arból de valor en valor del nodo.
    static void Despliega(Nodo n){
        if (n != null) {
            System.out.println(n.valor);
        }
    }

    // Este metodo busca en el arbol un elemento "x" de valor entero.
    static boolean Busqueda (Nodo n, int x){
        boolean encontrado = false;
        if (n != null) {
            if (x == n.valor) {
                encontrado = true;
                System.out.println("Se encontro "+x);
            }
            else{
                if (encontrado == false) {
                    encontrado = Busqueda(n.izq,x);
                    System.out.println("Se encontro "+x);
                }
                if (encontrado == false) {
                    encontrado = Busqueda(n.der, x);
                    System.out.println("Se encontro "+x);
                }
            }
        }
        return encontrado;
    }

    // Este metodo guarda los elemntos del arbol en un stack, con la sintaxis de la pagina web,
    static Stack<String>  GeneraInOrder(Nodo n, Stack <String> s){
        if (n != null) {
            s = GeneraInOrder(n.der,s);
            if (n.der != null) {
                s.push("\""+String.valueOf(n.valor)+"\"--"+"\""+String.valueOf(n.der.valor)+"\"");
            }


            if (n.izq != null) {
                s.push("\""+String.valueOf(n.valor)+"\"--"+"\""+String.valueOf(n.izq.valor)+"\"");
            }
            s = GeneraInOrder(n.izq,s);
        }
        return s;
    }

    // Este metodo guarda el arbol guardado en el stack en un archivo de texto.
    static void ImprimeGrafo(Stack s){
        try{
            File salida = new File("/Users/villalobos28/Desktop/EstructurasDeDatos/Salida.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(salida));
            bw.write("graph G {");
            bw.newLine();

            do{
                bw.write("  "+s.pop());
                bw.newLine();

            } while (!s.isEmpty());

            bw.write("}");
            bw.close();
            System.out.println("Texto creado en "+salida.getAbsolutePath());
        } catch(Exception e){
            System.out.println("Error al escribir");
        }

    }

}
