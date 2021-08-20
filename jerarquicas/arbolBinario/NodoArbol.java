package Estructura.jerarquicas.arbolBinario;

public class NodoArbol {
    
    // Atributos
    private Object elem;
    private NodoArbol izquierdo;    // Guarda el nodo hijo izquierdo
    private NodoArbol derecho;      // Guarda el nodo hijo derecho

    // Construtor 
    public NodoArbol(Object newElem, NodoArbol hijoIzquierdo, NodoArbol hijoDerecho){

        this.elem = newElem;
        this.izquierdo = hijoIzquierdo;
        this.derecho = hijoDerecho;
    }

    // Métodos Gets
    public Object getElem(){
        return this.elem;
    }
    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }
    public NodoArbol getDerecho(){
        return this.derecho;
    }

    // Métodos Sets
    public void setElem(Object otherElem){
        this.elem = otherElem;
    }
    public void setIzquierdo(NodoArbol otroHijoIzquierdo){
        this.izquierdo = otroHijoIzquierdo;
    }
    public void setDerecho(NodoArbol otroHijoDerecho){
        this.derecho = otroHijoDerecho;
    }
}
