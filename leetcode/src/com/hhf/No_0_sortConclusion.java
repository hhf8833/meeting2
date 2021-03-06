package com.hhf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class No_0_sortConclusion {
    public static void main(String[] args) {
        int[]nums = new int[]{5,8,3,7,4,2,9,6,1};
        partition(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
        mergeSort(nums);
        System.out.println("mergeSort:"+Arrays.toString(nums));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Integer[] integers = list.toArray(new Integer[0]);
        System.out.println(Arrays.toString(integers));
        String[] strs = new String[]{"asd","ftvv","agfrg",};
        Arrays.sort(strs,(x,y)->{
            return (x+y).compareTo(y+x);
        });
        System.out.println(Arrays.toString(strs));
        String ss = "abcdefg";
        char c = ss.charAt(3);
        String s = "ss" + c;

    }
    //快速排序,时间复杂度为nlogn
    //每一趟排序，把比选定值index小的数字放在它的左边，比它大的值放在右边
    public static void partition(int []nums,int start,int end){
        if (start >= end){
            return;
        }
        int index = nums[start];

        //这里采用快慢指针，来进行排序，上面的while复杂度高，注意指针的起始位置，最后位置
        int j = start;
        for (int i = start+1; i <= end; i++) {
            if (nums[i]<index){
                j++;
                int tmp = nums[j]; nums[j]=nums[i]; nums[i]=tmp;
            }
        }
        int tmp = nums[j]; nums[j]=index; nums[start]=tmp;
        System.out.println("index:"+index+Arrays.toString(nums));
        partition(nums,start,j-1);
        partition(nums,j+1,end);

    }


    //递归排序，分治合，分为一个一个小的数组，再将其合起来
    public static void mergeSort(int[] array){
        if (array == null || array.length<=1){
            return;
        }
        int[] newArray = new int[array.length];
        mergeSort(array,0,array.length-1,newArray);
    }
    public  static void mergeSort(int[] array,int left,int right,int[] newArray){
        if (left>=right){
            return;
        }
        int mid =(left+right)/2;
        mergeSort(array,left,mid,newArray);
        mergeSort(array,mid+1,right,newArray);
        //将数据先复制到新数组里面，方便对老数组进行修改,这里的newArray是和array一样，局部排序
        for (int i = left; i <=right ; i++) {
            newArray[i]=array[i];
        }
        int i = left;
        int j = mid+1;
        /*int k =left;
        while (i<=mid &&j<=right){
            if (newArray[i]<=newArray[j]){
                array[k++]=newArray[i++];
            }else {
                array[k++]=newArray[j++];
            }

        }
        while (i<=mid){
            array[k++]=newArray[i++];
        }
        while (j<=right){
            array[k++]=newArray[j++];
        }*/

        for(int k = left ; k <= right; k++){
            if(i == mid +1 ||  newArray[i] > newArray[j]){
                array[k] = newArray[j++];
            }else if(j ==right+1 || newArray[i] <= newArray[j]){
                array[k] = newArray[i++];
            }
        }


    }
}
