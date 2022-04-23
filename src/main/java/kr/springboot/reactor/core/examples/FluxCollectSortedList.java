package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxCollectSortedList {

    public static void main(String[] args) {

        Flux.just("a", "W", "1", "9", "b", "5", "3")
                .collectSortedList()
                .subscribe(System.out::println);
        // [1, 3, 5, 9, W, a, b]

    }

}
