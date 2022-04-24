package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

import java.util.stream.Stream;

/**
 * publisher 결합
 * 임의의 container 유형
 * 다른 side에 새 value가 도착할 때마다 : Flux#combineLatest
 * 좀어렵네
 */
public class FluxCombineLatest {

    public static void main(String[] args) {

        Flux.combineLatest(obj -> Stream.of(obj)
                                .allMatch(o -> (Boolean) o),
                        Flux.just(true),
                        Flux.just(true),
                        Flux.just(false))
                .subscribe(System.out::println);

        System.out.println("=======================");

        Flux.combineLatest(obj -> obj,
                        Flux.just(1),
                        Flux.just(2),
                        Flux.just(3),
                        Flux.just(4))
                .flatMap(Flux::fromArray)
                .all(o -> (int) o < 5)
                .subscribe(System.out::println);

    }

}
