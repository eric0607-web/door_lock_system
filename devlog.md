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