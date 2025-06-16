package org.getfin.controlador;

import org.getfin.modelos.DetalleEgreso;
import org.getfin.modelos.DetalleIngreso;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class DetalleIngresoController {


    private static DetalleIngresoController instance;

    public DetalleIngresoController() {}

    public void guardarDetalleIngresoController(DetalleIngreso detalle){
        IGenericService<DetalleIngreso> service = new GenericServiceImpl<>(DetalleIngreso.class, HibernateUtil.getSessionFactory());
        service.save(detalle);
    }

    public void eliminarDetalleIngresoController(DetalleIngreso detalle){
        IGenericService<DetalleIngreso> service = new GenericServiceImpl<>(DetalleIngreso.class, HibernateUtil.getSessionFactory());
        service.delete(detalle);
    }

    public void editarDetalleIngresoController(DetalleIngreso detalle){
        IGenericService<DetalleIngreso> service = new GenericServiceImpl<>(DetalleIngreso.class, HibernateUtil.getSessionFactory());
        service.update(detalle);
    }

    public List<DetalleIngreso> getDetallesIngresoController(){
        IGenericService<DetalleIngreso> service = new GenericServiceImpl<>(DetalleIngreso.class, HibernateUtil.getSessionFactory());
        return service.getAll();
    }

    public static DetalleIngresoController getInstance() {
        if (instance == null) {
            instance = new DetalleIngresoController();
        }
        return instance;
    }

}
