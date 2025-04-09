package io.meli.melishop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtils {
    
    public static List<String> createTopThreeListFromMapValue(Map<String, Integer> fakeObjectFromDb) {

        List<Map.Entry<String, Integer>> availableProducts = new ArrayList<>(fakeObjectFromDb.entrySet());
        availableProducts.sort(Map.Entry.comparingByValue());

        int size = availableProducts.size();

        List<String> sortedList = new ArrayList<>();
        sortedList.add(0, availableProducts.get(size - 1).getKey());
        sortedList.add(1, availableProducts.get(size - 2).getKey());
        sortedList.add(2, availableProducts.get(size - 3).getKey());
        
        return sortedList;
    }
}
