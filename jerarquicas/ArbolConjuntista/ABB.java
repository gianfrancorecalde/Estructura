package Estructura.jerarquicas.ArbolConjuntista;

import Estructura.Lineales.Dinamicas.*;

public class ABB {

    /*
     * Los hijos DgetDerechos estan los hijos menores a la raiz Los hijos derechos
     * estan los hijos mayores a la raiz ABB no acepta elementos repetidos ABB es un
     * arbol conjuntista completo y totalmente ordenado Metodo insertar de O(log n)
     * Metodo busqueda de O(log n). porque siempre hay una visita por nivel.
     * 
     */

    /* ATRIBUTOS */

    private NodoABB raiz;

    /* CONSTRUCTOR */

    public ABB() {
        this.raiz = null;
    }

    /* ESVACIO */

    public boolean esVacio() {
        return this.raiz == null;
    }

    /* VACIAR */

    public void vaciar() {
        this.raiz = null;
    }

    /*------------------------------------------------------    ITERATIVO   -------------------------------------------------------*/

    /* INSERTAR */

    public boolean insertarIterativo(Comparable elemento) {

        boolean salir = false;
        if (this.esVacio()) {
            this.raiz = new NodoABB(elemento, null, null);
            salir = true;
        } else {
            NodoABB recorrido = this.raiz;
            while (!salir && recorrido != null) {
                if (recorrido.getElem().compareTo(elemento) == 0) {
                    salir = true;
                } else {
                    if (elemento.compareTo(recorrido.getElem()) < 0) {
                        if (recorrido.getIzquierdo() == null) {
                            recorrido.setIzquierdo(new NodoABB(elemento, null, null));
                            salir = true;
                        } else {
                            recorrido = recorrido.getIzquierdo();
                        }
                    } else {
                        if (elemento.compareTo(recorrido.getElem()) > 0) {
                            if (recorrido.getDerecho() == null) {
                                recorrido.setDerecho(new NodoABB(elemento, null, null));
                                salir = true;
                            } else {
                                recorrido = recorrido.getDerecho();
                            }
                        }
                    }
                }
            }
        }
        return salir;
    }

    /* PERTENECE */

    public boolean perteneceIterativo(Comparable elemento) {

        boolean salir = false;
        if (!this.esVacio()) {
            NodoABB recorrido = this.raiz;
            while (!salir && recorrido != null) {
                if (recorrido.getElem().compareTo(elemento) == 0) {
                    salir = true;
                } else {
                    if (elemento.compareTo(recorrido.getElem()) < 0) {
                        recorrido = recorrido.getIzquierdo();
                    } else {
                        if (elemento.compareTo(recorrido.getElem()) > 0) {
                            recorrido = recorrido.getDerecho();
                        }
                    }
                }
            }
        }
        return salir;
    }

    /* MINIMOELEMENTO */

    public Comparable minElemento() {

        Comparable elementoMin = null;
        if (!this.esVacio()) {
            NodoABB recorrido = this.raiz;
            while (elementoMin == null && recorrido != null) {
                if (recorrido.getIzquierdo() == null) {
                    elementoMin = recorrido.getElem();
                }
                recorrido = recorrido.getIzquierdo();
            }
        }
        return elementoMin;
    }

    /* MAXIMOELEMENTO */

    public Comparable maxElemento() {

        Comparable elementoMax = null;
        if (!this.esVacio()) {
            NodoABB recorrido = this.raiz;
            while (elementoMax == null && recorrido != null) {
                if (recorrido.getDerecho() == null) {
                    elementoMax = recorrido.getElem();
                }
                recorrido = recorrido.getDerecho();
            }
        }
        return elementoMax;
    }

    /*------------------------------------------------------    RECURSIVO  -------------------------------------------------------*/

    /* PERTENECE */

