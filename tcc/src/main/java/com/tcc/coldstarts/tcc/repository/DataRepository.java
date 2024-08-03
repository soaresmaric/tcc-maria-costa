package com.tcc.coldstarts.tcc.repository;

import com.tcc.coldstarts.tcc.model.DataEntity;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<DataEntity, Long> {}
