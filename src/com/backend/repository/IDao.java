package com.backend.repository;

import java.util.List;

public interface IDao <T>{
    T registrar(T var1);
    T buscarPorId(Long var1);
    List<T> listarTodos();
}
