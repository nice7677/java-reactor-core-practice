package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;

public class FluxBufferUntil {

    public static void main(String[] args) {


        /**
         * Flux<T>와 버퍼 요소를 경계 내에서 함께 나누고 싶을 때
         * List로
         * 임의의 기준 경계 별 : bufferUntil(Predicate)
         * 다음 버퍼에서 경계를 유발한 요소를 입력 : .bufferUntil(predicate, true)
         * 일치한 동안 버퍼링하고 경계를 유발한 요소를 삭제 : bufferWhile(Predicate)
         */
        Flux.range(1, 10) // 1 ~ 10 range
                .bufferUntil(i -> (i / 5) == 0, false)
                .subscribe(System.out::println);
        // [1]
        // [2]
        // [3]
        // [4]
        // [5, 6, 7, 8, 9, 10]
        System.out.println("-------------------------------");

        Flux.range(1, 20)
                .bufferUntil(i -> (i % 3) == 0, false)
                .subscribe(System.out::println);
        // [1, 2, 3]
        // [4, 5, 6]
        // [7, 8, 9]
        // [10, 11, 12]
        // [13, 14, 15]
        // [16, 17, 18]
        // [19, 20]
        System.out.println("-------------------------------");

        Flux.range(1, 20)
                .bufferUntil(i -> (i % 3) == 0, true)
                .subscribe(System.out::println);
        // [1, 2]
        // [3, 4, 5]
        // [6, 7, 8]
        // [9, 10, 11]
        // [12, 13, 14]
        // [15, 16, 17]
        // [18, 19, 20]

    }


}
