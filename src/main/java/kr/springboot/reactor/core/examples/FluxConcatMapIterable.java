package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class FluxConcatMapIterable {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .concatMapIterable(i -> {
                    List<String> iter = new ArrayList<>();
                    iter.add(i + "#");
                    return iter;
                })
                .subscribe(System.out::println, Throwable::printStackTrace);
        /**
         * 1#
         * 2#
         * 3#
         * 4#
         * 5#
         */


    }

}
