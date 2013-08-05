package com.xpsoft.core.util;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
    
    
    /**
     * 分割List
     * @param list 待分割的list
     * @param pageSize 每段list的大小
     * @return List<<List<T>> 
     */
     public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();                                                           //list的大小
        int page = (listSize + (pageSize-1))/ pageSize;                      //页数
        List<List<T>> listArray = new ArrayList<List<T>>();              //创建list数组 ,用来保存分割后的list
        for(int i=0;i<page;i++) {                                                         //按照数组大小遍历
            List<T> subList = new ArrayList<T>();                               //数组每一位放入一个分割后的list
            for(int j=0;j<listSize;j++) {                                                 //遍历待分割的list
                int pageIndex = ( (j + 1) + (pageSize-1) ) / pageSize;   //当前记录的页码(第几页)
                if(pageIndex == (i + 1)) {                                               //当前记录的页码等于要放入的页码时
                    subList.add(list.get(j));                                               //放入list中的元素到分割后的list(subList)
                }
                if( (j + 1) == ((j + 1) * pageSize) ) {                               //当放满一页时退出当前循环
                    break;
                }
            }
            listArray.add(subList);                                                         //将分割后的list放入对应的数组的位中
        }
        return listArray;
    }
    

     /**
      * 分割数组
      * @param ary 待分割的数组
      * @param subSize 每段数组最大数
      * @return Object[] 
      */
     private static Object[] splitAry(int[] ary, int subSize) {  
    	  int count = ary.length % subSize == 0 ? ary.length / subSize: ary.length / subSize + 1;  
    	  List<List<Integer>> subAryList = new ArrayList<List<Integer>>();  
    	  for (int i = 0; i < count; i++) {  
    		  int index = i * subSize;  
    		  List<Integer> list = new ArrayList<Integer>();  
    		  int j = 0;  
    		  while (j < subSize && index < ary.length) {  
    			  list.add(ary[index++]);  
    			  j++;  
    		  }  
    		  subAryList.add(list);  
    	  }  
    	  Object[] subAry = new Object[subAryList.size()];  
    	  for(int i = 0; i < subAryList.size(); i++){  
    		  List<Integer> subList = subAryList.get(i);  
    		  int[] subAryItem = new int[subList.size()];  
    		  for(int j = 0; j < subList.size(); j++){  
    			  subAryItem[j] = subList.get(j).intValue();  
    		  }  
    		  subAry[i] = subAryItem;  
    	  }  
    	  return subAry;  
    	 }  
}