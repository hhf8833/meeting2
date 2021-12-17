package test1;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hello {
    public static void main( String args[] ){
        List<Integer> list =new ArrayList<>();
        List<Integer> list2 =new ArrayList<>();
        System.out.println(list.equals(list2));
        Generic<Integer> generic =new Generic<Integer>();
        generic.setVar(new Integer(1000));
        System.out.println(generic.getVar());

        Integer[] intArray ={1,2,3};
        String[] stringsArray= {"hello","word!"};
        Generic.printArray(intArray);
        Generic.printArray(stringsArray);
        Integer i =Integer.valueOf("1");
        System.out.println(i);

        int i2 =i.intValue();
        System.out.println(i2);
        Integer i3 =2;
        int i4 =2;
        System.out.println(i3==i4);
        Hero hero =new SuperMan();
        System.out.println(hero.name());

        StringBuffer s =new StringBuffer("abcd");
        System.out.println(s.charAt(1));
        Map<String,Integer> a=new HashMap<String,Integer>();
        a.put("a",1);
    }

}
class Hero {
    public String name() {
        return "超级英雄";
    }
}
 class SuperMan extends Hero{
    @Override
    public String name() {
        return "超人";
    }
    public Hero hero() {
        return new Hero();
    }
    public SuperMan(){

    }
     public SuperMan superMan() {
         return new SuperMan();
     }
}
 class SuperSuperMan extends SuperMan {
     @Override
     public Hero hero() {
         return hero();
     }

/*     @Override//返回的只能比父类类型更小或者相等
     public Hero superMan() {
         return Hero();
     }*/

     @Override
     public String name() {
         return super.name();
     }
 }