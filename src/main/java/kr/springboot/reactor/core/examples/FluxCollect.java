package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class FluxCollect {

    /**
     * Flux를 합할때 쓴다 collect
     * @param args
     */
    public static void main(String[] args) {

        Mono<?> list = Flux.range(1, 5)
                .collect(ArrayList::new, ArrayList::add)
                .as(Mono::from);

        list.subscribe(System.out::println);

    }

}
