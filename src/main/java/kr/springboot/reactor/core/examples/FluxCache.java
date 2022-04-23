package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FluxCache {

    public static void main(String[] args) {

        cache();

    }


    private static void cache() {

        // 시간 history 설정
        Flux<Integer> cache = Flux.range(1, 5).cache(3);

        // 1st
        cache.publishOn(Schedulers.immediate()).subscribe(System.out::println);

        // 2nd
        cache.publishOn(Schedulers.immediate()).subscribe(System.out::println);

    }

}
