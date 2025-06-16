package org.getfin.controlador;

import org.getfin.modelos.DetalleEgreso;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class DetalleEgresoController {

    private static DetalleEgresoController instance;

    public DetalleEgresoController() {}

    public void guardarDetalle(DetalleEgreso detalle){
        IGenericService<DetalleEgreso> service = new GenericServiceImpl<>(DetalleEgreso.class, HibernateUtil.getSessionFactory());
        service.save(detalle);
    }

    public void eliminarDetalle(DetalleEgreso detalle){
        IGenericService<DetalleEgreso> service = new GenericServiceImpl<>(DetalleEgreso.class, HibernateUtil.getSessionFactory());
        service.delete(detalle);
    }

    public void editarDetalle(DetalleEgreso detalle){
        IGenericService<DetalleEgreso> service = new GenericServiceImpl<>(DetalleEgreso.class, HibernateUtil.getSessionFactory());
        service.update(detalle);
    }

    public List<DetalleEgreso> getDetalles(){
        IGenericService<DetalleEgreso> service = new GenericServiceImpl<>(DetalleEgreso.class, HibernateUtil.getSessionFactory());
        return service.getAll();
    }

    public static DetalleEgresoController getInstance() {
        if (instance == null) {
            instance = new DetalleEgresoController();
        }
        return instance;
    }
}
