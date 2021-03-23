package com.javairus.webnovel.domain.enums;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
flyweight 패턴으로 애플리케이션의 메모리상에 정적 코드성 데이터를 적재하여 응답시간을 빠르게 함
 */
public class EnumMapper {

    //저장소
    private Map<String, List<EnumValue>> store;

    public EnumMapper() {
        //여러 인스턴스에서 사용되더라도(여러 서버에서 사용) 데이터를 조회하는 것이기 때문에 동기화작업을 하지 않아도 됨
        store = new HashMap<>();
    }

    private List<EnumValue> toEnumValues(Class<? extends EnumModel> e) {
        return Arrays.stream(e.getEnumConstants())
                .map(EnumValue::new)
                .collect(Collectors.toList());
    }

    public void put(String key, Class<? extends EnumModel> e) {
        store.put(key, toEnumValues(e));
    }

    public Map<String, List<EnumValue>> findAll() {
        return store;
    }

    public Map<String, List<EnumValue>> find(String keys) {

        return Arrays.stream(keys.split(","))
                .collect(Collectors.toMap(Function.identity(), key -> store.get(key)));
    }
}
