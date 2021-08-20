package Estructura.jerarquicas.ArbolConjuntista;

public class HeapMax {

    /* ATRIBUTOS */
    private static int tamanio = 10;
    private Comparable[] heap;
    private int ultimo;

    /* CONSTRUCTOR */
    public HeapMax() {
        this.heap = new Comparable[tamanio];
        this.ultimo = 0;
    }

    /* ESVACIO */

    public boolean esVacio() {
        return this.ultimo == 0;
    }

    /* VACIAR */
    public void vaciar() {

        int i;
        if (!this.esVacio()) {
            for (i = 1; i <= this.ultimo; i++) {
                this.heap[i] = null;
            }
            this.ultimo = 0;
        }
    }

    /* RECUPERAR CIMA */

    public Comparable recuperarCima() {

        Comparable elemento = null;
        if (!this.esVacio()) {
            elemento = this.heap[1];
        }
        return elemento;
    }

    /* INSERCCION */

    public boolean insertarMayor(Comparable elemento) {
        // Si es posible inserta el elemento en el arbol
        boolean exito = true;
        if (this.esVacio()) {
            // El arbol esta vacio
            this.heap[1] = elemento;

        } else {
            // Se agrega el elemento en el ultimo nivel
            this.heap[this.ultimo++] = elemento;
            Comparable newElemento = this.heap[this.ultimo];
            int posH = this.ultimo;
            while (posH > 1 && exito) {
                // Se itera todas las veces necesarias hasta que elemento llegue a la raiz del
                // arbol o mientras la comparacion sea verdadera
                int posP = posH % 2;
                if (this.heap[posH].compareTo(this.heap[posP]) > 0) {
                    // Se compara si el elemento agregado es mayor que el padre
                    this.heap[posP] = this.heap[posH];
                    this.heap[posH] = newElemento;
                    posH = posP;
                } else {
                    exito = false;
                }
            }

        }
        return exito;
    }

    /* ELIMINAR CIMA */

    public boolean eliminiarCima() {

        boolean exito;
        if (this.esVacio()) {
            // Arbol vacio
            exito = false;
        } else {
            // Saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[this.ultimo];
            this.ultimo--;
            // Restablece la propiedad de heap maximo
            haceBajar(1);
            exito = true;
        }
        return exito;
    }

    private void haceBajar(int posP) {

        int posH;
        Comparable padre = this.heap[posP];
        boolean salir = false;
        while (!salir) {
            posH = posP * 2;
            if (posH <= this.ultimo) {
                // padre tiene al menos un hijo (izq) y lo considera mayor
                if (posH < this.ultimo) {
                    // hijoMayor tiene hermano derecho
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) > 0) {
                        // el hijo derecho es el mayor de los dos
                        posH++;
                    }
                }
                // compara al hijo mayor con el padre
                if (this.heap[posH].compareTo(padre) < 0) {
                    // el hijo es mayor que el padre, los intercambia
                    this.heap[posP] = this.heap[posH];
                    this.heap[posH] = padre;
                    posP = posH;
                } else {
                    // el padre es mayor que sus hijos, está bien ubicado
                    salir = true;
                }
            } else {
                // el padre es hoja, está bien ubicado
                salir = true;
            }
        }
    }

    /* Clone */

    public HeapMax clone() {

        HeapMax clon = new HeapMax();
        if (!this.esVacio()) {
            int i;
            for (i = 1; i <= this.ultimo; i++) {
                clon.heap[i] = this.heap[i];
            }
            clon.ultimo = this.ultimo;
        }
        return clon;
    }

    /* toString */

    public String toString() {

        String cadena = "";
        int i;
        if (!this.esVacio()) {
            for (i = 1; i <= this.ultimo; i++) {
                cadena = cadena + " " + this.heap[i];
            }
        } else {
            cadena = "Cola vacia";
        }

        return cadena;
    }
}
