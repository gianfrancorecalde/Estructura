package Estructura.jerarquicas.ArbolConjuntista;

public class HeapMin {

    /*          ATRIBUTOS           */
    private static int tamanio = 10;
    private Comparable [] heap;
    private int ultimo;

    /*          CONSTRUCTOR         */
    public HeapMin(){
        this.heap = new Comparable[tamanio];
        this.ultimo = 0;
    }
    
    /*          ESVACIO            */

    public boolean esVacio(){
        return this.ultimo == 0;
    }

    /*          VACIAR           */
    public void vaciar(){
        
        int i;
        if(!this.esVacio()){
            for(i=1; i<=this.ultimo; i++){
                this.heap[i] = null;
            }
            this.ultimo = 0;
        }
    }

    /*          RECUPERAR CIMA          */

    public Comparable recuperarCima(){

        Comparable elemento= null;
        if(!this.esVacio()){
            elemento = this.heap[1];
        }
        return elemento;
    }
    
    /*          INSERCCION         */

    public boolean insertarMenor(Comparable elemento){
        
        boolean exito = true;
        if(this.esVacio()){
            this.heap[1] = elemento;

        }else{
            this.heap[this.ultimo++] = elemento;
            Comparable newElemento = this.heap[this.ultimo];
            int posH = this.ultimo;
            while(posH > 1 && exito){
                int posP = posH%2;
                if(this.heap[posH].compareTo(this.heap[posP]) < 0){
                    this.heap[posP] = this.heap[posH];
                    this.heap[posH] = newElemento;
                    posH = posP;
                }else{
                    exito = false;
                }
            }
            
        }
        return exito;
    }

    /*          ELIMINAR CIMA        */

    public boolean eliminiarCima(){

        boolean exito;
        if(this.esVacio()){
            // Arbol vacio
            exito = false;
        }else{
            // Saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[this.ultimo];
            this.ultimo--;
            // Restablece la propiedad de heap minimo
            haceBajar(1);
            exito = true;
        }
        return exito;
    }

    private void haceBajar(int posP){

        int posH;
        Comparable padre = this.heap[posP];
        boolean salir = false;
        while(!salir){
            posH = posP*2;
            if(posH <= this.ultimo){
                // padre tiene al menos un hijo (izq) y lo considera menor
                if(posH < this.ultimo){
                    // hijoMenor tiene hermano derecho
                    if(this.heap[posH+1].compareTo(this.heap[posH]) < 0){
                        // el hijo derecho es el menor de los dos
                        posH++;
                    }
                }
                // compara al hijo menor con el padre
                if(this.heap[posH].compareTo(padre) < 0){
                    // el hijo es menor que el padre, los intercambia
                    this.heap[posP] = this.heap[posH];
                    this.heap[posH] = padre;
                    posP = posH;
                }else{
                    // el padre es menor que sus hijos, está bien ubicado
                    salir = true;
                }
            }else{
                // el padre es hoja, está bien ubicado
                salir = true;
            }
        }
    }

    /*          Clone        */

    public HeapMin clone(){

        HeapMin clon = new HeapMin();
        if(!this.esVacio()){
            int i;
            for(i=1; i<= this.ultimo; i++){
                clon.heap[i] = this.heap[i];
            }
            clon.ultimo = this.ultimo;
        }
        return clon;
    }

    /*          toString       */

    public String toString(){
        
        String cadena = "";                                         
        int i;                               
        if(!this.esVacio()){
            for(i=1; i<= this.ultimo; i++){
                cadena = cadena + " " + this.heap[i];
            }
        }else{
            cadena = "Cola vacia";
        }

        return cadena;
    }
}
