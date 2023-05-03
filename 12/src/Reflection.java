import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;



class Reflection {

    public static Object generate(String name){

        System.out.println(name);

        try {
            Class<?> myClass = Class.forName(name);
            Class<?>[] classes = myClass.getClasses();
            Class<?>[] interfaces = myClass.getInterfaces();
            Constructor<?>[] constructors = myClass.getConstructors();


            for(var c : constructors){
                if(c.getParameterCount() == 0) {	// pre konstruktor bez parametrov
                    return c.newInstance();
                }
                if(c.getParameterCount() == 1) {	// pre konstruktor s jednym parametrom
                    var p = c.getParameters()[0];
                    return c.newInstance(p.getType().getConstructor(new Class[]{}).newInstance());
                }
                var params = c.getParameters();
            }

        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new Object();
    }

    public static void main(String[] args) {
        System.out.println(generate("Simple"));
        System.out.println(generate("java.lang.Integer"));
    }
}