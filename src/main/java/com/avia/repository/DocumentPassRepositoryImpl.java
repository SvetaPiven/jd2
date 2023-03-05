package com.avia.repository;

import com.avia.domain.DocumentPass;
import com.avia.domain.Passenger;

import java.util.List;
import java.util.Optional;

public class DocumentPassRepositoryImpl implements DocumentPassRepository {

    @Override
    public Optional<Passenger> findOne(Long id) {
        return null;
    }

    @Override
    public List<DocumentPass> findAll() {
        return null;
    }

    @Override
    public DocumentPass create(DocumentPass object) {
        return null;
    }

    @Override
    public DocumentPass update(DocumentPass object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<DocumentPass> findAllWoman() {
        return null;
    }

    @Override
    public List<DocumentPass> findFirstLetterSurname() {
        return null;
    }
}