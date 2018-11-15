package ua.training.fpl.model.dao;

/**
 * Generic interface for all dao
 * @param <T> associated entity type
 */
public interface GenericDao<T> {

    int create(T entity);

    T find(int id);
}
