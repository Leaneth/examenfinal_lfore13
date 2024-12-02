package tienda_zapatos;


public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String categoria;
    private double precio;
    private int cantidadDisponible;


    public Producto() {
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto.toUpperCase();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria.toUpperCase();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return "id  | Producto  | Categoria  | Precio  | Cantidad\n" + idProducto + "  |" + nombreProducto + "   |" + categoria + "        | $" + precio + "    |" + cantidadDisponible;
    }
    /*public String toString() {
        return  "id" + idProducto +
                "| nombreProducto='" + nombreProducto + '\'' +
                "| categoria='" + categoria + '\'' +
                "| precio=" + precio +
                "| cantidadDisponible=" + cantidadDisponible ;
    }*/


}


