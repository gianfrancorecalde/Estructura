package Estructura.jerarquicas.ArbolConjuntista;

public class AVL {
    
    /*      Atributo        */

    private NodoAVL raiz;

    /*      Constructor     */

    public AVL(){
        this.raiz = null;
    }

    /*      ESVACIO         */

    public boolean esVacio(){
        return this.raiz == null;
    }
   
    /*      VACIAR         */

    public void vaciar(){
        this.raiz = null;
    }

    /*      Insertar        */

    public boolean insertarRecursivo(Comparable elemento){

        boolean exito = false;
        if(this.esVacio()){
            this.raiz = new NodoAVL(elemento, null, null);
            exito = true;
        }else{
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL nodo, Comparable elemento){

        boolean exito = false;
        int balanceHijo, balancePadre;
        if(nodo != null){
            if(nodo.getElem().compareTo(elemento) == 0){
                exito = true;
            }else{
                if(elemento.compareTo(nodo.getElem()) < 0){
                    exito = insertarAux(nodo.getIzquierdo(), elemento);
                    if(!exito){
                        // si exito es falso es porque el hijo izquierdo en null 
                        // entonces enlazo el nuevo elemento al nodo actual
                        nodo.setIzquierdo(new NodoAVL(elemento, null, null));   
                        exito = true;
                    }
                    nodo.recalcularAltura();    // Recalculo la altura del nodo actual  
                    balanceHijo = getBalance(nodo.getIzquierdo());  // Calculo el balance del hijo izquierdo
                    if(Math.abs(balanceHijo) >= 2){
                        // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                        nodo.setIzquierdo(balancear(nodo.getIzquierdo()));
                    }
                    
                }else{
                    if(elemento.compareTo(nodo.getElem()) > 0){
                        exito = insertarAux(nodo.getDerecho(), elemento);
                        if(!exito){
                            // si exito es falso es porque el hijo derecho en null 
                            // entonces enlazo el nuevo elemento al nodo actual
                            nodo.setDerecho(new NodoAVL(elemento, null, null));
                            exito = true;
                        }
                        nodo.recalcularAltura();    // recalculo la altura del nodo actual
                        balanceHijo = getBalance(nodo.getDerecho());    // calculo el balance del hizo derecho
                        if(Math.abs(balanceHijo) >= 2){
                            // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                            nodo.setDerecho(balancear(nodo.getDerecho()));
                        }
                    }
                }
                if(nodo == this.raiz){
                    // En el caso de que el nodo actual sea la raiz del arbol mayor y este este esta desbalanceado se debera rotar
                    if(Math.abs(getBalance(nodo)) >= 2){
                        this.raiz = balancear(nodo);
                    }
                }
            }
        }
        return exito;
    }

    /*      Eliminar        */
    
    public boolean eliminar(Comparable elemento){

        boolean exito = false;
        if(!this.esVacio()){
            exito = eliminarAux(this.raiz,null, elemento);
        }   
        return exito;
    }

    private boolean eliminarAux(NodoAVL nodo, NodoAVL padre,  Comparable clave){

        boolean encontrado = false;
        if(nodo != null){
            if(clave.compareTo(nodo.getElem()) == 0){
                encontrado = true;
                if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
                    // Caso 1: se manda al mismo hijo para ser eliminado
                    caso1(nodo, padre);
                }else{
                    if(nodo.getDerecho() == null){
                        // Caso 2: solo tiene hijo izquierdo
                        casoSubArbolIzq(nodo, padre);
                    }else{
                        if(nodo.getIzquierdo() == null){
                            // Caso 2: solo tiene hijo derecho
                            casoSubArbolDer(nodo,padre);
                        }else{
                            // Caso 3: Tiene dos hijos
                            buscarCandidato(nodo,nodo.getIzquierdo(), nodo.getDerecho(), padre);
                        }
                    }
                }
            }else{
                if(clave.compareTo(nodo.getElem()) < 0){ // Elemento es menor al nodo
                    // Enlazo el hijo del hijo izquierdo del nodo actual, al nodo actual
                    if(eliminarAux(nodo.getIzquierdo(), nodo, clave)){
                        nodo.recalcularAltura();
                    }    
                    int balanceHijo = getBalance(nodo.getIzquierdo());  // Calculo el balance del hijo izquierdo
                    if(Math.abs(balanceHijo) > 1){
                        // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                        nodo.setIzquierdo(balancear(nodo.getIzquierdo()));
                    }                               
                }else{  // Elemento es mayor al nodo
                    if(clave.compareTo(nodo.getElem()) > 0){
                        // Enlazo el hijo del hijo derecho del nodo actual, al nodo actual
                        if(eliminarAux(nodo.getDerecho(), nodo, clave)){
                            nodo.recalcularAltura();    // recalculo la altura del nodo actual
                        }
                        int balanceHijo = getBalance(nodo.getDerecho());    // calculo el balance del hizo derecho
                        if(Math.abs(balanceHijo) > 1){
                            // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                            nodo.setDerecho(balancear(nodo.getDerecho()));
                        }
                    }
                }
            }
            if(nodo == this.raiz){
                // En el caso de que el nodo actual sea la raiz del arbol mayor y este este esta desbalanceado se debera rotar
                if(Math.abs(getBalance(nodo)) >= 2){
                    this.raiz = balancear(nodo);
                }
            }
        }
        return encontrado;
    }

