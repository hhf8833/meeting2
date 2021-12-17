package test1;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            list.add(i);
        }
        list.removeIf(filter -> filter % 2 == 0); /* 删除list中的所有偶数 */
        System.out.println(list); /* [1, 3, 5, 7, 9] */
        Test2 test2 =new Test2();
        test2.myTestLearnMore();

        print(new String[]{"沉"});
        print(new String[]{"沉", "默"});
        print(new String[]{"沉", "默", "王"});
        print(new String[]{"沉", "默", "王", "二"});
        String s ="abcdef";
        System.out.println(s.substring(1,2));
        String s1 ="abc";
        String s2 = "def";
        String s3 = "abcdef";
        String s4 = s1+ s2;
        System.out.println(s3==s4);
        Integer i1 = 33;
        Integer i2 = 33;
        System.out.println(i1 == i2);// 输出 true
        Integer i11 = 333;
        Integer i22 = 333;
        System.out.println(i11 == i22);// 输出 false

    }

    public void myTestLearnMore()
    {
        List<String> testList = new ArrayList<>();
        testList.add("1杨");
        testList.add("1李");
        testList.add("1李");
        testList.add("1王");
        testList.add("1王");
        testList.add("1张");
        testList.add("2杨");
        testList.add("2李");
        testList.add("2孙");
        testList.add("2孙");
        testList.add("2赵");

        List<String> temAddList = new ArrayList<>();
/*        for(String test : testList)
        {
            if(test.startsWith("1"))
            {
               temAddList.add(test);
            }
        }*/

        testList.removeIf(list ->list.startsWith("1"));
        //removeAll是正确的方法而不是remove
        testList.removeAll(temAddList);
        System.out.println(testList);
        Set<String> hashSet = removeDuplicateBySet(testList);
        System.out.println(hashSet);

    }
    public static <T>Set<T>removeDuplicateBySet(List<T> data){
        if (!data.isEmpty()){
            return new HashSet<>(data);
        }
        return new LinkedHashSet<>();
    }


    public static void print(String... strs) {
        for (String s : strs) {
            System.out.print(s);
        }
        System.out.println();
    }
}
