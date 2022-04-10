package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxBlockList {

    public static void main(String[] args) {

        /**
         * @delayElements
         *
         * 시간 작업 (Working with Time)
         *
         * 지연을 유발하고 싶을 때
         * onNext 신호 사이마다 : Mono#delayElement, Flux#delayElements
         *
         * 동기로 변환 (Going Back to the Synchronous World)
         * Note : Mono#toFuture 를 제외한 모든 메서드는 "non-blocking only"로 표시된 스케쥴러에서 호출되면 UnsupportedOperatorException 을 발생시킨다.
         *
         * @blockFirst
         * Flux<T>를 가지고 있고
         * 첫번 째 요소를 획득 할 때 까지 block : Flux#blockFirst
         * timeout과 함께 : Flux#blockFirst(Duration)
         *
         *
         * 출처: https://luvstudy.tistory.com/100 [파란하늘의 지식창고]
         */

        try {
            Integer first = Flux.just(1, 23, 12, 15)
                    .delayElements(Duration.ofMillis(200))
                    .blockFirst();
            System.out.println(first);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Integer first = Flux.just(1, 23, 12, 15)
                    .delayElements(Duration.ofSeconds(1))
                    .blockFirst(Duration.ofMillis(500));
            System.out.println(first);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * java.lang.IllegalStateException: Timeout on blocking read for 500000000 NANOSECONDS
         * 	at reactor.core.publisher.BlockingSingleSubscriber.blockingGet(BlockingSingleSubscriber.java:123)
         * 	at reactor.core.publisher.Flux.blockFirst(Flux.java:2623)
         * 	at kr.springboot.reactor.core.examples.FluxBlockList.main(FluxBlockList.java:43)
         */

    }

}
