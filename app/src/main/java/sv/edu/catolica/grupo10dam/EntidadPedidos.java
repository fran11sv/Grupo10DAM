package sv.edu.catolica.grupo10dam;

import java.io.Serializable;

public class EntidadPedidos implements Serializable {
    private int id_pedido;
    private int id_usuario;
    private int id_plato;
    private int cantidad;
    private String fecha_pedido;
    private Double total;
    private String com_pedido;
    private int estado;

    public EntidadPedidos(int id_pedido, int id_usuario, int id_plato, int cantidad, String fecha_pedido, Double total, String com_pedido, int estado) {
        this.id_pedido = id_pedido;
        this.id_usuario = id_usuario;
        this.id_plato = id_plato;
        this.cantidad = cantidad;
        this.fecha_pedido = fecha_pedido;
        this.total = total;
        this.com_pedido = com_pedido;
        this.estado = estado;
    }

    public EntidadPedidos() {
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_plato() {
        return id_plato;
    }

    public void setId_plato(int id_plato) {
        this.id_plato = id_plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCom_pedido() {
        return com_pedido;
    }

    public void setCom_pedido(String com_pedido) {
        this.com_pedido = com_pedido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
