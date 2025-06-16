package org.getfin.controlador;

import org.getfin.modelos.Egreso;
import org.getfin.modelos.Herramienta;
import org.getfin.servicios.GenericServiceImpl;
import org.getfin.servicios.IGenericService;
import org.getfin.util.HibernateUtil;

import java.util.List;

public class HerramientaController {
    private static HerramientaController instance;

    public HerramientaController() {}

    public void guardarHerramienta(Herramienta herramienta){
        IGenericService<Herramienta> service = new GenericServiceImpl<>(Herramienta.class, HibernateUtil.getSessionFactory());
        service.save(herramienta);
    }

    public void eliminarHerramienta(Herramienta herramienta){
        IGenericService<Herramienta> service = new GenericServiceImpl<>(Herramienta.class, HibernateUtil.getSessionFactory());
        service.delete(herramienta);
    }

    public void editarHerramienta(Herramienta herramienta){
        IGenericService<Herramienta> service = new GenericServiceImpl<>(Herramienta.class, HibernateUtil.getSessionFactory());
        service.update(herramienta);
    }

    public List<Herramienta> getHerramienta(){
        IGenericService<Herramienta> service = new GenericServiceImpl<>(Herramienta.class, HibernateUtil.getSessionFactory());
        return service.getAll();
    }

    public static HerramientaController getInstance() {
        if (instance == null) {
            instance = new HerramientaController();
        }
        return instance;
    }

}
