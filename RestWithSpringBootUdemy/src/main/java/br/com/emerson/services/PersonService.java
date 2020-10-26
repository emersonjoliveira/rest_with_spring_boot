package br.com.emerson.services;

import br.com.emerson.adapter.DozerAdapter;
import br.com.emerson.adapter.custom.PersonAdapter;
import br.com.emerson.data.model.Person;
import br.com.emerson.data.vo.v1.PersonVO;
import br.com.emerson.data.vo.v2.PersonVOV2;
import br.com.emerson.exception.ResourceNotFoundException;
import br.com.emerson.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    private static final String RECORDS_NOT_FOUND_FOR_THIS_ID = "No records for this ID!";

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonAdapter converter;

    public PersonVO create(PersonVO person) {
        var entity = DozerAdapter.parseObject(person, Person.class);
        return DozerAdapter.parseObject(repository.save(entity), PersonVO.class);
    }

    public Page<PersonVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertToPersonVO);
    }

    public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
        var page = repository.findPersonByName(firstName, pageable);
        return page.map(this::convertToPersonVO);
    }

    private PersonVO convertToPersonVO(Person entity) {
        return DozerAdapter.parseObject(entity, PersonVO.class);
    }

    public PersonVO findById(Long id) {
        return DozerAdapter.parseObject(
                repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID)),
                PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerAdapter.parseObject(repository.save(entity), PersonVO.class);
    }

    @Transactional
    public PersonVO disablePerson(Long id) {
        repository.disablePerson(id);
        return DozerAdapter.parseObject(
                repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID)),
                PersonVO.class);
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID));
        repository.delete(entity);
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        var entity = converter.convertVOToEntity(person);
        return converter.convertEntityToVO(repository.save(entity));
    }
}
