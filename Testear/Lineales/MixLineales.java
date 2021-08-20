package Estructura.Testear.Lineales;
import Estructura.Lineales.Dinamicas.*;
public class MixLineales {
    // hecho con pila y cola unicamente
    public static Cola generarOtraCola(Cola colaOriginal){

        Cola colaReturn = new Cola();
        Pila pilaAux = new Pila();
        char aux, auxInvertido;
        colaOriginal.poner('0'); // le agrego un elemento mas a la cola para poder copiar en la nueva cola el ultimo tramo invertido de la cola original.
        while(!colaOriginal.esVacia()){
            aux = (char)colaOriginal.obtenerFrente();
            colaOriginal.sacar();
            if(aux != '$' && aux != '0'){
                colaReturn.poner(aux);
                pilaAux.apilar(aux);
            }else{
                while(!pilaAux.esVacia()){
                    auxInvertido = (char)pilaAux.obtenerTope();
                    pilaAux.desapilar();
                    colaReturn.poner(auxInvertido);
                }
                if(aux != '0'){
                    colaReturn.poner(aux);
                }
            }
        }
        return colaReturn;
    }

    public static Lista generarOtraLista(Lista lis){
        //lis.insertar('$', lis.longitud()+1);
        Cola cola = new Cola();
        Pila pilaAux = new Pila();
        Lista lisReturn = new Lista();
        int pos = 1;
        char aux; 
        while(pos <= lis.longitud()){
            aux = (char)lis.recuperar(pos);
            if(aux != '$'){
                lisReturn.insertar(aux, lisReturn.longitud()+1);
                cola.poner(aux);
                pilaAux.apilar(aux);
            }else{
                agregarCadenas(pilaAux, cola, lisReturn);
                //if(pos != lis.longitud()){ 
                    lisReturn.insertar(aux,lisReturn.longitud()+1);
                //}
            }
            pos++;
        }
        if(pos > lis.longitud()){
            agregarCadenas(pilaAux, cola, lisReturn);
        }
        return lisReturn;
    }

    public static void agregarCadenas(Pila pila1, Cola cola1, Lista lis){

        while(!pila1.esVacia()){
            lis.insertar(pila1.obtenerTope(),lis.longitud()+1);
            pila1.desapilar();
        }
        while(!cola1.esVacia()){
            lis.insertar(cola1.obtenerFrente(),lis.longitud()+1);
            cola1.sacar();
        }
    }

    // ------- ejercicio 2 del parcial 1

    /* Sin uso de estructuras auxiliares */
    public static Lista formarLista(Pila pila1){
        Lista lisReturn = new Lista();
        int contador = 0, pos = 1;
        char aux;
        while(!pila1.esVacia()){
            aux = (char)pila1.obtenerTope();
            pila1.desapilar();
            if(aux != '@'){
                if(contador%2 == 0){
                    // inserto la cadena inverta, tal cual me la devuelve la pila
                    lisReturn.insertar(aux,pos);
                    pos++; // inserto los elementos en la posicion pos
                }else{
                    // inserto la cadana no invertida
                    lisReturn.insertar(aux,1); // inserto los elementos siempre al inicio de la lista
                }
            }else{
                lisReturn.insertar(aux,1);
                pos = 1; // vuelve a inicializar a pos en 1 para que en la proxima secuencia par la cadena se inserte invertida
                contador++;
            }
        }
        return lisReturn;
    }

    public static Lista armarLista(Pila pila1){
        Lista lisReturn = new Lista();
        int contador = 0, pos = 1;
        char aux;
        while(!pila1.esVacia()){
            aux = (char)pila1.obtenerTope();
            pila1.desapilar();
            if(aux != '&'){
                if(contador%2 != 0){
                    lisReturn.insertar(aux,pos);
                    pos++;
                }else{
                    lisReturn.insertar(aux,1);
                }
            }else{
                lisReturn.insertar(aux,1);
                contador++;
                pos = 1;
            }
        }
        return lisReturn;
    }

    public static Lista generarLista(Cola cola1){
        Lista lisReturn = new Lista();
        int contador = 1, pos = 1;
        char aux;
        while(!cola1.esVacia()){
            aux = (char)cola1.obtenerFrente();
            cola1.sacar();
            if(aux != '#'){
                if(contador%2 == 0){
                    lisReturn.insertar(aux,pos);
                }else{
                    lisReturn.insertar(aux,lisReturn.longitud()+1);
                }
            }else{
                lisReturn.insertar(aux,lisReturn.longitud()+1);
                pos = lisReturn.longitud()+1;
                contador++;
            }
        }
        return lisReturn;
    }

