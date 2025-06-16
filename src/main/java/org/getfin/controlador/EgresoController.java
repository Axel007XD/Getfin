package org.getfin.controlador;

import org.getfin.modelos.Egreso;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class EgresoController {

    private static EgresoController instance;

    public EgresoController() {}

    public void guardarEgreso(Egreso egreso){
        IGenericService<Egreso> service = new GenericServiceImpl<>(Egreso.class, HibernateUtil.getSessionFactory());
        service.save(egreso);
    }

    public void eliminarEgreso(Egreso egreso){
        IGenericService<Egreso> service = new GenericServiceImpl<>(Egreso.class, HibernateUtil.getSessionFactory());
        service.delete(egreso);
    }

    public void editarEgreso(Egreso egreso){
        IGenericService<Egreso> service = new GenericServiceImpl<>(Egreso.class, HibernateUtil.getSessionFactory());
        service.update(egreso);
    }

    public List<Egreso> getEgresos(){
        IGenericService<Egreso> service = new GenericServiceImpl<>(Egreso.class, HibernateUtil.getSessionFactory());
        return service.getAll();
    }

    public static EgresoController getInstance() {
        if (instance == null) {
            instance = new EgresoController();
        }
        return instance;
    }
}
