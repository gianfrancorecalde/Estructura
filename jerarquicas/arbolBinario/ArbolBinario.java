package Estructura.jerarquicas.arbolBinario;

import Estructura.Lineales.Dinamicas.*;

public class ArbolBinario {
    
    // Atributo 
    private NodoArbol raiz;

    // Constructor vacio
    public ArbolBinario(){
        this.raiz = null;
    }

    // Método vaciar 
    public void vaciar(){
        this.raiz = null;
    }

    // Método esVacio
    public boolean esVacio(){   
        // Si el arbol binario tiene al menos un elemento devulve falso si no devuelve verdadero

        boolean exito = false;
        if(this.raiz == null){
            exito = true;
        }
        return exito;
    }

    // Método altura 
    public int altura(){            
        // Devuelve -1 si el arbol esta vacio
        return obtenerAltura(this.raiz);
    }
    private int obtenerAltura(NodoArbol n){
        
        int alturaHijoIzquierdo = -1, alturaHijoDerecho = -1;
        if(n != null){
            alturaHijoIzquierdo = obtenerAltura(n.getIzquierdo()) + 1;
            alturaHijoDerecho = obtenerAltura(n.getDerecho())+1;
        }
        int altura;
        if(alturaHijoDerecho >= alturaHijoIzquierdo){
            altura = alturaHijoDerecho;
        }else{
            altura = alturaHijoIzquierdo;
        }
        return altura;
    }

