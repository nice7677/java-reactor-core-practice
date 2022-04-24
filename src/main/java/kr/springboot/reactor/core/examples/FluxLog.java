package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

public class FluxLog {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .log()
                .subscribe(System.out::println);

        /**
         * [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxRange.RangeSubscription)
         * [ INFO] (main) | request(unbounded)
         * [ INFO] (main) | onNext(1)
         * 1
         * [ INFO] (main) | onNext(2)
         * 2
         * [ INFO] (main) | onNext(3)
         * 3
         * [ INFO] (main) | onNext(4)
         * 4
         * [ INFO] (main) | onNext(5)
         * 5
         * [ INFO] (main) | onNext(6)
         * 6
         * [ INFO] (main) | onNext(7)
         * 7
         * [ INFO] (main) | onNext(8)
         * 8
         * [ INFO] (main) | onNext(9)
         * 9
         * [ INFO] (main) | onNext(10)
         * 10
         * [ INFO] (main) | onComplete()
         *
         */

        Flux.range(1, 5)
                .log("FluxLog", Level.INFO, SignalType.ON_NEXT)
                .subscribe(System.out::println);

        /**
         * [ INFO] (main) | onNext(1)
         * 1
         * [ INFO] (main) | onNext(2)
         * 2
         * [ INFO] (main) | onNext(3)
         * 3
         * [ INFO] (main) | onNext(4)
         * 4
         * [ INFO] (main) | onNext(5)
         * 5
         */

        System.out.println("=============");
        Flux.range(5, 3)
                .log("FluxLog", Level.INFO, false, SignalType.ON_NEXT)
                .subscribe(System.out::println);

        /**
         * is true
         * [ INFO] (main) | onNext(5) Flux.log ⇢ at kr.springboot.reactor.core.examples.FluxLog.main(FluxLog.java:62)
         * 5
         * [ INFO] (main) | onNext(6) Flux.log ⇢ at kr.springboot.reactor.core.examples.FluxLog.main(FluxLog.java:62)
         * 6
         * [ INFO] (main) | onNext(7) Flux.log ⇢ at kr.springboot.reactor.core.examples.FluxLog.main(FluxLog.java:62)
         * 7
         * is false
         * [ INFO] (main) | onNext(5)
         * 5
         * [ INFO] (main) | onNext(6)
         * 6
         * [ INFO] (main) | onNext(7)
         * 7
         */

    }

}
