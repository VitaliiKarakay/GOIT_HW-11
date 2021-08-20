package task2;

/*
Напишите программу, которая выводит в консоль строку, состоящую из чисел от  1 до n, но с заменой некоторых значений:
если число делится на 3 - вывести "fizz"
если число делится на 5 - вывести "buzz"
если число делится на 3 и на 5 - вывести "fizzbuzz"
Например, для n = 15, ожидаемый результат: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
Программа должна быть многопоточной, работать с 4 потоками:
Поток A вызывает fizz() чтобы проверить делимость на 3 и вывести fizz.
Поток B вызывает buzz() чтобы проверить делимость на 5 и вывести buzz.
Поток C вызывает fizzbuzz() чтобы проверить делимость на 3 и 5 и вывести fizzbuzz.
Поток D вызывает number() чтобы вывести число.
 */

import java.util.function.IntConsumer;

public class FizzBuzz {

    private final int n;
    private int currentNumber = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public synchronized void fizz(Runnable fizz) {
        while (currentNumber <= n) {
            if (currentNumber % 3 == 0 && currentNumber != 0) {
                fizz.run();
                currentNumber++;
                notifyAll();
            }
            else {
                waiter();
            }
        }
    }

    public synchronized void buzz(Runnable buzz) {
        while (currentNumber <= n) {
            if (currentNumber % 5 == 0 && currentNumber != 0) {
                buzz.run();
                currentNumber++;
                notifyAll();
            }
            else {
                waiter();
            }
        }
    }

    public synchronized void fizzBuzz(Runnable fizzBuzz) {
        while (currentNumber <= n) {
            if (currentNumber % 5 == 0 && currentNumber % 3 == 0 && currentNumber != 0) {
                fizzBuzz.run();
                currentNumber++;
                notifyAll();
            }
            else {
                waiter();
            }
        }
    }

    public synchronized void number(IntConsumer number) {

        while (currentNumber <= n) {
            if (currentNumber % 3 != 0 && currentNumber % 5 != 0) {
                number.accept(currentNumber);
                currentNumber++;
                notify();
            }
            else {
                waiter();
            }
        }
    }

    public void waiter() {

        try {
            wait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}
