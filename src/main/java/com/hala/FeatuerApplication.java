package com.hala;

import com.sun.istack.internal.NotNull;
import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class FeatuerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeatuerApplication.class, args);
    }



    public List<String> sortGetTop3LongWords(@NotNull String sentence){

        String[] words = sentence.split("");
        List<String> wordList = new ArrayList<>();
        for (String word : words) {
            if (word.length() > 5){
                wordList.add(word);
            }
        }
        wordList.sort((o1,o2) -> o2.length() - o1.length());
        if (wordList.size() > 3 ){
            wordList =  wordList.subList(0,3);
        }
        return wordList;

    }


    public List<String> sortGetTop3LongWordsByStream(@NotNull String sentence){

        return Arrays.stream(sentence.split(""))
                .filter(word -> word.length() >5 )
                .sorted((o1,o2) -> o2.length() - o1.length())
                .limit(3)
                .collect(Collectors.toList());
    }

    public void stringToMap(){
       List<String> ids =  Arrays.asList("205", "105", "308", "469", "627", "193", "111");
       List<User> result = ids.stream().map(id ->{
           User user = new User();
           user.setId();
           return user;
       }).collect(Collectors.toList());

    }

    public void stringToIntFlatmap(){
        List<String> sentences = Arrays.asList("hello world","Jia Gou Wu Dao");
        List<String> result = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split("")))
                .collect(Collectors.toList());

    }

    public void testPeekAndForeach(){
        List<String> sentences = Arrays.asList("hello world","Jia Gou Wu Dao");
        sentences.stream().peek(sentence -> System.out.println(sentence));
        sentences.stream().peek(sentence -> System.out.println(sentence)).count();

        sentences.stream().forEach(sentence -> System.out.println(sentence));

    }

    public void testGetTargetUsers(){
        List<String> ids =  Arrays.asList("205", "105", "308", "469", "627", "193", "111");

        List<Object> result = ids.stream()
                //过滤不符合条件的数据
                .filter(s -> s.length() > 3)
                //去重
                .distinct()
                //通过Map将字符串转成整数类型
                .map(Integer::valueOf)
                //按照数字大小正序排列
                .sorted(Comparator.comparing(o -> o))
                //取前3位
                .limit(3)
                //将Id转为dept对象类型
                .map(id -> new Dept(id))
                //终止操作将最终数据收集到集合中
                .collect(Collectors.toList());


    }

}
