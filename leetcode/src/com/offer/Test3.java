package com.offer;

public class Test3 {
    class Phone{
        String color;
        int price;

        public void print(){
            System.out.println("Phone---" + color + "---" + price );
        }
    }

    class Nokia extends Phone{
        //String color = "red";
        int price = 1009;

        //public void print(){
        //    System.out.println("Nokia---" + color + "---" + price);
        //}
    }

    class Moto extends Phone{
        String color = "yellow";
        //int price = 1599;

        @Override
        public void print(){
            price =1;
            System.out.println("Moto---" + color + "---" + price);
        }
    }

    class Nexus extends Phone{
        String color = "black";
        int price = 1999;

        @Override
        public void print(){
            System.out.println("Nexus---" + color + "---" + price);
        }
    }
}

