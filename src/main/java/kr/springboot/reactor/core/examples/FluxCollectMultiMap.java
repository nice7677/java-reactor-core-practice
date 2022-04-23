package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

import java.util.TreeMap;

public class FluxCollectMultiMap {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .collectMultimap(i -> "((" + i + "))")
                .subscribe(System.out::println);
        // {((4))=[4], ((5))=[5], ((2))=[2], ((3))=[3], ((1))=[1]}

        Flux.range(1, 5)
                .collectMultimap(i -> "(" + i + ")", v -> v + "$")
                .subscribe(System.out::println);
        // {(1)=[1$], (5)=[5$], (4)=[4$], (3)=[3$], (2)=[2$]}

        Flux.range(1, 5)
                .collectMultimap(i -> "(" + i + ")", v -> v + "#", () -> new TreeMap<>())
                .subscribe(System.out::println);
        // {(1)=[1#], (2)=[2#], (3)=[3#], (4)=[4#], (5)=[5#]}

    }

}
