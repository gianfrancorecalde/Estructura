package Estructura.jerarquicas.ArbolConjuntista.TablaHash;


public class HashCerrado {
    
    /*  Atributos   */

    private static int tamanio = 10;
    private CeldaHash [] hash;
    private int cant;
    private int vacio = 0;
    private int ocupado = 1;
    private int borrado = -1;
    
    /*  Constrcutor */

    public HashCerrado(){
        this.hash = new CeldaHash[tamanio];
        this.cant = 0;
    }

    /* esVacio */
    public boolean esVacio(){
        return this.cant == 0;
    }

    /* Pertenece */
    public boolean pertenece(Object buscado){
        boolean encontrado = false;
        int pos = buscado.hashCode()%this.tamanio;
        int incremento = Funciones.rehashing((int)buscado)%this.tamanio;
        int intento = 1;
        while(!encontrado && intento < this.tamanio){
            if(this.hash[pos].getEstado() == 1){
                if(this.hash[pos].getElemento().equals(buscado)){
                    encontrado = true;
                }
            }
            pos=(pos+intento*incremento)%this.tamanio;
            intento++;
        }
        return encontrado;
    }

    /* Insertar */
    public boolean insertar2(Object elemento){
        int pos = elemento.hashCode() % this.tamanio;
        int incremento = Funciones.rehashing((int)elemento) % this.tamanio;
        int intento = 1;
        int otraPos = 0;
        boolean insertado = false;
        while(!insertado && intento < this.tamanio && this.hash[pos].getElemento().equals(elemento)){
            // corta si ya existe el elemento o si la cantidad de intentos supera el tamanio
            if(this.hash[pos].getEstado() == 0){
                this.hash[pos].setElemento(elemento);
                this.hash[pos].setEstado(1);
                this.cant++;
                insertado = true;
            }
            if(this.hash[pos].getEstado() == -1){
                otraPos = pos;
            }
            pos = (pos + intento *incremento) % this.tamanio;
            intento++;
        }
        if(intento >= this.tamanio && otraPos != 0){
            // caso en el que existe una celda con estado borrado
            this.hash[otraPos].setElemento(elemento);
            this.hash[otraPos].setEstado(1);
            this.cant++;
            insertado = true;
        }
        return insertado;
    }

    /* Eliminar */
    public boolean eliminar(Object buscado){
        // calcula posicion inicial e incrementa
        int pos = buscado.hashCode() % this.tamanio;
        int incremento = Funciones.rehashing((int)buscado) % this.tamanio;
        boolean encontrado = false;
        int intento = 1;
        // busca el elemento hasta encontrarlo o encoontrar una celda vacia
        // o para despues de tam intentos
        while(!encontrado && intento<this.tamanio && this.hash[pos].getEstado() != 0){
            if(this.hash[pos].getEstado() == 1){
                if(this.hash[pos].getElemento().equals(buscado)){
                    this.hash[pos].setElemento(null);
                    this.hash[pos].setEstado(-1);
                    this.cant--;
                }
            }
            pos = (pos + intento * incremento) % this.tamanio;
            intento ++; 
        }
        return encontrado;
    }
}
