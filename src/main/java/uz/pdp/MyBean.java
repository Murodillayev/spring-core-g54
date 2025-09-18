package uz.pdp;

import javax.xml.parsers.SAXParser;
import java.util.HashMap;
import java.util.Map;

public class MyBean {


    Integer value = 12;


    public String[] arr = {"a", "b", "c"};
    public Map<String, String> myMap = Map.of(
            "key",
            "map-value"
    );

    public Integer getValue() {
        return value;
    }
}
