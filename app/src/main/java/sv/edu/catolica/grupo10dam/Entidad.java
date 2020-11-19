package sv.edu.catolica.grupo10dam;

import java.io.Serializable;

public class Entidad implements Serializable {
    private int imagen;
    private String nombre;
    private String descripcion;
    private String precio;

    public Entidad(int imagen, String nombre, String descripcion, String precio){
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
        public int getImagen(){
            return imagen;
        }
        public String getNombre(){
            return nombre;
        }
        public String getDescripcion(){
            return descripcion;
        }
        public String getPrecio(){
            return precio;
        }
}
