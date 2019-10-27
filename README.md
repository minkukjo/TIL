<p align="center">
  <img src="https://user-images.githubusercontent.com/43809168/67631249-25f54980-f8d7-11e9-875a-d84564f880a0.png">
</p>


# THE JAVA
본 내용은 ```백기선```님의 ```Inflern``` 강좌인 ```The Java, 코드를 조작하는 다양한 방법```을 학습하고 정리한 내용입니다.

```JVM```이란 무엇인지, ```JAVA```의 컴파일 원리

```BYTE CODE```를 어떻게 조작할 수 있는지, 조작해서 어떠한 마법을 부릴 수 있는지?

```JAVA REFLECTION```이란 무엇인지, ```SPRING DI```는 어떻게 가능한 것인지?

```DYNAMIC PROXY```란 무엇인지, ```JPA```는 어떻게 동작 하는지?

```ANNOTATION PROCESSOR```란 무엇이고 ```Lombok```은 어떻게 구현하는 것 인지?

에 대해서 알아보도록 하겠습니다.

## JAVA는 어떻게 컴파일 되는가?

![java_program](https://user-images.githubusercontent.com/43809168/67631417-9a30ec80-f8d9-11e9-997b-f2ea055f6ea9.png)

일반적으로 ```JAVA```는 다음과 같은 ```Step```을 거쳐서 컴파일 됩니다.

1. ```JAVA CODE```를 ```JAVA Compiler (javac)```가 ```.java``` 파일을 ```.class``` 파일로 변경합니다.

2. ```.class``` 파일은 ```Byte Code```라고 불립니다. 

3. ```JVM```의 ```Class Loader```는 ```.classFile```을 읽어와 메모리의 ```Method``` 영역에 저장합니다.

4. ```Execute Engine```에서 ```Byte Code```를 한줄씩 읽어 인터프리팅 합니다.


## JVM 내부 구조와 Garabge Collertor

![jvm](https://user-images.githubusercontent.com/43809168/67631501-981b5d80-f8da-11e9-98b8-ce0e674662c8.png)

```Method``` 영역은 ```Runtime Data Area```의 영역중 하나입니다. 일반적으로 JVM 내부에서 ```Method```와 ```Heap```영역은 공유되고, ```Stack, PC Register, NativeMethod Stack```은 쓰레드 단위로 분리되어 집니다.


## 이미지 출처

[2ssue님의 블로그](https://2ssue.github.io/base/190509_PJI/#)