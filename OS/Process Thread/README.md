# Process & Thread

지금부터 가장 대표적인 면접 질문(?)으로 알려져 있는

Process와 Thread를 알아보도록 하겠습니다.

**Process란**

- 컴퓨터에서 실행중인 **Program**
- **Process**는 **Program**의 **Instance**이다.
- OS로 부터 CPU 자원을 할당받는 작업의 단위
- Process 간의 메모리는 독립적인 공간을 할당받는다.

**Process 내부**

![process_components](https://user-images.githubusercontent.com/43809168/69495971-47fedd80-0f10-11ea-9e86-524f00a4f0b9.jpg)

- Stack : 지역 변수, Return address 등을 포함한 영역
- Heap : 동적 할당 메모리가 자리잡는 영역 
- Data : 전역, Static 변수가 저장되는 영역
- Text : 현재 활동중인 Program Counter( 다음 실행될 기계어 코드의 주소를 지정하는 레지스터 ) 값, Processor register( 매우 빠른 기억 저장 공간 )의 내용물을 포함한다.

**Thread란**

- Thread는 경량 Process라고도 불린다.
- Thread는 하나의 Process안에 존재할 수 있으며 Process 외부에 존재할 수 없다.
- Process의 수행 흐름의 단위
- 일반적으로 Process가 최초로 생성되면 하나의 Thread를 갖고 있고, 이 Thread를 보통 Main Thread라고 부른다.

**Thread 내부**

![다운로드](https://user-images.githubusercontent.com/43809168/69496147-551ccc00-0f12-11ea-99bf-8c031ed99f21.jpeg)

- 하나의 Process안에는 여러개의 Thread가 존재할 수 있다.
- 각각의 Thread는 고유한 register와 stack영역을 가지며 Process의 text(code), data, heap 영역은 공유한다.


## Multi Process Vs Multi Thread

**Multi Process**

하나의 응용 프로그램을 여러 Process가 나누어 처리하는 기술

**장점**
- 각각의 프로세스는 독립된 메모리 공간을 갖기 때문에, 서로에게 영향을 미치지 않는다. 즉, 안정적이다.

**단점**

- Context Switching에 비용이 많이 든다.
	- Process는 Context Switching이 되어 새로운 Process가 실행될 때 캐시 메모리를 비우게 되는데 이것이 매우 많은 비용이 들게 된다. 
	- Context Switching이 일어나는 동안 CPU는 어떠한 작업도 할 수 없는데 이것 또한 오버헤드로 이어진다.
	- 서로 다른 Process간에는 메모리를 공유하지않기 때문에 IPC라고 불리우는 통신기법을 사용하는데 이것이 매우 복잡하다.

Multi Process의 단점을 극복하는 방법으로 Multi Thread를 사용할 수 있다.

**Multi Thread**

하나의 응용 프로그램 안에 여러 Thread를 만들어 처리량을 높이는 기술

**장점**

- 시스템 자원 효율 증가 ( Process를 여러개 만드는 것 보다 Thread를 여러개 만드는 것이 더 저렴하다. )
- 시스템 처리량 증가
	- Thread는 프로세스 내에서 자원을 공유하기 때문에 시스템 자원 소모가 줄어든다.
- 더 빠른 Context Switching
	- Thread 또한 Process와 마찬가지로 Context Switching을 한다.
	- Process의 Context Switching 보다 Thread의 Context Switching이 더 빠르다.

**단점**

- 하나의 Thread에서 에러가 나면 프로세스가 영향을 받는다.
- 디버깅이 힘들다.
- 동기화 문제가 발생할 수 있다.

## Multi Thread Context Switching

그러며 왜 Multi Thread의 Context Switching이 Multi Process의 Context Switching 보다 더 빠른 성능을 보이는 걸까요?

그 이유는 **Cache**에 있습니다.

Multi Process의 경우 Context Switching이 발생하면 기존 **Cache**에 저장된 주소들을 비워야 합니다.

왜냐하면 Process가 Context Switching이 일어나서 새로운 Process가 실행할 때 기존의 **Cache**가 가르키고 있는 주소는 쓸모없어지게 되기 때문입니다.

문제는 이후 얼마 동안 TLB가 **Cashe**에 Hit되지 않을 확률이 높아지게 되고, 이는 메모리 엑세스 비용이 증가로 이어지게 됩니다.

그러나 Multi Thread는 Stack을 제외한 Heap,Text,Data 영역을 공유하기 때문에 위와 같은 현상이 발생하지 않습니다.

**이것이 Multi Thread에서 Context Switching이 더 빠른 이유입니다.**

## Reference

[https://gmlwjd9405.github.io/2018/09/14/process-vs-thread.html](https://gmlwjd9405.github.io/2018/09/14/process-vs-thread.html)

[https://www.tutorialspoint.com/operating_system/os_processes.htm](https://www.tutorialspoint.com/operating_system/os_processes.htm)

[https://stackoverflow.com/questions/5440128/thread-context-switch-vs-process-context-switch](https://stackoverflow.com/questions/5440128/thread-context-switch-vs-process-context-switch)

[https://m.blog.naver.com/PostView.nhn?blogId=xowns4817&logNo=221182043348&proxyReferer=https%3A%2F%2Fwww.google.com%2F](https://m.blog.naver.com/PostView.nhn?blogId=xowns4817&logNo=221182043348&proxyReferer=https%3A%2F%2Fwww.google.com%2F)
