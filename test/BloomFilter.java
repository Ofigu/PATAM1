package test;

import java.util.BitSet;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;


public class BloomFilter {
    BitSet bitSets;
    ArrayList<MessageDigest> hashArr = new ArrayList<>();
    
    public BloomFilter(int size, String...args){
        bitSets = new BitSet(size);
        for(String funcName: args){
            try {
                MessageDigest md = MessageDigest.getInstance(funcName);
                hashArr.add(md);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void add(String word) {
        for(MessageDigest funcName: hashArr){
            byte[] bts=funcName.digest(word.getBytes());
            BigInteger bi=new BigInteger(bts);
            int num=bi.intValue();
            num=Math.abs(num);
            num=num% bitSets.size();
            if(!(bitSets.get(num)))
                bitSets.flip(num);
        }
    }
    
    public boolean contains(String word){
        for(MessageDigest funcName: hashArr){
            byte[] bts=funcName.digest(word.getBytes());
            BigInteger bi=new BigInteger(bts);
            int num=bi.intValue();
            num=Math.abs(num);
            num=num% bitSets.size();
            if(!bitSets.get(num))
                return false;
        }   return true;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bitSets.length(); i++){
            if(bitSets.get(i))
                sb.append("1");
            else
                sb.append("0");
        }
        return sb.toString();
    }
}
