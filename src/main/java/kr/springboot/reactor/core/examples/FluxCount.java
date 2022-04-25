package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxCount {

    public static void main(String[] args) {

        Flux.range(1, 50)
                .count()
                .subscribe(System.out::println);
        // 50


    }

}
