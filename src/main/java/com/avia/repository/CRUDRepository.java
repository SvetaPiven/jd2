package com.avia.repository;

import com.avia.domain.Passenger;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<K, T> {

    Optional<Passenger> findOne(K id);

    List<T> findAll();

    T create(T object);

    boolean update(T object);

    boolean deleteById(K id);

    List<T> findAllWoman();
    List<T> findFirstLetterSurname();
}