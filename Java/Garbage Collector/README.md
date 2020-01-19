# Garbage Collector

Garbage Collector(이하 GC)는 Heap 메모리 영역에서 더이상 참조하지 않는 객체를 탐색하여 자동으로 제거해주는 JVM의 기능 중 하나이다.

GC는 밑에서 자세히 다룰 Old 영역에서 GC가 일어날 경우 모든 쓰레드가 정지하게 되는 이를 'Stop The World'라고 표현하며 GC의 성능을 튜닝한다는 것은 이 Stop The World의 시간을 줄이는 것이기도 하다.

![KakaoTalk_Photo_2020-01-19-23-15-27](https://user-images.githubusercontent.com/43809168/72682574-b7a8d780-3b11-11ea-9de7-af18445522fd.jpeg)


위의 그림에서 Runtime Data Area는 5가지 영역으로 나뉘어져 있다.

여기서 Heap 영역에서 GC가 일어난다.

Heap 영역을 다시 자세히 봐보자.

![다운로드](https://user-images.githubusercontent.com/43809168/72682634-867cd700-3b12-11ea-942b-84eacab5cf5e.png)

Heap 영역은 다시 5개의 영역으로 나뉘어 진다.

# Heap Area

힙 영역은 5개로 나뉘어진다. Eden,survivor1, survivor2까지의 영역에 일어나는 GC를 Minor GC라고 하며, Old 영역에 일어나는 GC를 Major GC(Full GC)라고 부른다.

우선 **Minor GC**가 어떻게 작동하는지 부터 알아보자

# Minor GC

Minor GC는 Young 영역에서 일어나는 GC를 의미한다.

Young 영역은 Eden과 두개의 Surviovr으로 이루어져 있다.

지금부터 Minor GC의 동작과정을 살펴보자.

1. 새로 생성된 객체는 Eden 영역에 먼저 위치한다.

2. Eden 영역에서 GC가 일어나면 생존한 객체는 survivor 영역 중 하나로 이동한다.

3. Eden 영역에서 GC가 발생하면 이미 살아남은 객체가 존재하는 surviovr 영역으로 객체가 계속 쌓인다.

4. 하나의 surviovor영역이 가득차면 그 중 살아남은 객체를 다른 survivor 영역으로 이동한다. 가득찼던 surviovor영역은 아무 데이터가 없는 상태가 된다.

5. 이 과정을 반복하다 계속해서 살아남은 객체는 Old 영역으로 이동한다.

위 과정을 보면 알겠지만 survivor영역 둘 중 하나는 반드시 비어있는 상태로 남아있어야 한다.

만약 두 surviovor영역에 모두 데이터가 존재하거나 모두 사용량이 0이라면 이 시스템은 정상적인 상황이 아니라고 판단할 수 있다.

# Old 영역의 객체가 Young 영역을 참조하고 있는 경우라면?

![helloworld-1329-2](https://user-images.githubusercontent.com/43809168/72685413-1f205080-3b2d-11ea-9a6f-4e57cf43d403.png)

만약 Old 영역의 객체가 Young 영역의 객체를 참조하고 있는 경우를 처리하기 위해 512바이트 덩어리의 카드 테이블이 존재한다.

이 카드테이블은 Old 영역에 있는 객체가 Young 영역의 객체를 참조할때 마다 정보가 표시된다. 

Young 영역의 GC를 수행할 때에는 Old 영역에 있는 모든 객체 참조를 확인하지 않고 카드 테이블을 보고 GC 대상인지 식별한다.

# Old 영역 GC (Major GC, Full GC)

Old 영역 또한 데이터가 가득차면 GC를 실행한다.

앞서 이야기한 Stop-The-World 현상은 이 Full GC에서 발생한다.

때문에 GC 튜닝을 하기 위해서는 JVM이 제공하는 Full GC 방식들에 대해서 알 필요가 있다.

JDK 7버전 기준으로 5가지 GC가 존재한다.

- Serial GC
- Parallel GC
- Cuncurrent Mark & Sweep GC( CMS )
- G1(Garbage First) GC

이 중 Serial GC는 절대 사용하면 안된다.

왜냐하면 Serial GC는 과거 데스크톱의 CPU 코어가 1개이던 시절에 사용하기 위해 만들어진 방법이기 때문이다.

그럼 각 GC에 대해서 하나씩 알아보자

## Serial GC

mark-sweep-compact라는 알고리즘을 사용하여 GC를 한다.

이 알고리즘의 첫 단계는 Old 영역에 살아있는 객체를 식별(Mark)하는 것이다. 

그 후 Heap의 앞 부분부터 확인하여 살아 있는 것만 남긴다 (Sweep).

마지막에는 각 객체들이 연속되게 쌓이도록 힙의 가장 앞부분 부터 채워서 객체가 존재하는 부분과 객체가 없는 부분으로 나눈다.(Compact)

## Parallel GC

Parallel GC는 자바 7버전의 Default GC dlek.

Serial GC와 마찬가지로 mark-sweep-compact 알고리즘을 사용한다.

그러나 Parallel GC는 GC를 처리하는 쓰레드가 여러개다.

그래서 Serial GC보다 빠르다.

즉, Serial GC와 Parallel GC의 차이는 쓰레드의 개수 차이이다.

## CMS GC

![helloworld-1329-5](https://user-images.githubusercontent.com/43809168/72685425-3cedb580-3b2d-11ea-9b27-130b20154072.png)

위 그림은 Serial GC와 CMS GC의 절차를 비교한 그림이다.

그림에서 보듯이 CMS는 지금까지의 GC와는 다르게 좀 복잡하다.

우선 Initial Mark 단계에서는 클래스 로더에서 가장 가까운 객체 중 살아 있는 객체만 찾는다.

따라서 Stop-The-World가 매우 짧다.

그리고 Concurrent Mark 단계에서는 방금 살아있는 객체가 참조하고 있는 객체들을 따라가면서 확인한다.

이 단계의 특징은 다른 스레드가 실행 중인 상태에서 동시에 진행된다는 점이다. (!!)

그 다음 Remark 단계에서는 Concurrent Mark 단계에서 새로 추가되거나 참조가 끊긴 객체를 확인한다. 역시나 여기서도 Stop-The-World가 일어나지만 짧다. 

마지막으로 Concurrent Sweep 단계에서는 참조가 끊긴 객체를 제거한다. 이 작업 또한 스레드가 실행되고 있는 상황에서 같이 진행된다.

CMS는 이러한 단계로 진행되기 때문에 Stop-The-World가 매우 짧다.

모든 어플리케이션의 응답속도가 매우 중요할 때 CMS GC를 사용하며 Low Latency GC라고도 불린다.

하지만 이 업계는 Trade Off를 철저히 지킨다.

장점이 있는 만큼 단점도 있는 법.

단점은 두가지이다.

1. 다른 GC보다 메모리와 CPU를 더 많이 사용한다.

당연하다 그럴 수 밖에 없는 것이 GC의 진행 과정이 어플리케이션이 구동되고 있을 때 같이 진행되기 때문에 더 많은 연산과 메모리를 필요로 할 수 밖에 없다.

2. Compaction 단계가 제공되지 않는다.

Compcation 단계가 제공되지 않는다는 말은 다시 말하면 메모리가 파편화 될 수 있기 때문에 Compaction 작업을 자주 실행하면 다른 GC의 Stop-The-World보다 더 긴 시간을 멈춰있어야 하기 때문에 Compaction이 얼마나 자주, 오랫동안 수행되는지를 판단해서 사용해야 한다.

## G1 GC

자바 7에서 처음 등장한 GC이자, 자바 9의 Default GC인 G1 GC이다.

![스크린샷 2020-01-20 오전 2 54 37](https://user-images.githubusercontent.com/43809168/72685723-36ad0880-3b30-11ea-99be-bb00d487ffac.png)

G1 GC를 이해하려면 지금까지의 Young 영역과 Old 영역에 대한 것들을 잊어야 한다.

G1 GC는 바둑판의 각 영역에 객체를 할당하고 GC를 실행한다.

그러다가 해당 영역이 꽉 차면 다른 영역에서 객체를 할당하고 GC를 실행한다. 즉 지금까지 봤던 Young 영역의 세가지 영역에서 데이터가 Old 영역으로 이동하는 단계가 사라진 GC 방식이다.

Young, Old 영역을 따로 나누지 않고 사용하며 Young 영역의 GC는 parallel GC를 사용한다.

Old GC는 CMS와 유사한 방식을 사용한다.

또한 G1 GC는 young,Old GC 동안 live 영역으로 이동하는데, 이때 virtual memory address로 이동하기 때문에 객체들 사이에 빈공간이 생기는 메모리 파편화 현상이 발생하지 않는다.

Java Heap이 많이 필요한 경우에 G1을 사용하면 좋다.

Parallel GC와 CMS GC의 절충안 정도의 GC  Algorithm을 사용하기 때문에 준수한 throughput, latency를 제공한다는 특징이 있다.

# Java 9 버전에서 왜 G1 GC는 Default가 되었을까?

Java 7,8 버전에서 Default Garbage Collector는 Parallel GC 였다.

그러나 7버전에서 처음 등장한 G1 GC가 9버전부터는 Default로 되었다.

앞서 살펴본 것과 같이 G1 GC는 Parallel GC와 CMS GC의 절충안이라고 하였다. 다시 말해 Parallel GC는 처리량은 많지만 그만큼의 Stop-The-World 시간을 감내해야 했다.

그러나 G1 GC는 8 버전에서 충분한 성능 개선이 이루어졌고, JDK 9에서 추가 개선이 되면서 G1은 완벽하게 사용해도 된다고 판단하였기 때문에 자바는 9버전부터 G1을 Default Garbage Collector로 지정하였다.

# Z GC의 등장

Java 11버전 부터 등장한 ZGC는 "가장 짧은 Stop-The-World"를 위해 만들어졌다.

아무리 큰 힙이라도 GC의 대기시간을 10m/s를 넘지 않도록 약속하는 것을 의미한다.

튜닝도 쉽다고 한다.

![스크린샷 2020-01-20 오전 3 16 34](https://user-images.githubusercontent.com/43809168/72686015-71647000-3b33-11ea-8f35-72d25c9b38a3.png)

ZGC는 메모리를 ZPages라고 하는 영역으로 나눈다.

ZPage는 동적으로 생성과 제거가 가능하다.

2MB의 배수인 G1 GC와는 달리 크기도 조정 가능하다.

총 세가지 크기로 나눠지는데

- Small (2MB)
- Medium (32MB)
- Large (N X 2 MB)

로 이루어진다.

위 그림은 ZGC의 힙영역을 보여주는 그림이다.

다른 GC와 달리 ZGC는 실제 힙 영역이 더 큰 힙 주소 공간(가상 메모리 포함)에 매핑될 수 있다고 한다.

이는 메모리 조각화 문제를 해결하는데 있어 중요한 부분이다.

결국 G1 GC보다 더 짧은 latency를 가지면서 G1보다 크게 뒤쳐지지 않는 처리량을 갖는 것이 이 ZGC의 목표다.

# Reference

https://d2.naver.com/helloworld/1329

https://jeong-pro.tistory.com/148?category=793347

https://preamtree.tistory.com/118

https://stackoverflow.com/questions/46377561/why-g1-is-default-garbage-collector-for-java-9

https://hub.packtpub.com/getting-started-with-z-garbage-collectorzgc-in-java-11-tutorial/