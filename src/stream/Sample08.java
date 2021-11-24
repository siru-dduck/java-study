package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class Sample08 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Box", "Simple", "Complex", "Robot");

        BinaryOperator<String> longStringOp = (s1, s2) ->
                s1.length() > s2.length()? s1 : s2;

        String longStr = list.stream()
                .reduce("", longStringOp);
        System.out.println(longStr);
    }
}
