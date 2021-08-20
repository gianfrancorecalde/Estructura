package Estructura.Testear.Lineales;

import Estructura.Lineales.Dinamicas.*;

public class TestCola {
    
    public static void main(String[] args) {
        
        Cola c1 = new Cola();
        c1.poner(1);
        c1.poner(2);
        c1.poner(3);
        c1.poner(4);
        c1.poner(5);
        System.out.println(c1.toString());
    }
}
