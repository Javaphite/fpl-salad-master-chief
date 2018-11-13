package ua.training.fpl.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface GenericDao<T> {

    Logger LOG = LoggerFactory.getLogger(GenericDao.class);

    int create(T entity);

    T read(int id);
}
