package test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFU implements CacheReplacementPolicy{
    LinkedHashMap<String, Integer> cache = new LinkedHashMap<>();

    @Override
    public void add(String word){
        if(cache.containsKey(word)){
            cache.put(word, cache.get(word) + 1); // increment the frequency of the word
        } else {
            cache.put(word, 1);
        }
    }
    @Override
    public String remove(){
        int min = Integer.MAX_VALUE; // finds the word with the minimum frequency
        String lfu = null; // least frequently used word
        for (Map.Entry<String, Integer> entry : cache.entrySet()) {
            if(entry.getValue() < min){
                min = entry.getValue();
                lfu = entry.getKey();
            }
        }
        return lfu; 
    }
}
