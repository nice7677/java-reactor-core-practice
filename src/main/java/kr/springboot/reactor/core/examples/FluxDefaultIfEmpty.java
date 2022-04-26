package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxDefaultIfEmpty {

    /*
        empty sequence를 가지고 있는 경우
        대체 값을 원하는 경우 : defaultIfEmpty
     */
    public static void main(String[] args) {

        Flux.<String>empty().defaultIfEmpty("#$")
                .subscribe(System.out::println);
        // #$
    }

}
