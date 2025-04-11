package io.meli.melishop.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUtils {
    
    public static List<String> createTopThreeListFromMapValue(Map<String, Integer> fakeObjectFromDb) {

        List<String> topThree = new ArrayList<>();
        topThree = fakeObjectFromDb.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
        
        return topThree;
    }
}
