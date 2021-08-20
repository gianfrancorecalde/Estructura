package Estructura.Testear.Lineales;
import Estructura.Lineales.Dinamicas.*;
public class PruebaLista {
    
    public static Lista concatenar(Lista lis1, Lista lis2){
        
        Lista lisReturn = new Lista();
        Object aux;
        int longLista1 = 1;
        int longLista2 = 1;
        while(longLista1 <= lis1.longitud()){
            aux = lis1.recuperar(longLista1);
            longLista1++;
            lisReturn.insertar(aux, lisReturn.longitud()+1);
        }
        while(longLista2 <= lis2.longitud()){
            aux = lis2.recuperar(longLista2);
            longLista2++;
            lisReturn.insertar(aux, lisReturn.longitud()+1);
        }
        return lisReturn;
    }

    public static Lista invertir(Lista lis){
        Lista lisReturn = new Lista();
        Pila aux = new Pila();
        int pos = 1;
        while(pos <= lis.longitud()){
            aux.apilar(lis.recuperar(pos));
            pos++;
        }
        while(!aux.esVacia()){
            lisReturn.insertar( aux.obtenerTope(), lisReturn.longitud()+1);
            aux.desapilar();
        }
        return lisReturn;
    }

    public static Lista intercalar(Lista lis1, Lista lis2){
        Cola aux = new Cola();
        Lista newLita = new Lista();
        int longMax = Math.max(lis1.longitud(), lis2.longitud()), pos=1;
        while(pos <= longMax){
            if(pos <= lis1.longitud()){
                aux.poner(lis1.recuperar(pos));
            }
            if(pos <= lis2.longitud()){
                aux.poner(lis2.recuperar(pos));
            }
            pos++;
        }
        while(!aux.esVacia()){
            newLita.insertar(aux.obtenerFrente(), newLita.longitud()+1);
            aux.sacar();
        }
        return newLita; 
    }

    // contar en forma iterativa
    public static int contarIterativo(Object elemento, Lista lis){
        int contador = 0, pos = 1;
        while(pos <= lis.longitud()){
            if(lis.recuperar(pos).equals(elemento)){
                contador++;
            }
            pos++;
        }
        return contador;
    }

    // contar en forma recursiva
    public static int contarRecursivo(Object elemento, Lista lis, int pos){
        int contador = 0;
        if(pos == lis.longitud()){
            if(lis.recuperar(pos).equals(elemento)){
                contador = 1;
            }
        }else{
            contador = contarRecursivo(elemento, lis,pos+1);
            if(lis.recuperar(pos).equals(elemento)){
                contador++;
            }
        }
        return contador;
    }

    public static boolean esCapicua(Lista lis){
        boolean resp = true;
        int ini = 1, fin= lis.longitud();
        while(ini < fin && resp){
            if(lis.recuperar(ini).equals(lis.recuperar(fin))){
                ini++;
                fin--;
            }else{
                resp = false;
            }
        }
        return resp;
    }

    public static void main(String[] args) {
        Lista lis1 = new Lista();
        lis1.insertar(2, 1);
        lis1.insertar(9, 2);
        lis1.insertar(5, 3);
        Lista lis2 = new Lista();
        lis1.insertar(3, 4);
        lis1.insertar(2, 5);
        lis2.insertar(2, 6);
        lis2.insertar(7, 7);
        //Lista lis = concatenar(lis1, lis2);
        //Lista lis = invertir(lis1);
        //Lista lis = intercalar(lis1, lis2);
        //int resp =  contarRecursivo(2, lis1, 1);
        //System.out.println(lis.toString());
        /* int resp =  contarIterativo(2, lis1); */
        boolean resp =  esCapicua(lis1);
        System.out.println(resp);
    }
}
