package org.getfin.controlador;

import org.getfin.modelos.Producto;
import org.getfin.modelos.Raza;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class RazaController {

    private static RazaController instance;

    public RazaController(){}

    public void guardarCliente(Raza raza){
        IGenericService<Raza> razaGenericService = new GenericServiceImpl<>(Raza.class, HibernateUtil.getSessionFactory());
        razaGenericService.save(raza);
    }
    public void eliminarCliente(Raza raza){
        IGenericService<Raza> razaGenericService = new GenericServiceImpl<>(Raza.class, HibernateUtil.getSessionFactory());
        razaGenericService.delete(raza);
    }
    public void editarCliente(Raza raza){
        IGenericService<Raza> razaGenericService = new GenericServiceImpl<>(Raza.class, HibernateUtil.getSessionFactory());
        razaGenericService.update(raza);
    }
    public List<Raza> getClientes() {
        IGenericService<Raza> clienteIGenericService= new GenericServiceImpl<>(Raza.class, HibernateUtil.getSessionFactory());
        return clienteIGenericService.getAll();
    }

    public static RazaController getInstance() {
        if (instance == null) {
            instance = new RazaController();
        }
        return instance;
    }

}