    public boolean perteneceRecursivo(Comparable elemento) {

        boolean exito = false;
        if (!this.esVacio()) {
            exito = perteneceAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean perteneceAux(NodoABB nodo, Comparable elemento) {

        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elemento) == 0) {
                exito = true;
            } else {
                if (elemento.compareTo(nodo.getElem()) < 0) {
                    exito = perteneceAux(nodo.getIzquierdo(), elemento);
                } else {
                    if (elemento.compareTo(nodo.getElem()) > 0) {
                        exito = perteneceAux(nodo.getDerecho(), elemento);
                    }
                }
            }
        }
        return exito;
    }

    /* INSERTAR */

    public boolean insertarRecursivo(Comparable elemento) {

        boolean exito = false;
        if (this.esVacio()) {
            this.raiz = new NodoABB(elemento, null, null);
            exito = true;
        } else {
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB nodo, Comparable elemento) {

        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elemento) == 0) {
                exito = true;
            } else {
                if (elemento.compareTo(nodo.getElem()) < 0) {
                    exito = insertarAux(nodo.getIzquierdo(), elemento);
                    if (!exito) {
                        nodo.setIzquierdo(new NodoABB(elemento, null, null));
                        exito = true;
                    }
                } else {
                    if (elemento.compareTo(nodo.getElem()) > 0) {
                        exito = insertarAux(nodo.getDerecho(), elemento);
                        if (!exito) {
                            nodo.setDerecho(new NodoABB(elemento, null, null));
                            exito = true;
                        }
                    }
                }
            }
        }
        return exito;
    }

    /* LISTA */

    public Lista listarInOrden() {

        Lista lista = new Lista();
        if (!this.esVacio()) {
            recorridoInOrdenMejorado(this.raiz, lista);
        }
        return lista;
    }

    private void recorridoInOrdenMejorado(NodoABB nodo, Lista lista) {

        if (nodo != null) {
            recorridoInOrdenMejorado(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            recorridoInOrdenMejorado(nodo.getDerecho(), lista);
        }
    }

    /* toString */

    public String toString() {

        String cadena;
        if (this.esVacio()) {
            cadena = "Arbol vacio";
        } else {
            cadena = generarString(this.raiz);
        }
        return cadena;
    }

    private String generarString(NodoABB n) {

        String cadena;
        if (n == null) {
            // Cuando el hijo es nulo devuelve "-"
            cadena = "-";
        } else {
            String cadenaHI = generarString(n.getIzquierdo());
            String cadenaHD = generarString(n.getDerecho());
            if (cadenaHI.equalsIgnoreCase("-") && cadenaHD.equalsIgnoreCase("-")) {
                // Si los hijos son nulos devuelve un String:
                // elemento HI: - HD: -
                cadena = n.getElem() + " HI: " + cadenaHI + " HD: " + cadenaHD;
            } else {
                if (!cadenaHI.equalsIgnoreCase("-") && cadenaHD.equalsIgnoreCase("-")) {
                    // Si solo el hijo derecho es nulo, devuelve un String:
                    // elemento HI: elementoHI HD: -
                    // elementoHI HI: ... HD: ...
                    cadena = n.getElem() + " HI: " + n.getIzquierdo().getElem() + " HD: " + cadenaHD + "\n" + cadenaHI;
                } else {
                    if (cadenaHI.equalsIgnoreCase("-") && !cadenaHD.equalsIgnoreCase("-")) {
                        // Si solo el hijo izquierdo es nulo, devuelve un String:
                        // elemento HI: - HD: elementoHD
                        // elementoHD HI: ... HD: ...
                        cadena = n.getElem() + " HI: " + cadenaHI + " HD: " + n.getDerecho().getElem() + "\n"
                                + cadenaHD;
                    } else {
                        // Si ninguno de los dos hijos es nulo, devuelve:
                        // elemento HI: elementoHI HD: elementoHD
                        // elementoHI HI: ... HD: ...
                        // elementoHD HI: ... HD: ...
                        cadena = n.getElem() + " HI: " + n.getIzquierdo().getElem() + " HD: " + n.getDerecho().getElem()
                                + "\n" + cadenaHI + "\n" + cadenaHD;
                    }
                }
            }

        }
        return cadena;
    }

    /* Clone */

    public ABB clone() {

        ABB copiaArbol = new ABB();
        if (this.raiz != null) {
            copiaArbol.raiz = cloneAux(this.raiz);
        }
        return copiaArbol;
    }

    private NodoABB cloneAux(NodoABB nodo) {

        NodoABB nodoArbolCopia = null;
        if (nodo != null) {
            nodoArbolCopia = new NodoABB(nodo.getElem(), null, null);
            NodoABB aux = cloneAux(nodo.getIzquierdo());
            nodoArbolCopia.setIzquierdo(aux);
            aux = cloneAux(nodo.getDerecho());
            nodoArbolCopia.setDerecho(aux);
        }
        return nodoArbolCopia;
    }

    /* ListarRango */

    public Lista listarRango(Comparable min, Comparable max) {

        Lista lista = new Lista();
        if (!this.esVacio()) {
            lista.insertar(min, lista.longitud() + 1);
            listarRangoAux(this.raiz, min, max, lista);
            lista.insertar(max, lista.longitud() + 1);
        }

        return lista;
    }

    private void listarRangoAux(NodoABB nodo, Comparable min, Comparable max, Lista lista) {

        if (nodo != null) {
            if ((min.compareTo(nodo.getElem()) < 0 || min.compareTo(nodo.getElem()) == 0)
                    && (max.compareTo(nodo.getElem()) > 0 || max.compareTo(nodo.getElem()) == 0)) {
                // min menor q raiz y max mayor q raiz
                listarRangoAux(nodo.getIzquierdo(), min, max, lista);
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
                listarRangoAux(nodo.getDerecho(), min, max, lista);
            } else {
                if (max.compareTo(nodo.getElem()) < 0 || max.compareTo(nodo.getElem()) == 0) {
                    // el maximo es menor q la raiz
                    listarRangoAux(nodo.getIzquierdo(), min, max, lista);
                    if (nodo.getElem().compareTo(min) > 0 && nodo.getElem().compareTo(max) < 0) {
                        lista.insertar(nodo.getElem(), lista.longitud() + 1);
                        listarRangoAux(nodo.getDerecho(), min, max, lista);
                    }

                } else {
                    if (min.compareTo(nodo.getElem()) > 0 || (min.compareTo(nodo.getElem()) == 0)) {
                        // el minimo es mayor q la raiz
                        listarRangoAux(nodo.getDerecho(), min, max, lista);
                        if (nodo.getElem().compareTo(min) > 0 && nodo.getElem().compareTo(max) < 0) {
                            lista.insertar(nodo.getElem(), lista.longitud() + 1);
                            listarRangoAux(nodo.getDerecho(), min, max, lista);
                        }

                    }
                }
            }
        }
    }

    /* Eliminar */

    public boolean eliminar(Comparable elemento) {

        boolean exito = false;
        if (!this.esVacio()) {
            this.raiz = eliminarAux(this.raiz, elemento);
            exito = true;
        }
        return exito;
    }

    private NodoABB eliminarAux(NodoABB nodo, Comparable elemento) {

        NodoABB n = null;
        if (nodo != null) {
            if (elemento.compareTo(nodo.getElem()) == 0) {
                if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                    // Caso 1: se manda al mismo hijo para ser eliminado
                    n = null;
                } else {
                    if (nodo.getDerecho() == null) {
                        // Caso 2: solo tiene hijo izquierdo
                        n = nodo.getIzquierdo();
                    } else {
                        if (nodo.getIzquierdo() == null) {
                            // Caso 2: solo tiene hijo derecho
                            n = nodo.getDerecho();
                        } else {
                            // Caso 3: Tiene dos hijos
                            n = aux(nodo);
                        }
                    }
                }
            } else {
                if (elemento.compareTo(nodo.getElem()) < 0) { // Elemento es menor al nodo
                    // Enlazo el hijo del hijo izquierdo del nodo actual, al nodo actual
                    nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), elemento));
                    n = nodo;
                } else { // Elemento es mayor al nodo
                    // Enlazo el hijo del hijo derecho del nodo actual, al nodo actual
                    nodo.setDerecho(eliminarAux(nodo.getDerecho(), elemento));
                    n = nodo;
                }
            }
        }
        return n;
    }

    private NodoABB aux(NodoABB nodo) {
        // Este metod se usa cuando el nodo buscado tiene dos hijos
        NodoABB hijo = nodo.getIzquierdo();
        NodoABB hijoIzq = hijo;
        NodoABB hijoDer = nodo.getDerecho();
        if ((hijoDer.getDerecho() == null && hijoDer.getIzquierdo() == null)
                && (hijoIzq.getDerecho() == null && hijoIzq.getIzquierdo() == null)) {
            // Se implementa este If para los caso en el que los hijo no tienen hijos
            // O en el caso de que uno de ellos si tenga hijos
            hijoIzq.setDerecho(hijoDer);
            nodo.setDerecho(null);
            hijo = hijoIzq;
        } else {
            if (hijoDer.getDerecho() == null && hijoDer.getIzquierdo() == null) {
                hijoDer.setIzquierdo(hijoIzq);
                nodo.setIzquierdo(null);
                hijo = hijoDer;
            } else {
                if (hijoIzq.getDerecho() == null && hijoIzq.getIzquierdo() == null) {
                    hijoIzq.setDerecho(hijoDer);
                    nodo.setDerecho(null);
                    hijo = hijoIzq;
                } else {
                    // Si ambos hijos tiene hijos entonces se hace un recorrido en busqueda del hijo
                    // mayor del hijo izquierdo.
                    NodoABB hijoAnterior = hijo;
                    boolean corte = false;
                    while (hijo != null && !corte) {
                        // Recorre los nodos izquierdo hasta encontrado el mayor
                        if (hijo.getDerecho() == null) {
                            hijo.setDerecho(nodo.getDerecho()); // Enlazo al nodo mayor los hijos derecho del nodo
                                                                // actual
                            nodo.setDerecho(hijo); // Enlazo el nodo actual con el nodo mayor como si fuera hijo derecho
                            hijoAnterior.setDerecho(null); // Desenlaza el nodo mayor del padre actual
                            hijo.setIzquierdo(nodo.getIzquierdo()); // Enlazo al nodo mayor los hijos izquierdo del nodo
                                                                    // actual
                            nodo.setIzquierdo(null); // Pongo en null enlace Izquierdo del nodo acutal
                            corte = true;
                        } else {
                            hijoAnterior = hijo;
                            hijo = hijo.getDerecho();
                        }
                    }
                }
            }
        }

        return hijo;
    }

    // -------------------------------------------------------------

    public boolean eliminarMinimo() {

        boolean exito = false;
        if (!this.esVacio()) {
            if (this.raiz.getIzquierdo() == null && this.raiz.getDerecho() == null) {
                this.raiz = null;
            } else {
                if (this.raiz.getIzquierdo() == null && this.raiz.getDerecho() != null) {
                    this.raiz = this.raiz.getDerecho();
                }
            }
            NodoABB hijo = this.raiz;
            NodoABB padre = hijo;
            while (hijo != null && !exito) {
                if (hijo.getIzquierdo() == null) {
                    padre.setIzquierdo(null);
                    exito = true;
                } else {
                    padre = hijo;
                    hijo = hijo.getIzquierdo();
                }
            }
        }
        return exito;
    }

    public ABB clonarParteInvertida(Comparable elemento) {

        ABB copiaArbol = new ABB();
        if (this.raiz != null) {
            copiaArbol.raiz = clonarParteInvertidaAux(this.raiz, elemento);
        }
        return copiaArbol;
    }

    private NodoABB clonarParteInvertidaAux(NodoABB nodo, Comparable elemento) {

        NodoABB nodoArbolCopia = null;
        if (nodo != null) {
            System.out.println("llama clonarPArteInvertida" + nodo.getElem());
            if (nodo.getElem().compareTo(elemento) == 0) {
                nodoArbolCopia = new NodoABB(nodo.getElem(), clonarHijosInvertidos(nodo.getDerecho()),
                        clonarHijosInvertidos(nodo.getIzquierdo()));
            } else {
                if (nodo.getElem().compareTo(elemento) < 0) {
                    nodoArbolCopia = clonarParteInvertidaAux(nodo.getDerecho(), elemento);
                } else {
                    nodoArbolCopia = clonarParteInvertidaAux(nodo.getIzquierdo(), elemento);
                }
            }
        }
        return nodoArbolCopia;
    }

    private NodoABB clonarHijosInvertidos(NodoABB nodo) {

        NodoABB copia = null;
        if (nodo != null) {
            System.out.println("llama clonarHijosInvertido" + nodo.getElem());
            copia = new NodoABB(nodo.getElem(), clonarHijosInvertidos(nodo.getDerecho()),
                    clonarHijosInvertidos(nodo.getIzquierdo()));
        }
        return copia;
    }

    private NodoABB obtenerNodo(NodoABB nodo, Comparable elemento) {

        NodoABB n = null;
        if (nodo != null) {
            if (nodo.getElem().compareTo(elemento) == 0) {
                n = nodo;
            } else {
                if (elemento.compareTo(nodo.getElem()) < 0) {
                    n = obtenerNodo(nodo.getIzquierdo(), elemento);
                } else {
                    if (elemento.compareTo(nodo.getElem()) > 0) {
                        n = obtenerNodo(nodo.getDerecho(), elemento);
                    }
                }
            }
        }
        return n;
    }

    public Lista listarMaqoyQ(Comparable valor, Comparable elemento) {

        Lista lis = new Lista();
        if (!this.esVacio()) {
            System.out.println(obtenerNodo(this.raiz, elemento).getElem());
            listarMayorQAux(obtenerNodo(this.raiz, elemento), valor, lis);
        }
        return lis;
    }

    private void listarMayorQAux(NodoABB nodo, Comparable valor, Lista lis) {

        if (nodo != null) {
            if (nodo.getElem().compareTo(valor) > 0) {
                // Cuando valor es menor al nodo
                listarMayorQAux(nodo.getIzquierdo(), valor, lis);
                if (nodo.getElem().compareTo(valor) > 0 || nodo.getElem().compareTo(valor) == 0) {
                    lis.insertar(nodo.getElem(), lis.longitud() + 1);
                }
                listarMayorQAux(nodo.getDerecho(), valor, lis);
            } else {
                if (nodo.getElem().compareTo(valor) < 0 || nodo.getElem().compareTo(valor) == 0) {
                    // cuando valor es mayor al nodo
                    if (nodo.getElem().compareTo(valor) == 0) {
                        lis.insertar(nodo.getElem(), lis.longitud() + 1);
                    }
                    listarMayorQAux(nodo.getDerecho(), valor, lis);
                    if (nodo.getElem().compareTo(valor) > 0) {
                        lis.insertar(nodo.getElem(), lis.longitud() + 1);
                    }
                }
            }

        }
    }

    /* private void listarRangoAux(NodoABB nodo, Comparable elemMin, Comparable elemMax, Lista lis) {
        if (nodo != null) {
            // RECORRE A SUS HIJOS EN INORDEN
            if (nodo.getIzquierdo() != null && nodo.getElemento().compareTo(elemMin) > 0) {
                listarRangoAux(nodo.getIzquierdo(), elemMin, elemMax, lis);
            }

            if (nodo.getElemento().compareTo(elemMin) >= 0 && nodo.getElemento().compareTo(elemMax) <= 0) {
                lis.insertar(nodo.getElemento(), lis.longitud() + 1);
            }
            if (nodo.getDerecho() != null) {
                listarRangoAux(nodo.getDerecho(), elemMin, elemMax, lis);
            }

        }
    }
 */

 //---------------- parcial 

