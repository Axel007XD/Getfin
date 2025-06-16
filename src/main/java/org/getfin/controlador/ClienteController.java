package org.getfin.controlador;

import org.getfin.modelos.Cliente;
import org.getfin.modelos.Usuario;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class ClienteController {
    private static ClienteController instance;
    public ClienteController(){}

    public void guardarCliente(Cliente usuario){
        IGenericService<Cliente> clientes = new GenericServiceImpl<>(Cliente.class, HibernateUtil.getSessionFactory());
        clientes.save(usuario);
    }
    public void eliminarCliente(Cliente cliente){
        IGenericService<Cliente> clientes = new GenericServiceImpl<>(Cliente.class, HibernateUtil.getSessionFactory());
        clientes.delete(cliente);
    }
    public void editarCliente(Cliente cliente){
        IGenericService<Cliente> clientes = new GenericServiceImpl<>(Cliente.class, HibernateUtil.getSessionFactory());
        clientes.update(cliente);
    }
    public List<Cliente> getClientes() {
        IGenericService<Cliente> clienteIGenericService= new GenericServiceImpl<>(Cliente.class, HibernateUtil.getSessionFactory());
        return clienteIGenericService.getAll();
    }

    public static ClienteController getInstance() {
        if (instance == null) {
            instance = new ClienteController();
        }
        return instance;
    }

}
