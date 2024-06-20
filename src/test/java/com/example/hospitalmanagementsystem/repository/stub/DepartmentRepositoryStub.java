package com.example.hospitalmanagementsystem.repository.stub;

import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.repository.DepartmentRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DepartmentRepositoryStub implements DepartmentRepository {

    private List<Department> departments = new ArrayList<>();
    private Long nextId = 1L;

    public DepartmentRepositoryStub() {
        // Add departments for testing
        Department department1 = new Department(1L, "Emergency Care", "EC01");
        departments.add(department1);
    }

    @Override
    public Optional<Department> findByName(String name) {
        return departments.stream().filter(d -> d.getName().equals(name)).findFirst();
    }

    @Override
    public List<Department> filter(String filter) {
        return Arrays.asList(
                new Department(1L, "Emergency Care", "EC01")
        );
    }

    @Override
    public void deleteByName(String name) {
        departments.removeIf(d -> d.getName().equals(name));
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllInBatch(Iterable<Department> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Department getOne(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Department getById(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Department getReferenceById(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Department, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Optional<Department> findById(Long aLong) {
        return departments.stream()
                .filter(d -> d.getId().equals(aLong))
                .findFirst();
    }

    @Override
    public boolean existsById(Long aLong) {
        return departments.stream()
                .anyMatch(d -> d.getId().equals(aLong));
    }

    @Override
    public <S extends Department> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(nextId++);
        }
        departments.add(entity);
        return entity;
    }

    @Override
    public <S extends Department> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<Department> findAll() {
        return departments;
    }

    @Override
    public List<Department> findAllById(Iterable<Long> longs) {
        List<Department> result = new ArrayList<>();
        longs.forEach(id -> findById(id).ifPresent(result::add));
        return result;
    }

    @Override
    public long count() {
        return departments.size();
    }

    @Override
    public void deleteById(Long aLong) {
        departments.removeIf(d -> d.getId().equals(aLong));
    }

    @Override
    public void delete(Department entity) {
        departments.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        longs.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends Department> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        departments.clear();
    }

    @Override
    public List<Department> findAll(Sort sort) {
        return departments;
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }
}

