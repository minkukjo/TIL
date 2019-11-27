# 가상화

<img width="623" alt="스크린샷 2019-11-26 오후 4 56 14" src="https://user-images.githubusercontent.com/43809168/69609790-b311f680-106d-11ea-8d52-90c65a0ef60f.png">

**가상화란?**

- 단일 물리 하드웨어 시스템에서 여러 시뮬레이션 환경이나 리소스를 생성하는 기술

가상화에는 두가지 종류가 있습니다.

**Host 가상화**기술과 **베어메탈 가상화** 기술에 대해서 지금부터 알아보도록 하겠습니다.

## Host 가상화

<img width="535" alt="스크린샷 2019-11-26 오후 5 11 22" src="https://user-images.githubusercontent.com/43809168/69610764-d178f180-106f-11ea-809e-53aa720a9eb3.png">

**Host 가상화** 방식은 기존의 OS 위에 VMM 혹은 하이퍼 바이저를 설치 후 게스트 OS를 설치하여 사용하는 구조입니다.

**Host 가상화**의 장점은 설치와 구성이 간편합니다.

일반적으로 다양한 OS 환경에서의 테스트를 위해 사용되어지거나

각기 다른 OS의 어플리케이션에 자주 접근되는 경우 사용됩니다.

## 베어메탈 가상화

<img width="542" alt="스크린샷 2019-11-26 오후 5 11 28" src="https://user-images.githubusercontent.com/43809168/69610765-d178f180-106f-11ea-9ed8-8f2dfc4a17df.png">

**베어메탈 가상화**는 직접 하드웨어 위에 가상화 소프트웨어를 설치하는 방식입니다.

**Virtual Machine Monitor(VMM)**은 **Host OS**에 의존하지 않고 직접 시스템 하드웨어와 통신합니다.

단, 하이퍼 바이저가 하드웨어의 자원에 대한 디바이스 드라이버를 모두 갖고 있어야 하기 때문에 설치가 힘들다는 단점이 있습니다.

## 두 방식의 차이

이러한 두 방식의 차이점에서 가장 큰 차이점은 **Host 가상화**에 비해 **베어메탈 가상화**가 성능상 더 좋다는 점인데요.

왜 그럴까요?

우선 **베어메탈 가상화**는 하드웨어 위에 가상화 솔루션을 설치하기 때문에 직접 CPU,Memory,IO 등의 하드웨어들을 직접 제어하기 때문에 **Host 가상화**에 비해 Delay가 없습니다.

또한 보안 측면에서도 뛰어난데요.

**Host 가상화** 방식의 경우 **Host OS**가 공격당하면 **Guest OS**들도 노출될 위험이 있지만, 베어메탈의 경우 그러한 노출 위험 자체가 없습니다.

## Process 가상화 방식의 등장

리눅스에서 개발한 **cgroup**, **namespace**, **chroot** 기술을 사용하여 만든 LXC(Linux Container)에서부터 컨테이너 가상화 기술이 본격적으로 발전 했습니다.

LXC는 기존 가상화 방식과는 다르게 **Process**를 가상화한 방식입니다.

**Process**를 격리하기 때문에 CPU와 메모리를 필요한 만큼만 사용가능하며 컨테이너를 만드는 시간은 고작 1초도 안되게 가능합니다.

Process 가상화 방식은 일찌감치 있었지만 가장 크게 대중화가 된 것은 **Docker**의 등장부터 입니다.

## Docker

<img width="631" alt="스크린샷 2019-11-26 오후 6 02 46" src="https://user-images.githubusercontent.com/43809168/69614741-fae94b80-1076-11ea-8c29-5fe489dfc93c.png">

**Docker**는 현재 가장 유명한 Container 오픈소스 가상화 플랫폼이며 de facto(사실상 표준)이 되었습니다.

**Docker**는 LXC와 마찬가지로 **Container** 기반의 **Process 가상화** 방식을 사용하여 개발되었습니다.

Docker가 어떻게 프로세스 격리 가상화 플랫폼에서 1위가 될 수 있었냐에는 많은 이야기가 있습니다.

우선, Docker는 오픈소스를 기반으로 하고 있습니다.

Docker Hub는 Docker의 이미지(프로젝트의 스냅샷)들을 사용자들이 공유할 수 있는 공간 입니다. 

Docker는 이 Docker Hub를 무료로 제공하고 있습니다.

많은 유명 기업들이 Docker를 사용하고 있고 덕분에 훌륭한 생태계가 형성될 수 있었습니다.

Docker의 등장은 여러 새로운 변화를 일으켰는데요.

**Micro Service Architecture**가 새로운 현대의 Architecture의 형태로 등장할 수 있었던 가장 큰 계기 또한 **Docker** 덕분 입니다.

또한, 다중 Container를 제어하기 위해 **Kubernetes**라는 **Container Orchestration Platform**이 등장하게 되었고 현재도 **Docker**는 무궁무진한 **Service**를 개발하는데 사용되고 있습니다.


![docker-whale-home-logo](https://user-images.githubusercontent.com/43809168/69687207-7396e900-1105-11ea-819c-e698cc571d66.png)


~~사실상 도커의 유명세에 1등 공신은 고래 캐릭터~~


## Reference

https://blog.naver.com/PostView.nhn?blogId=shakey7&logNo=221472286783

https://duksoo.tistory.com/entry/%EA%B0%80%EC%83%81%ED%99%94-Virtualization%EC%9D%98-%EB%82%B4%EB%B6%80-%EA%B8%B0%EC%88%A0

https://subicura.com/2017/01/19/docker-guide-for-beginners-1.html