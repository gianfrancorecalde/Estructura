package Estructura.jerarquicas.ArbolConjuntista;

public class NodoABB {
    
    // Atributos
    private Comparable elem;
    private NodoABB izquierdo;    // Guarda el nodo hijo izquierdo
    private NodoABB derecho;      // Guarda el nodo hijo derecho

    // Construtor 
    public NodoABB(Comparable newElem, NodoABB hijoIzquierdo, NodoABB hijoDerecho){

        this.elem = newElem;
        this.izquierdo = hijoIzquierdo;
        this.derecho = hijoDerecho;
    }

    // Métodos Gets
    public Comparable getElem(){
        return this.elem;
    }
    public NodoABB getIzquierdo(){
        return this.izquierdo;
    }
    public NodoABB getDerecho(){
        return this.derecho;
    }

    // Métodos Sets
    public void setElem(Comparable otherElem){
        this.elem = otherElem;
    }
    public void setIzquierdo(NodoABB otroHijoIzquierdo){
        this.izquierdo = otroHijoIzquierdo;
    }
    public void setDerecho(NodoABB otroHijoDerecho){
        this.derecho = otroHijoDerecho;
    }
}
