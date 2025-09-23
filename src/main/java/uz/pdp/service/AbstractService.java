package uz.pdp.service;

public abstract class AbstractService<R, M, V> {
    protected final R repository;
    protected final M mapper;
    protected final V validator;


    protected AbstractService(R repository, M mapper, V validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }
}