    private void caso1(NodoAVL hijo, NodoAVL padre){
        if(hijo != null){
            if(hijo.getElem().compareTo(padre.getElem()) < 0){
                padre.setIzquierdo(null);
            }else{
                if(hijo.getElem().compareTo(padre.getElem()) > 0){
                    padre.setDerecho(null);
                }
            }
        }
    }

    private void casoSubArbolIzq(NodoAVL hijo, NodoAVL padre){
        if(hijo != null){
            if(hijo.getElem().compareTo(padre.getElem()) < 0){
                padre.setIzquierdo(hijo.getIzquierdo());
            }else{
                if(hijo.getElem().compareTo(padre.getElem()) > 0){
                    padre.setDerecho(hijo.getIzquierdo());
                }
            }
            
        }
    }

    private void casoSubArbolDer(NodoAVL hijo, NodoAVL padre){
        if(hijo != null){
            if(hijo.getElem().compareTo(padre.getElem()) < 0){
                padre.setIzquierdo(hijo.getDerecho());
            }else{
                if(hijo.getElem().compareTo(padre.getElem()) > 0){
                    padre.setDerecho(hijo.getDerecho());
                }
            }
        }
    }

    

    private void buscarCandidato(NodoAVL hijo, NodoAVL nietoIzq, NodoAVL nietoDer, NodoAVL padre){
        NodoAVL aux = null;
        if(hijo != null){
            if(nietoIzq.getIzquierdo() == null && nietoIzq.getDerecho() == null){
                aux = nietoIzq;
                aux.setDerecho(nietoDer);
            }else{
                if(nietoDer.getIzquierdo() == null && nietoDer.getDerecho() == null){
                    aux = nietoDer;
                    aux.setIzquierdo(nietoIzq);
                }else{
                    aux = menorCandidatoDerecho(nietoDer, hijo);
                    aux.setDerecho(nietoDer);
                    aux.setIzquierdo(nietoIzq);
                }
            }
            if(hijo.getElem().compareTo(padre.getElem()) < 0){
                padre.setIzquierdo(aux);
            }else{
                if(hijo.getElem().compareTo(padre.getElem()) > 0){
                    padre.setDerecho(aux);
                }
            }
        } 
    }

    private NodoAVL menorCandidatoDerecho(NodoAVL hijo, NodoAVL padre){
        NodoAVL n = null;
        if(hijo != null){
            if(hijo.getIzquierdo() == null && hijo.getDerecho() == null){
                n = hijo;
                padre.setIzquierdo(null);
            }else{
                if(hijo.getIzquierdo() == null){
                    n = hijo;
                    padre.setIzquierdo(hijo.getDerecho());
                }else{
                    n = menorCandidatoDerecho(hijo.getIzquierdo(), hijo);
                    hijo.recalcularAltura();
                    int balance = getBalance(hijo);
                    if(Math.abs(balance) > 1){
                        // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                        padre.setIzquierdo(balancear(hijo));
                    }
                }
            }
        }
        
        return n;
    }

