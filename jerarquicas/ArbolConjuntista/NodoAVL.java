package Estructura.jerarquicas.ArbolConjuntista;

public class NodoAVL {
    
    // Atributos
    private Comparable elem;
    private NodoAVL izquierdo;    // Guarda el nodo hijo izquierdo
    private NodoAVL derecho;      // Guarda el nodo hijo derecho
    private int altura;

    // Construtor 
    public NodoAVL(Comparable newElem, NodoAVL hijoIzquierdo, NodoAVL hijoDerecho){

        this.elem = newElem;
        this.izquierdo = hijoIzquierdo;
        this.derecho = hijoDerecho;
        this.altura = 0;
    }

    // Métodos Gets
    public Comparable getElem(){
        return this.elem;
    }
    public NodoAVL getIzquierdo(){
        return this.izquierdo;
    }
    public NodoAVL getDerecho(){
        return this.derecho;
    }
    public int getAltura(){
        return this.altura;
    }

    // Métodos Sets
    public void setElem(Comparable otherElem){
        this.elem = otherElem;
    }
    public void setIzquierdo(NodoAVL otroHijoIzquierdo){
        this.izquierdo = otroHijoIzquierdo;
    }
    public void setDerecho(NodoAVL otroHijoDerecho){
        this.derecho = otroHijoDerecho;
    }
    public void recalcularAltura(){

        if(this.izquierdo != null && this.derecho != null){
            if(this.izquierdo.getAltura() > this.derecho.getAltura()){
                this.altura = this.izquierdo.getAltura()+1;
            }else{
                this.altura = this.derecho.getAltura() +1;
            }
        }else{
            if(this.izquierdo != null && this.derecho == null){
                this.altura = this.izquierdo.getAltura() +1;
            }else{
                if(this.izquierdo == null && this.derecho != null){
                    this.altura = this.derecho.getAltura() +1;
                }else{
                    if(this.izquierdo == null && this.derecho == null){
                        this.altura = 0;
                    }
                }
            }
        }
    }
}
