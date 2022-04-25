package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxConcatWith {

    public static void main(String[] args) {

        // 기존 배열에 5 넣음
        Integer[] iter = {1, 2, 3, 4};
        Flux.fromArray(iter)
                .concatWith(Flux.just(5))
                .subscribe(System.out::println);

    }

}
