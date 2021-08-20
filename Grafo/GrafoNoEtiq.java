package Estructura.Grafo;
import Estructura.Lineales.Dinamicas.*;

public class GrafoNoEtiq {
    
    private NodoVert inicio;

    /* Constructor */
    public GrafoNoEtiq(){
        this.inicio = null;
    }

    public boolean esVacio(){
        return this.inicio == null;
    }

    public void vaciar(){
        this.inicio = null;
    }

    /* Grafo y digrafo */

    /* Metodo para ubicar vertice */
    private NodoVert ubicarVertice(Object buscado){
        NodoVert aux = this.inicio;
        if(!this.esVacio()){
            while(aux != null && !aux.getElem().equals(buscado)){
                aux = aux.getSigVert();
            }
        }
        return aux;
    }

    /* Metodos para insertar y eliminar vertices junto con los arcos (uso recorrido en profundida) */

    public boolean insertarVertice(Object newVert){
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(newVert);
        if(aux == null){
            this.inicio = new NodoVert(newVert,this.inicio,null);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object buscado){
        boolean exito = false;
        Lista visitado = new Lista();
        if(!this.esVacio()){
            NodoVert auxVert = this.inicio, antVert = auxVert;
            while(auxVert != null){
                if(auxVert.getElem().equals(buscado)){
                    // elimino el vertice
                    antVert.setSigVert(auxVert.getSigVert());
                    auxVert = antVert;
                    exito = true;
                }else{
                    if(visitado.localizar(auxVert.getElem()) <0){
                        eliminarArcosEnProfundidad(auxVert, visitado, buscado);
                    }
                }
                antVert = auxVert;
                auxVert = auxVert.getSigVert();
            }
        }        
        return exito;
    }

    private void eliminarArcosEnProfundidad(NodoVert v, Lista vis, Object buscado){
        if(v != null){
            // marca al vertice v como visitado
            vis.insertar(v.getElem(), vis.longitud()+1);
            NodoAdy ady = v.getPrimerAdy(), antAdy = ady;
            while(ady != null){
                // visita en profundidad los adyacentes de v aun no visitados
                if(ady.getVertice().getElem().equals(buscado)){
                    // elimino los arcos al vertice adyacente o nodo que contienen la refenrecia al vertice que se quiere eliminar
                    antAdy.setSigAdyacente(ady.getSigAdyacente());
                    ady = antAdy;
                }else{
                    if(vis.localizar(ady.getVertice().getElem())<0){
                        eliminarArcosEnProfundidad(ady.getVertice(), vis, buscado);
                    }
                }
                antAdy = ady;
                ady = ady.getSigAdyacente();
            }
        }
    }

    /* Metodo para verificar la existencia de un vertice */

    public boolean existeVertice(Object buscado){
        boolean exito = false;
        if(this.ubicarVertice(buscado) != null){
            exito = true;
        }
        return exito;
    }

    /* Metodos para insertar y eliminar arcos */

    public boolean insertarArco(Object origen, Object destino){
        // Genero multigrafos, multidigrafos, digrafos y grafos simples.
        boolean exito = false;
        NodoVert auxD, auxO = this.ubicarVertice(origen);
        if(auxO != null){
            // vertice origen existe
            auxD = this.ubicarVertice(destino);
            if(auxD != null){
                // vertice destino existe
                auxO.setPrimerAdy(new NodoAdy(auxD, auxO.getPrimerAdy()));
                // seteo el enlace del vertice origen con un nuevo adyacente que tendrá como vertice al destino y como adyacente al primer vertice adyacente del vertice origen.
                exito = true;
            }
        }
        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino){
        boolean exito = false;
        NodoVert auxO = this.ubicarVertice(origen);
        NodoAdy adyacente, adyAnterior;
        if(auxO != null){
            adyacente = auxO.getPrimerAdy();
            adyAnterior = adyacente;
            while(adyacente != null){
                // recorro los vertices adyacentes
                // obvió la condicion de !exito para que se eliminen todos los arcos que existan (multifrago o digrafo) al vertice destino 
                if(adyacente.getVertice().getElem().equals(destino)){
                    adyAnterior.setSigAdyacente(adyacente.getSigAdyacente());
                    exito = true;
                }
                adyAnterior=adyacente;
                adyacente = adyacente.getSigAdyacente();
            }
        }
        return exito;
    }

    public boolean existeArco(Object origen , Object destino){
        boolean exito = false;
        NodoVert auxO = this.ubicarVertice(origen);
        NodoAdy adyacente;
        if(auxO != null){
            adyacente = auxO.getPrimerAdy();         
            while(adyacente != null){
                // recorro nodos adyacentes al vertice
                if(adyacente.getVertice().getElem().equals(destino)){
                   exito = true;
                }
            }
        }        
        return exito;
    }

    public Lista listarEnAnchura(){
        Lista visitado = new Lista();
        NodoVert aux = this.inicio;
        while(aux!= null){
            if(visitado.localizar(aux.getElem()) < 0){
                // si el vertice no fue visitado aun, avanza en profundidad
                recorridoEnAnchura(aux, visitado);
            }
            aux = aux.getSigVert();
        }
        return visitado;
    }

    private void recorridoEnAnchura(NodoVert v, Lista visitado){
        Cola q = new Cola();
        visitado.insertar(v.getElem(), visitado.longitud()+1);
        q.poner(v);
        NodoVert u;
        NodoAdy ady;
        while(!q.esVacia()){
            // mientras existan vertices sin visitar
            u = (NodoVert)q.obtenerFrente();
            q.sacar();
            ady = u.getPrimerAdy();
            while(ady!= null){
                // visito vertices adyacente y los guardo en cola
                if(visitado.localizar(ady.getVertice().getElem()) <0){
                    visitado.insertar(ady.getVertice().getElem(), visitado.longitud()+1);
                    q.poner(ady.getVertice());
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Lista listarEnProfundidad(){
        Lista visitado = new Lista();
        // define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        while(aux != null){
            if(visitado.localizar(aux.getElem()) < 0){
                // si el vertice no fue visitado aun, avanza en profundidad
                recorridoEnProfundidad(aux, visitado);
            }
            aux = aux.getSigVert();
        }
        return visitado;
    }

    private void recorridoEnProfundidad(NodoVert v, Lista vis){
        if(v != null){
            // marca al vertice v como visitado
            vis.insertar(v.getElem(), vis.longitud()+1);
            NodoAdy ady = v.getPrimerAdy();
            while(ady != null){
                // visita en profundidad los adyacentes de v aun no visitados
                if(vis.localizar(ady.getVertice().getElem())<0){
                    recorridoEnProfundidad(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    /* Grafo simple y digrafo */

    public boolean existeCamino(Object origen, Object destino){
        boolean existe = false;
        Lista aux = new Lista();
        NodoVert vertInicio = ubicarVertice(origen);
        if(vertInicio != null && ubicarVertice(destino) != null){
            existe = caminoEnProfundidad(vertInicio, aux, destino);
        }
        return existe; 
    }

    private boolean caminoEnProfundidad(NodoVert v, Lista vis, Object destino){
        boolean exito = false;
        if(v != null){
            if(v.getElem().equals(destino)){
                vis.insertar(v.getElem(), vis.longitud()+1);
                exito = true;
            }else{
                vis.insertar(v.getElem(), vis.longitud()+1);
                NodoAdy ady = v.getPrimerAdy();
                while(ady != null && !exito){
                // visita en profundidad los adyacentes de v aun no visitados
                    if(vis.localizar(ady.getVertice().getElem())<0){
                        exito =  caminoEnProfundidad(ady.getVertice(), vis, destino);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public Lista caminoMasCorto(Object origen, Object destino){
        Lista caminoMasCorto = new Lista(), aux = new Lista();
        NodoVert vertInicio = ubicarVertice(origen);
        if(vertInicio != null && ubicarVertice(destino) != null){
            caminoCortoEnProfundidad(vertInicio, caminoMasCorto, aux, destino);
        }
        return caminoMasCorto;
    }

    private void caminoCortoEnProfundidad(NodoVert v, Lista caminoMasCorto, Lista camino, Object destino){
        if(v != null){
            if(v.getElem().equals(destino)){
                camino.insertar(destino,camino.longitud()+1);
                if(camino.longitud()<caminoMasCorto.longitud() || caminoMasCorto.longitud() == 0){
                    // compara las lista y guardo la lista con menor longitud en caminoMasCorto
                    caminoMasCorto = camino.cloneMejorado();
                }
            }else{
                camino.insertar(v.getElem(), camino.longitud()+1);
                NodoAdy ady = v.getPrimerAdy();
                while(ady != null){
                // visita en profundidad los adyacentes de v aun no visitados
                    if(camino.localizar(ady.getVertice().getElem()) <0){
                        caminoCortoEnProfundidad(ady.getVertice(), caminoMasCorto, camino, destino);
                        camino.eliminar(camino.longitud());
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
    }

    public Lista caminoMasLargo(Object origen, Object destino){
        Lista caminoMasLargo = new Lista(), aux = new Lista();
        NodoVert vertInicio = ubicarVertice(origen);
        if(vertInicio != null && ubicarVertice(destino) != null){
            caminoLargoEnProfundidad(vertInicio, caminoMasLargo, aux, destino);
        }
        return caminoMasLargo;
    }

    private void caminoLargoEnProfundidad(NodoVert v, Lista caminoMasLargo, Lista camino, Object destino){
        if(v != null){
            if(v.getElem().equals(destino)){
                camino.insertar(destino,camino.longitud()+1);
                if(camino.longitud()>caminoMasLargo.longitud()){
                    caminoMasLargo = camino.cloneMejorado();
                }
            }else{
                camino.insertar(v.getElem(), camino.longitud()+1);
                NodoAdy ady = v.getPrimerAdy();
                while(ady != null){
                // visita en profundidad los adyacentes de v aun no visitados
                    if(camino.localizar(ady.getVertice().getElem()) <0){
                        caminoLargoEnProfundidad(ady.getVertice(), caminoMasLargo, camino, destino);
                        camino.eliminar(camino.longitud());
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
    }
}
