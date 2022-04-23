package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxCollectMap {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .collectMap(v -> "(" + v + ")")
                .subscribe(System.out::println);

        // {(1)=1, (5)=5, (4)=4, (3)=3, (2)=2}

    }

}
