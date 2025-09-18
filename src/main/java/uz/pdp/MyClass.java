package uz.pdp;


import org.springframework.beans.factory.annotation.Value;

public class MyClass {

    @Value("#{'Muhammadkomli'.substring(1)}")
    String name;

    @Value("#{12}")
    Integer age;


    @Value("#{oybek.getValue() > 11}")
    Boolean match;


    @Value("#{oybek.arr[1]}")
    String arrValue;

    @Value("#{oybek.myMap['key']}")
    String mapValue;


}
