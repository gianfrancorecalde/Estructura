package Estructura.Grafo;

public class NodoAdy {
    
    private NodoVert vertice;
    private NodoAdy sigAdyacente;

    public NodoAdy(NodoVert vert, NodoAdy ady){
        this.vertice = vert;
        this.sigAdyacente = ady;
    }

    public NodoVert getVertice(){
        return this.vertice;        
    }
    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }

    public void setVertice(NodoVert newVertice){
        this.vertice = newVertice;
    }
    public void setSigAdyacente(NodoAdy newAdyacente){
        this.sigAdyacente = newAdyacente;
    }
}
