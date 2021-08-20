package task2;


import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {

        FizzBuzz fizzBuzz = new FizzBuzz(22);
        Runnable printFizz = () -> System.out.print("fizz ");
        Runnable printBuzz = () -> System.out.print("buzz ");
        Runnable printFizzBuzz = () -> System.out.print("fizzbuzz ");
        IntConsumer printNumber = i -> System.out.print(i + " ");

        new Thread(() -> fizzBuzz.fizz(printFizz)).start();
        new Thread(() -> fizzBuzz.buzz(printBuzz)).start();
        new Thread(() -> fizzBuzz.fizzBuzz(printFizzBuzz)).start();
        new Thread(() -> fizzBuzz.number(printNumber)).start();
    }

}
