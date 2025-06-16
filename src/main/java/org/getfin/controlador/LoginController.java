package org.getfin.controlador;

import org.getfin.modelos.Usuario;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class loginController {

    private static loginController instance;

    public loginController(){

    }
    public void guardarCliente(Usuario usuario){
        IGenericService<Usuario> clientes = new GenericServiceImpl<>(Usuario.class, HibernateUtil.getSessionFactory());
        clientes.save(usuario);
    }
    public void eliminarCliente(Usuario cliente){
        IGenericService<Usuario> clientes = new GenericServiceImpl<>(Usuario.class, HibernateUtil.getSessionFactory());
        clientes.delete(cliente);
    }
    public void editarCliente(Usuario cliente){
        IGenericService<Usuario> clientes = new GenericServiceImpl<>(Usuario.class, HibernateUtil.getSessionFactory());
        clientes.update(cliente);
    }
    public List<Usuario> getClientes() {
        IGenericService<Usuario> clienteIGenericService= new GenericServiceImpl<>(Usuario.class, HibernateUtil.getSessionFactory());
        return clienteIGenericService.getAll();
    }

    public static loginController getInstance() {
        if (instance == null) {
            instance = new loginController();
        }
        return instance;
    }


}