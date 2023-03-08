package com.avia.repository;

import com.avia.domain.Passenger;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<K, T> {
    T findById(K id);

    Optional<T> findOne(K id);

    List<T> findAll();

    T create(T object);

    T update(T object);

    T deleteById(K id);

    List<T> findAllWoman();
    List<T> findFirstLetterSurname();
    List<T> findMinskRegionPass();
}