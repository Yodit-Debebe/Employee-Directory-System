package com.act.intern.employeedirectory.infrastructure.persistence.adapter;

import com.act.intern.employeedirectory.application.port.output.DepartmentRepositoryPort;
import com.act.intern.employeedirectory.domain.model.Department;
import com.act.intern.employeedirectory.infrastructure.persistence.entity.DepartmentEntity;
import com.act.intern.employeedirectory.infrastructure.persistence.mapper.DepartmentMapper;
import com.act.intern.employeedirectory.infrastructure.persistence.repository.DepartmentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DepartmentRepositoryAdapter
        implements DepartmentRepositoryPort {

    private final DepartmentJpaRepository jpaRepository;

    public DepartmentRepositoryAdapter(
            DepartmentJpaRepository jpaRepository) {

        this.jpaRepository = jpaRepository;
    }

    @Override
    public Department save(
            Department department) {

        DepartmentEntity entity =
                DepartmentMapper.toEntity(department);  // convert domain → DB format

        DepartmentEntity saved =
                jpaRepository.save(entity);

        return DepartmentMapper.toDomain(saved);
    }

    @Override
    public Optional<Department> findById(Long id) {

        return jpaRepository.findById(id)
                .map(DepartmentMapper::toDomain);
    }

    @Override
    public List<Department> findAll() {

        return jpaRepository.findAll()
                .stream()
                .map(DepartmentMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByName(String name) {

        return jpaRepository.existsByName(name);
    }

    @Override
    public void delete(Department department) {

        jpaRepository.delete(
                DepartmentMapper.toEntity(department)
        );
    }
}