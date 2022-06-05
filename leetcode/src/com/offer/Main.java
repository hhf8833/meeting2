package com.offer;

import java.util.*;

public class Main {
    private static Map<String ,String > map= new HashMap<>();
    private static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n =scanner.nextInt();
        int m =scanner.nextInt();
        scanner.nextLine();

        String[] strN= new String[n];
        String[] strM= new String[m];
        for (int i = 0; i < n; i++) {
            System.out.println("输入N："+i);
            strN[i] = scanner.nextLine();
        }
        System.out.println(Arrays.toString(strN));
        for (int i = 0; i < m; i++) {
            strM[i] = scanner.nextLine();
        }
        System.out.println(Arrays.toString(strM));

        for (int i = 0; i < n; i++) {
            strPrefix(strN[i]);
        }
        System.out.println(map);
        for (int i = 0; i < m; i++) {
            String s = map.get(strM[i]);
            if (s != null){
                list.add(s);
            }
        }
        System.out.println(list);
    }
    public static void strPrefix(String str){
        String[] s = str.split(" ");
        StringBuilder  stringBuilder = new StringBuilder();
        stringBuilder.append(s[0]);
        for (int i = 1; i < s.length-1; i++) {
            map.put(stringBuilder.toString(),s[i]);
            stringBuilder.append(" ");
            stringBuilder.append(s[i]);
        }
    }
}
