package test;

import java.util.HashSet;

public class CacheManager {
	int size;
    CacheReplacementPolicy crp;
	HashSet<String> cache = new HashSet<>();

   public CacheManager(int size, CacheReplacementPolicy crp){
       this.size = size;
       this.crp = crp;
   }
   boolean query(String word) {
        if(cache.contains(word)){
            return true;
        }
        return false;
   }
   void add(String word){
        crp.add(word);
        cache.add(word);
        if(cache.size() > size){
            cache.remove(crp.remove());
        }
   }
}
