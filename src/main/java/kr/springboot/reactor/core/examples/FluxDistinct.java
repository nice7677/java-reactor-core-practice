package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxDistinct {

    public static void main(String[] args) {

        Flux.just(1, 3, 4, 3, 1, 1, 1, 1, 6, 5, 3, 4, 2, 2, 2)
                .distinct()
                .sort() // ... 헉..
                .subscribe(System.out::println);

    }

}
