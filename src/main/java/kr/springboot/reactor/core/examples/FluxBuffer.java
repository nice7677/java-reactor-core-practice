package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxBuffer {

    public static void main(String[] args) {
        FluxBuffer fluxBuffer = new FluxBuffer();
        System.out.println("------- bufferMaxSizeSkip -------");
        fluxBuffer.bufferMaxSizeSkip();
        System.out.println("------- bufferMaxSizeEquals -------");
        fluxBuffer.bufferMaxSizeEquals();
        System.out.println("------- bufferMaxSizeEquals -------");
        fluxBuffer.bufferCompanion();
        System.out.println("------- bufferDuration -------");
        fluxBuffer.bufferDuration();

    }

    private void bufferMaxSizeSkip() {

        /**
         * flatMap()에 전달한 함수를 보면 Integer 값을 받아서 다시 1부터 해당 값 개수만큼의 숫자를 생성하는 Flux를 생성한다.
         * 아래 코드를 보면 다음과 같은 변환이 발생한다.
         *
         * range --
         * 1 -> Flux.range(1, 1) : [1] 생성
         * 2 -> Flux.range(1, 2) : [1, 2] 생성
         * 3 -> Flux.range(1, 3) : [1, 2, 3] 생성
         *
         * buffer --
         * Flux<T>와 버퍼 요소를 경계 내에서 함께 나누고 싶을 때
         * List로
         * size 별 : buffer(int)
         * 겹치거나 삭제하려는 경우 : buffer(int, int)
         *
         */
        Flux.range(1, 100)
                .buffer(5, 3)
                .subscribe(System.out::println);
        // [1, 2, 3, 4, 5]
        // [4, 5, 6, 7, 8]
        // [7, 8, 9, 10, 11]
        // ...

    }

    private void bufferMaxSizeEquals() {

        Flux.range(1, 100)
                .buffer(5, 5)
                .subscribe(System.out::println);

        // [1, 2, 3, 4, 5]
        // [6, 7, 8, 9, 10]
        // [11, 12, 13, 14, 15]
        // ...

    }

    private void bufferCompanion() {

        /**
         * delayElements --
         * 시간 작업 (Working with Time)
         * 지연을 유발하고 싶을 때
         * onNext 신호 사이마다 : Mono#delayElement, Flux#delayElements
         *
         * publishOn --
         * publishOn을 이용한 신호 처리 쓰레드 스케줄링
         * publishOn() 메서드를 이용하면 next, complete, error 신호를 별도 쓰레드로 처리할 수 있다.
         * map(), flatMap() 등의 변환도 publishOn()이 지정한 쓰레드를 이용해서 처리한다.
         * publishOn, subscribeOn 은 scheduler 의 팩토리 메서드
         *
         */
        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(50))
                .buffer(
                        Flux.range(1, 3) // [1,2,3]
                                .delayElements( // 지연
                                        Duration.ofMillis(300),
                                        Schedulers.newSingle("inner")
                                ) // 1... 2... 3...
                )
                .publishOn(Schedulers.immediate())
                .subscribe(System.out::println);

        // [1, 2, 3, 4, 5]
        // [6, 7, 8, 9, 10, 11]
        // [12, 13, 14, 15, 16, 17]

    }

    private void bufferDuration() {

        Flux.range(1, 100)
                .delayElements(Duration.ofMillis(10), Schedulers.newSingle("FluxBuffer"))
                .buffer(Duration.ofMillis(50), Duration.ofMillis(300))
                .subscribe(System.out::println);

        /**
         * [1, 2, 3, 4]
         * [1, 2, 3, 4, 5]
         * [27, 28, 29, 30, 31]
         * [6, 7, 8, 9, 10, 11]
         * [53, 54, 55, 56, 57]
         * [12, 13, 14, 15, 16, 17]
         * [80, 81, 82, 83]
         */

    }

}
