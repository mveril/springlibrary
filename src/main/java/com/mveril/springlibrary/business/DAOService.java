package com.mveril.springlibrary.business;

import java.util.Collection;
import java.util.Optional;

public interface DAOService<T> {
    public Collection<T> getAll();
    public Optional<T> getById(int id);
    public void add(T item);
    public boolean update(int id,T newValue);
    public boolean delete(int id);
    public boolean delete(T item);
}
