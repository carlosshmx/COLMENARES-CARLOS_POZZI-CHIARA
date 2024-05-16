package com.backend.repository;

public interface IDao <T>{
    T registrar(T var1);

    T buscarPorId(Long var1);
}
