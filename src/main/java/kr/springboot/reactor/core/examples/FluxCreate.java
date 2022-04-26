package kr.springboot.reactor.core.examples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class FluxCreate {

    /**
     * Flux.generate()는 Subscriber로부터 요청이 있을 때에 next 신호를 발생하는 Flux를 생성한다.
     * 즉 pull 방식의 Flux를 생성한다. 이는 단순하지만 데이터 발생을 비동기나 push 방식으로 할 수 없다는 제약도 있다.
     * Flux.create()를 사용하면 이런 제약 없이 비동기나 push 방식으로 데이터를 발생할 수 있다.
     * 좀 어렵다 공부 필요ㅎ
     */
    // Flux.create()를 이용한 pull 방식 메시지 생성
    public static void main(String[] args) {

        Flux.create(sink -> {
            if (sink.isCancelled()) return;
            sink.onRequest(n -> System.out.println("onRequest => " + n));
            Integer[] source = {1, 2, 3, 4, 5, 6};
            for (int i = 0, len = source.length; i < len && !sink.isCancelled(); i++) {
                sink.next(source[i]);
            }
            sink.complete();
        }, FluxSink.OverflowStrategy.BUFFER).subscribe(System.out::println);
        /**
         * onRequest => 9223372036854775807
         * 1
         * 2
         * 3
         * 4
         * 5
         * 6
         */
    }

}
