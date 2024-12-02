package tienda_zapatos;

import java.awt.*;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.sql.Array;
import java.util.*;
import java.lang.RuntimeException;
import java.util.List;

public class Inventario {

    public Inventario() {
        listaProductos = new ArrayList<Producto>();
    }

    private String ruta = "D://fundamentos-programacion//repositorio-github//ExamenFinal//";
    File file = new File(ruta + "Productos.txt");
    File reporte = new File(ruta + "reporte_inventario.txt");
    public ArrayList<Producto> listaProductos;
    public Producto productos;


    /*
    QUEDAN ESTOS METODOS A CONSIDERACION

    public  Producto buscarNombre(String nombre){
         for (Producto product : listaProductos
         ) {
             if (product.getNombreProducto() == nombre) {
                 return  product;
             }
         }
         return null;
     }
     public  Producto buscarID(int id){
         for (Producto product : listaProductos
         ) {
             if (product.getIdProducto() == id) {
                 return  product;
             }
         }
         return null;
     }
 */
    public boolean llenarLista() {

        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            listaProductos.clear();


            String linea;

            while ((linea = br.readLine()) != null) {
                productos = new Producto();
                String[] list = linea.split(",");

                productos.setIdProducto(Integer.parseInt(list[0]));
                productos.setNombreProducto(list[1]);
                productos.setCategoria(list[2]);
                productos.setPrecio(Double.parseDouble(list[3]));
                productos.setCantidadDisponible(Integer.parseInt(list[4]));
                listaProductos.add(productos);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean guardarDatos() {

        try (FileWriter fw = new FileWriter(file, true)) {
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(Integer.toString(productos.getIdProducto()) + ",");
            bw.write(productos.getNombreProducto() + ",");
            bw.write(productos.getCategoria() + ",");
            bw.write(Double.toString(productos.getPrecio()) + ",");
            bw.write(Integer.toString(productos.getCantidadDisponible()));
            bw.newLine();
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean actualizardatos() {

        try (FileWriter fw = new FileWriter(file, false)) {
            BufferedWriter bw = new BufferedWriter(fw);

            for (Producto product : listaProductos
            ) {
                bw.write(Integer.toString(product.getIdProducto()) + ",");
                bw.write(product.getNombreProducto() + ",");
                bw.write(product.getCategoria() + ",");
                bw.write(Double.toString(product.getPrecio()) + ",");
                bw.write(Integer.toString(product.getCantidadDisponible()));
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean exist(int id) {
        for (Producto product : listaProductos) {
            if (product.getIdProducto() == id) {
                return true;
            }
        }
        return false;
    }

    public void agregarProducto(int Id, String nombre, String categoria, double precio, int cantidad) {
        try {

            productos = new Producto();
            productos.setIdProducto(Id);
            productos.setNombreProducto(nombre);
            productos.setCategoria(categoria);
            productos.setPrecio(precio);
            productos.setCantidadDisponible(cantidad);

            listaProductos.add(productos);
            guardarDatos();
            System.out.println("Se guardo el producto Correctamente!!!!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Producto getProducto(int id) {
        for (Producto product : listaProductos) {
            if (product.getIdProducto() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean update(int Id, String nombre, String categoria, double precio, int cantidad) {
        if (exist(Id)) {
            for (Producto product : listaProductos) {
                if (product.getIdProducto() == Id) {
                    product.setNombreProducto(nombre);
                    product.setCategoria(categoria);
                    product.setPrecio(precio);
                    product.setCantidadDisponible(cantidad);
                    break;
                }
            }
            actualizardatos();
            System.out.println("Se actualizo el producto Correctamente!!!!");
        } else {
            System.out.println("--No se encontro el producto --");
        }

        return true;
    }


    public boolean Delete(int id) {
        int index = 0;

        if (exist(id)) {

            int contador = 0;

            for (Producto product : listaProductos) {
                if (product.getIdProducto() == id) {
                    index = contador;
                    break;
                }
                contador++;
            }
            System.out.println(index);
            listaProductos.remove(index);
            actualizardatos();
            System.out.println("Se borró el producto Correctamente!!!!");
            return true;
        } else {
            System.out.println("--No se encontro el producto --");
        }

        return false;
    }

    public ArrayList<String> getCategorias() {
        ArrayList<String> lista = new ArrayList<String>();
        for (Producto product : listaProductos
        ) {
            if (!lista.contains(product.getCategoria())) {
                lista.add(product.getCategoria());
            }
        }

        return lista;
    }

    public ArrayList<Producto> buscarCategoria(String categoria) {
        ArrayList<String> listaCategorias = getCategorias();
        if (!listaCategorias.contains(categoria)) {
            return null;
        }
        ArrayList<Producto> ListaP = new ArrayList<>();
        for (Producto product : listaProductos
        ) {
            if (product.getCategoria().equals(categoria)) {
                ListaP.add(product);
            }
        }
        return ListaP;
    }

    public boolean reporteProductos() {
        if (listaProductos.isEmpty()) return false;

        ArrayList<String> categorias = getCategorias();
        double valorTotal = 0;
        ArrayList<Producto> listaP = new ArrayList<Producto>();

        for (String categoria : categorias
        ) {
            for (Producto product : listaProductos
            ) {
                if (product.getCategoria().equals(categoria)) {
                    valorTotal += product.getCantidadDisponible() * product.getPrecio();
                    listaP.add(product);
                }
            }
        }

        try (FileWriter fw = new FileWriter(reporte, false)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("INVENTARIO TIENDA DE ZAPATOS");
            bw.newLine();
            bw.write("Producto  | Categoria  |  Cantidad  |  Total   ");
            bw.newLine();
            for (Producto zapato : listaP) {
            //    bw.write(zapato.toString());
             //   bw.newLine();

              //  bw.write(Integer.toString(za.getIdProducto()) + ",");
                bw.write(zapato.getNombreProducto() + ",");
                bw.write(zapato.getCategoria() + ",");
                bw.write(Integer.toString(zapato.getCantidadDisponible())+ ",");
                bw.write(Double.toString(zapato.getPrecio()) + ",");

                bw.newLine();



            }
            bw.write("valor Total= " + Double.toString(valorTotal));
            bw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void cantidadCategoria() {
        int cantidad = 0;
        boolean existe;
        class CantCategorias {
            String categoria;
            int cantidad;
        }
        ArrayList<CantCategorias> lista = new ArrayList<>();

        for (Producto product : listaProductos
        ) {
            existe = false;
            for (CantCategorias listcant : lista
            ) {
                if (listcant.categoria.equals(product.getCategoria())) {
                    existe = true;
                    listcant.cantidad += product.getCantidadDisponible();
                    break;
                }
            }
            if (!existe) {
                CantCategorias ca = new CantCategorias();
                ca.cantidad = product.getCantidadDisponible();
                ca.categoria = product.getCategoria();
                lista.add(ca);
            }
        }

        if (lista.isEmpty()) {
            System.out.println("No hay ninguna categoria");
            return;
        }
        System.out.println("CATEGORIA : CANTIDAD -----");
        for (CantCategorias cant : lista
        ) {
            System.out.println(cant.categoria + ": " + cant.cantidad);
        }
    }


    public void masCostoso() {
        Producto producto_mayor = new Producto();
        producto_mayor.setPrecio(0);

        for (Producto product : listaProductos
        ) {
            if (product.getPrecio() > producto_mayor.getPrecio()) {
                producto_mayor = product;
            }
        }
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos");
            return;
        }
        System.out.println("El producto mas consotos es:");
        System.out.println(producto_mayor.toString());
    }


}
