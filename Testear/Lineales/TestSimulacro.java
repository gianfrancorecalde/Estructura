package Estructura.Testear.Lineales;
import Estructura.Lineales.Dinamicas.*;
public class TestSimulacro {
    
    public static void main(String[] args) {
        Cola c1 = new Cola();
        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');
        Cola c2 = generar(c1);
        System.out.println(c2.toString());
    }

    public static Cola generar(Cola c1){

        Cola cola2 = new Cola();
        Pila aux1 = new Pila();
        Pila aux2 = new Pila();
        char letra;
        c1.poner('$');                                      // Agrego este elemento al final de la cola para poder terminar de generar la otra cola con las nuevas secuecias.
        while(!c1.esVacia()){
            letra = (char)c1.obtenerFrente();
            if(letra == '#' || letra == '$'){
                agregarInvertido(c1, cola2, aux1, aux2);
                if(letra == '#'){
                    cola2.poner('#');
                }
            }else{
                cola2.poner(letra);
                aux1.apilar(letra);
                
            }
            c1.sacar();
        }
        return cola2;
    }

    public static void agregarInvertido(Cola colaOriginal, Cola otraCola, Pila aux1, Pila aux2){

        while(!aux1.esVacia()){
            otraCola.poner(aux1.obtenerTope());
            aux2.apilar(aux1.obtenerTope());
            aux1.desapilar();
        }
        while(!aux2.esVacia()){
            otraCola.poner(aux2.obtenerTope());
            aux2.desapilar();
        }        
    }

}
