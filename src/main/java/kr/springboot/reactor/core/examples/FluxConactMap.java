package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxConactMap {

    public static void main(String[] args) {
        Flux.just(1)
                .concatMap(i -> Flux.just("java"))
                .subscribe(System.out::println);
        // java
    }

}
