package com.avia.repository;

import com.avia.domain.PassDoc;

import java.util.List;

public interface PassDocRepository extends CRUDRepository<Long, PassDoc> {
    List<PassDoc> findMinskRegionPass();
}