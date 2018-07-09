package com.vimbug;

import com.vimbug.base.Person;
import com.vimbug.base.PersonConvert;
import com.vimbug.base.PersonVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class PersonConvertTest {

    @Test
    public void testPersonConvert() {
        Person person = new Person("wayne", 12, new Date(), 12f);
        PersonVo personVo = PersonConvert.INSTANCE.poToVo(person);
        log.info("person->{}", person);
        log.info("personVo->{}", personVo);
        Map<String, Object> personMap = PersonConvert.INSTANCE.poToMap(person);
        log.info("personMap->{}", personMap);
        Map<String, Object> personVoMap = PersonConvert.INSTANCE.voToMap(personVo);
        log.info("personVoMap->{}", personVoMap);
        Person person1 = PersonConvert.INSTANCE.mapToPo(personMap);
        log.info("person1->{}", person1);
        PersonVo personVo1 = PersonConvert.INSTANCE.mapToVo(personVoMap);
        log.info("personVo1->{}", personVo1);
    }

    @Test
    public void testPersonConvertList() {
        Person person1 = new Person("wayne", 12, new Date(), 12f);
        Person person2 = new Person("wayne2", 13, new Date(System.currentTimeMillis() + 3600000), 13f);
        Person person3 = new Person("wayne3", 14, new Date(System.currentTimeMillis() + 7200000), 14f);
        Person person4 = new Person("wayne4", 15, new Date(System.currentTimeMillis() + 9800000), 15f);
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        List<PersonVo> personVos = PersonConvert.INSTANCE.poToVo(persons);
        log.info("{}", persons);
        log.info("{}", personVos);
    }

}
