package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class FluxConcatDelayError {

    public static void main(String[] args) {

        Flux<Integer> errorElement = Flux.just(1).flatMap(i -> {
            if (i > 1) return Flux.just(i);
            else return Flux.error(new IllegalStateException());
        }).doOnSubscribe(s -> System.out.println("error Element => doOnSubscribe"));

        Flux.concatDelayError(Flux.just(Flux.just(5), Flux.just(8), errorElement, Flux.just(7).doOnSubscribe(s -> System.out.println("next element => doOnSubscribe"))))
                .subscribe(System.out::println, Throwable::printStackTrace);

        /**
         * 5
         * 8
         * error Element => doOnSubscribe
         * next element => doOnSubscribe
         * 7
         * java.lang.IllegalStateException
         * 	at kr.springboot.reactor.core.examples.FluxConcatDelayError.lambda$main$0(FluxConcatDelayError.java:11)
         * 	at reactor.core.publisher.FluxFlatMap.trySubscribeScalarMap(FluxFlatMap.java:152)
         * 	at reactor.core.publisher.FluxFlatMap.subscribeOrReturn(FluxFlatMap.java:93)
         * 	at reactor.core.publisher.Flux.subscribe(Flux.java:8455)
         * 	at reactor.core.publisher.FluxConcatMap$ConcatMapDelayed.drain(FluxConcatMap.java:808)
         * 	at reactor.core.publisher.FluxConcatMap$ConcatMapDelayed.onSubscribe(FluxConcatMap.java:584)
         * 	at reactor.core.publisher.FluxArray.subscribe(FluxArray.java:53)
         * 	at reactor.core.publisher.FluxArray.subscribe(FluxArray.java:59)
         * 	at reactor.core.publisher.Flux.subscribe(Flux.java:8469)
         * 	at reactor.core.publisher.Flux.subscribeWith(Flux.java:8642)
         * 	at reactor.core.publisher.Flux.subscribe(Flux.java:8439)
         * 	at reactor.core.publisher.Flux.subscribe(Flux.java:8363)
         * 	at reactor.core.publisher.Flux.subscribe(Flux.java:8333)
         * 	at kr.springboot.reactor.core.examples.FluxConcatDelayError.main(FluxConcatDelayError.java:15)
         * 10:49:12 PM: Execution finished ':FluxConcatDelayError.main()'.
         */

        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept("==================================");

        Flux.concatDelayError(Flux.just(Flux.just(5), errorElement, Flux.just(8), Flux.just(7).doOnSubscribe(s -> System.out.println("next element => doOnSubscribe"))), false, 2)
                .subscribe(System.out::println, Throwable::printStackTrace);
        /**
         * 5
         * error Element => doOnSubscribe
         * java.lang.IllegalStateException
         */

    }

}
