import api.AbstractNode;
import datastructures.*;
import ex4_java_client.AgentV1;
import ex4_java_client.Pokemon;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Ex4 {
    public static void main(String[] args) {
        //java -jar Ex4_Server_v0.0.jar 0
        Class c= new Pokemon(1d, 1d,5d, 1).getClass();
        for(Method m: Arrays.stream(c.getDeclaredMethods()).unordered().toList()){
            System.out.println("+"+m.getName());
        }
    }
}
