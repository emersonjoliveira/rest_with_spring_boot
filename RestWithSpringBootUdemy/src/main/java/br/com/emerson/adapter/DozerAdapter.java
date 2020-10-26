package br.com.emerson.adapter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import java.util.ArrayList;
import java.util.List;

public class DozerAdapter {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    private DozerAdapter() {
    }

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origins, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();
        for (Object origin : origins) {
            destinationObjects.add(mapper.map(origin, destination));
        }
        return destinationObjects;
    }
}
