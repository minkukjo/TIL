# 동기화

하나의 자원에 여러 프로세스, 쓰레드가 접근하려고 할때 동기화 문제는 발생 합니다.

## Critical Section

OS에서 임계영역은 매우 중요한 영역입니다.

**Critical Section**이란 멀티 프로세스 환경에서 둘 이상의 프로세스가 동시에 접근해서는 안되는 공유 자원의 영역을 의미합니다.

이때 여러 프로세스가 하나의 자원에 접근해서 발생하는 문제를 **Race Condition**이라고 하는데, 이는 하나의 자원에 여러 프로세스들이 경쟁적으로 점유하려고 할때 발생하는 현상을 일컫습니다.

## Lock

이러한 동기화 문제를 해결하는 가장 쉬운 방법은 **Lock**을 거는 방법 입니다.

특정 프로세스가 **Critical Section**에 접근 중일 때 다른 프로세스가 접근하지 못하게 Lock을 거는 것 입니다.

## Mutex

Critical Section에 동시에 접근하지 못하도록 막는 방법 입니다.

Mutex는 공유 리소스 자원에 접근을 제어하기 위해 Locking과 UnLocking을 사용합니다.

## Semaphores

Mutex와는 다르게 하나의 자원에 여러 프로세스가 접근할 수 있습니다.

재밌는 점은, Semaphore에는 Binary Semaphore라는 것이 존재하는데 이는 접근할 수 있는 프로세스가 1개로 한정되어 있습니다.

그렇죠 Mutext와 같습니다. 그래서 Semaphore는 Mutex가 될 수 있지만, Mutex는 Semaphore가 될 수 없습니다.

또한 Mutex는 소유할 수 있는 반면 Semaphore는 소유할 수 없습니다.

Mutex는 소유주가 책임을 지지만 Semaphore는 그렇지 않죠.



## Reference

https://about-myeong.tistory.com/34

https://jwprogramming.tistory.com/13