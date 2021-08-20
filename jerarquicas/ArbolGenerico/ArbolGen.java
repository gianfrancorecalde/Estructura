package Estructura.jerarquicas.ArbolGenerico;

import Estructura.Lineales.Dinamicas.*;
import Estructura.jerarquicas.arbolBinario.NodoArbol;

public class ArbolGen {
    
    /*                                    ATRIBUTOS                                    */

    private NodoGen raiz;

    /*                                    CONSTRUCTOR                                    */

    public ArbolGen(){

        this.raiz = null;
    }
    
    /*                                    METODO VACIAR                                     */

    public void vaciar(){
        
        if(!this.esVacio()){
            this.raiz = null;
        }
    }

    /*                                    METODO ESVACIO                                  */

    public boolean esVacio(){
        
        boolean estado = false;
        if(this.raiz == null){
            estado = true;
        }

        return estado;
    }

    /*                                    METODO INSERTAR                                    */

    public boolean insertar(Object newElement, Object padre){
        //Este metodo buscara el padre y agregara un hijo con el newElement dado por paramentro
        boolean exito = false;
        if(this.esVacio()){
            // Caso 1: arbol vacio 
            this.raiz = new NodoGen(newElement, null, null);
            exito = true;
        }else{
            // Caso 2: arbol con elementos
            exito = insertarAux(buscarNodo(this.raiz, padre), newElement);
        }

        return exito;
    }

    private boolean insertarAux(NodoGen nodo, Object newElement){
        // Si el padre existe, agrega un hijo al principio y devuelve un true; en caso contrario devuevle false.
        // En caso de que el padre este repetido solo se insertara en el primer padre que encuentre.
        boolean exito = false;
        if(nodo != null){
            if(nodo.getHijo() == null){
                // caso 1: el padre buscado no tiene hijos
                nodo.setHijo(new NodoGen(newElement, null, null));
            }else{
                // caso 2: el padre ya tienes hijos, se agrega el hijo por delante de todos sus hijos 
                nodo.setHijo(new NodoGen(newElement, null, nodo.getHijo()));
            }
            exito = true;
        }
        return exito;
    }

    /*                                    METODO PERTENECE                                    */

    public boolean pertenece(Object element){
        // Busca el elemento pasado por parametro
        boolean existe = false;
        if(!this.esVacio()){
            if(buscarNodo(this.raiz, element) != null){
                existe = true;
            }
        }
        return existe;
    }

   /*                                    METODO ANCETROS                                    */

    public Lista ancestros(Object element){

        boolean exito = false; // agrege esta variable boolean porque el metodo ancestroAux() devuelve un boolean, es necesario esto? 
        Lista newLista = new Lista();
        if(!this.esVacio()){
            exito = ancestrosAux(this.raiz, newLista, element);
        }
        
        return newLista;
    }

    private boolean ancestrosAux(NodoGen nodo, Lista unaLista, Object element){
        // Busca el elemento pasado por paramentro y, si lo encuentra lista los elementos ancestro al elemnento buscado y devuelve true
        // en caso que no encuentre el elemento buscado devolvera un false
        boolean encontrado = false;
        if(nodo != null){
            if(nodo.getElemento().equals(element)){
                // elemento encontrado
                encontrado = true;
            }else{
                NodoGen recorridoHijos = nodo.getHijo();
                while(recorridoHijos != null && !encontrado){
                    // Se recorre los hijos del nodo actual (recorrido preorden)
                    encontrado = ancestrosAux(recorridoHijos, unaLista, element);
                    if(encontrado){
                        // Si el paso recursivo posterior devulve un verdadero se lista el elemento del nodo actual
                        unaLista.insertar(nodo.getElemento(), unaLista.longitud()+1);
                    }else{
                        // En caso contrario se sigue recorriendo
                        recorridoHijos = recorridoHijos.getHno();
                    }
                }
            }
        }

        return encontrado; 
    }

    /*                                    METODO ALTURA                                     */

    public int altura(){

        int altura = -1;
        if(!this.esVacio()){
            altura = alturaAux(this.raiz);
        }

        return altura;
    }

    private int alturaAux(NodoGen nodo){

        int aux = -1;
        int res = -1;
        if(nodo != null){
            NodoGen recorridoHijos = nodo.getHijo();
            while(recorridoHijos != null){
                aux = alturaAux(recorridoHijos);
                if(aux>res){
                    res = aux;
                }
                recorridoHijos = recorridoHijos.getHno();
            }
            res++;
        }

        return res;
    }