/* public int sumarInordenDesde(Comparable valor, int t){

    int total = 0;
    if(!this.esVacio()){
        NodoABB buscado = obtenerNodo(this.raiz, valor);
        System.out.println(buscado.getElem());
        if(buscado != null){
            total = obtenerTotalSubArbol(buscado, t);
        }
    }
    return total;
}

private int obtenerTotalSubArbol(NodoABB buscado, int t){

    int acum=0;
        if (nodo != null) {
            //VISITA EL ELEMENTO EN EL NODO




                if (acum < (int) k) {
                    acum+=obtenerTotalSubArbol(nodo.getIzquierdo(),(int) k-acum);
                    if(acum < (int) k){
                        acum = acum+(int) nodo.getElem() ;
                    }
                    if(acum < (int) k){
                        acum+=obtenerTotalSubArbol(nodo.getDerecho(), (int)k-acum);
                    }

                }



        }
        return acum;
    }
    
    int total = 0;
    if (buscado != null) {
        if(buscado.getIzquierdo() != null && total < t){
            total += obtenerTotalSubArbol(buscado.getIzquierdo(), t-total);
        }
        if(total< t){
            total += (int)buscado.getElem();
            if(buscado.getDerecho()!= null && total < t){
                total += obtenerTotalSubArbol(buscado.getDerecho(), t-total);
            }
        }
    }
    return total;
    } */

    //---------------------------------------------------------------------

    public int diferenciaCandidatos(Comparable element) {

        int diferencia = -1;
        if (!this.esVacio()) {
            diferencia = diferenciaCandidatosAux(this.raiz, element);
        }
        return diferencia;
    }

    private int diferenciaCandidatosAux(NodoABB nodo, Comparable element) {

        int diferencia = -1;
        if (nodo != null) {
            if (nodo.getElem().compareTo(element) == 0) {
                if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
                    diferencia = -2;
                } else {
                    int menor = obtenerMenor(nodo);
                    int mayor = obtenerMayor(nodo);
                    diferencia = menor - mayor;
                }
            } else {
                if (element.compareTo(nodo.getElem()) > 0) {
                    diferencia = diferenciaCandidatosAux(nodo.getDerecho(), element);
                } else {
                    diferencia = diferenciaCandidatosAux(nodo.getIzquierdo(), element);
                }
            }
        }
        return diferencia;
    }

    private int obtenerMenor(NodoABB nodo) {

        int menor = 0; // si el hijo derecho es null
        if (nodo.getDerecho() != null) {
            NodoABB recorrer = nodo.getDerecho();
            NodoABB anterior = nodo.getDerecho();
            menor = (int) recorrer.getElem();
            while (recorrer != null) {
                if (recorrer.getElem().compareTo(anterior.getElem()) < 0) {
                    // Se compara en busqueda del menor
                    menor = (int) recorrer.getElem();
                }
                anterior = recorrer; // Lleva el nodo del paso anterior
                recorrer = recorrer.getIzquierdo(); // Recorre los hijos izquierdo del nodo en busqueda del mayor de
                                                    // ellos
            }
        }
        return menor;
    }

    private int obtenerMayor(NodoABB nodo) {
        int mayor = 0; // Si el hijo izquierdo es null 
        if (nodo.getIzquierdo() != null) {
            NodoABB recorrer = nodo.getIzquierdo();
            NodoABB anterior = nodo.getIzquierdo();
            mayor = (int) nodo.getElem();
            while (recorrer != null) {
                if (recorrer.getElem().compareTo(anterior.getElem()) > 0) {
                    // Se compara en busqueda del mayor
                    mayor = (int) recorrer.getElem();
                }
                anterior = recorrer; // Lleva el nodo del paso anterior
                recorrer = recorrer.getDerecho(); // Recorre los hijos izquierdo del nodo en busqueda del mayor de ellos
            }
        }
        return mayor;
    }

    public int mejorCandidato(Comparable element) {

        int diferencia = -1;
        if (!this.esVacio()) {
            diferencia = mejorCandidatoAux(this.raiz, element);
        }
        return diferencia;
    }

    private int mejorCandidatoAux(NodoABB nodo, Comparable element) {

        int diferencia = 0;
        if (nodo != null) {
            if (nodo.getElem().compareTo(element) == 0) {
                if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
                    diferencia = -1;
                } else {
                    int valor = (int) nodo.getElem();
                    int menor = obtenerMenor(nodo); // Devuelve el menor de los hijos derechos 
                    int mayor = obtenerMayor(nodo); // Devuelve el mayor de los hijos izquierdos
                    if(Math.abs(menor - valor) <= Math.abs(valor - mayor)){
                        diferencia = menor;
                    }else{
                        diferencia = mayor;
                    }
                }
            } else {
                if (element.compareTo(nodo.getElem()) > 0) {
                    diferencia = diferenciaCandidatosAux(nodo.getDerecho(), element);
                } else {
                    diferencia = diferenciaCandidatosAux(nodo.getIzquierdo(), element);
                }
            }
        }
        return diferencia;
    }
    // ------------- parcial 2
    public int sumarInordenDesde(Comparable valor, int t){
        int suma = 0;
        NodoABB encontrado = null;
        if(!this.esVacio()){
            encontrado = obtenerNodo(this.raiz, valor);
            if(encontrado!= null){
                suma = auxPreOrden(encontrado, t);
                if(suma<t){
                    suma = suma*(-1);
                }
            }
        }
        return suma;
    }

    private int sumarInordenDesdeAux(NodoABB nodo, int t, int total){  
        if (nodo != null) {
            total = sumarInordenDesdeAux(nodo.getIzquierdo(), t, total);
            if(total< t){
                total += (int)nodo.getElem();
                if(total<t){
                    total = sumarInordenDesdeAux(nodo.getDerecho(), t, total);
                }
            }
        }
        return total;
    }

    private int auxInOrden(NodoABB nodo, int t){  
        int suma = 0;
        if (nodo != null) {
            suma = auxInOrden(nodo.getIzquierdo(), t);
            if(suma< t){
                suma += (int)nodo.getElem();
                if(suma<t){
                    suma += auxInOrden(nodo.getDerecho(), t-suma);
                }
            }
        }
        return suma;
    }

    private int auxPreOrden(NodoABB nodo, int t){  
        int suma = 0;
        if (nodo != null) {
            suma = (int)nodo.getElem();
            if(suma< t){
                suma += auxPreOrden(nodo.getIzquierdo(), t-suma);
                if(suma< t){
                    suma += auxPreOrden(nodo.getDerecho(), t-suma);
                }
            }       
        }
        return suma;
    }

    public boolean verificarCamino(Pila p){
        boolean encontrado = false;
        if(!this.esVacio() && !p.esVacia()){
            encontrado = aux1(this.raiz, p, 0);
            if(encontrado && !p.esVacia()){
                encontrado = false;
            }
        }
        return encontrado; 
    }

    private boolean aux1(NodoABB nodo, Pila p, int contador){

        boolean encontrado = true;
        Comparable buscado = (Comparable)p.obtenerTope();
        if (nodo != null && buscado != null) {
                if (nodo.getElem().compareTo(buscado) == 0) {
                    p.desapilar();
                    buscado = (Comparable)p.obtenerTope();
                    contador++;
                }else{
                    if(contador != 0){
                        encontrado = false;
                    }
                }
                if(encontrado && buscado != null){
                    if (buscado.compareTo(nodo.getElem()) < 0) {
                        encontrado = aux1(nodo.getIzquierdo(), p, contador);
                    }else{
                        encontrado = aux1(nodo.getDerecho(), p, contador);
                    }
                }
        }
        return encontrado; 
    }
}
