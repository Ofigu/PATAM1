package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Dictionary {
    CacheManager LRU;
    CacheManager LFU;
    BloomFilter bf;
    String[] files;

    Dictionary(String...files){
        this.files = files;
        this.LRU = new CacheManager(400, new LRU());
        this.LFU = new CacheManager(100, new LFU());
        this.bf = new BloomFilter(256, "MD5","SHA1");
        Scanner scanner=null;
        for(String file : files){
            try {
                scanner=new Scanner(new BufferedReader(new FileReader(file)));
                while(scanner.hasNext()){
                    bf.add(scanner.next());
                }
                } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
                } scanner.close();
            }
        }
        public boolean query(String word){
            if(LRU.query(word))
                return true;
            else if(LFU.query(word))
                return false;
            else {
                if(bf.contains(word)){
                    LRU.add(word);
                return true;
            }
            else {
                LFU.add(word);
                return false;
            }
        }
    }
    public boolean challenge(String word) {
        try {
            if (IOSearcher.search(word,files)){
                LRU.add(word);
                return true;
            } else {
                LFU.add(word);
                return false;
            }
        }
        catch (Exception e){return false;}
    }
}
