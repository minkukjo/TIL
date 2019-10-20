![1200px-React-icon svg](https://user-images.githubusercontent.com/43809168/67155370-d5b34000-f348-11e9-9a27-0b678b05248f.png)


# React


오늘은 ```JavaScript Library```인 ```React```에 대해 공부해보겠습니다.

과거 ```JavaScript```는 웹 브라우저상에서의 간단한 연산을 하거나 시각적 효과를 주기 위해 사용되어졌습니다.

하지만 현재 ```JavaScript```는 웹 어플리케이션을 개발에 있어 핵심적인 역할을 한다고 하여도 과언이 아닙니다.

```JavaScript```만으로 대규모 어플리케이션을 개발할 수 있게 되면서 개발자들은 프런트엔드 사이드에서 돌아가는 어플리케이션의 구조를 관리하기 위한 방법을 고민하기 시작합니다.

그렇게 등장한 것이 ```JavaScript FrameWork```입니다. ```Vue```나 ```Angular```등이 대표적인 예시죠.

이러한 ```FrameWrok```는 MVC,MVVM같은 아키텍처를 주로 사용합니다. Model이 변경되면 View를 반영하는 것이죠.

본 글에서는 디자인 패턴에 관한 이야기는 넘어가도록 하겠습니다. 만약 자세하게 알고 싶은 분들은 아래의 링크를 참조해주세요.

[버미 노트님의 디자인 패턴 설명](https://beomy.tistory.com/43)

하지만 대규모 웹 어플리케이션에서의 View는 빈번한 변경이 발생하고 이러한 변경에 일일이 대응하기 위해서는 이러한 변경을 하는 작업이 굉장히 복잡하고 성능상에 문제가 있을 수 있습니다.


리액트는 이러한 문제를 해결하기 위해 등장하였습니다.

페이스북 개발팀은 ```지속적으로 데이터가 변화하는 대규모 어플리케이션 구축```을  ```React```를 만들었습니다.

그럼 도대체 어떻게 ```React```는 이것을 가능하게 하였을까요?

이를 알아보기에 앞서, 먼저 ```FrameWork```와 ```Library```의 차이가 무엇인지에 대해서 먼저 짚고 넘어가도록 하겠습니다.


## The Difference Between a FrameWork and a Library

```Library```와 ```FrameWork``` 모두 일반적인 문제해결을 위해 다른 누군가가 만든 코드를 사용한다는 점에서 공통점이 있습니다.

가령 예를 들어, ```URL```을 넘겨주면 해당 ```URL```에 ```GET Request```를 보내주는 함수가 있다고 해봅시다. 아래처럼요.

```kotlin
fun getMethod(url: String): CloseableHttpResponse  {
	val httpClient = HttpClients.createDefault()
    val httpGetp = httpGet(url)
    val httpResponse = httpClient.execute(httpGet)
    return httpResponse
}
```

이때 우리는 getMethod를 보내는 방법에 대해 정확히 모르더라도, ```getMethod``` 함수에 ```URL```만 넘겨주는 것으로,

우리가 원하는 문제를 해결할 수 있습니다.

이것이 Library입니다. 끝!

일반적으로 ```Library```와 ```FrameWork```는 집에 비유할 수 있습니다.

```Library```는 가구를 사서 집을 꾸미는 것에 비유할 수 있어요. 

이미 집은 있고, 저는 여기에 필요한 가구들을 직접 사와서 꾸밀 수 있죠.

```Library```는 제가 제 집을 꾸미는 ```제어권```을 갖고 있음을 의미합니다.

그러나 ```FrameWork```는 풀옵션의 집에 사는 것에 비유할 수 있어요.

풀옵션의 집은 일반적으로 침대의 위치나 가전 제품, 가구들의 위치가 정해져있지요.

그래서 제가 집을 꾸미는데 할 수 있는 선택권이 줄어듭니다. 결국

```FrameWork```는 제가 제 집을 꾸미는 ```제어권```이 집주인에게 넘어갔다고 보면 됩니다.


프로그램의 주도권을 누가 쥐고 있느냐가 이 둘의 차이를 결정하게 됩니다.

```Library```는 개발자가 주도적으로 ```Library```를 선언하여 프로그램 전체를 주도하여 개발할 수 있습니다.

하지만 ```FrameWork```는 그렇지 않습니다. ```FrameWork```가 설계되어있으면 우리는 이 ```FrameWork```에 우리가 만든 함수를 넘깁니다.

그러면 ```FrameWork```는 이 함수를 ```invoke```하여 실행해줍니다. 

```Spring FrameWork```를 사용해보신 분이라면 ```IoC Container```를 떠올리면 이해가 빠를 것 같네요.

우리가 만든 ```Bean```들을 관리하고 ```DI```를 해주는 것은 개발자가 신경쓸 문제가 아닙니다.

이는 ```Spring FrameWork```가 해주는 것이지요.


**즉 둘의 가장 큰 차이는 ```inversion of control``` 여부 입니다.**


## Why React?

다시 리액트로 넘어와서 하던 얘기를 계속 하도록 하겠습니다.

기존의 MVC,MVVM,MVW같은 ```FrameWork```들과 달리 리액트는 **View**에 집중된 ```Library```입니다.

그래서 페이스북 개발자들은 기존의 데이터가 변할때 마다 어떤 변화를 줄지를 고민하는 것이 아닌

**그냥 싹 다 날려버리고 다시 그리는건 어떨까?** 라는 아주 발칙한 상상을 하게 됩니다.

그리고 그 상상은 현실이 됩니다.

물론 이렇게 하면 어플리케이션 구조가 매우 간단해지긴 하겠습니다만 이렇게 되면 성능에 문제가 생기게 됩니다.

웹 브라우저에서 DOM에 변화가 일어날때 마다 CSS를 다시 연산하고, 레이아웃을 구성하여 페이지를 리페인트하는 과정은 시간이 많이 걸리는 작업입니다.

그래서 리액트는 이를 해결하기 위해 ```Virtual DOM```이라는 개념을 도입합니다.


![QRtqIui](https://user-images.githubusercontent.com/43809168/67155773-25e1d080-f350-11e9-8e3d-3a9ac92737ff.png)


```Virtual DOM```이란 기존의 DOM과 같은 모양의 DOM을 메모리에 저장해놓고 있다가, 변화가 생기면

실제 DOM에 바뀐 부분만 변경하는 방법입니다.

이렇게 하면 마치 처음부터 다시 렌더링하는 것처럼 보이는 작업이 최적의 자원을 사용하는 효과를 낼 수 있게 됩니다.

그러면 이 ```Virtual DOM```이 만능일까요? 그렇지는 않습니다.

리액트를 사용하지 않더라도, ```DOM```의 성능을 높일 수 있고, 오히려 요구사항이 간단한 경우라면 ```React```자체를 사용하지 않는 편이 더 나을 수 있습니다.

하지만 ```React```와 ```Virtual DOM```을 사용하는 이유는 업데이트 과정에서 발생하는 복잡함을 모두 해소하고, 더욱 쉽게 업데이트를 할 수 있기 때문입니다.

물론, ```React```는 ```Library```이기 때문에 다른 ```FrameWork```에서 제공하는 ```Ajax```, ```Routing```등은 내장하고 있지 않습니다.

하지만 기존에 뛰어난 개발자들이 만든 ```react-router```나 ```axios```를 사용하면 되니 너무 걱정할 필요는 없습니다.

## 마치며

지금까지 ```React```의 탄생 배경과 ```Library```와 ```Frame Work```차이에 대해 알아보았습니다.

현재 진행중인 인턴 프로젝트에서 ```FrontEnd``` 개발을 해야해서 ```React```와 ```JavaScript```를 공부하고 있어서

정리할겸 글을 써보았습니다.

```FrontEnd``` 개발을 하면서 느낀 점은, ```BackEnd``` 개발자를 하길 잘했다는 생각밖에 안들정도로 ```FrontEnd``` 가 어렵게 느껴집니다.

그만큼 진도도 안나가고 CSS와 씨름중이긴 하지만 그래도 어쩌겠습니까 하는데 까진 최선을 다해서 열심히 해봐야죠.

긴 글 읽어주셔서 감사합니다!


## 출처

[프레임워크와 라이브러리의 차이](https://www.freecodecamp.org/news/the-difference-between-a-framework-and-a-library-bd133054023f/)

[위 글에서 예시로 나온 미국의 Model Home 문화에 관하여](https://en.wikipedia.org/wiki/Show_house)

[리액트를 다루는 기술 저자 Velopert](http://www.yes24.com/Product/Goods/62597469)

[Virtual DOM 이미지](http://blog.drakejin.me/React-VirtualDOM-And-Repaint-Reflow/)
