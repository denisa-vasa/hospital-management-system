package com.example.hospitalmanagementsystem.repository.stub;

import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.PatientsRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class PatientRepositoryStub implements PatientsRepository {
    private List<Patient> patients = new ArrayList<>();
    private Long nextId = 1L;

    public PatientRepositoryStub() {
        Patient patient1 = new Patient(1L, "Patient6", "Patient6", LocalDate.of(1991,10,1));
        patients.add(patient1);
    }

    @Override
    public List<Patient> filter(String firstName, String lastName) {
        return Arrays.asList(
                new Patient(1L, "Patient6", "Patient6", LocalDate.of(1991,10,1))
        );
    }

    @Override
    public void deleteByFirstNameAndLastName(String firstName, String lastName) {
        patients.removeIf(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName));
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> List<S> saveAllAndFlush(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        entities.forEach(result::add);
        result.forEach(this::save);
        return result;
    }

    @Override
    public void deleteAllInBatch(Iterable<Patient> entities) {
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
    public Patient getOne(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Patient getById(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Patient getReferenceById(Long aLong) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> long count(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends Patient> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(nextId++);
        }
        patients.add(entity);
        return entity;
    }

    @Override
    public <S extends Patient> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        entities.forEach(result::add);
        result.forEach(this::save);
        return result;
    }

    @Override
    public Optional<Patient> findById(Long aLong) {
        return patients.stream()
                .filter(p -> p.getId().equals(aLong))
                .findFirst();
    }

    @Override
    public boolean existsById(Long aLong) {
        return patients.stream()
                .anyMatch(p -> p.getId().equals(aLong));
    }

    @Override
    public List<Patient> findAll() {
        return patients;
    }

    @Override
    public List<Patient> findAllById(Iterable<Long> longs) {
        List<Patient> result = new ArrayList<>();
        longs.forEach(id -> findById(id).ifPresent(result::add));
        return result;
    }

    @Override
    public long count() {
        return patients.size();
    }

    @Override
    public void deleteById(Long aLong) {
        patients.removeIf(p -> p.getId().equals(aLong));
    }

    @Override
    public void delete(Patient entity) {
        patients.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        longs.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends Patient> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        patients.clear();
    }

    @Override
    public List<Patient> findAll(Sort sort) {
        return patients;
    }

    @Override
    public Page<Patient> findAll(Pageable pageable) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