    /*                                    METODO NIVEL                                    */

    public int nivel(Object element){

        int nivel = -1;
        if(!this.esVacio()){
            nivel = nivelAux(this.raiz, element);
        }

        return nivel;
    }

    private int nivelAux(NodoGen nodo, Object element){
        // Busca el elemento pasado por paramentro y devuelve el nivel de ese elemento
        // Sera -1 el nivel en caso de que no encuentre el elemento buscado
        int nivel = -1; 
        if(nodo != null){
            if(nodo.getElemento().equals(element)){
                nivel = 0;
            }else{
                NodoGen recorridoHijos = nodo.getHijo();
                while(recorridoHijos != null && nivel == -1){
                    // Mientras el elemento no se encuentre el nivel permanecera en -1 
                    nivel = nivelAux(recorridoHijos, element);
                    if(nivel != -1){
                        nivel++;
                    }
                    recorridoHijos = recorridoHijos.getHno();
                }
            }
        }

        return nivel;
    }

    /*                                    METODO PADRE                                     */
    
    public Object padre(Object element){

        Object padre = null;
        if(!this.esVacio() && !this.raiz.getElemento().equals(element)){
            padre = padreAux(this.raiz, element);
        }

        return padre;
    }

    private Object padreAux(NodoGen nodo, Object element){

        Object padre = null;
        if(nodo != null){
            NodoGen recorridoHijos = nodo.getHijo();
            while(recorridoHijos != null && padre == null){
                if(recorridoHijos.getElemento().equals(element)){
                    padre = nodo.getElemento();
                }
                recorridoHijos = recorridoHijos.getHno();
            }
            NodoGen recorrido = nodo.getHijo();
            while(recorrido != null && padre == null){
                padre = padreAux(recorrido, element);
                recorrido = recorrido.getHno();
            }
        }

        return padre;
    }

    /*                                    METODO LISTA EN PREORDEN                                    */
    public Lista listarPreOrden(){

        Lista newLista = new Lista();
        if(!this.esVacio()){
            listarPreOrdenAux(this.raiz, newLista);
        }   
        
        return newLista;
    }

    private void listarPreOrdenAux(NodoGen nodo, Lista unaLista){

        unaLista.insertar(nodo.getElemento(), unaLista.longitud()+1);
        NodoGen recorridoHijos = nodo.getHijo();
        while(recorridoHijos != null){
            listarPreOrdenAux(recorridoHijos, unaLista);
            recorridoHijos = recorridoHijos.getHno();
        }
    }

    /*                                    METODO LISTA EN POSORDEN                                    */

    public Lista listarPosOrden(){
        
        Lista newLista = new Lista();
        if(!this.esVacio()){
            listarPosOrdenAux(this.raiz, newLista);
        }

        return newLista;
    }

    private void listarPosOrdenAux(NodoGen nodo, Lista unaLista){

        if(nodo!= null){
            NodoGen recorridoHijos = nodo.getHijo();
            while(recorridoHijos != null){
                listarPosOrdenAux(recorridoHijos, unaLista);
                recorridoHijos = recorridoHijos.getHno();
            }
            unaLista.insertar(nodo.getElemento(), unaLista.longitud()+1);
        }
    }

    

    /*                                    METODO LISTA POR NIVEL                                     */

    public Lista listarPorNiveles(){

        Lista listaPorNivel = new Lista();
        if(!this.esVacio()){
            Cola q = new Cola();
            q.poner(this.raiz);
            while(!q.esVacia()){
                NodoGen nodoActual = (NodoGen)q.obtenerFrente();
                q.sacar();
                listaPorNivel.insertar(nodoActual.getElemento(), listaPorNivel.longitud()+1);
                if(nodoActual.getHijo() != null){
                    NodoGen recorreHnos = nodoActual.getHijo();
                    while(recorreHnos != null){
                        q.poner(recorreHnos);
                        recorreHnos = recorreHnos.getHno();
                    }
                }
            }
        }
        return listaPorNivel;
    }

    /*                                    METODO CLONE                                    */

