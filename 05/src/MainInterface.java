interface Interface {

    public static void foo() {

    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
public class MainInterface implements Interface {

    public static void main(String[] args) {
        Interface.main(args);
    }
}