package com.zzt.javalib;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {
    public static void main(String[] args) {
        String str = "fdasfsafd181.22fdasfdsaf";
        Pattern pattern = Pattern.compile("[0-9]+(\\.[0-9]+)?");
        Matcher isNum = pattern.matcher(str);
        if (isNum.find()) {
            System.out.println("start:" + isNum.start() + "-end:" + isNum.end());
            System.out.println(":::" + str.subSequence(isNum.start(), isNum.end()));
        }

        Map<Integer , String > map = new HashMap<>();
        for (int i = 0; i < 1000 ; i++) {
            map.put(i , "数据:" +i);
        }

    }
}
