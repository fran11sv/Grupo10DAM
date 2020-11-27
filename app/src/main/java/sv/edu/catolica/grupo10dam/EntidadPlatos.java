package sv.edu.catolica.grupo10dam;

import java.io.Serializable;

public class EntidadPlatos implements Serializable {
    private int id_plato;
    private int id_menu;
    private int id_categoria;
    private String plato;
    private double precio;
    private String descripcion;
    private String img_plato;
    private int estado;

    public EntidadPlatos(int id_plato, int id_menu, int id_categoria, String plato, double precio, String descripcion, String img_plato, int estado) {
        this.id_plato = id_plato;
        this.id_menu = id_menu;
        this.id_categoria = id_categoria;
        this.plato = plato;
        this.precio = precio;
        this.descripcion = descripcion;
        this.img_plato = img_plato;
        this.estado = estado;
    }

    public EntidadPlatos() {
    }

    public int getId_plato() {
        return id_plato;
    }

    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg_plato() {
        return img_plato;
    }

    public void setImg_plato(String img_plato) {
        this.img_plato = img_plato;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