    // Método Insertar 
    public boolean insertar(Object newElem, Object elemPadre, char lugar){
        // NEWELEM  nuevo elemento que se agragara al arbol
        // ELEMPADRE el elemento padre al que se le va agregar el NEWELEM
        // LUGAR indica TRUE si se agrega a la izquierda o FALSE  si se agrega a la derecha

        boolean exito = false;
        if(this.raiz == null){                                                      // Si el arbol esta vacio
            this.raiz = new NodoArbol(newElem,null,null);                           // Se le asigna un nodo a la raiz con el NEWELEM
        }else{
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if(nodoPadre != null){                                                  // Si NODOPADRE existe
                if(lugar == 'I' && nodoPadre.getIzquierdo() == null){                      // Si el hijo izquierdo esta vacio, agrega el NEWELEM
                    nodoPadre.setIzquierdo(new NodoArbol(newElem,null,null));       // Se setea el enlaze izquierdo del NODOPADRE, con el nuevo hijo
                    exito = true;
                }else{                                                              // El hijo izquierdo no esta vacio
                    if(lugar == 'D' && nodoPadre.getDerecho() == null){                   // Si el hijo derecho esta vacio, agrega el NEWELEM
                        nodoPadre.setDerecho(new NodoArbol(newElem,null,null));     // Se setea el enlaze derecho del NODOPADRE, con el nuevo hijo
                        exito = true;
                    }
                }
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol nodo, Object elemBuscado){ 
        // Busca el nodo que contiene el elemento ELEMBUSCADO, si no exite devuelve null

        NodoArbol resultado = null;
        if(nodo != null){
            if(nodo.getElem().equals(elemBuscado)){
                resultado = nodo;
            }else{
                resultado = obtenerNodo(nodo.getIzquierdo(), elemBuscado);
                if(resultado == null){
                    resultado = obtenerNodo(nodo.getDerecho(), elemBuscado);
                }
            }
        }
        return resultado;
    }

    // Método padre 
    public Object padre(Object elem){
        Object elemPadre=null;
        if(!this.esVacio() && (this.raiz.getIzquierdo() != null && this.raiz.getDerecho() != null)){
            elemPadre=padreAux(this.raiz,elem).getElem();        
        }
        return elemPadre;
    }

    private NodoArbol padreAux(NodoArbol n, Object buscado){
        NodoArbol resultado=null;
        if(n!=null){
            if(n.getIzquierdo()!=null && (n.getIzquierdo().getElem().equals(buscado))){
                //SI EL BUSCADO ES EL HI DE n, LO DEVUELVE
                resultado=n;
            }
            else if(n.getDerecho()!=null && (n.getDerecho().getElem().equals(buscado))){
                //SI EL BUSCADO ES EL HD DE n, LO DEVUELVE
                resultado=n;
            }
            else{
                //NO ES EL BUSCADO: BUSCA PRIMERO EL HI
                resultado=padreAux(n.getIzquierdo(),buscado);
                //SI NO LO ENCUENTRA EN EL HI, BUSCA EN HD
                if(resultado==null){
                    resultado=padreAux(n.getDerecho(),buscado);
                }
            }
        }
        return resultado;
    }

    // Método devolver nivel
    public int nivel(Object elemento){

        int nivel = -1;
        if(!this.esVacio()){
            if(buscarElemento(this.raiz, elemento)){
                nivel = buscarNivel(this.raiz, elemento, 0);
            }
        }
        return nivel;
    }

    private int buscarNivel(NodoArbol nodo, Object elemento, int nivel){
        
        int nivelDelArbol =-1 ;
        if(nodo != null){
            if(nodo.getElem().equals(elemento)){
                nivelDelArbol = nivel;
            }else{
                nivelDelArbol = buscarNivel(nodo.getIzquierdo(), elemento,nivel+1);
                if(nivelDelArbol == -1){
                    nivelDelArbol = buscarNivel(nodo.getDerecho(), elemento, nivel+1); 
                }
            }   
        }
        return nivelDelArbol; 
    }

    private boolean buscarElemento(NodoArbol nodo, Object elemento){

        boolean encontrado = false;
        if(nodo !=null){
            if(nodo.getElem().equals(elemento)){
                encontrado = true;
            }else{
                encontrado = buscarElemento(nodo.getIzquierdo(), elemento);
                if(!encontrado){
                    encontrado = buscarElemento(nodo.getDerecho(), elemento);
                }
            }
        }
        return encontrado;
    }

    // Método listar arbol binario en preorden
    public Lista listarPreOrden(){
        
        Lista listaPreOrden = new Lista();
        if(!this.esVacio()){
            recorridoPreOrden(this.raiz, listaPreOrden);
        }
        return listaPreOrden;
    }

    private void recorridoPreOrden(NodoArbol nodo, Lista listaPreOrden){

        if(nodo!=null){
            listaPreOrden.insertar(nodo.getElem(), listaPreOrden.longitud()+1);
            recorridoPreOrden(nodo.getIzquierdo(), listaPreOrden);
            recorridoPreOrden(nodo.getDerecho(), listaPreOrden);
        }
    }

    // Método listar arbol binario en inorden
    public Lista listarInOrden(){
        
        Lista listaInOrden = new Lista();
        if(!this.esVacio()){
            recorridoInOrdenMejorado(this.raiz, listaInOrden);
        }
        return listaInOrden;
    }

    private int recorridoInOrdenMejorado(NodoArbol nodo, Lista listaInOrden){
        
        int posicionEnLista = listaInOrden.longitud();
        if(nodo != null){
            posicionEnLista = recorridoInOrdenMejorado(nodo.getIzquierdo(), listaInOrden) +1 ;
            listaInOrden.insertar(nodo.getElem(), posicionEnLista);
            posicionEnLista =  recorridoInOrdenMejorado(nodo.getDerecho(), listaInOrden);
        }
        return posicionEnLista;
    }

    // Método listar arbol binario en posorden
    public Lista listarPosOrden(){

        Lista listaPosOrden = new Lista();
        if(!this.esVacio()){
            recorridoPosOrden(this.raiz, listaPosOrden);
        }
        return listaPosOrden;
    }
    private int recorridoPosOrden(NodoArbol nodo, Lista listaPosOrden){
        
        int posicionEnLista = listaPosOrden.longitud();
        if(nodo != null){
            posicionEnLista = recorridoPosOrden(nodo.getIzquierdo(), listaPosOrden);
            posicionEnLista =  recorridoPosOrden(nodo.getDerecho(), listaPosOrden)+ 1;
            listaPosOrden.insertar(nodo.getElem(), posicionEnLista);
        }
        return posicionEnLista;
    }
    
    // Método listar arbol binario por nivel
    public Lista listarPorNiveles(){

        Lista listaPorNivel = new Lista();
        Cola q = new Cola();
        q.poner(this.raiz);
        if(!this.esVacio()){
            while(!q.esVacia()){
                NodoArbol nodoActual = (NodoArbol)q.obtenerFrente();
                q.sacar();
                listaPorNivel.insertar(nodoActual.getElem(), listaPorNivel.longitud()+1);
                if(nodoActual.getIzquierdo()!=null){
                    q.poner(nodoActual.getIzquierdo());
                }
                if(nodoActual.getDerecho()!=null){
                    q.poner(nodoActual.getDerecho());
                }
            }
        }
        return listaPorNivel;
    }

    // Método clone
    public ArbolBinario clone(){

        ArbolBinario copiaArbol = new ArbolBinario();
        if(this.raiz != null){
            copiaArbol.raiz = copiarNodosDelArbolOriginalMejorado(this.raiz);
        }
        return copiaArbol;
    }

    private NodoArbol copiarNodosDelArbolOriginal(NodoArbol nodo){

        NodoArbol nodoArbolCopia = new NodoArbol(nodo.getElem(), null, null);
        NodoArbol aux = nodoArbolCopia;
        if(nodo.getIzquierdo() != null){
            aux = copiarNodosDelArbolOriginal(nodo.getIzquierdo());
            nodoArbolCopia.setIzquierdo(aux);
        }
        if(nodo.getDerecho() != null){
            aux = copiarNodosDelArbolOriginal(nodo.getDerecho());
            nodoArbolCopia.setDerecho(aux);
        }
        return nodoArbolCopia;
    }

    private NodoArbol copiarNodosDelArbolOriginalMejorado(NodoArbol nodo){

        NodoArbol nodoArbolCopia = null;
        if(nodo != null){
            nodoArbolCopia= new NodoArbol(nodo.getElem(), null, null);
            NodoArbol aux = copiarNodosDelArbolOriginalMejorado(nodo.getIzquierdo());
            nodoArbolCopia.setIzquierdo(aux);
            aux = copiarNodosDelArbolOriginalMejorado(nodo.getDerecho());
            nodoArbolCopia.setDerecho(aux);
        }
        return nodoArbolCopia;
    }

    // Método toString
    public String toString(){
        
        String cadena;
        if(this.esVacio()){
            cadena = "Arbol vacio";
        }else{
            cadena = generarString(this.raiz);
        }
        return cadena;
    }

    private String generarString(NodoArbol n){
        
        String cadena;
        if(n == null){
            // Cuando el hijo es nulo devuelve "-"
            cadena = "-"; 
        }else{
            String cadenaHI = generarString(n.getIzquierdo());
            String cadenaHD = generarString(n.getDerecho());
            if(cadenaHI.equalsIgnoreCase("-") && cadenaHD.equalsIgnoreCase("-")){
                // Si los hijos son nulos devuelve un String:
                //elemento HI: - HD: -
                cadena = n.getElem() + " HI: "+ cadenaHI + " HD: "+cadenaHD;
            }else{
                if(!cadenaHI.equalsIgnoreCase("-") && cadenaHD.equalsIgnoreCase("-")){
                    // Si solo el hijo derecho es nulo, devuelve un String: 
                    // elemento HI: elementoHI HD: - 
                    // elementoHI HI: ... HD: ...
                    cadena = n.getElem() + " HI: " + n.getIzquierdo().getElem() + " HD: "+ cadenaHD + "\n"+ cadenaHI; 
                }else{
                    if(cadenaHI.equalsIgnoreCase("-") && !cadenaHD.equalsIgnoreCase("-")){
                        // Si solo el hijo izquierdo es nulo, devuelve un String:
                        // elemento HI: - HD: elementoHD 
                        // elementoHD HI: ... HD: ...
                        cadena = n.getElem() + " HI: " + cadenaHI + " HD: "+ n.getDerecho().getElem() + "\n"+ cadenaHD; 
                    }else{
                        // Si ninguno de los dos hijos es nulo, devuelve:
                        // elemento HI: elementoHI HD: elementoHD 
                        // elementoHI HI: ... HD: ...
                        // elementoHD HI: ... HD: ...
                        cadena = n.getElem() + " HI: " + n.getIzquierdo().getElem() + " HD: "+ n.getDerecho().getElem() + "\n"+ cadenaHI + "\n"+ cadenaHD ; 
                    }
                }
            }
            
        }
        return cadena;
    }

    // Método frontera
    public Lista frontera(){
        Lista lis=new Lista();
        fronteraAux(this.raiz,lis);
        return lis;
    }

    public void fronteraAux(NodoArbol nodo, Lista lis){
        if(nodo!=null){
            if(nodo.getDerecho()==null && nodo.getIzquierdo()==null){
                lis.insertar(nodo.getElem(),lis.longitud()+1 );
            }
            else{
                fronteraAux(nodo.getIzquierdo(),lis);
                fronteraAux(nodo.getDerecho(),lis);
            }
        }
    }

    public Lista fronteraMejor() {
        Lista listaFrontera = new Lista();
        fronteraAux1(this.raiz, listaFrontera, 1);
        return listaFrontera;
    }

    private void fronteraAux1(NodoArbol nodo, Lista lista, int posicion) {
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            lista.insertar(nodo.getElem(), posicion);
            posicion++;
        } else {
            if (nodo.getIzquierdo() != null) {
                fronteraAux1(nodo.getIzquierdo(), lista, posicion);
            }
            if (nodo.getDerecho() != null) {
                fronteraAux1(nodo.getDerecho(), lista, posicion);
            }
        }
    }

    //--------------------------------------------------------EJERCICIOS SIMULACRO
    public boolean verificarPatron(Lista patron){
        
        boolean coincidePatron = false;
        if(patron.recuperar(1).equals(this.raiz.getElem())){
            coincidePatron = verificarPatronAux(this.raiz, patron,2);
        }
        return coincidePatron;
    }
    private boolean verificarPatronAux(NodoArbol nodo, Lista patron, int pos){

        boolean coincidencia = false;
        if(pos <=patron.longitud()){
            if(nodo.getElem().equals(patron.recuperar(pos))){
                if(nodo.getIzquierdo() != null && nodo.getDerecho() != null){
                    coincidencia = verificarPatronAux(nodo.getIzquierdo(), patron, pos+1);
                    if(!coincidencia){
                        coincidencia = verificarPatronAux(nodo.getDerecho(), patron, pos+1);
                    }
                }else{
                    coincidencia = true;
                }
            }
        }

        return coincidencia;
    }

    //----------------------------------------------------------PARCIAL

    public boolean cambiarHijos(Object izq, Object p, Object der){
        
        boolean sePudoCambiar = false;
        if(!this.esVacio()){
            sePudoCambiar = cambiarHijosAux(this.raiz,izq,p,der);
        }
        return sePudoCambiar;
    }

    private boolean cambiarHijosAux(NodoArbol nodo,Object izq, Object p, Object der){

        boolean corte = false;
        if(nodo!=null){
            if(nodo.getElem().equals(p)){

                if(nodo.getIzquierdo() != null){
                    nodo.getIzquierdo().setElem(izq);
                }else{
                    NodoArbol nuevoNodo=new NodoArbol(izq, null, null);
                    nodo.setIzquierdo(nuevoNodo);
                }
                
                if(nodo.getDerecho() != null){
                    nodo.getDerecho().setElem(der);
                }else{
                    NodoArbol nuevoNodo=new NodoArbol(der, null, null);
                    nodo.setDerecho(nuevoNodo);
                }
                corte = true;
            }else{
                cambiarHijosAux(nodo.getIzquierdo(), izq, p, der);
                cambiarHijosAux(nodo.getDerecho(), izq, p, der);
            }
        }
        return corte;
    }

}
