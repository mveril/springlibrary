package com.mveril.springlibrary.business;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookDaoImpl implements DAOService<Book> {

    private Map<Integer,Book> bookMap = new HashMap<Integer,Book>();
    private int index = 0;
    @Override
    public Collection<Book> getAll() {
        return bookMap.values();
    }

    @Override
    public Optional<Book> getById(int id) {
       return Optional.ofNullable(bookMap.getOrDefault(id,null));
    }

    @Override
    public void add(Book item) {
        index++;
        item.setId(index);
        bookMap.put(index,item);
    }

    @Override
    public boolean update(int id, Book newValue) {
        var result = bookMap.containsKey(id);
        if(result){
            bookMap.put(id,newValue);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        return bookMap.remove(id) != null;
    }

    @Override
    public boolean delete(Book item) {
        return delete(item.getId());
    }
}
