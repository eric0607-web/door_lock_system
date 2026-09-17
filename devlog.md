## 2026년 9월 14일 
프로젝트 주제: 원격 출입인증서비스 QR로 인증 시 자동으로 문을 열어주며 관리 및 통제 

Start.Spring.io로 스프링 프로젝트 만들기
MySQL 전용계정 생성 후 연동
Git 연결 및 커밋 


## 9월 15일
domain 페키지 생성
Store/User/AccessLog/OtpCode 엔티티 작성 및 enum작성

오늘 배운점:
int와 Integer차이 int는 null값이 들어가지 않고 Integer은 null값이 포함됨
Store 엔티티에 현재 점포에 문이 열려있는지 닫혀있는지의 상황을 알기 위해 doorOpenSec를 선언
해당값에 닫혀있으면 0이 아닌 null값을 넣어야 하기에 Integer선언 
*관례적으로 Integer을 쓰기도함*

@GeneratedValue(strategy = GenerationType.IDENTITY)에 IDENTITY는 DB에 increment에게 알아서 지정하라고 맡기는것

@ManyToOne = 참조 시키는 어노테이션 현 프로젝트는 Store하나만 참조하지만 확장성과 유연성을 고려해서 ManyToOne 사용
@ManyToOne: 여러 개(Many)가 하나(One)를 참조함
@OneToMany: 하나가 여러 개를 참조함 (위의 반대쪽 시점)
@OneToOne: 하나가 딱 하나만 참조함
@ManyToMany: 여러 개가 여러 개를 참조함

fetch = FetchType.LAZY = 지연로딩 성능의 차이 불필요한 조회를 줄임
단, LazyInitializationException 조심 DB연결 트랜잭션이 끝난 뒤에 호출을 하면 오류가 뜸
FetchType.LAZY <=> FetchType.EAGER

@MapsId + @OneToOne 다른 테이블의 기본키를 외례키로 지정하는 것이 아니라 기본키로 설정하기 위해 사용되는 구조

![최종 ERD](doc\20260915_ERD.png)
9월 15일 기준 최종 ERD

Flyway 마이그레이션 SQL 작명 규칙
V(대문자){버전}__{설명}.sql
DB수정시 V2 또는 V1.1 같은 소수점으로 변경해서 새로 만들어야함

오늘 정리 엔티티 작성+관련 enum 작성+SQL문 작성+마이그레이션+Hibernate가 검증 
=> 성공!

## 9월 16일

오늘 배운점 :
repository는 도메인 객체를 실제로 꺼내오는 창구 즉 자바식 CRUD의 실행문

인터페이스를 사용한 이유는 메서드를 선언만 하기위해 인터페이스 사용 상속받은 Jpa가 해당 메서드를 DB SQL로 번역해서 실행
즉 domain과 repository는 자바식언어로 DB, CRUD를 작성한것 DB는 flyway 마이그레이션이 .sql을 보고 만듦

<T,ID>제네릭은 T는 테이블의 엔티티 클래스, ID는 해당 테이블의 PK타입 

Optional 은 해당 값이 있을 수 도 있고 없을 수도 있다는걸 명시, 나중에 예외처리를 하기 위함
*DB값은 NOT NULL이지만 조회값이 DB자체에 없을 수도 있가때문에 예외처리*

Page함수는 값으 페이지형식으로 가져옴
Page<AccessLog> findByStoreIdOrderByOccurredAtDesc(Long storeId, Pageable pageable)
OrderBy 는 SQL에서 사용하는 Orderby랑 같음

<Repository Query creation from method names 법 팁>
findBy필드명 — 조건 조회, 결과 1개면 Optional<엔티티>, 여러 개면 List<엔티티>
existsBy필드명 — 있는지만 확인, boolean
findTopBy필드명...OrderBy필드명Desc — 조건 맞는 것 중 최신/최상위 1개
findBy필드1And필드2 — 조건 여러 개 AND
Page<엔티티> findBy...(..., Pageable pageable) — 목록이 계속 쌓이는 데이터는 페이징

로그인 방식 선택
세션, 토큰, HTTP Basic Auth 중 모바일 확장성을 고려해 토큰으로 결정

Bean(빈)의 사용용도는 여러 클래스에서 공통되어 쓰는 객체를 미리 Spring에게 만등어 주는 것
떄문에 개인정보와 같은 신상을 빈으로 만들면 안됨
빈을 모아두는 클래스에는 @Configuration 어노테이션 적용

Component는 Configuration + Bean을 사용하는 목적과 같은 다른 방법
Configuration + Bean은 외부 라이브러리를 빈으로 사용할때 사용
Component는 내가 직접 설계한 클래스를 빈으로 사용할 때 사용

@Value사용법 로직은 그대로 두고 변하는 값읗 가져올 때 혹은 기본 설정값(yaml, prppertise)을 가져올때
"${}"을 사용해서 값을 가져옴

this 활용법 매개변수와 필드의 이름이 겹칠때 this를 사용해 필드 를 지칭

enum 클래스는 자동적으로 java.lang.Enum클래스를 상속받아사 내장 메소드가 있음 
ex) enum.ADMIN.name()은 ADMIN이 나온다.

클래스와 이름이 같고 반환값이 없으면 생성자


## 9월17일

오늘 진행도: AuthService, SecurityConfig, SecurityConfig(수정), JwtAuthenticationFilter,DTO3개, AuthController, login.html

오늘 배운점:
try catch와 throws Exception의 차이 
try catch는 에러 발생 시 일종의 다른 루트의 제시
throws Exception는 떠넘기기 호출부에게 넘겨 책임전가

람다식
함수형 인터페이스를 구현하는 코드를 클래스를 만들지 않고 바로 사용하는 문법
(매개변수) -> {실행코드} 같은 문법으로 사용
매개변수가 없을 경우 ()로 사용가능
메서드 하나만 호출할 경우 ::와 같이 축약해서 사용
참고-SecurityConfig

@RestController
@Controller + @ResponseBody 를 합쳐놓은 어노테이션
리턴값을 뷰 이름으로 해석해서 jsp(뷰)를 찾는 과정을 생략하고, 값 자체를 응답 body로 바로 넘김

레코드 형식
기존에 DTO를 작성시 필드, Getter등을 사용하여 반복되는 작업이 많았는데 그 과정을 줄여놓은 클래스
레코드는 불변이 원칙 때문에 DTO에 적합
(Setter는 사용 불가)
