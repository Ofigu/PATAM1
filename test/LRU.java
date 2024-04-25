package test;

import java.util.LinkedHashSet;
import java.util.Iterator;

public class LRU implements CacheReplacementPolicy{
    LinkedHashSet<String> cache = new LinkedHashSet<>();

    @Override
    public void add(String word){
        cache.remove(word); // remove the word if it is already in the cache
        cache.add(word); 
    }
    @Override
    public String remove(){ // remove the first element in the cache
        Iterator<String> it = cache.iterator();
        return it.next(); // return the first element in the cache
    }
}
