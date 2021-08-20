package Estructura.Lineales.Dinamicas;

/*
- clone funciona OK. Clone2 no está bien nodoOriginal = this.tope.getEnlace() dentro del while (no avanza en la cola original)
*/
public class Pila {
    
    // Atributos 
    private Nodo tope; 

    // Constructor Vacio 
    public Pila(){
        this.tope = null;
    }

    // Apilar
    public boolean apilar(Object newElemt){

        Nodo newNodo = new Nodo(newElemt,this.tope); // Crear un nodo con el elemnto que se quiere apilar y el enlace de ese nodo toma el valor que tiene el Tope
        this.tope = newNodo; // el tope toma como valor de enlace al nodo anteriormente creado
        return true; // Siempre devolvera verdadero el apilar ya que el tamaño de la pila no esta definido
    }

    // Desapilar
    public boolean desapilar(){
        boolean exito;
        if(esVacia()){
            // pila vacia
            exito = false;
        }else{
            // cambia el valor de tope por el nodo posterior al nodo desenlazado 
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }
    
    // Obtener Tope de la pila
    public Object obtenerTope(){
        Object tope = null; // Devolvera null si la pila esta vacia
        if(!esVacia()){
            tope = this.tope.getElemento(); // Devuelve el elemento que esta en el tope de la pila
        }
        return tope; 
    }

    // Pila vacia
    public boolean esVacia(){
        return this.tope == null; // Devuelve null porque el tope no esta enlazado a ningun nodo  
    }

    // Vaciar pila
    public void vaciar(){

        if(!esVacia()){             // Si no esta vacia, vacia la pila 
            this.tope = null;       // Al poner el TOPE en null los nodos enlazados son recogidos por el garbage collector
        }
    }
    
    // Clonar Nodo
    private Nodo cloneNodo(Nodo nodoAux){ // metodo privado porque solo es necesario que lo use el metodo clonPila
    // Se declara un nuevo, nodoTope, para copiar en el el contenido de los nodos de la pila orginal  
        Nodo nodoTope = null; 
        if(nodoAux != null){
    
            nodoTope = new Nodo(nodoAux.getElemento(), cloneNodo(nodoAux.getEnlace()));
        }

        return nodoTope;
    }
   
    // Clonar pila - metodo recursivo
    public Pila clone(){

        Pila nuevaPila = new Pila();
        nuevaPila.tope = cloneNodo(this.tope); // Al copiarse todos los nodos de la pila original, se instancia al ultimo nodo copia como tope de la pila clon
        return nuevaPila;
    }

    // Clonar pila - metodo iterativo 
    public Pila Clone2(){

        Pila nuevaPila = new Pila();
        Nodo nodoOriginal, nodoCopia, nodoAux;                              
        if (!this.esVacia()){
            nodoCopia = new Nodo(this.tope.getElemento(), null);           // Creo un nodo con el elemento del tope de la pila original
            nuevaPila.tope = nodoCopia;                                    // Se lo asigno al tope de la nuevaPila 
            nodoOriginal = this.tope.getEnlace();                          // sera el nodo que le sigue al tope, de la pila original 
            while(nodoOriginal != null){                                   // Itera hasta que me encuentro con NULL 
                nodoAux = new Nodo(nodoOriginal.getElemento(), null);      // Creo nodoAux con el elemento de nodoOriginal
                nodoCopia.setEnlace(nodoAux);                              // Al nodoCopia lo enlazo con el nodo creado, nodoAux
                // Primera Iteracion enlaza nodo 2 al tope de la nuevaPila
                nodoCopia = nodoCopia.getEnlace();                         // nodoCopia pasa a ser el nodo creado al que se enlazo
                nodoOriginal = this.tope.getEnlace();                      // nodoOriginal pasa a ser el siguiente nodo de la pila original         
            }
        }
        return nuevaPila;
    }

    // toString
    public String toString(){
    // Se devuelve un String con todos los elementos, de la pila, en el orden en que fueron ingresados    
        String s="";

        if(this.tope == null){
            s = "Pila vacia";
        }else{
            Nodo aux = this.tope;
            s = "]";
            
            while(aux !=null){
                s = aux.getElemento().toString() + s;
                aux = aux.getEnlace();
                if(aux != null){
                    s = " , " + s;
                }else{
                    s = "[" + s;
                }
            }
        }
        return s;
    }
}
