package org.getfin.controlador;

import org.getfin.modelos.Cliente;
import org.getfin.modelos.Producto;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class ProductoController {
    private static ProductoController instance;

    public ProductoController(){}

    public void guardarCliente(Producto usuario){
        IGenericService<Producto> clientes = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
        clientes.save(usuario);
    }
    public void eliminarCliente(Producto cliente){
        IGenericService<Producto> clientes = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
        clientes.delete(cliente);
    }
    public void editarCliente(Producto cliente){
        IGenericService<Producto> clientes = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
        clientes.update(cliente);
    }
    public List<Producto> getClientes() {
        IGenericService<Producto> clienteIGenericService= new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
        return clienteIGenericService.getAll();
    }

    public static ProductoController getInstance() {
        if (instance == null) {
            instance = new ProductoController();
        }
        return instance;
    }


}
