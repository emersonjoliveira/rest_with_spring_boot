package br.com.emerson.services;

import br.com.emerson.adapter.DozerAdapter;
import br.com.emerson.adapter.custom.PersonAdapter;
import br.com.emerson.data.model.Person;
import br.com.emerson.data.vo.v1.PersonVO;
import br.com.emerson.data.vo.v2.PersonVOV2;
import br.com.emerson.exception.ResourceNotFoundException;
import br.com.emerson.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService extends ServiceBase<Person, PersonRepository, PersonVO> {

    @Autowired
    PersonAdapter converter;

    public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
        var page = getRepository().findPersonByName(firstName, pageable);
        return page.map(this::convertToVO);
    }

    @Transactional
    public PersonVO disablePerson(Long id) {
        getRepository().disablePerson(id);
        return DozerAdapter.parseObject(
                getRepository().findById(id).orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID)),
                PersonVO.class);
    }

    @Override
    public Class<PersonVO> getVOClass() {
        return PersonVO.class;
    }

    @Override
    public Class<Person> getEntityClass() {
        return Person.class;
    }

    @Override
    protected void setEntityValues(Person entity, PersonVO vo) {
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setAddress(vo.getAddress());
        entity.setGender(vo.getGender());
    }

    @Override
    protected Long getIdVO(PersonVO vo) {
        return vo.getKey();
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        var entity = converter.convertVOToEntity(person);
        return converter.convertEntityToVO(getRepository().save(entity));
    }
}
