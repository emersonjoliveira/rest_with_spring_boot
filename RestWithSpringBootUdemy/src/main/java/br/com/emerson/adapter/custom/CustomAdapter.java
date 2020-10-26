package br.com.emerson.adapter.custom;

public interface CustomAdapter<E, V> {

    E convertVOToEntity(V vo);
    V convertEntityToVO(E entity);
}
