package Estructura.Testear.Jerarquicos;
import Estructura.jerarquicas.ArbolConjuntista.TablaHash.*;
public class testearHash {
    
    public static void main(String[] args) {
        HashAbierto t1 = new HashAbierto();
        t1.insertar(91);
        t1.insertar(119);
        t1.insertar(147);
        t1.insertar(43);
        t1.insertar(148);
        t1.insertar(109);
        t1.insertar(137);
        t1.insertar(72);
        t1.insertar(85);
        t1.insertar(101);
        t1.insertar(38);
        t1.insertar(141);
        System.out.println(t1.toString());
        t1.eliminar(137);
        System.out.println(t1.toString());
        t1.vaciar();
        System.out.println(t1.toString());
        
    }
}
