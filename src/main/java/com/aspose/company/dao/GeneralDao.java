package com.aspose.company.dao;

import java.util.Collection;

/**
 * T - entity type
 * I - id type
 */
public interface GeneralDao<T,I> {

    T getById(I id);

    T create(T el);

    T delete(I id);

    Collection<T> getAll();

}
