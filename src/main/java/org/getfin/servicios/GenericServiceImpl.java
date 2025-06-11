package org.getfin.servicios;

import org.getfin.dao.GenericDAOImpl;
import org.getfin.dao.IGenericDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class GenericServiceImpl<T> implements IGenericService<T> {

    private IGenericDAO<T> dao;
    private Class<T> cl;
    SessionFactory session;

    public GenericServiceImpl(Class<T> cl, SessionFactory session) {
        this.cl = cl;
        this.session = session;
        dao = new GenericDAOImpl<>(cl,session);
    }

    @Override
    public List<T> getAll() {
        return dao.query("FROM"+cl.getSimpleName(),null)==null?null:dao.query("FROM"+cl.getSimpleName(),null);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public T getById(int id) {
        return dao.get(cl,id);
    }

    @Override
    public T getId(Long id) {
        return dao.get(cl,id);
    }

    @Override
    public T getByName(String name) {
        return null;
    }

    @Override
    public T get(Class<T> cl, Integer id) {
        return (T) dao.get(cl,id);
    }

    @Override
    public T get(Class<T> cl, Long id) {
        return null;
    }

    @Override
    public T save(T object) {
        return (T) dao.save(object);
    }

    @Override
    public void update(T object) {
        dao.update(object);

    }

    @Override
    public void delete(T object) {
        dao.delete(object);

    }

    @Override
    public List<T> query(String hsql, Map<String, Object> params) {
        return (List<T>) dao.query(hsql,params);
    }
}
