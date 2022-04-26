package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxDelayElements {

    public static void main(String[] args) {

        Flux.range(3, 3)
                .delayElements(Duration.ofMillis(1500),
                        Schedulers.newSingle("delayElements"))
                .log()
                .subscribe(System.out::println);
        /**
         * [ INFO] (main) onSubscribe(FluxConcatMap.ConcatMapImmediate)
         * [ INFO] (main) request(unbounded)
         * [ INFO] (delayElements-1) onNext(3)
         * 3
         * [ INFO] (delayElements-1) onNext(4)
         * 4
         * [ INFO] (delayElements-1) onNext(5)
         * 5
         * [ INFO] (delayElements-1) onComplete()
         */

    }

}
