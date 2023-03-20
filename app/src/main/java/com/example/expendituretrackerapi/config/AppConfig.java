package com.example.expendituretrackerapi.config;

import com.example.expendituretrackerapi.controllers.ExpenditureController;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Configuration
@ComponentScan(basePackages = {"com.example.expendituretrackerapi.repositories", "com.example.expendituretrackerapi.controllers"})
public class AppConfig {

    @Bean("expenditureController")
    @DependsOn("incomeRepository")
    public ExpenditureController expenditureController(){
        return new ExpenditureController();
    }
    @Bean("incomeRepository")
    public IncomeRepository incomeRepository() {
        return new IncomeRepository() {
            @Override
            public Optional<Income> findById(Long incomeId) {
                return Optional.empty();
            }

            @Override
            public List<Income> findAll() {
                return null;
            }

            @Override
            public List<Income> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Income> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends Income> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Income> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends Income> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<Income> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Income getOne(Long aLong) {
                return null;
            }

            @Override
            public Income getById(Long aLong) {
                return null;
            }

            @Override
            public Income getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends Income> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Income> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Income> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Income> S save(S entity) {
                return null;
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Income entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends Income> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Income> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Income> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Income> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Income> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends Income, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }

}
