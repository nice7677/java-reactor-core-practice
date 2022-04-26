package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxDelayUntil {

    public static void main(String[] args) {

        Flux.just(1, 2, 3)
                .delayUntil(i -> Flux.just(i).delayElements(Duration.ofMillis(300),
                        Schedulers.newSingle("flux delay until")))
                .subscribe(System.out::println);
        // 1,2,3....
    }

}
