package com.lockie.order.util;

import java.math.BigDecimal;
import java.util.Random;

public class RandomUtil {
	
	
    public static String getCharNumStr(int length){
    	StringBuffer retStr = new StringBuffer();
    	Random random = new Random(); 
    	
    	for(int i = 0; i < length; i++) {
    		String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                retStr.append((char)(random.nextInt(26) + temp));
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                retStr = retStr.append(random.nextInt(10));
            } 
    	}
    	
    	return retStr.toString();
    }
    
    public static String getNumForStr(int length){
    	StringBuffer retStr = new StringBuffer();
    	Random random = new Random();
    	
    	for(int i = 0; i < length; i++) {
    		retStr.append(random.nextInt(10));
    	}
    	
    	return retStr.toString();
    }
      
    public static void  main(String[] args) {  
        
        System.out.println("getNumForStr: " + getNumForStr(6));  
        System.out.println("getCharNumStr: " + getCharNumStr(6)); 
        System.out.println("getNumForStr: " + getNumForStr(3));

        // RP 0 180305 897 88640 0001
        String RP = "00";

        Long date = System.currentTimeMillis() / 1000;
        System.out.println(date);

        String order = RP + date +  getNumForStr(3) + "0001";
        System.out.println(order);

        Integer userId = 1234;
        int l = userId.toString().length();
        System.out.println("位数 "+ l);
        if (l > 4) {
            String code = userId.toString().substring(l - 4, l);
            System.out.println("后四位 "+code);
        } else {
            String s=String.format("%04d", userId);
            System.out.println("左边补0 "+s);
        }

        BigDecimal orderTotal = BigDecimal.ZERO;
        for (int i = 0; i < 4; i++) {
            BigDecimal p = new BigDecimal(i);
            orderTotal = orderTotal.add(p);
        }
        System.out.println("累加结果："+orderTotal);

    }

}
