package br.edu.ite.financeiroandroid.dao;

import java.util.List;

public interface GenericDao<T> {

    T findById(Integer pk);
    void save(T entidade);
    void delete(Integer pk);
    List<T> findAll();
}
