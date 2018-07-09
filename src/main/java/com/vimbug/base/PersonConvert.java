package com.vimbug.base;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonConvert extends BaseConvert<Person, PersonVo> {

    PersonConvert INSTANCE = Mappers.getMapper(PersonConvert.class);

    @Override
    @Mappings({
            @Mapping(source = "birthdate", target = "birth"),//属性名不一致映射
            @Mapping(source = "birthdate", target = "birthformat", dateFormat = "yyyy-MM-dd HH:mm:ss")

    })
    PersonVo poToVo(Person person);

    @Override
    @Mappings({
            @Mapping(source = "birthdate", target = "birth"),//属性名不一致映射
            @Mapping(target = "birthformat", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(person.getBirthdate(),\"yyyy-MM-dd HH:mm:ss\"))")//自定义属性通过java代码映射

    })
    void updatePoToVo(Person person, @MappingTarget PersonVo personVo);
}


