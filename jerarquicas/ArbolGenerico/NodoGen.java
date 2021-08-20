package Estructura.jerarquicas.ArbolGenerico;

public class NodoGen {
    
    // Atributo
    private Object element;
    private NodoGen hijoIzq;
    private NodoGen hnoDer;

    //Constructor 
    public NodoGen(Object newElement, NodoGen newHijoIzq, NodoGen newHnoDer){
        
        this.element = newElement;
        this.hijoIzq = newHijoIzq;
        this.hnoDer = newHnoDer;
    }

    // Métodos Gets
    public Object getElemento(){
        return this.element;
    }
    public NodoGen getHijo(){
        return this.hijoIzq;
    }
    public NodoGen getHno(){
        return this.hnoDer;
    }

    // Métodos Sets
    public void setElemento(Object otherElement){
        this.element = otherElement;
    }
    public void setHijo(NodoGen otroHijo){
        this.hijoIzq = otroHijo;
    }
    public void setHno(NodoGen otroHno){
        this.hnoDer = otroHno;
    }

}
