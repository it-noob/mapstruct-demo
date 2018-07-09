package com.vimbug.translation;

import com.vimbug.base.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderConvert extends BaseConvert<OrderInfo, OrderInfoVo> {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    @Override
    @Mappings({
            @Mapping(source = "createTime", target = "createTimeFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "amount", numberFormat = "#,###.00")

    })
    OrderInfoVo poToVo(OrderInfo orderInfo);

    @Override
    @Mappings({
            @Mapping(source = "createTime", target = "createTimeFormat", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "amount", numberFormat = "#,###.00")
    })
    void updatePoToVo(OrderInfo orderInfo, @MappingTarget OrderInfoVo orderInfoVo);
}
