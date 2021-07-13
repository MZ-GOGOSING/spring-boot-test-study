# Spring Boot Test Study Application

## 개요
본 어플리케이션은 spring-boot-starter-test 에서 지원하는  
계층 별(Presentation Layer, Business Layer, Persistence Layer) 테스트 기법인 Slice Test 를 실제로 어떻게 구현하는지,  
그리고 Slice Test 가 integration-test 에 비해 속도 차이가 얼마나 나는지에 대해 그 차이를 아는데에 목적을 가지고 작성되었습니다.

본 어플리케이션은 Slice Test 를 어떻게 구현하는지, 혹은 그 성능을 알고자 하는데에만 목적을 가질 뿐,  
단위 테스트를 어떻게 작성하는지(Mock, Stub, Spy, Verify) 를 설명하기 위한 목적이 아님을 밝힙니다.

## 알 수 있는 것
우리는 본 어플리케이션을 통해서 아래와 같은 상황에 대해서 테스트를 진행 해 볼 수 있고,  
또한, 이러한 상황들에 대해 Slice Test 가 실제 어떻게 적용 되었는지 살펴볼 수 있습니다.
* Presentation Layer
    * Client 로 부터 전송된 데이터가 Controller 의 각 End-Point 에 해당하는 method 의 argument 에 정상적으로 binding 이 되는지
    * 각 argument 에 대한 javax.validation.constraints 의 validation 이 의도한 대로 작동하는지
    * 우리가 의도한 대로(혹은, 의도한 형태로) response 가 되돌아 오는지
* Business Layer
    * 예상되어지는(혹은, 허용 범위의) input 에 대한, 의도한 output 이 정상적으로 return 되는지
* Persistence Layer
    * 의도한 대로 Projection 이 이루어지는지
    * 의도한 대로 Fetch Join 이 이루어지는지
    * JPA 의 Entity Relationship 에서 Fetch 전략을 잘못 이해(또는, 선언) 하였을 때, 나타날 수 있는 N+1 문제가 일어나는지
    * 자신이 작성한 JPQL(QueryDsl) 이 실제 어떠한 SQL 로 RDBMS 에 전달 되어지는지   

## 특이사항
* 본 어플리케이션의 웹 접근을 위한 port 는 9090 으로 설정되어 있습니다.
* 본 어플리케이션을 로컬상에서 구동하기 위해 별도의 RDBMS, Docker Container 설치는 불필요합니다.
* 본 어플리케이션 구동 시, h2 Database 를 In Memory 모드로 사용하도록 설정해 두었습니다.
    * [H2 Web Console](http://localhost:9090/h2-console)
    * Web Console ID / PW 는 접근 시, 지정되어 있는 상태 그대로 Connect 할 수 있습니다.
    * In Memory Mode 이기에, 어플리케이션이 종료되면 기 반영되어 있던 Schema / init Data 정보는 모두 소거됩니다.
* 본 어플리케이션 구동 시, flyway 를 통하여 DB에 Schema / init Data 가 반영될 수 있도록 설정해 두었습니다.
* Swagger REST API 명세는 [링크](http://localhost:9090/swagger-ui.html) 로 접근이 가능합니다.
    
## 사용된 의존성
본 어플리케이션을 작성하기 위해서 대표적으로 아래와 같은 의존성이 사용되었습니다.
* spring-boot-starter-web
* spring-boot-starter-data-jpa
* spring-boot-starter-test  
* querydsl-jpa
* flyway-core
* springfox-swagger
* lombok
* junit 5
* spock
* h2database (in memory)