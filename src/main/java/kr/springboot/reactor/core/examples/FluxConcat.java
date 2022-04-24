package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxConcat {

    public static void main(String[] args) {

        // 순서대로 merge를 해야할 땐 concat

        Flux<Flux<Integer>> array = Flux.just(Flux.just(1), Flux.just(3), Flux.just(8), Flux.just(5), Flux.just(6), Flux.just(10), Flux.just(21));

        Flux.concat(array, 2)
                .subscribe(System.out::println);

    }

}
