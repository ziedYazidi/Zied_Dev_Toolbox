import scala.Int;

import java.util.ArrayList;
import java.util.List;

public class Java17LTS {
    public static void main(String[] args) {
        Animal chien = new Chien();
        Animal chat = new Chat();
        overloading(chien);
        overloading(chat);

        User user = new User(1, "Zied");
        System.out.println(user);
        System.out.println(user.id());
        System.out.println(user.name());

        instanceOfPOC();

        System.out.println("""
                ----------------------
                Hello
                World
                \t tabulation \n
                "escaped"
                //escaped
                /escaped
                """);
    }

    public static void instanceOfPOC() {
        List<Object> list = List.of("zied", 29, true);
        for (Object element: list) {
            if (element instanceof String s) {
                System.out.println("String: " + s);
            } else if (element instanceof Integer i) {
                System.out.println("Integer: " + i);
            } else if (element instanceof Boolean b) {
                System.out.println("Boolean: " + b);
            } else {
                System.out.println("Other type");
            }
        }
    }
    record User(int id, String  name) {
        public User {
//            Custom validation
        }
    }

    private static void overloading(Animal animal) {
        animal.sayHello();
    }


    sealed interface Animal permits Chien, Chat {
        void sayHello();
    }

    static final class Chien implements Animal {
        @Override
        public void sayHello() {
            System.out.println("Wof");
        }
    }

    static final class Chat implements Animal {

        @Override
        public void sayHello() {
            System.out.println("Miawww");
        }
    }
}
