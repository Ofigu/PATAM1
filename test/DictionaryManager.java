package test;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    public Map<String, Dictionary> map;

    public DictionaryManager() {
        map = new HashMap<>();
    }

    public boolean query(String...args) {
        for(int i = 0; i < args.length - 1; i++) {
            if(!map.containsKey(args[i])) {
                map.put(args[i], new Dictionary(args[i])); // create a new dictionary if it doesn't exist
            }
        }
        for(Dictionary d : map.values()) {
            if(d.query(args[args.length - 1])) { // query the word
                return true;
            }
        }
        return false;
    }

    public boolean challenge(String...args) {
        for(int i = 0; i < args.length - 1; i++) {
            if(!map.containsKey(args[i])) {
                map.put(args[i], new Dictionary(args[i])); // create a new dictionary if it doesn't exist
            }
        }
        for(Dictionary d : map.values()) {
            if(d.challenge(args[args.length - 1])) { // challenge the word
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return map.size();
    }
    
    // singleton
    private static DictionaryManager dm = null;
    public static DictionaryManager get() {
        if(dm == null) {
            dm = new DictionaryManager();
        }
        return dm;
    }
}
