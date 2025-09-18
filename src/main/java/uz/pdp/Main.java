package uz.pdp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Hello' + ' World'");
        Expression arr = parser.parseExpression("{1,2,3,4}");
        Expression bigArr = parser.parseExpression("{{'a','b'},{'x','y'}}");
        Expression subs = parser.parseExpression("'abc'.substring(0)");

        String value = subs.getValue(String.class);
//        System.out.println(value);
        Club club = parser.parseExpression("new uz.pdp.Club()").getValue(Club.class);
        EvaluationContext context = new StandardEvaluationContext(club);
        Boolean isMember = parser.parseExpression("isMember('Muhammadkomil')").getValue(context, Boolean.class);


        Boolean instanceOf = parser.parseExpression("12 instanceOf T(Float)").getValue(context, Boolean.class);
        Boolean instanceOf2 = parser.parseExpression("12 instanceOf T(Integer)").getValue(context, Boolean.class);

        Boolean isNumber = parser.parseExpression("'123' matches '\\d{1,}'").getValue(context, Boolean.class);
        Boolean match = parser.parseExpression("12 < 1 and 22==22").getValue(context, Boolean.class);

//        System.out.println(match);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        MyClass my = (MyClass) applicationContext.getBean("myClass");

        System.out.println("my.name = " + my.name);
        System.out.println("my.age = " + my.age);
        System.out.println("my.match = " + my.match);
        System.out.println("my.arr[1] = " + my.arrValue);
        System.out.println("my.mapValue = " + my.mapValue);

    }
}