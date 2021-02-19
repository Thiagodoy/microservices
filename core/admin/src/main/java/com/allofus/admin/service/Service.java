package com.allofus.admin.service;

import com.allofus.admin.model.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;


import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
@org.springframework.stereotype.Service
public class Service <T extends Model, R extends JpaRepository<T, Long> & JpaSpecificationExecutor<T>>{


    protected R repository;

    public Service(R repositoy) {
        this.repository = repositoy;
    }

    @Transactional
    public Long save(Principal principal,T entity) {
        return this.repository.save(entity).getId();
    }

    @Transactional
    public List<Long> save(Principal principal,List<T> entitys) {
        return this.repository
                .saveAll(entitys)
                .stream()
                .map(T::getId)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(Principal principal,T update) throws Exception {

        T entity = this.repository.findById(update.getId()).orElseThrow(() -> new Exception("Not found entity"));

        entity.update(update);

        this.repository.save(entity);
    }

    @Transactional(readOnly = false)
    public Page find(Principal principal,Specification<T> spc, Pageable page) throws Exception {
        return this.repository.findAll(spc, page);
    }


    @Transactional(readOnly = true)
    public T find(Principal principal,Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Not found entity"));
    }

    @Transactional
    public void delete(Principal principal,Long id) throws Exception {
        this.repository.deleteById(id);
    }


}
