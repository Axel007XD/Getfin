package org.getfin.controlador;

import org.getfin.modelos.Ingreso;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class IngresoController {

    private static IngresoController instance;

    public IngresoController() {}

    public void guardarIngreso(Ingreso ingreso){
        IGenericService<Ingreso> service = new GenericServiceImpl<>(Ingreso.class, HibernateUtil.getSessionFactory());
        service.save(ingreso);
    }

    public void eliminarIngreso(Ingreso ingreso){
        IGenericService<Ingreso> service = new GenericServiceImpl<>(Ingreso.class, HibernateUtil.getSessionFactory());
        service.delete(ingreso);
    }

    public void editarIngreso(Ingreso ingreso){
        IGenericService<Ingreso> service = new GenericServiceImpl<>(Ingreso.class, HibernateUtil.getSessionFactory());
        service.update(ingreso);
    }

    public List<Ingreso> getIngresos(){
        IGenericService<Ingreso> service = new GenericServiceImpl<>(Ingreso.class, HibernateUtil.getSessionFactory());
        return service.getAll();
    }

    public static IngresoController getInstance() {
        if (instance == null) {
            instance = new IngresoController();
        }
        return instance;
    }
}
