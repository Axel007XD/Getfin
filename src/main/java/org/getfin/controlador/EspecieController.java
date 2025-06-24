package org.getfin.controlador;

import org.getfin.modelos.Especies;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class EspecieController {

    private static EspecieController instance;

    public EspecieController(){}

    public void guardarCliente(Especies especies){
        IGenericService<Especies> razaGenericService = new GenericServiceImpl<>(Especies.class, HibernateUtil.getSessionFactory());
        razaGenericService.save(especies);
    }
    public void eliminarCliente(Especies especies){
        IGenericService<Especies> razaGenericService = new GenericServiceImpl<>(Especies.class, HibernateUtil.getSessionFactory());
        razaGenericService.delete(especies);
    }
    public void editarCliente(Especies especies){
        IGenericService<Especies> razaGenericService = new GenericServiceImpl<>(Especies.class, HibernateUtil.getSessionFactory());
        razaGenericService.update(especies);
    }
    public List<Especies> getClientes() {
        IGenericService<Especies> clienteIGenericService= new GenericServiceImpl<>(Especies.class, HibernateUtil.getSessionFactory());
        return clienteIGenericService.getAll();
    }

    public static EspecieController getInstance() {
        if (instance == null) {
            instance = new EspecieController();
        }
        return instance;
    }

}
