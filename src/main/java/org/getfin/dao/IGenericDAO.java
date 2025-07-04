package org.getfin.dao;

import java.util.List;
import java.util.Map;

public interface IGenericDAO <T> {

        public T get(Class<T> cl, Integer id);
        T get(Class<T> cl, Long id);
        public T save(T object);
        public void update(T object);
        public void delete(T object);
        public List<T> query(String hsql, Map<String, Object> params);

        //buscar por nombre
        T findOne(String hsql, Map<String , Object> params);
}
