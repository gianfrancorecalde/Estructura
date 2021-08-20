package Estructura.Repaso;
import Estructura.TpFinal.Estructuras.Diccionario.AVL.*;

public class algo {
    private boolean eliminarAux(NodoAVLDicc nodo, NodoAVLDicc padre,  Comparable clave){

        boolean encontrado = false;
        if(nodo != null){
            if(clave.compareTo(nodo.getClave()) == 0){
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
                if(clave.compareTo(nodo.getClave()) < 0){ // Elemento es menor al nodo
                    // Enlazo el hijo del hijo izquierdo del nodo actual, al nodo actual
                    if(eliminarAux(nodo.getIzquierdo(), nodo, clave)){
                        nodo.getIzquierdo().recalcularAltura();
                        nodo.recalcularAltura();
                    }    
                    int balanceHijo = getBalance(nodo.getIzquierdo());  // Calculo el balance del hijo izquierdo
                    if(Math.abs(balanceHijo) > 1){
                        // El hijo se encuentra desbalanceado por lo que sera necesario rotar ese subarbol
                        nodo.setIzquierdo(balancear(nodo.getIzquierdo()));
                    }                               
                }else{  // Elemento es mayor al nodo
                    if(clave.compareTo(nodo.getClave()) > 0){
                        // Enlazo el hijo del hijo derecho del nodo actual, al nodo actual
                        if(eliminarAux(nodo.getDerecho(), nodo, clave)){
                            nodo.getDerecho().recalcularAltura();
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
                    n = balancear(nodo);
                }
            }
        }
        return encontrado;
    }

    private void caso1(NodoAVLDicc hijo, NodoAVLDicc padre){
        if(hijo != null){
            if(hijo.getClave().compareTo(padre.getClave()) < 0){
                padre.setIzquierdo(null);
            }else{
                if(hijo.getClave().compareTo(padre.getClave()) > 0){
                    padre.setDerecho(null);
                }
            }
        }
    }

    private void casoSubArbolIzq(NodoAVLDicc hijo, NodoAVLDicc padre){
        if(hijo != null){
            if(hijo.getClave().compareTo(padre.getClave()) < 0){
                padre.setIzquierdo(hijo.getIzquierdo());
            }else{
                if(hijo.getClave().compareTo(padre.getClave()) > 0){
                    padre.setDerecho(hijo.getIzquierdo());
                }
            }
            
        }
    }

    private void casoSubArbolDer(NodoAVLDicc hijo, NodoAVLDicc padre){
        if(hijo != null){
            if(hijo.getClave().compareTo(padre.getClave()) < 0){
                padre.setIzquierdo(hijo.getDerecho());
            }else{
                if(hijo.getClave().compareTo(padre.getClave()) > 0){
                    padre.setDerecho(hijo.getDerecho());
                }
            }
        }
    }

    

    private void buscarCandidato(NodoAVLDicc hijo, NodoAVLDicc nietoIzq, NodoAVLDicc nietoDer, NodoAVLDicc padre){
        NodoAVLDicc aux = null;
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
            if(hijo.getClave().compareTo(padre.getClave()) < 0){
                padre.setIzquierdo(aux);
            }else{
                if(hijo.getClave().compareTo(padre.getClave()) > 0){
                    padre.setDerecho(aux);
                }
            }
        } 
    }

    private NodoAVLDicc menorCandidatoDerecho(NodoAVLDicc hijo, NodoAVLDicc padre){
        NodoAVLDicc n = null;
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

    private NodoAVLDicc mayorCandidatoIzq(NodoAVLDicc hijo, NodoAVLDicc padre){
        NodoAVLDicc n = null;
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

    private NodoAVLDicc balancear(NodoAVLDicc nodo){

        NodoAVLDicc resp = null;
        
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


    private int getBalance(NodoAVLDicc nodo){

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

    private NodoAVLDicc rotacionDer(NodoAVLDicc nodo){
        // hace una rotacion a la derecha del subarbol
        NodoAVLDicc h, temp;
        h = nodo.getIzquierdo();
        temp = h.getDerecho();
        h.setDerecho(nodo);
        nodo.setIzquierdo(temp);
        nodo.recalcularAltura();    // recalculo la altura padre
        h.recalcularAltura();
        return h;
    }

    private NodoAVLDicc rotacionIzq(NodoAVLDicc nodo){
        // Hace una rotacion a la izquierda del subarbol
        NodoAVLDicc h, temp;
        h = nodo.getDerecho();
        temp = h.getIzquierdo();
        h.setIzquierdo(nodo);
        nodo.setDerecho(temp);   
        nodo.recalcularAltura();    // recalculo la altura padre
        h.recalcularAltura();
        return h;
    }
}

