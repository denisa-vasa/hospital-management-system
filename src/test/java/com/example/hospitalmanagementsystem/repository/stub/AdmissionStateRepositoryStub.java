package com.example.hospitalmanagementsystem.repository.stub;

import com.example.hospitalmanagementsystem.model.AdmissionState;
import com.example.hospitalmanagementsystem.repository.AdmissionStateRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class AdmissionStateRepositoryStub implements AdmissionStateRepository {
    private final List<AdmissionState> admissionStates = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public Optional<AdmissionState> findById(Long id) {
        return admissionStates.stream()
                .filter(admissionState -> admissionState.getId().equals(id))
                .findFirst();
    }

    @Override
    public <S extends AdmissionState> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(nextId++);
        }
        admissionStates.removeIf(as -> as.getId().equals(entity.getId()));
        admissionStates.add(entity);
        return entity;
    }

    @Override
    public <S extends AdmissionState> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<AdmissionState> findAll() {
        return new ArrayList<>(admissionStates);
    }

    @Override
    public long count() {
        return admissionStates.size();
    }

    @Override
    public void deleteById(Long id) {
        admissionStates.removeIf(admissionState -> admissionState.getId().equals(id));
    }

    @Override
    public void delete(AdmissionState entity) {
        admissionStates.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends AdmissionState> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        admissionStates.clear();
    }

    @Override
    public List<AdmissionState> findAllById(Iterable<Long> ids) {
        List<AdmissionState> result = new ArrayList<>();
        ids.forEach(id -> findById(id).ifPresent(result::add));
        return result;
    }

    @Override
    public boolean existsById(Long id) {
        return admissionStates.stream().anyMatch(admissionState -> admissionState.getId().equals(id));
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAllInBatch(Iterable<AdmissionState> entities) {
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
    public AdmissionState getOne(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public AdmissionState getById(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public AdmissionState getReferenceById(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends AdmissionState, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<AdmissionState> findAll(Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Page<AdmissionState> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
