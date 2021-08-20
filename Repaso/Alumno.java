package Estructura.Repaso;

public class Alumno {
    
    // Atributos
    private int legajo;
    private String nombre;
    private String apellido;
    private String tipoDni; // tipo de dni
    private int dni; // numero de dni
    private String calle;
    private int numCalle; // altura de la calle
    private String ciudad;
    private String telefono;
    private String usuario;
    private String clave;

    // Construtor con 11 parametros
    public Alumno(int legajo, String nombre, String apellido, String tipoDni, int dni, int numCalle, String calle,
            String ciudad, String telefono, String usuario, String clave) {

        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDni = tipoDni;
        this.dni = dni;
        this.numCalle = numCalle;
        this.calle = calle;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.usuario = usuario;
        this.clave = clave;
    }

    /* Operaciones primitivas */

    // Modificadores
    public void setNombre(String nuevoNombre) {

        nombre = nuevoNombre;
    }

    public void setApellido(String nuevoApellido) {

        apellido = nuevoApellido;
    }

    public void setTipoDni(String nuevoTipoDni) {

        tipoDni = nuevoTipoDni;
    }

    public void setDni(int nuevoDni) {

        dni = nuevoDni;
    }

    public void setNumCalle(int nuevoNumCalle) {

        numCalle = nuevoNumCalle;
    }

    public void setCalle(String newCalle) {

        calle = newCalle;
    }

    public void setCiudad(String newCiudad) {

        ciudad = newCiudad;
    }

    public void setTelefono(String newTelefono) {

        telefono = newTelefono;
    }

    public void setUsuario(String newUsuario) {

        usuario = newUsuario;
    }

    private void setClave(String newClave) {

        clave = newClave;
    }

    // Observadores
    public int getLegajo() {

        return legajo;
    }

    public String getNombre() {

        return nombre;
    }

    public String getApellido() {

        return apellido;
    }

    public String getTipoDni() {

        return tipoDni;
    }

    public int getDni() {

        return dni;
    }

    public int getNumCalle() {

        return numCalle;
    }

    public String getCalle() {

        return calle;
    }

    public String getCiudad() {

        return ciudad;
    }

    public String getTelefono() {

        return telefono;
    }

    public String getUsuario() {

        return usuario;
    }

    public String toString() {

        return "Legajo: " + legajo + " Nombre: " + nombre + " Apellido: " + apellido + " Tipo de Dni: " + tipoDni
                + " DNI: " + dni + " Domiclio: " + numCalle + " " + calle + ", " + ciudad + " " + "Telefono: "
                + telefono + " Usuario: " + usuario;
    }
}

class prueba {

    public static void main(String[] args) {

        Alumno e = new Alumno(123, "franco", "recalde", "enrolamiento", 20818575, 376, "sarmiento", "cipolletti",
                "0280154816092", "fran98", "asdess");
        System.out.println(e.toString());
        e.setDni(123458255);
        System.out.println(e.toString());
    
    }
}
