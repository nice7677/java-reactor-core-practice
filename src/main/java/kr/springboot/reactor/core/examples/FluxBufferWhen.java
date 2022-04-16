package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxBufferWhen {

    private Integer[] companion = {1, 2, 3, 4};

    /**
     * 상태에 따라 프로그래밍 방식으로 이벤트를 생성하는 경우
     * 동기적으로 하나씩 : Flux#generate
     * 비동기 (또는 동기)적으로 한번에 여러개 생성 : Flux#create (Mono#create)
     */

    // first
    private Flux<Integer> one = Flux.create(emitter -> {

        int len = companion.length;

        for (int i = 0; i < len && !emitter.isCancelled(); i++) {
            emitter.next(companion[i]);
        }

        if (!emitter.isCancelled()) {
            emitter.complete();
        }

    });

    // 2nd
    private Flux<Integer> two = Flux.create(emitter -> {

        int len = companion.length;
        for (int i = 0; i < len && !emitter.isCancelled(); i++) {
            emitter.next(companion[i]);
        }

        if (!emitter.isCancelled()) {
            emitter.complete();
        }

    });

    // 3rd
    private Flux<Integer> three = Flux.create(emitter -> {

        int len = companion.length;
        for (int i = 0; i < len && emitter.isCancelled(); i++) {
            emitter.next(companion[i]);
        }

        if (!emitter.isCancelled()) {
            emitter.complete();
        }

    });

    // 4th
    private Flux<Integer> four = Flux.create(emitter -> {

        int len = companion.length;
        for (int i = 0; i < len && !emitter.isCancelled(); i++) {
            emitter.next(companion[i]);
        }

        if (!emitter.isCancelled()) {
            emitter.complete();
        }

    });

    public static void main(String[] args) {
        FluxBufferWhen fluxBufferWhen = new FluxBufferWhen();

        /**
         * concat --
         * publisher 결합
         * 순서대로 결합 : Flux#concat 또는 .concatWith(other)
         * 남은 publisher가 방출될 때까지 오류를 지연시키는 경우 : Flux#concatDelayError
         * 다음 publisher를 subscribe : Flux#mergeSequential
         *
         * bufferWhen
         * Flux<T>와 버퍼 요소를 경계 내에서 함께 나누고 싶을 때
         * List로
         * control publisher에서 onNexts로 표시되는 임의 경계에 의해 : buffer(Publisher), bufferWhen
         */
        Flux.concat(
                        fluxBufferWhen.one.delayElements(Duration.ofMillis(100), Schedulers.newSingle("one")),
                        fluxBufferWhen.two.delayElements(Duration.ofMillis(90), Schedulers.newSingle("two")),
                        fluxBufferWhen.three.delayElements(Duration.ofMillis(150), Schedulers.newSingle("three")),
                        fluxBufferWhen.four.delayElements(Duration.ofMillis(200), Schedulers.newSingle("four")))
                .bufferWhen(
                        Flux.range(2, 8)
                                .delayElements(Duration.ofMillis(200)),
                        i -> Flux.just(true).delayElements(Duration.ofMillis(200)))
                .subscribe(System.out::println);
        /**
         * [2, 3]
         * [4, 1, 2]
         * [3, 4]
         * [1]
         * [2]
         * [3]
         * [4]
         */

    }

}
