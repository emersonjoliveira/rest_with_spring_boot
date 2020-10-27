package br.com.emerson.services;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceBase<T> {

    @Autowired
    T repository;

    public T getRepository() {
        return repository;
    }
}
