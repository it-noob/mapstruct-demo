package com.vimbug.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.reflect.TypeToken;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public interface BaseConvert<SOURCE, TARGET> {

    /**
     * 映射同名属性
     */
    TARGET poToVo(SOURCE source);

    /**
     * 反向，映射同名属性
     */
    @InheritInverseConfiguration(name = "poToVo")
    SOURCE voToPo(TARGET target);

    /**
     * 映射同名属性，集合形式
     */
    @InheritConfiguration(name = "poToVo")
    List<TARGET> poToVo(List<SOURCE> sources);

    /**
     * 反向，映射同名属性，集合形式
     */
    @InheritConfiguration(name = "voToPo")
    List<SOURCE> voToPo(List<TARGET> targets);

    /**
     * 更新属性
     */
    @InheritConfiguration(name = "poToVo")
    void updatePoToVo(SOURCE source, @MappingTarget TARGET target);

    /**
     * 反向，更新属性
     */
    @InheritConfiguration(name = "voToPo")
    void updateVoToPo(@MappingTarget SOURCE source, TARGET target);

    /**
     * 映射同名属性，集合流形式
     */
    List<TARGET> poToVo(Stream<SOURCE> stream);

    /**
     * 反向，映射同名属性，集合流形式
     */
    List<SOURCE> voToPo(Stream<TARGET> stream);

    default SOURCE mapToPo(Map<String, Object> map) {
        try {
            TypeToken<SOURCE> voType = new TypeToken<SOURCE>(getClass()) {
            };
            Class<SOURCE> rawType = (Class<SOURCE>) voType.getRawType();
            String s = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
            return JSONObject.parseObject(s, rawType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Map<String, Object> poToMap(SOURCE bean) {
        String s = JSONObject.toJSONString(bean, SerializerFeature.WriteMapNullValue);
        return JSONObject.parseObject(s, Map.class);
    }

    default List<Map<String, Object>> listPoToListMap(List<SOURCE> list) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (SOURCE source : list) {
            listMap.add(poToMap(source));
        }
        return listMap;
    }

    default List<SOURCE> listMapToListPo(List<Map<String, Object>> listMap) {
        String s = JSONObject.toJSONString(listMap, SerializerFeature.WriteMapNullValue);
        try {
            TypeToken<SOURCE> voType = new TypeToken<SOURCE>(getClass()) {
            };
            Class<SOURCE> rawType = (Class<SOURCE>) voType.getRawType();
            return JSONObject.parseArray(s, rawType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default TARGET mapToVo(Map<String, Object> map) {
        try {
            TypeToken<TARGET> voType = new TypeToken<TARGET>(getClass()) {
            };
            Class<TARGET> rawType = (Class<TARGET>) voType.getRawType();
            String s = JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
            return JSONObject.parseObject(s, rawType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Map<String, Object> voToMap(TARGET bean) {
        String s = JSONObject.toJSONString(bean, SerializerFeature.WriteMapNullValue);
        return JSONObject.parseObject(s, Map.class);
    }

    default List<Map<String, Object>> listVoToListMap(List<TARGET> list) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (TARGET source : list) {
            listMap.add(voToMap(source));
        }
        return listMap;
    }

    default List<TARGET> listMapToListVo(List<Map<String, Object>> listMap) {
        String s = JSONObject.toJSONString(listMap, SerializerFeature.WriteMapNullValue);
        try {
            TypeToken<TARGET> voType = new TypeToken<TARGET>(getClass()) {
            };
            Class<TARGET> rawType = (Class<TARGET>) voType.getRawType();
            return JSONObject.parseArray(s, rawType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default TARGET translate(SOURCE source, Map<String, String> format, final Map<String, Map<String, String>> cache) {
        TARGET target = poToVo(source);
        Map<String, Object> targetMap = voToMap(target);
        for (String key : format.keySet()) {
            String formatValue = format.get(key);
            if (targetMap.containsKey(key) && targetMap.containsKey(formatValue)) {
                String formatKey = targetMap.get(key).toString();
                targetMap.put(formatValue, cache.get(key).get(formatKey));
            } else {
                throw new RuntimeException("the input format incorrect,format is " + format.toString());
            }
        }
        target = mapToVo(targetMap);
        return target;
    }
}
