package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxCheckPoint {

    /**
     * Exception Helper
     * 체크포인트
     * 시퀀스가 신호를 발생하는 과정에서 익셉션이 발생하면 어떻게 될까?
     * 시퀀스가 여러 단게를 거쳐 변환한다면 어떤 시점에 익셉션이 발생했는지 단번에 찾기 힘들 수도 있다.
     * 이럴 때 도움이 되는 것이 체크포인트이다. 다음은 체크포인트 사용 예이다.
     * @param args
     */
    public static void main(String[] args) {

        Flux<Integer> check = Flux.just(2)
                .flatMap(i -> {
                    return (i == 2) ? Flux.error(new IllegalStateException()) : Flux.just(i);
                });

        check.checkpoint("Flux Check Point", false)
                .subscribe(System.out::println, Throwable::printStackTrace);

        check.checkpoint("Flux Check Point", true)
                .subscribe(System.out::println, Throwable::printStackTrace);

    }

}
