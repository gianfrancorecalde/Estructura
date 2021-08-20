package Estructura.Testear.Lineales;
import Estructura.Lineales.Dinamicas.*;
public class testParcial {
    

        public static Lista crearLista2(Pila c1){
            Cola copiaCola1;
            Lista lis;
            Pila aux=new Pila();
            Lista copia=null;
            lis=new Lista();
            int contador=1;
            int pos=1;
            
            while(!c1.esVacia()){
                copia.insertar(c1.obtenerTope(), 1);
            }
            
            
            
            while(!copia.esVacia()){
                //System.out.println("ENTRA WHILE 1");
                if(contador%2!=0){
                    while(!copia.esVacia()&&(char)copia.recuperar(pos)!='$'){
                        //System.out.println("ENTRA WHILE 2");
                        //System.out.println(copiaCola1.obtenerFrente().toString());
                        aux.apilar(copia.recuperar(pos));
                    
                        lis.insertar(copia.recuperar(pos),lis.longitud()+1);
                    
                        copia.eliminar(pos);
                        pos++;
                    }
                    while(!aux.esVacia()){
                        //System.out.println("ENTRA WHILE 3");
                        //System.out.println("SACA DE LA PILA "+aux.obtenerTope().toString());
                        lis.insertar(aux.obtenerTope(), lis.longitud()+1);
                        aux.desapilar();
                    
                    }
                    //System.out.println(lis.toString());
                    //System.out.println(copiaCola1.obtenerFrente().toString());
                    if(!copia.esVacia()){
                        //System.out.println("ENTRA AL IF FINAL");
                        lis.insertar(copia.recuperar(pos),lis.longitud()+1);
                        copia.eliminar(pos);
                        contador++;
                        pos++;
                    }
                }
                else{
                    while(!copia.esVacia()&&(char)copia.recuperar(pos)!='$'){
                        lis.insertar(copia.recuperar(pos), lis.longitud()+1);
                        copia.eliminar(pos);
                    }
                    lis.insertar(copia.recuperar(pos), lis.longitud()+1);
                    copia.eliminar(pos);
                    contador++;
                    pos++;
                    
                }
            }
            
            return lis;
        }
}
