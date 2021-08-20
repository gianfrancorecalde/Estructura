package Estructura.Testear.Lineales;

import Estructura.Lineales.Dinamicas.*;

public class Test {
    
    public static void main(String[] args) {
        
        Lista lista1 = new Lista();
        lista1.insertar(1, 1);
        lista1.insertar(2, 2);
        lista1.insertar(1, 3);
        lista1.insertar(3, 4);
        lista1.insertar(4, 5);
        //lista1.insertar(4, 4);
        //lista1.insertar(4, 5);
        //lista1.insertar(7, 6);
        //lista1.insertar(9, 7);
        //System.out.println(lista1.toString());
        lista1.eliminarAparicion(1);
        System.out.println(lista1.toString());

    }
}

