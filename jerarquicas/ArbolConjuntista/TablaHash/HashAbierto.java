package Estructura.jerarquicas.ArbolConjuntista.TablaHash;

import Estructura.Lineales.Dinamicas.*;

public class HashAbierto {
    
    /*      Atributos       */
    private static int tamanio = 13;   // Tamanio de la tabla hash
    private Nodo [] hash;  // Arreglo de nodos 
    private int cant;   // Cantidad de elementos que tiene la tabla hash

    /*      Constructor     */

    public HashAbierto(){
        this.hash = new Nodo[tamanio];
        this.cant = 0;
    }

    /*      Hash vacio      */

    public boolean esVacio(){
        return this.cant == 0;
    }

    /*      Insertar        */

    public boolean insertar(Object elemento){

        int pos = elemento.hashCode() % this.tamanio;   // Calcula la posicion en la que se va a guarda el elemento
        Nodo aux = this.hash[pos];                      // Obtiene el pimer nodo en esa posicion
        boolean encontrado = false;

        while(!encontrado && aux != null){
            // Busca si esta el nuevo elemento en esa posicion
            encontrado = aux.getElemento().equals(elemento);
            aux = aux.getEnlace();
        }
        if(!encontrado){
            // Si el elemento no se encontro entonces se guarda al principio del conjunto
            this.hash[pos] = new Nodo(elemento, this.hash[pos]);
            this.cant++;
        }
        return !encontrado;
    }

    /*      Pertenece       */
    
    public boolean pertenece(Object elemento){

        int pos = elemento.hashCode() % this.tamanio;
        Nodo aux = this.hash[pos];
        boolean encontrado = false;

        while(!encontrado && aux != null){
            // Busca si esta el nuevo elemento en esa posicion
            encontrado = aux.getElemento().equals(elemento);
            aux = aux.getEnlace();
        }

        return encontrado;
    }
    
    /*      Eliminar        */

    public boolean eliminar(Object elemento){

        int pos = elemento.hashCode() % this.tamanio;
        Nodo aux = this.hash[pos];
        Nodo anterior = aux;
        boolean eliminado = false;

        if(aux.getElemento().equals(elemento)){
            // caso 1: el elemeneto a eliminar es el primero en el conjunto
            this.hash[pos] = aux.getEnlace();
            this.cant--;
            eliminado = true;
        }else{
            aux = aux.getEnlace();
            while(!eliminado && aux != null){
                // Busca si esta el nuevo elemento en esa posicion
                eliminado = aux.getElemento().equals(elemento);
                if(eliminado){
                    // caso 2: el elemento a eliminar es cualquier del conjunto
                    anterior.setEnlace(aux.getEnlace());
                    this.cant--;
                }
                anterior = aux;
                aux = aux.getEnlace();
            }
        }
        return eliminado;
    }

    public String toString(){
        String cadena = "";
        int i;
        if(!this.esVacio()){
            for (i=0; i < tamanio; i++){
                cadena = cadena + i + " --> ";
                Nodo aux = this.hash[i];
                while(aux != null){
                    cadena += aux.getElemento() + " --> ";
                    aux = aux.getEnlace();
                }
                cadena += "\n";
            }
        }else{
            cadena += "tabla hash vacia";
        }
        return cadena;
    }

    public void vaciar(){
        int i;
        for(i=0; i < tamanio; i++){
                this.hash[i] = null;
                if(!this.esVacio()){
                    this.cant--;
                }
            }
    }
}
