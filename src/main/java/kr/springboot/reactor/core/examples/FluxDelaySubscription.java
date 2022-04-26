package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxDelaySubscription {

    /**
     * 시간 작업 (Working with Time)
     * 지연을 유발하고 싶을 때
     * subscription이 발생하기 전 : delaySubscription
     */
    public static void main(String[] args) {

//        Flux.just(5)
//                .delaySubscription(Duration.ofMillis(500),
//                        Schedulers.newSingle("delaySubscription"))
//                .subscribe(System.out::println, Throwable::printStackTrace);
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //....?

        Flux.range(1, 4)
                .delaySubscription(Flux.just(6).delayElements(Duration.ofMillis(300), Schedulers.newSingle("delay-publisher")))
                .subscribe(System.out::println);

    }

}