    public static Lista crearLista(Cola cola1){
        Lista lisReturn = new Lista();
        int contador = 1, pos = 1;
        char aux;
        while(!cola1.esVacia()){
            aux = (char)cola1.obtenerFrente();
            cola1.sacar();
            if(aux != '$'){
                if(contador%2 != 0){
                    lisReturn.insertar(aux,pos);
                }else{
                    lisReturn.insertar(aux,lisReturn.longitud()+1);
                }
            }else{
                lisReturn.insertar(aux,lisReturn.longitud()+1);
                pos = lisReturn.longitud()+1;
                contador++;
            }
        }
        return lisReturn;
    }
    /* Usando estructuras auxiliares */
    public static Lista formarListaConEstructura(Pila pila1){
        Lista lisReturn = new Lista();
        Pila pilaAux = new Pila();
        int contador = 0;
        char aux;
        while(!pila1.esVacia()){
            aux = (char)pila1.obtenerTope();
            pila1.desapilar();
            if(aux != '@'){
                if(contador%2 == 0){
                    pilaAux.apilar(aux);
                }else{
                    lisReturn.insertar(aux,1); 
                }
            }else{
                while(!pilaAux.esVacia()){
                    lisReturn.insertar(pilaAux.obtenerTope(), 1);
                    pilaAux.desapilar();
                }
                lisReturn.insertar(aux,1);
                contador++;
            }
        }
        if(!pilaAux.esVacia()){
            while(!pilaAux.esVacia()){
                lisReturn.insertar(pilaAux.obtenerTope(), 1);
                pilaAux.desapilar();
            }
        }
        return lisReturn;
    }

    public static Lista generarListaConEstructura(Cola cola1){
        Cola clon = cola1.clone2();
        clon.poner('0');
        Lista lisReturn = new Lista();
        Pila pilaAux = new Pila();
        int contador = 1;
        char aux;
        while(!clon.esVacia()){
            aux = (char)clon.obtenerFrente();
            clon.sacar();
            if(aux != '#' && aux != '0'){
                if(contador%2 == 0){
                    pilaAux.apilar(aux);
                }else{
                    lisReturn.insertar(aux,lisReturn.longitud()+1); 
                }
            }else{
                while(!pilaAux.esVacia()){
                    lisReturn.insertar(pilaAux.obtenerTope(), lisReturn.longitud()+1);
                    pilaAux.desapilar();
                }
                if(aux != '0'){
                    lisReturn.insertar(aux,lisReturn.longitud()+1);
                }
                contador++;
            }
        }
        return lisReturn;
    }

    // ------------------------------ recuperatorio 
    public static boolean verificarPatron(Pila p){
        Pila pilaAux = new Pila();
        Cola colaAux = new Cola();
        boolean verificar = true;
        int contador = 0;
        char aux;
        char aux2='.';
        while(!p.esVacia() && verificar){
            aux = (char)p.obtenerTope();
            p.desapilar();
            if(aux != '&' && verificar){
                if(contador%2 == 0){
                    if(aux2 != aux){
                        pilaAux.apilar(aux);
                        colaAux.poner(aux);
                    }else{
                        verificar=false;
                    }   
                }else{
                        if(!pilaAux.esVacia()){
                            if(aux == (char)pilaAux.obtenerTope()){
                                pilaAux.desapilar();
                            }else{
                                verificar = false;
                            }
                        }else{
                            if(!colaAux.esVacia()){
                                if(aux == (char)colaAux.obtenerFrente()){
                                    colaAux.sacar();
                                }else{
                                    verificar = false;
                                }
                            }else{
                                verificar = false;
                            }
                        }
                }    
            }else{
                if(aux == '&'){
                    contador++;
                }
            }
            aux2 = aux;
        }
        /* if(!pilaAux.esVacia() && !colaAux.esVacia()){
            verificar = false;
        } */
        return verificar;
    }

    public static void main(String[] args) {
        
        /* Cola cl = new Cola();
        cl.poner('A');
        cl.poner('B');
        cl.poner('#');
        cl.poner('C');
        cl.poner('A');
        cl.poner('E');
        cl.poner('#');
        cl.poner('D');
        cl.poner('E');
        cl.poner('F');
        cl.poner('#');
        cl.poner('E');
        cl.poner('B');
        cl.poner('D');
        Lista cl1 = generarListaConEstructura(cl);
        System.out.println(cl1.toString()); */
        /* Lista lis1 = new Lista();
        lis1.insertar('A', 1);
        lis1.insertar('B', 2);
        lis1.insertar('$', 3);
        //lis1.insertar('C', 4);
        lis1.insertar('$', 4);
        lis1.insertar('D', 5);
        lis1.insertar('E', 6);
        lis1.insertar('F', 7);
        Lista lis = generarOtraLista(lis1);
        System.out.println(lis.toString()); */
        Pila pila = new Pila();
        pila.apilar('7');
        pila.apilar('4');
        //pila.apilar('4');
        pila.apilar('4');
        pila.apilar('7');
        //pila.apilar('&');
        pila.apilar('7');
        pila.apilar('4');
        //pila.apilar('&');
        pila.apilar('3');
        pila.apilar('2');
        pila.apilar('1');
        pila.apilar('1');
        pila.apilar('2');
        pila.apilar('3');
        //pila.apilar('&');
        pila.apilar('3');
        pila.apilar('2');
        pila.apilar('1');
        System.out.println(pila.toString());
        System.out.println(pila.obtenerTope());
        boolean v = verificarPatron(pila);
        System.out.println(pila.toString());
        System.out.println(v);
        /* Pila pila = new Pila();
        pila.apilar('A');
        pila.apilar('B');
        pila.apilar('&');
        pila.apilar('C');
        pila.apilar('A');
        pila.apilar('E');
        pila.apilar('&');
        pila.apilar('D');
        pila.apilar('E');
        pila.apilar('F');
        pila.apilar('&');
        pila.apilar('E');
        pila.apilar('B');
        pila.apilar('D');
        Lista lis = armarLista(pila);
        System.out.println(lis.toString()); */
    }
}
