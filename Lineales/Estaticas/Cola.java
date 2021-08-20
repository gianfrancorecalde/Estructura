package Estructura.Lineales.Estaticas;

public class Cola {
    
    // Atributo
    private Object [] arr;
    private static final int tamanio = 10;
    private int frente;
    private int fin;
    
    // Constructor vacio 
    public Cola(){
        this.arr = new Object[tamanio];
        this.frente = 0;
        this.fin = 0;
    }

    // Poner elementos
    public boolean poner(Object newElement){
        
        boolean exito = false;

        if((fin+1)%tamanio != frente){    // Si la posicion siguiente a la que referencia la variable fin es distinta de la posicion a la que referencia la variable frente  
        // verficia si la cola esta llena
            arr[fin] = newElement;            // Guarda en la posicion en el arreglo, referencia por fin, el newElement
            fin = (fin+1)%tamanio;        // Incremento el fin en relacion al MOD con tamaño
            exito = true;                               // Confirmo que se pudo agregar el newElement a la cola
        }

        return exito;
    }

    // Cola vacia
    public boolean esVacia(){

        return this.frente == this.fin;
    }

    // Sacar elementos 
    public boolean sacar(){

        boolean exito = false;                          // Cuando la cola esta vacia

        if(!esVacia()){
            this.arr[this.frente] = null;               // la posicion frente pasa a referencia a un objeto null
            this.frente = (this.frente+1)%this.tamanio;  // Se incrementa el frente en relacion al MOD con tamaño
            exito = true;                               // Cuando se saco un elemento de la cola
        }

        return exito;
    }

    // Vaciar cola
    public void vaciar(){
        
        if(!this.esVacia()){                        
            
            boolean vaciando = true;            
            while(vaciando){                
                vaciando = this.sacar();
            }
            System.out.println("Cola vaciada");
        }else{
            System.out.println("Cola vacia");
        }
    }

    // Obtener frente 
    public Object obtenerFrente(){

        Object element = null;

        if(!esVacia()){
            element = this.arr[this.frente];;
        }
        return element;
    }
    
    // Clonar cola
    public Cola clone(){                                    

        Cola colaClon = new Cola();                         // Creo una nueva cola
        colaClon.frente = this.frente;                      // Comienzo de la cola
        colaClon.fin = this.fin;                            // Fin de la cola 
        int posicion = this.frente;                         // POSICION va a recorrer el arreglo a partir de la posicion en tenga FRENTE

        if(!this.esVacia()){
            while((posicion)%this.tamanio != colaClon.fin){    // No incremento POSICION porque no me copia el elemento en la posicion FIN de la cola original
            
                colaClon.arr[posicion] = this.arr[posicion];     // copia el elemento ubicado en POSICION de la cola original a la misma posicion pero en la cola clon 
                posicion = (posicion+1)%this.tamanio;             // paso a la siguiente posicion de la cola original 
            }
        }else{
            System.out.println("Cola original vacia");
        }
        
        return colaClon;
    }

    // toString 
    public String toString(){
        
        String cadena = "";                                         
        int posicion = this.frente;                             

        if(!this.esVacia()){
                while( (posicion)%this.tamanio != this.fin){     // No incremento POSICION porque no me imprime el elemento en la posicion FIN
                    
                cadena = cadena + " " + this.arr[posicion];
                posicion = (posicion+1)%this.tamanio;
            }
        }else{
            cadena = "Cola vacia";
        }

        return cadena;
    }
}
