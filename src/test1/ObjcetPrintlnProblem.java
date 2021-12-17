package test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HP  打印问题
 */
public class ObjcetPrintlnProblem {
    private String name;

    public ObjcetPrintlnProblem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"---name is "+name;
    }

    public static void main(String[] args) {
        ObjcetPrintlnProblem op = new ObjcetPrintlnProblem("hhf");
        System.out.println("op:::"+op);
        ObjcetPrintlnProblem[] op2 = {new ObjcetPrintlnProblem("hhf1"),new ObjcetPrintlnProblem("hhf2")};
        System.out.println(Arrays.toString(op2));
        List<ObjcetPrintlnProblem> list = new ArrayList<>();
        list.add(new ObjcetPrintlnProblem("沉默王二"));
        list.add(new ObjcetPrintlnProblem("沉默王三"));

        System.out.println(list);
        //数组转集合
        String[] array ={"Apple", "Banana", "Orange"};
        List<String> list1 = Arrays.asList(array);
        System.out.println(list1.get(1));
        System.out.println(list1.getClass());
        Integer[] myArray = {1,2,3};
        List mylist = Arrays.stream(myArray).collect(Collectors.toList());
        //在将基本类型转换时要用boxed装箱
        int [] myArray2 = { 1, 2, 3 };
        List list2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
        System.out.println(list2);
    }
}
