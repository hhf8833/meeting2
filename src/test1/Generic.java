package test1;

public class Generic<T> implements GenericInterface<String >{

    private T var;


    public void setVar(T var) {
        this.var = var;
    }

    public T getVar() {
        return var;
    }


    @Override
    public String method() {
        return null;
    }
    public static <E> void printArray(E[] array){
        for(E elment :array){
            System.out.printf("%s\t",elment);
        }
        System.out.printf("\n");
    }
}
