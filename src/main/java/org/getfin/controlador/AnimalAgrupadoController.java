package org.getfin.controlador;

import org.getfin.modelos.AnimalAgrupado;
import org.getfin.modelos.Producto;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class AnimalAgrupadoController {

    private static AnimalAgrupadoController instance;

    public AnimalAgrupadoController(){}

    public void guardarCliente(AnimalAgrupado animalAgrupado){
        IGenericService<AnimalAgrupado> cliente = new GenericServiceImpl<>(AnimalAgrupado.class, HibernateUtil.getSessionFactory());
        cliente.save(animalAgrupado);
    }
    public void eliminarCliente(AnimalAgrupado animalAgrupado){
        IGenericService<AnimalAgrupado> cliente = new GenericServiceImpl<>(AnimalAgrupado.class, HibernateUtil.getSessionFactory());
        cliente.delete(animalAgrupado);
    }
    public void editarCliente(AnimalAgrupado animalAgrupado){
        IGenericService<AnimalAgrupado> cliente = new GenericServiceImpl<>(AnimalAgrupado.class, HibernateUtil.getSessionFactory());
        cliente.update(animalAgrupado);
    }
    public List<AnimalAgrupado> getClientes() {
        IGenericService<AnimalAgrupado> clienteIGenericService= new GenericServiceImpl<>(AnimalAgrupado.class, HibernateUtil.getSessionFactory());
        return clienteIGenericService.getAll();
    }

    public static AnimalAgrupadoController getInstance() {
        if (instance == null) {
            instance = new AnimalAgrupadoController();
        }
        return instance;
    }

}