    private NodoAVL mayorCandidatoIzq(NodoAVL hijo, NodoAVL padre){
        NodoAVL n = null;
        if(hijo != null){
            if(hijo.getIzquierdo() == null && hijo.getDerecho() == null){
                n = hijo;
                padre.setDerecho(null);
            }else{
                if(hijo.getDerecho() == null){
                    n = hijo;
                    padre.setDerecho(hijo.getIzquierdo());
                }else{
                    n = menorCandidatoDerecho(hijo.getDerecho(), hijo);
                    hijo.recalcularAltura();
                    int balance = getBalance(hijo);
                    if(Math.abs(balance) > 1){
                        // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                        padre.setDerecho(balancear(hijo));
                    }
                }
            }
        }
        return n;
    }

    private NodoAVL balancear(NodoAVL nodo){

        NodoAVL resp = null;
        
        if(nodo != null){

            if(getBalance(nodo) >= 2 && getBalance(nodo.getIzquierdo()) >= 0){
                //  rotacion simple hacia la derecha 
                resp = rotacionDer(nodo);
            }else{
                if(getBalance(nodo) <= -2 && getBalance(nodo.getDerecho()) <= 0){
                    // rotacion simple hacia la izquierda
                    resp = rotacionIzq(nodo);
                }else{
                    if(getBalance(nodo) >=2 && getBalance(nodo.getIzquierdo()) <= 0){
                        // rotacion doble izq - der 
                        nodo.setIzquierdo(rotacionIzq(nodo.getIzquierdo()));
                        resp = rotacionDer(nodo);
                    }else{
                        if(getBalance(nodo) <= -2 && getBalance(nodo.getDerecho()) >= 0){
                            // rotacion doble der - izq
                            nodo.setDerecho(rotacionDer(nodo.getDerecho()));
                            resp = rotacionIzq(nodo);
                        }
                    }
                }
            }
        }
        return resp;
    }


    private int getBalance(NodoAVL nodo){

        int balance = -1;
        if(nodo != null){
            if(nodo.getIzquierdo() != null && nodo.getDerecho() != null){
                // tiene dos hijos 
                balance = nodo.getIzquierdo().getAltura() - nodo.getDerecho().getAltura();
            }else{
                if(nodo.getIzquierdo() != null && nodo.getDerecho() == null){
                    // solo tiene hijo izquierdo
                    balance = nodo.getIzquierdo().getAltura() - (-1);
                }else{
                    if(nodo.getIzquierdo() == null && nodo.getDerecho() != null){
                        // solo tiene hijo derecho
                        balance  = (-1) - nodo.getDerecho().getAltura();
                    }else{
                        // no tiene hijos
                        balance = 0;
                    }
                }
            }
        }
        return balance;
    }

