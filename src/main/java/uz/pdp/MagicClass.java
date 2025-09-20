package uz.pdp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Aspect
@Service
public class MagicClass {


    @Before(value = "execution(String uz.pdp.TodoService.*(..))")
    public void hasAnyPermission() {
        System.out.println("checking permissions....");
    }

    @AfterReturning(value = "execution(* uz.pdp.TodoService.*(..))")
    public void logCreate() {
        System.out.println("creating a new todo");
    }


    @AfterThrowing(value = "execution(* uz.pdp.TodoService.*(..))")
    public void hasError() {
        System.out.println("Throwing error");
    }

    @Around(value = "execution(* uz.pdp.TodoService.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object object = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        Object[] args = joinPoint.getArgs();
        System.out.println("Time taken: " + (endTime - startTime) + " " + joinPoint.getSignature().getName() + " " + Arrays.toString(args));
        return object;
    }
}

// Aspect
// advice
// pointcut
// join point
