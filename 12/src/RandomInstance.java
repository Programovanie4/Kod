import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Random;

public class RandomInstance {

    // inspiroval som sa kodom zo stack overflow
    // https://codereview.stackexchange.com/questions/151860/generate-random-instance-of-given-class

    private static final Random rnd = new Random();

    public static Object getInstance(Class clazz) {
        try {

            int depth = 0;
            while (clazz.getComponentType() != null) { // ak je array tak ma != null component type
                clazz = clazz.getComponentType();
                depth++;
            }

            Constructor[] constructors = clazz.getConstructors();

            int index = (clazz == String.class) ? 9 : rnd.nextInt(constructors.length); // string ma vela konstruktorov, 9 je new String(String)
            Type[] types = constructors[index].getParameterTypes();

            if (types.length == 0) // ak je empty constructor
                return clazz.getDeclaredConstructor().newInstance();

            Object[] args = new Object[types.length];

            for (int i = 0; i < types.length; i++) {
                args[i] = getRandomVal(types[i].getTypeName(), clazz);
            }

            if (depth == 0) {
                return constructors[index].newInstance(args); // newInstance mozem podsunut varargs
            } else {
                return getArray(depth, constructors[index].newInstance(args));
            }



        } catch (Exception e) {
            return null;
        }
    }

    // moj pokus spravit vnorene polia ale vracia to objekt typu Object takze nic moc

    private static Object getArray(int depth, Object obj) {
        if (depth == 0)
            return obj;
        else {
            Object[] object = new Object[1];
            object[0] = obj;
            return getArray(depth-1, object);
        }
    }

    public static Object getRandomVal(String type, Class clazz) {
        System.out.println(type);
        switch (type) {
            case "java.lang.Integer":
            case "int":
                return rnd.nextInt();
            case "java.lang.String":
                if (clazz == String.class) {
                    char[] chars = new char[rnd.nextInt(100)]; // do 100 znakov
                    for (int i = 0; i < chars.length; i++) {
                        chars[i] = (char)(rnd.nextInt('z' - 'A') + 'A');
                    }
                    return new String(chars);

                } else {
                    return "" + rnd.nextInt();
                }
            case "java.lang.Long":
            case "long":
                return rnd.nextLong();
            case "java.lang.Double":
            case "double":
                return rnd.nextDouble();
            case "java.lang.Boolean":
            case "boolean":
                return rnd.nextBoolean();
            default:
                return null;
        }

    }

    public static void main(String[] args) {
//        Object obj = RandomInstance.getInstance(Integer.class);
//        System.out.println(obj);
//        Object obj2 = RandomInstance.getInstance(String.class);
//        System.out.println(obj2);
//        Object obj3 = RandomInstance.getInstance(Double.class);
//        System.out.println(obj3);
//        Object obj4 = RandomInstance.getInstance(Boolean.class);
//        System.out.println(obj4);
//        Object obj5 = RandomInstance.getInstance(Integer.class);
//        System.out.println(obj5.getClass());
//        System.out.println(obj5);
        Object obj55 = RandomInstance.getInstance(Integer[].class);
        System.out.println(obj55.getClass());

//        Object obj6 = RandomInstance.getInstance(Integer[][][].class);
//        System.out.println(obj6.getClass());
//        System.out.println(obj6);
//        Object obj7 = RandomInstance.getInstance(String[][][].class);
//        System.out.println(obj7);



    }
}