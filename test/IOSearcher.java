package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class IOSearcher {
    public static boolean search(String word, String...files) {
        Scanner scanner = null;
        for(String file : files){
            try {
                scanner=new Scanner(new BufferedReader(new FileReader(file)));
                while(scanner.hasNext()){
                    if(scanner.next().matches(word))
                        return true;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }scanner.close();
        }return false;
    }
}
