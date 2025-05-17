package jr.tokioschool.motioeventandkeyevent;

import androidx.annotation.NonNull;

public class Personas {
    private final String nombre;
    private final String edad;
    private final String ciudad;

    public Personas(String nombre, String edad, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;}

    public String getEdad() {
        return edad;
    }

    public String getCiudad() {
        return ciudad;
    }
    @NonNull
    @Override
    public String toString() {return nombre;}
}





















