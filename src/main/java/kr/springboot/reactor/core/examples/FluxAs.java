package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAs {

    /**
     * 'as' 는 Mono<T> 타입을 얻는다
     *
     * @param args
     */
    public static void main(String[] args) {

        Flux.just("reactor-core")
                .as(Mono::from)
                .subscribe(System.out::println);

        Mono<String> mono = Flux.just("Moooooooooooono")
                .as(Mono::from);

        mono.subscribe(System.out::println);

    }

}
