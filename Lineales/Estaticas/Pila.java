package Estructura.Lineales.Estaticas;

public class Pila {
    
    // Atributos 
    private Object [] arr;
    private static final int tamanio = 10; // Cantidad de elemento que puede contener la pila
    private int tope;

    // Constructor Vacio 
    public Pila(){
        this.arr = new Object[tamanio];
        this.tope = -1; // Referencia por convencion de la catedra, que indica que la pila esta vacia.
    }

    // Apilar
    public boolean apilar(Object newElemt){
        boolean exito;
        if(this.tope+1>= tamanio){
            // pila llena
            exito = false;
        }else {
            // pila con lugares libre
             this.tope ++;
             this.arr[tope] = newElemt;
             exito = true;
        }
        return exito;
    }

    // Desapilar
    public boolean desapilar(){
        boolean exito;
        if(esVacia()){
            // pila vacia
            exito = false;
        }else{
            // elemento en el tope desapilado
            this.arr[this.tope] = null;
            this.tope--;
            exito = true;
        }
        return exito;
    }
    
    // Obtener Tope de la pila
    public Object obtenerTope(){ // Devuelve el elemento, que se encuentra en el tope de la pila
        Object tope = null;
        if(!esVacia()){
            tope = arr[this.tope];
        }
        return tope; 
    }

    // Pila vacia
    public boolean esVacia(){
        
        return this.tope == -1;
    }

    // Vaciar pila
    public void vaciar(){
        
        int i;  
        if(!esVacia()){
            for(i=0; i<=this.tope; i++){
                this.arr[i] = null;
            }
            this.tope = -1;
        }
    }

    // toString 
    public String toString(){
        // Devuelve un cadena con todos los elementos que contiene la pila
        int i;
        String cadena = "";
        if(!esVacia()){
            for(i=0; i<this.tope+1;i++){
            cadena = cadena + arr[i] + " ";
            }
        }else{
            cadena = "Pila Vacia"; // Cuando la pila este vacia
        }
        return cadena;
    }
    
    // Clonar pila
    public Pila clone(){
        // Copia los elementos de una pila en otra pila secundaria
        Pila pilaClon = new Pila();
        pilaClon.tope = this.tope;
        int i;
        for(i=0; i<this.tope+1;i++){
            pilaClon.arr[i] = this.arr[i];
        }
        return pilaClon;
    }
    
    public Pila clone2(){
        // Segunda opcion para clonar pila
        Pila pilaClon = new Pila();
        pilaClon.tope = this.tope;
        pilaClon.arr = this.arr.clone(); // Se utiliza metodo para arreglos de java, clone()
        return pilaClon;
    }
}
