package org.example.Repositories.Interfaces;

import java.util.List;

public interface ICRUD<T> {
    public void add(T t);
    public void update(T t);
    public void delete(String id);
    public void get(String id);
    public List<T> getAll();
}  


