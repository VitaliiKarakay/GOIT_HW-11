package task1;

/*
Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от начала сессии (запуска программы).
Другой ее поток выводит каждые 5 секунд сообщение  "Прошло 5 секунд".
Предусмотрите возможность ежесекундного оповещения потока, воспроизводящего сообщение, потоком, отсчитывающим время.
 */

public class Timer {
    public static volatile int i = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (i % 5 == 0)
                System.out.println("Прошло 5 секунд");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
                for (; ; i++) {
                    try {
                        Thread.sleep(1000L);
                        System.out.println(i);
//                        System.out.println(t1.getState());
                        } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        });
        t1.start();
        t2.start();
    }
}

