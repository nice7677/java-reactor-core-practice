package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxDefer {

    private static Integer[] hotSource = {1, 5, 8, 7};

    /**
     * defer method
     * 지연된 instance를 publisher에 등록하기 위해 사용
     */
    public static void main(String[] args) {

        Flux<Integer> defer = Flux.defer(() -> Flux.fromArray(hotSource));

        defer.subscribe(System.out::println);

    }

}