    public ArbolGen clone(){

        ArbolGen clone = new ArbolGen();
        if(!this.esVacio()){
            clone.raiz = cloneAux(this.raiz); // raiz del arbol clon
        }

        return clone;
    }
    private NodoGen cloneAux(NodoGen nodo){

        NodoGen nodoClone = new NodoGen(nodo.getElemento(),null,null);
        if(nodo != null){
            NodoGen recorrido = nodo.getHijo();
            NodoGen aux = null; // se usa de puntero para poder enlazar los hnos 
            while(recorrido != null){
                if(aux != null){
                    // Se agregan los hnos del hijo
                    aux.setHno(cloneAux(recorrido));
                    aux = aux.getHno(); 
                }else{
                    // Se agrega el primer hijo de la raiz del subarbol 
                    nodoClone.setHijo(cloneAux(recorrido));
                    aux = nodoClone.getHijo();
                }
                recorrido = recorrido.getHno(); // se recorre los hijos del arbol original
            }
        }
        return nodoClone;
    }

    /*                                    METODO TOSTRING                                   */

    public String toString(){

        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen nodo){
        String cadena = "";
        if(nodo != null){
            cadena += nodo.getElemento().toString() + " -> ";
            NodoGen recorridoHijo = nodo.getHijo();
            while(recorridoHijo != null){
                cadena += recorridoHijo.getElemento().toString() + " , ";
                recorridoHijo = recorridoHijo.getHno();
            }
            recorridoHijo = nodo.getHijo();
            while(recorridoHijo != null){
                cadena += "\n" + toStringAux(recorridoHijo);
                recorridoHijo = recorridoHijo.getHno();
            }
        }else{
            cadena = "arbol vacio";
        }

        return cadena;
    }
    /*                                    METODO GRADO DE ARBOL COMPLETO                                     */

    public int grado(){
        
        int gradoMayor = -1;
        if(!this.esVacio()){
            gradoMayor = gradoAuxMejorado(this.raiz);
        }
        return gradoMayor;
    }

    private int gradoAuxMejorado(NodoGen nodo){
        // Compara el grado entre los hijos y entre los hijos y el padre y eligue el mayor
        int gradoMayor = 0;
        int grado = 0;
        int gradoHijo = 0;
        if(nodo != null){
            NodoGen recorrido = nodo.getHijo();
            while(recorrido != null){
                grado++;    // Por cada vuelta el grado de padre incrementa mientras el hijo sea distintos de null
                gradoHijo = gradoAuxMejorado(recorrido);
                if(gradoHijo > gradoMayor){
                    // Compara los grados de los hijos 
                    gradoMayor = gradoHijo;
                }
                recorrido = recorrido.getHno();
            }
            if(grado > gradoMayor && recorrido == null){
                // Compara el grado del nodo padre con el grado mayor de los hijos 
                gradoMayor = grado;
            }
        }
        return gradoMayor;
    }

    /*                                    METODO GRADO DE SUBARBOL                                    */
    
    public int gradoSubarbol(Object padre){
        // Devulve la cantidad de hijos del subarbol pasado por parametro
        int grado = -1; // Si el arbol esta vacio o si el subarbol ingresado no existe
        if(!this.esVacio()){
            NodoGen subArbol = buscarNodo(this.raiz, padre);
            grado = gradoAuxMejorado(subArbol);
        }

        return grado;
    }

    private NodoGen buscarNodo(NodoGen nodo, Object buscado) {
        //METODO QUE BUSCA UN DETERMINADO ELEMENTO EN EL ARBOL GENERICO
        NodoGen encontrado = null;
        if (nodo != null) {
            if (nodo.getElemento().equals(buscado)) {
                //SI EL BUSCADO ES nodo, LO DEVUELVE
                encontrado = nodo;
            } else {
                //NO ES EL BUSCADO: BUSCA PRIMERO EL HERMANO DERECHO
                encontrado = buscarNodo(nodo.getHijo(), buscado);
                //SI NO LO ENCUENTRA EN EL HERMANO DERECHO, BUSCA EN HIJO IZQUIERDO
                if (encontrado == null) {
                    encontrado = buscarNodo(nodo.getHno(), buscado);
                }

            }

        }
        return encontrado;
    }

    /*                                    METODO FRONTERA                                     */

    public Lista frontera(){

        Lista listaFrontera = new Lista();
        if(!this.esVacio()){
            fronteraAux(this.raiz,listaFrontera);
        }
        return listaFrontera;
    }

    private void fronteraAux(NodoGen nodo, Lista unaLista){

        if(nodo!= null){
            if(nodo.getHijo() == null){
                unaLista.insertar(nodo.getElemento(), unaLista.longitud()+1);
            }else{
                NodoGen recorridoHijos = nodo.getHijo();
                while(recorridoHijos != null){
                    fronteraAux(recorridoHijos, unaLista);
                    recorridoHijos = recorridoHijos.getHno();
                }
            }
        }
    }

