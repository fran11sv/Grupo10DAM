package sv.edu.catolica.grupo10dam;

import java.io.Serializable;

public class EntidadMenus implements Serializable {
    private int id_menu;
    private String menu;
    private String descripcion;
    private int estado;

    public EntidadMenus() {
    }

    public EntidadMenus(int id_menu, String menu, String descripcion, int estado) {
        this.id_menu = id_menu;
        this.menu = menu;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return menu +" "+ descripcion;
    }
}
