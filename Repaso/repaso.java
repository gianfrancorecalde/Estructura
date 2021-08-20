package Estructura.Repaso;

class Fecha {
    
    // Atributos
    private int dia;
    private int mes; // meses identificados con numeros
    private int año;

    // Constructor de clase Fecha
    public Fecha(int dia, int mes, int año) {

        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    // Observadores
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAño() {
        return año;
    }

    // Modificadores
    public void setDia(int unDia) {
        dia = unDia;
    }

    public void setMes(int unMes) {
        mes = unMes;
    }

    public void setAño(int unAño) {
        año = unAño;
    }

    // Fechas iguales
    public boolean equalsFecha(Fecha unaFecha) {
        return dia == unaFecha.getDia() && mes == unaFecha.getMes() && año == unaFecha.getAño();
    }
}

class main{

    public static void main(String[] args) {
        String cadena = "LosValientes";
        System.out.println(Math.abs(cadena.hashCode()));
        
        /* Fecha f = new Fecha(23, 4, 1998);
        Fecha f1 = new Fecha(23, 4, 1998);
        if (f.equalsFecha(f1)) {
            System.out.println("fechas iguales");
        } else {
            System.out.println("Fechas diferentes");
        } */
    }
}
    
class FechaString{

    // Atributo
    private String fecha;

    // Constructor
    public FechaString(String fecha) {
        this.fecha = fecha;
    }

    // Observadores
}

class Factorial{

    public static int fact(int n) {
        int resultado;
        if (n > 1) {
            resultado = fact(n - 1) * n;
        } else {
            resultado = 1;
        }
        return resultado;
    }

    
}