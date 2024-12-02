package tienda_zapatos;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

public class Menu {

    public Inventario inventario;
    public Menu() {
        inventario = new Inventario();
    }

    public void menuActualizar(Scanner entrada,int id){
        int OpMod;
        String nombre;
        String categoria;
        double precio;
        int cantidad;

        Producto zapato = inventario.getProducto(id);
        do {
            try {
                System.out.println("Ingrese que desea modificar:");
                System.out.println("1. nombre");
                System.out.println("2. categoria");
                System.out.println("3. precio");
                System.out.println("4. cantidad");
                System.out.println("5. todo");
                System.out.println("6. salir");
                OpMod = entrada.nextInt();
                entrada.nextLine();

                switch (OpMod) {
                    case 1:
                        System.out.println("Nombre:");
                        nombre = entrada.nextLine();

                        inventario.update(id, nombre, zapato.getCategoria(), zapato.getPrecio(), zapato.getCantidadDisponible());
                        break;
                    case 2:
                        System.out.println("categoria:");
                        categoria = entrada.nextLine();

                        inventario.update(id, zapato.getNombreProducto(), categoria, zapato.getPrecio(), zapato.getCantidadDisponible());

                        break;
                    case 3:

                        System.out.println("precio:");
                        precio = entrada.nextDouble();

                        inventario.update(id, zapato.getNombreProducto(), zapato.getCategoria(), precio, zapato.getCantidadDisponible());

                        break;
                    case 4:
                        System.out.println("cantidad:");
                        cantidad = entrada.nextInt();

                        inventario.update(id, zapato.getNombreProducto(), zapato.getCategoria(), zapato.getPrecio(), cantidad);

                        break;
                    case 5:
                        while (true) {
                            try {
                                System.out.println("Nombre:");
                                nombre = entrada.nextLine();
                                System.out.println("Categoria:");
                                categoria = entrada.nextLine();
                                System.out.println("Precio:");
                                precio = entrada.nextDouble();
                                System.out.println("cantidad:");
                                cantidad = entrada.nextInt();
                                inventario.update(id, nombre, categoria, precio, cantidad);
                                break;

                            } catch (Exception e) {
                                entrada.nextLine();
                                System.out.println(e);
                            }

                        }
                    break;
                    case 6:
                        return;
                    default:
                        System.out.println("Ingresastes una opciòn no valida");
                }

                break;

            } catch(Exception e){
                entrada.nextLine();
                System.out.println("Ingrese un valor incorrecto - error:" + e);
            }
        } while (true) ;
    }



    public void ver() {

        Scanner entrada = new Scanner(System.in);

        System.out.println("\nVAS A INGRESAR AL INVENTARIO DE PRODUCTOS DE LA TIENDA DE ZAPATOS DEPORTIVOS");


        int id;
        int opcion ;
        String categoria;

        System.out.println("\n                  QUE QUIERES HACER ");


        do {
            try{

            inventario.llenarLista();
            //  System.out.println(inventario.listaProductos);
            System.out.println("------------------------------------------------------");
            System.out.println("-               1.Agregar producto                   -");
            System.out.println("-               2.Actualizar producto                -");
            System.out.println("-               3.Eliminar producto                  -");
            System.out.println("-               4.Buscar por categoría               -");
            System.out.println("-               5.Generar reporte                    -");
            System.out.println("-               6.Cantidad de productos por categoría-");
            System.out.println("-               7.Producto más caro                   ");
            System.out.println("-               8.SALIR                              -");
            System.out.println("------------------------------------------------------");

            opcion = entrada.nextInt();

            switch (opcion) {
                case 1: //AGREGAR
                    do {
                        try {

                            System.out.println("ID:");
                            id = entrada.nextInt();
                            if(inventario.exist(id)){
                                System.out.println("ya existe un producto con este id");
                                break;
                            }
                            entrada.nextLine();
                            System.out.println("Nombre:");
                            String nombre = entrada.nextLine();
                            System.out.println("Categoria:");
                            categoria = entrada.nextLine();
                            System.out.println("Precio:");
                            double precio = entrada.nextDouble();
                            System.out.println("cantidad:");
                            int cantidad = entrada.nextInt();

                            inventario.agregarProducto(id, nombre, categoria, precio, cantidad);

                            break;
                        } catch (Exception e) {
                            entrada.nextLine();
                            System.out.println(e);
                        }
                    } while (true);
                    break;

                case 2: //ACTUALIZAR
                    do {
                        try {
                            System.out.println("ID:");
                            id = entrada.nextInt();

                            break;

                    } catch (Exception e) {
                        entrada.nextLine();
                        System.out.println("Ingrese un nùmero entero - error:"+e);
                    }
                    } while (true);


                    if(inventario.exist(id)){

                        menuActualizar(entrada,id);

                    }else{ System.out.println("--No se encontro el producto --");}


                    break;

                case 3: //ELIMINAR

                    do {
                        try {
                            System.out.println("ID:");
                            id = entrada.nextInt();
                            inventario.Delete(id);
                            break;
                        }catch (Exception e) {
                            entrada.nextLine();
                            System.out.println(e);
                        }
                    } while (true);
                    break;

                case 4: //BUSCAR POR CATEGORIA
                    do {
                        try {
                            System.out.println("---- Categorias ----");
                            ArrayList<String> lista = inventario.getCategorias();
                            for (String categori: lista
                            ) {
                                System.out.println(categori);
                            }
                            System.out.println("");
                            System.out.println("Ingrese la Categoria:");
                            categoria = entrada.next();

                            ArrayList<Producto> listproductos = inventario.buscarCategoria(categoria.toUpperCase());
                            if(listproductos == null){
                                System.out.println("No existe la categoria");
                            }else{
                                System.out.println("----- Productos -----");
                                System.out.println("id |  Producto  | Categoria  |  Cantidad  |  Total   ");
                                for (Producto product: listproductos
                                     ) {
                                   // System.out.println(product.toString());
                                    System.out.println(product.getIdProducto()+ "," +product.getNombreProducto() + "," + product.getCategoria() + ","+Integer.toString(product.getCantidadDisponible())+ ","+Double.toString(product.getPrecio()));

                                }
                            }
                            break;
                        }catch (Exception e) {
                            entrada.nextLine();
                            System.out.println(e);
                        }
                    } while (true);
                    break;
                case 5:
                    boolean resultado = inventario.reporteProductos();
                    if(resultado){
                        System.out.println("Se ha generadp el Reporte Correctamente!!!");
                    }else{
                        System.out.println("No se puede realizar un Reporte sin productos");
                    }
                    break;


                case 6: //CANTIDAD POR CATEGORIA
                    inventario.cantidadCategoria();
                    break;

                case 7: //PRODUCTO MAS COSTOSO
                    inventario.masCostoso();
                    break;

                case 8:
                    System.out.println("-------------------------------------------------");
                    System.out.println("-                Salir                          -");
                    System.out.println("-------------------------------------------------");
                    break;

                default:
                    System.out.println("-------------------------------------------------");
                    System.out.println("-          OPCIÓN NO VALIDA...                  -");
                    System.out.println("-------------------------------------------------");

            }
            }catch (Exception e) {
                entrada.nextLine();
                opcion = 0;
                System.out.println("Ingresa un entero -- error: "+e);
            }

        } while (opcion != 8);
        System.out.println("Hasta Luego");


    }

}