    /*                                    METODO VERIFICAR PATRON                                     */

    public boolean verificarPatronRecursivo(Lista listaPatron){

        boolean encontrado = false;
        if(!this.esVacio()){
                encontrado = verificarPatronRecursivoAux(this.raiz, listaPatron, 1);
        }
        return encontrado;
    }

    private boolean verificarPatronRecursivoAux(NodoGen nodo, Lista listaPatron, int pos){
        // Recorre el arbol buscando los nodos que conforman el camino dado por una coleccion del tipo lista.
        // Va invocando el metodo sobre los nodos iguales a los del camino y eliminando esos mismos nodos encontrados de la lista,
        // hasta encontrarse con el nodo hoja que coincida con el ultimo nodo del camino.
        // Si lo encuentra devulve verdadero
        // pero si no lo encuentra, porque la lista no termina en un nodo hoja o algunos de los nodos no pertenecen al arbol, devuelve falso 
        boolean encontrado = false;
        if(nodo != null){
            if(listaPatron.recuperar(pos).equals(nodo.getElemento())){  
                // El hijo visitado es el mismo que alguno de la lista
                if(listaPatron.longitud() == pos){
                    // Se verifica el patron
                    encontrado = true;
                }else{
                    NodoGen recorridoHnos = nodo.getHijo();
                    while(recorridoHnos != null && !encontrado){
                    // mientras no se hayan verificado todos los elmentos de la lista
                        encontrado = verificarPatronRecursivoAux(recorridoHnos, listaPatron, pos+1);
                        recorridoHnos = recorridoHnos.getHno();
                    } 
                }
                
            }
            
        }
        return encontrado;
    }

// ---------------------------------------------------------------------------------------------

    /*                                    METODO LISTA EN INORDEN                                   */

    public Lista listarInOrden(){

        Lista newLista = new Lista();
        if(!this.esVacio()){
            listarInOdenAux(this.raiz, newLista);
        }

        return newLista;
    }
    private void listarInOdenAux(NodoGen nodo, Lista unaLista){

        if(nodo!= null){
            // Se recorre el primer hijo
            listarInOdenAux(nodo.getHijo(), unaLista);
            // Se visita al nodo (se agrega a la lista)
            unaLista.insertar(nodo.getElemento(), unaLista.longitud()+1);
            // Se recorre el resto de los hijos o hnos del primer hijo
            if(nodo.getHijo() != null){
                NodoGen hijo = nodo.getHijo().getHno();
                while(hijo!=null){
                    listarInOdenAux(hijo, unaLista);
                    hijo = hijo.getHno();
                }
            }
        }
    }

    

    // ---------------------------------------------------------------------------------------------

    /*      Listar entre nivels con recorrido inorden       */

    public Lista listarEntreNiveles(int niv1, int niv2){

        Lista lis = new Lista();
        if(!this.esVacio()){
            aux(this.raiz, 0, niv1, niv2, lis);
        }
        return lis;
    }

    private void aux(NodoGen nodo, int nivArbol, int niv1, int niv2, Lista lis){

        if(nodo != null){
            // Se recorre el primer hijo 
            if(nivArbol <2){
                aux(nodo.getHijo(), nivArbol+1, niv1, niv2, lis);
            }
            // Se visita al nodo (se agrega a la lista)
            if(nivArbol >= niv1 && nivArbol <= niv2){
                lis.insertar(nodo.getElemento(), lis.longitud()+1);
            }
            if(nodo.getHijo() != null && nivArbol <2 ){
                NodoGen recorrerHijos = nodo.getHijo().getHno();   
                while(recorrerHijos != null){
                    aux(recorrerHijos, nivArbol+1, niv1, niv2, lis);
                    recorrerHijos = recorrerHijos.getHno();        
                }
            }
        }
    } 

    /*      Eliminar subArbol       */

