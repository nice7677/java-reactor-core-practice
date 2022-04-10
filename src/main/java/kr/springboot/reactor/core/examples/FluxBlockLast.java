package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxBlockLast {

    // 1st Publisher
    private static Flux<Integer> one = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(1);
            emitter.complete();
        }
    });

    // 2nd Publisher
    private static Flux<Integer> two = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(2);
            emitter.complete();
        }
    });

    // 3rd Publisher
    private static Flux<Integer> three = Flux.create(emitter -> {
        if (!emitter.isCancelled()) {
            emitter.next(3);
            emitter.complete();
        }
    });

    public static void main(String[] args) {

        // delay and blocklast
        System.out.println("---------- delay and blocklast ----------");
        try {
            Integer last = Flux.just(1, 23, 12, 15)
                    .delayElements(Duration.ofMillis(200))
                    .blockLast();
            System.out.println(last);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // delay and blockLast on duration
        System.out.println("---------- delay and blockLast on duration ----------");
        try {
            Integer last = Flux.just(1, 23, 12, 15).delayElements(Duration.ofMillis(200)).blockLast(Duration.ofMillis(200));
            System.out.println(last);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // blockLast on duration
        System.out.println("---------- blockLast on duration ----------");
        try {
            Integer last = Flux.just(5, 7, 8).blockLast(Duration.ofMillis(100));
            System.out.println(last);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // delay per element and blockLast
        System.out.println("---------- delay per element and blockLast ----------");
        try {
            Integer last = Flux.concat(one.delayElements(Duration.ofMillis(200), Schedulers.newSingle("FirstSource")),
                            two.delayElements(Duration.ofMillis(500), Schedulers.newSingle("TwoSource")),
                            three.delayElements(Duration.ofSeconds(1), Schedulers.newSingle("ThreeSource")))
                    .blockLast();
            System.out.println(last);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // delay per element and blockLast on duration
        System.out.println("---------- delay per element and blockLast on duration ----------");
        try {
            Integer last2 = Flux.concat(one.delayElements(Duration.ofMillis(200), Schedulers.newSingle("FirstSource")),
                            two.delayElements(Duration.ofMillis(500), Schedulers.newSingle("TwoSource")),
                            three.delayElements(Duration.ofSeconds(1), Schedulers.newSingle("ThreeSource")))
                    .blockLast(Duration.ofMillis(300));
            System.out.println(last2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // delay per element and blockLast on duration
        System.out.println("---------- delay per element and blockLast on duration ----------");
        try {
            Integer last3 = Flux.concat(one.delayElements(Duration.ofMillis(200), Schedulers.newSingle("FirstSource")),
                            two.delayElements(Duration.ofMillis(500), Schedulers.newSingle("TwoSource")),
                            three.delayElements(Duration.ofSeconds(1), Schedulers.newSingle("ThreeSource")))
                    .blockLast(Duration.ofSeconds(2));
            System.out.println(last3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