    private NodoAVL rotacionDer(NodoAVL nodo){
        // hace una rotacion a la derecha del subarbol
        NodoAVL h, temp;
        h = nodo.getIzquierdo();
        temp = h.getDerecho();
        h.setDerecho(nodo);
        nodo.setIzquierdo(temp);
        nodo.recalcularAltura();    // recalculo la altura padre
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotacionIzq(NodoAVL nodo){
        // Hace una rotacion a la izquierda del subarbol
        NodoAVL h, temp;
        h = nodo.getDerecho();
        temp = h.getIzquierdo();
        h.setIzquierdo(nodo);
        nodo.setDerecho(temp);   
        nodo.recalcularAltura();    // recalculo la altura padre
        h.recalcularAltura();
        return h;
    }

/*     private NodoAVL eliminarAux(NodoAVL nodo, Comparable elemento){

        NodoAVL n = null;
        int balanceHijo;
        if(nodo != null){
            if(elemento.compareTo(nodo.getElem()) == 0){
                if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
                    // Caso 1: se manda al mismo hijo para ser eliminado
                    n = null;
                }else{
                    if(nodo.getDerecho() == null){
                        // Caso 2: solo tiene hijo izquierdo
                        n = nodo.getIzquierdo();
                    }else{
                        if(nodo.getIzquierdo() == null){
                            // Caso 2: solo tiene hijo derecho
                            n = nodo.getDerecho();
                        }else{
                            // Caso 3: Tiene dos hijos
                            n = aux(nodo);  
                        }
                    }
                }
            }else{
                if(elemento.compareTo(nodo.getElem()) < 0){ // Elemento es menor al nodo
                    // Enlazo el hijo del hijo izquierdo del nodo actual, al nodo actual
                    nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), elemento));
                    n = nodo;
                    nodo.recalcularAltura();
                    balanceHijo = getBalance(nodo.getIzquierdo());  // Calculo el balance del hijo izquierdo
                    if(Math.abs(balanceHijo) >= 2){
                        // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                        nodo.setIzquierdo(balancear(nodo.getIzquierdo()));
                    }
                    
                }else{  // Elemento es mayor al nodo
                    // Enlazo el hijo del hijo derecho del nodo actual, al nodo actual
                    nodo.setDerecho(eliminarAux(nodo.getDerecho(), elemento));
                    n = nodo;
                    nodo.recalcularAltura();    // recalculo la altura del nodo actual
                    balanceHijo = getBalance(nodo.getDerecho());    // calculo el balance del hizo derecho
                    if(Math.abs(balanceHijo) >= 2){
                        // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                        nodo.setDerecho(balancear(nodo.getDerecho()));
                    }

                }
            }
            if(nodo == this.raiz){
                // En el caso de que el nodo actual sea la raiz del arbol mayor y este este esta desbalanceado se debera rotar
                if(Math.abs(getBalance(nodo)) >= 2){
                    n = balancear(nodo);
                }
            }
        }
        return n;
    }

    private NodoAVL aux(NodoAVL nodo){
        // Este metod se usa cuando el nodo buscado tiene dos hijos 
        NodoAVL hijo = nodo.getIzquierdo();
        NodoAVL hijoIzq = hijo;
        NodoAVL hijoDer = nodo.getDerecho();
        if((hijoDer.getDerecho() == null && hijoDer.getIzquierdo() == null) && (hijoIzq.getDerecho() == null && hijoIzq.getIzquierdo() == null)){
            // Se implementa este If para los caso en el que los hijo no tienen hijos
            // O en el caso de que uno de ellos si tenga hijos
            hijoIzq.setDerecho(hijoDer);
            nodo.setDerecho(null);
            hijo = hijoIzq;
        }else{
            if(hijoDer.getDerecho() == null && hijoDer.getIzquierdo() == null){
                hijoDer.setIzquierdo(hijoIzq);
                nodo.setIzquierdo(null);
                hijo = hijoDer;
            }else{
                if(hijoIzq.getDerecho() == null && hijoIzq.getIzquierdo() == null){
                    hijoIzq.setDerecho(hijoDer);
                    nodo.setDerecho(null);
                    hijo = hijoIzq;
                }else{
                    // Si ambos hijos tiene hijos entonces se hace un recorrido en busqueda del hijo mayor del hijo izquierdo.
                    NodoAVL hijoAnterior = hijo;
                    boolean corte = false;
                    while(hijo != null && !corte){
                    // Recorre los nodos izquierdo hasta encontrado el mayor
                        if(hijo.getDerecho() == null){
                            hijo.setDerecho(nodo.getDerecho());    // Enlazo al nodo mayor los hijos derecho del nodo actual
                            nodo.setDerecho(hijo);                 // Enlazo el nodo actual con el nodo mayor como si fuera hijo derecho
                            hijoAnterior.setDerecho(null);         // Desenlaza el nodo mayor del padre actual
                            hijo.setIzquierdo(nodo.getIzquierdo());    // Enlazo al nodo mayor los hijos izquierdo del nodo actual
                            nodo.setIzquierdo(null);                // Pongo en null enlace Izquierdo del nodo acutal
                            corte = true;   
                        }else{
                            hijoAnterior = hijo;
                            hijo = hijo.getDerecho();
                        }
                    }  
                }
            }
        }
        return hijo;       
    }

    private NodoAVL balancear(NodoAVL nodo){

        NodoAVL resp = null;
        
        if(nodo != null){

            if(getBalance(nodo) >= 2 && getBalance(nodo.getIzquierdo()) >= 0){
                //  rotacion simple hacia la derecha 
                resp = rotacionDer(nodo);
                resp.recalcularAltura();    // recalculo la altura del nuevo padre
            }else{
                if(getBalance(nodo) <= -2 && getBalance(nodo.getDerecho()) <= 0){
                    // rotacion simple hacia la izquierda
                    resp = rotacionIzq(nodo);
                    resp.recalcularAltura();    // recalculo la altura del nuevo padre
                }else{
                    if(getBalance(nodo) >=2 && getBalance(nodo.getIzquierdo()) <= 0){
                        // rotacion doble izq - der 
                        nodo.setIzquierdo(rotacionIzq(nodo.getIzquierdo()));
                        resp = rotacionDer(nodo);
                        resp.recalcularAltura();    // recalculo la altura del nuevo padre
                    }else{
                        if(getBalance(nodo) <= -2 && getBalance(nodo.getDerecho()) >= 0){
                            // rotacion doble der - izq
                            nodo.setDerecho(rotacionDer(nodo.getDerecho()));
                            resp = rotacionIzq(nodo);
                            resp.recalcularAltura();    // recalculo la altura del nuevo padre
                        }
                    }
                }
            }
        }
        return resp;
    }


    private int getBalance(NodoAVL nodo){

        int balance = -1;
        if(nodo != null){
            if(nodo.getIzquierdo() != null && nodo.getDerecho() != null){
                // tiene dos hijos 
                balance = nodo.getIzquierdo().getAltura() - nodo.getDerecho().getAltura();
            }else{
                if(nodo.getIzquierdo() != null && nodo.getDerecho() == null){
                    // solo tiene hijo izquierdo
                    balance = nodo.getIzquierdo().getAltura() - (-1);
                }else{
                    if(nodo.getIzquierdo() == null && nodo.getDerecho() != null){
                        // solo tiene hijo derecho
                        balance  = (-1) - nodo.getDerecho().getAltura();
                    }else{
                        // no tiene hijos
                        balance = 0;
                    }
                }
            }
        }
        return balance;
    }

    private NodoAVL rotacionDer(NodoAVL nodo){
        // hace una rotacion a la derecha del subarbol
        NodoAVL h, temp;
        h = nodo.getIzquierdo();
        temp = h.getDerecho();
        h.setDerecho(nodo);
        nodo.setIzquierdo(temp);
        nodo.recalcularAltura();    // recalculo la altura padre
        return h;
    }

    private NodoAVL rotacionIzq(NodoAVL nodo){
        // Hace una rotacion a la izquierda del subarbol
        NodoAVL h, temp;
        h = nodo.getDerecho();
        temp = h.getIzquierdo();
        h.setIzquierdo(nodo);
        nodo.setDerecho(temp);   
        nodo.recalcularAltura();    // recalculo la altura padre
        return h;
    } */

    /*      toString        */

    public String toString(){
        
        String cadena;
        if(this.esVacio()){
            cadena = "Arbol vacio";
        }else{
            cadena = generarString(this.raiz);
        }
        return cadena;
    }

    private String generarString(NodoAVL n){
        
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
                cadena = n.getElem()+ "("+n.getAltura()+")" + " HI: "+ cadenaHI + " HD: "+cadenaHD;
            }else{
                if(!cadenaHI.equalsIgnoreCase("-") && cadenaHD.equalsIgnoreCase("-")){
                    // Si solo el hijo derecho es nulo, devuelve un String: 
                    // elemento HI: elementoHI HD: - 
                    // elementoHI HI: ... HD: ...
                    cadena = n.getElem()+ "("+n.getAltura()+")" + " HI: " + n.getIzquierdo().getElem() + " HD: "+ cadenaHD + "\n"+ cadenaHI; 
                }else{
                    if(cadenaHI.equalsIgnoreCase("-") && !cadenaHD.equalsIgnoreCase("-")){
                        // Si solo el hijo izquierdo es nulo, devuelve un String:
                        // elemento HI: - HD: elementoHD 
                        // elementoHD HI: ... HD: ...
                        cadena = n.getElem()+ "("+n.getAltura()+")" + " HI: " + cadenaHI + " HD: "+ n.getDerecho().getElem() + "\n"+ cadenaHD; 
                    }else{
                        // Si ninguno de los dos hijos es nulo, devuelve:
                        // elemento HI: elementoHI HD: elementoHD 
                        // elementoHI HI: ... HD: ...
                        // elementoHD HI: ... HD: ...
                        cadena = n.getElem()+ "("+n.getAltura()+")" + " HI: " + n.getIzquierdo().getElem() + " HD: "+ n.getDerecho().getElem() + "\n"+ cadenaHI + "\n"+ cadenaHD ; 
                    }
                }
            }
            
        }
        return cadena;
    }



}