    public boolean eliminar(Object elemento){

        boolean exito = false;
        if(!this.esVacio()){
            exito = eliminarAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean eliminarAux(NodoGen nodo, Object buscado){

        boolean exito = false;
        if(nodo != null){
            if(nodo.getElemento().equals(buscado)){
                if(nodo == this.raiz){
                    this.raiz = null;
                }
                exito = true;
            }else{
                NodoGen recorrerHijos = nodo.getHijo();
                NodoGen anterior = recorrerHijos;   
                while(recorrerHijos != null && !exito){
                    exito = eliminarAux(recorrerHijos, buscado);
                    if(exito){
                        if(anterior.getElemento().equals(nodo.getHijo().getElemento())){
                            nodo.setHijo(recorrerHijos.getHno());
                        }else{
                            anterior.setHno(recorrerHijos.getHno());
                        }
                    }
                    anterior= recorrerHijos;
                    recorrerHijos = recorrerHijos.getHno();        
                }
            }
        }
        return exito;
    }

    public boolean insertarEnPosicion(Object newElement, Object padre, int pos){
        //Este metodo buscara el padre y agregara un hijo con el newElement dado por paramentro
        boolean exito = false;
        if(this.esVacio()){
            // Caso 1: arbol vacio 
            this.raiz = new NodoGen(newElement, null, null);
            exito = true;
        }else{
            // Caso 2: arbol con elementos
            exito = insertarEnPosicionAux(buscarNodo(this.raiz, padre), newElement, pos);
        }

        return exito;
    }


    private boolean insertarEnPosicionAux(NodoGen nodo, Object newElement, int pos){
        // Si el padre existe, agrega un hijo al principio y devuelve un true; en caso contrario devuevle false.
        // En caso de que el padre este repetido solo se insertara en el primer padre que encuentre.
        boolean exito = false;
        int posHijos;
        NodoGen recorrido, anterior;
        if(nodo != null){
            if(nodo.getHijo() == null){
                // caso 1: el padre buscado no tiene hijos
                nodo.setHijo(new NodoGen(newElement, null, null));
            }else{
                if(pos == 1){
                    // caso 2: pos conincide con al posicion del primer hijo entonces se coloca por delate de todos los hijos
                    nodo.setHijo(new NodoGen(newElement, null, nodo.getHijo()));
                }else{
                    // caso 3 y 4
                    recorrido = nodo.getHijo();
                    anterior = recorrido;
                    posHijos = 1;
                    while(recorrido != null && !exito){
                        if(posHijos == pos){
                            // caso 3: la pos coincide con alguna otra posicion distinta de la del primer hijo
                            anterior.setHno(new NodoGen(newElement, null, recorrido));
                            exito = true;
                        }else{
                            anterior = recorrido;
                            recorrido = recorrido.getHno();
                            posHijos++; 
                        }         
                    }
                    if(recorrido == null && !exito){
                        // caso 4: la pos enviada por parametro es invalida por lo que se le coloca el nuevo elemento a lo ultimo
                        anterior.setHno(new NodoGen(newElement, null, null));
                    }
                }
                
                
            }
            exito = true;
        }
        return exito;
    }

    //--------------------------------

    public Lista listarPorNivelPar(){

        Lista lis = new Lista();
        auxPar(this.raiz, 0, lis);
        return lis;
    }

    private void auxPar(NodoGen nodo, int nivArbol, Lista lis){

        if(nodo != null){
            // Se visita al nodo (se agrega a la lista)
            if(nivArbol%2 != 0){
                lis.insertar(nodo.getElemento(), lis.longitud()+1);
            }
            if(nodo.getHijo() != null){
                NodoGen recorrerHijos = nodo.getHijo();   
                while(recorrerHijos != null){
                    auxPar(recorrerHijos, nivArbol+1, lis);
                    recorrerHijos = recorrerHijos.getHno();        
                }
            }
        }
    }

    private boolean aux(NodoGen nodo, Object a, Object b){
    
        boolean exito = false;
        if(nodo != null){
            if(nodo.getHijo() != null){ 
                NodoGen recorrer = nodo.getHijo();  
                while(recorrer != null && !exito){
                        if(recorrer.getElemento().equals(a)){
                            NodoGen segundoHno = recorrer.getHno();
                            while(segundoHno != null && !exito){
                                if(segundoHno.getElemento().equals(b)){
                                    exito = true;
                                }else{
                                    segundoHno = segundoHno.getHno();
                                }
                            }
                            if(!exito){
                                recorrer = null;
                            }
                        }else{
                            recorrer = recorrer.getHno();
                        }   
                }
                if(!exito){
                    recorrer = nodo.getHijo();
                    while(recorrer != null && !exito){
                    exito = aux(recorrer, a, b);
                    if(!exito){
                        recorrer = recorrer.getHno();
                    }
                }
                }             
            }
        }
        return exito;
    }
}
