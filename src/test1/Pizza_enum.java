package test1;

public class Pizza_enum {
    private PizzaStatus pizzaStatus;

    public PizzaStatus getPizzaStatus() {
        return pizzaStatus;
    }

    public void setPizzaStatus(PizzaStatus pizzaStatus) {
        this.pizzaStatus = pizzaStatus;
    }

    public enum PizzaStatus{//披萨状态
        ORDERED,
        READY,
        DELIVERED;
    }
    public  boolean isDeliverable(){
        return getPizzaStatus()==PizzaStatus.READY;
//        this.setPizzaStatus();
    }

    public static void main(String[] args) {
        System.out.println(PizzaStatus.ORDERED);
        Pizza_enum.PizzaStatus  piz= null;
        //枚举类型要用==进行比较
        if (piz!=PizzaStatus.ORDERED){
            System.out.println("dddd");
        }
    }

}
