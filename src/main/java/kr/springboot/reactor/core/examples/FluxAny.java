package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxAny {

    private static Integer[] source = {1, 1, 1, 1, 1};

    private static Integer[] source2 = {1, 1, 1, 2, 1};

    private static Integer[] source3 = {};

    public static void main(String[] args) {

        Flux.fromArray(source)
                .any(i -> i == 1)
                .subscribeOn(Schedulers.immediate())
                .publishOn(Schedulers.immediate())
                .subscribe(System.out::println, Throwable::printStackTrace);

        Flux.fromArray(source2)
                .any(i -> i == 2)
                .subscribe(System.out::println, Throwable::printStackTrace);

        Flux.fromArray(source3)
                .any(i -> i == 3)
                .subscribe(System.out::println);

    }


}
