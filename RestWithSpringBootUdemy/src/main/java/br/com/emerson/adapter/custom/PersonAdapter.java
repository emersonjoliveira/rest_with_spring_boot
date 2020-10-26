package br.com.emerson.adapter.custom;

import br.com.emerson.data.model.Person;
import br.com.emerson.data.vo.v2.PersonVOV2;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class PersonAdapter implements CustomAdapter<Person, PersonVOV2>{

    @Override
    public PersonVOV2 convertEntityToVO(Person person) {
        var vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(LocalDate.now());
        return vo;
    }

    @Override
    public Person convertVOToEntity(PersonVOV2 person) {
        var entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }
}
