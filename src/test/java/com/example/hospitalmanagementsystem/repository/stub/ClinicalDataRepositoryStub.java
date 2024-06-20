package com.example.hospitalmanagementsystem.repository.stub;

import com.example.hospitalmanagementsystem.model.ClinicalData;
import com.example.hospitalmanagementsystem.repository.ClinicalDataRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class ClinicalDataRepositoryStub implements ClinicalDataRepository {
    private final Map<Long, ClinicalData> data = new HashMap<>();
    private long idCounter = 1;

    public ClinicalDataRepositoryStub() {
        this.idCounter = idCounter;
    }

    @Override
    public <S extends ClinicalData> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public List<ClinicalData> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<ClinicalData> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<ClinicalData> findByPatientFirstNameAndPatientLastName(String firstName, String lastName) {
        return Collections.emptyList(); // Not used in tests
    }

    @Override
    public List<ClinicalData> filter(String clinicalRecord) {
        List<ClinicalData> filteredList = new ArrayList<>();
        for (ClinicalData cd : data.values()) {
            if (cd.getClinicalRecord() != null && cd.getClinicalRecord().contains(clinicalRecord)) {
                filteredList.add(cd);
            }
        }
        return filteredList;
    }

    @Override
    public Optional<ClinicalData> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends ClinicalData> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(idCounter++);
        }
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        data.remove(id);
    }

    @Override
    public void delete(ClinicalData entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ClinicalData> entities) {

    }

    @Override
    public void deleteAll() {
        data.clear();
    }

    // Additional methods for testing purposes
    public List<ClinicalData> getAllClinicalData() {
        return new ArrayList<>(data.values());
    }

    public void saveAll(List<ClinicalData> entities) {
        for (ClinicalData entity : entities) {
            save(entity);
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ClinicalData> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends ClinicalData> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<ClinicalData> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ClinicalData getOne(Long aLong) {
        return null;
    }

    @Override
    public ClinicalData getById(Long aLong) {
        return null;
    }

    @Override
    public ClinicalData getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends ClinicalData> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ClinicalData> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends ClinicalData> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends ClinicalData> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ClinicalData> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ClinicalData> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ClinicalData, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<ClinicalData> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<ClinicalData> findAll(Pageable pageable) {
        return null;
    }
}

