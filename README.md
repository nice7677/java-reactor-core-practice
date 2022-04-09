# JAVA REACTOR CORE PRACTICE

---

## 참고 [Flux, Mono](https://brunch.co.kr/@springboot/154), [Subscriber](https://brunch.co.kr/@springboot/155)

---

## Flux 를 생성하는 방법 

Flux 를 생성하는 가장 간단한 방법은, Flux 클래스에서 제공하는 팩토리 메서드 를 사용하는 것이다. 대표적으로 아래와 같은 팩토리 메서드를 제공한다.

- just
- range
- fromArray, fromIterable, fromStream
- empty

Flux 는 subscribe 가 실행하기 전까지는 어떤 일도 발생하지 않는다

Publisher (발행자)는 구독이 되었을 경우에만 데이터를 Subscriber(구독자)에게 전달한다.

Subscriber(구독자)가 Publisher 에 구독을 하는 과정은, Publisher(발행자)에 정의된 subscribe() 메서드를 사용한다.

이때, 매개변수로 Consumer 함수를 전달할 수 있는데, Consumer 함수는 데이터 전달을 해서 Subscriber의 onNext 이벤트가 발생을 했을때 실행되는 함수이다.

---

### just 

이벤트 순서

`onSubscribe --> request --> onNext --> onNext --> onComplete` 

### range

range 메서드는 int 범위를 지정하여 순차적인 데이터를 생성해주는 Flux 의 메서드이다. 

이벤트 순서

`onSubscribe --> request --> onNext --> onComplete` 

### fromXXX (ex. fromArray, fromIterable, fromStream)

fromArray, fromIterable, fromStream 메서드를 사용하면 Array, Iterable, Streams 를 사용해서 Flux 를 생성할 수 있다.

### empty

empty 메서드를 사용하면 아무값도 전달하지 않는, Empty Flux를 만들 수 있다.

---

## Flux, Laziness

Flux 와 Mono 는 subscribe() 가 실행하기 전까지는 그 어떤 데이터도 전달하지 않는다

---

## Mono 를 생성하는 방법

- just
- empty

---

## Lifecycle hooks

- doOnSubscribe

- doOnRequest

- doOnNext

- doOnError

- doOnComplete

- doOnCancle

- doOnEach

---

## Flux.subscribe()

Flux 는 Publisher 의 구현체이다. 

일반적인 Reactive Streams 에서의 Publisher 는 subscribe 를 실행할 때 subscriber 를 등록해준다. 

Flux 에서 제공하는 팩토리 메서드는 subscriber 를 등록해주는 메서드도 있는 반면에, subscriber 를 매개변수로 등록하지 않는 함수도 존재한다. 

subscriber 가 필요없어서가 아니라, 내부 로직에서 자동으로 subscriber 를 만들어 준다. 

개발자가 따로 subscriber 를 등록하지 않아도 된다.