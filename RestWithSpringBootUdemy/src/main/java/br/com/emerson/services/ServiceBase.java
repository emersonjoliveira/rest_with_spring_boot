package br.com.emerson.services;

import br.com.emerson.adapter.DozerAdapter;
import br.com.emerson.data.model.EntityBase;
import br.com.emerson.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class ServiceBase<E extends EntityBase, R extends JpaRepository<E,Long>, V> {

    public static final String RECORDS_NOT_FOUND_FOR_THIS_ID = "No records for this ID!";

    @Autowired
    private R repository;

    public R getRepository() {
        return repository;
    }

    public V create(V vo) {
        var entity = DozerAdapter.parseObject(vo, getEntityClass());
        return DozerAdapter.parseObject(getRepository().save(entity), getVOClass());
    }

    public Page<V> findAll(Pageable pageable) {
        var page = getRepository().findAll(pageable);
        return page.map(this::convertToVO);
    }

    protected V convertToVO(E entity) {
        return DozerAdapter.parseObject(entity, getVOClass());
    }

    public V findById(Long id) {
        return DozerAdapter.parseObject(getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID)), getVOClass());
    }

    public V update(V vo) {
        E entity = getRepository().findById(getIdVO(vo))
                .orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID));
        setEntityValues(entity, vo);
        return DozerAdapter.parseObject(getRepository().save(entity), getVOClass());
    }

    public void delete(Long id) {
        E entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID));
        getRepository().delete(entity);
    }

    public abstract Class<V> getVOClass();

    public abstract Class<E> getEntityClass();

    protected abstract void setEntityValues(E entity, V vo);

    protected abstract Long getIdVO(V vo);
}
