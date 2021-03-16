# Simple JPA board
### 🔘 프로젝트 목적
- Spring Boot, JPA를 활용해서 간단한 게시판 만들기

</br>

### 🔘 프로젝트 소개
#### version 1.0
- JPA를 활용한 회원가입
- JPA를 활용한 게시판 CRUD구현
- JPA를 활용하여 페이징 구현
#### version 1.1
- Session을 활용하여 사용자 정보 유지 기능 - 추가
- Interceptor를 활용하여 게시판 접근관리(비로그인 시 특정 게시판 접근불가) - 추가

</br>

___
언어 : `Java`

tool : `intellij`

DataBase : `H2 DataBase`

관련 기술 : `JPA`  `MVC`  `ORM`  `Hibernate`  `Thymeleaf` </br>
　　　　　`paging`  `Interceptor`  `Session`  `spring boot` </br>
　　　　　`aaa`

</br>

### 🔘 프로젝트 구현
gif넣고 그거에 대한설명 어떻게 구현 했는지 기술

</br>

### 🔘 마무리
- 프로젝트를 진행하면서 일부 간단한 쿼리문 처리는 `JPArepository`를 상속받은 개체를 통해서 별다른 구현없이 또는 규칙에 맞게 인터페이스를 작성하면 손쉽게 데이터를 가져올 수 있다는 것을 알게되었다.
여기서 구현한 인터페이스를 따로 작성할 필요가 없다.
```java
public interface UserRepository extends JpaRepository<Object_type, Key_type>{
            List<Object> findByEmailAddress(String emailAddress); //따로 구현 필요X
}

public Other_Object{

    private UserRepositort userRepository;

        ...
        
    userRepository.findByEmailAddress(uesr_email); //구현없이 규칙에 맞게 작성하면 된다.
    userRepository.findAll(); //UserRepository에 없지만 많이 JpaRepository상속받아 가져다 쓸 수 있다.

}

```

</br>

- JPA를 활용하면 페이징 처리도 좀 더 간단하게 할 수 있다는 것을 알았다. 이것도 `JpaRepository`를 상속받으면 간단하게 페이징을 구현할 수 있다. 
```java
public return_type boardList(Model model , @PageableDefault(size = 4) Pageable pageable){ 
                            // 한 페이지에 보여줄 게시글의 갯수(size=4)   

    Page<Object_type> list = boardJpaRepository.findAll(pageable);
        ...

    model.addAttribute("list",list);
    // view단에서 list를 사용하여 페이징 구현

}

```

</br>

- `thymeleaf`를 사용하여 front단을 작성하는 것이 익숙하지 않아서 작성하는데 좀 힘들었다.
- `spring boot`를 사용하니까 개발을 위한 환경설정을 하는데 `spring framework`를 사용할때 보다 빠르게 세팅할 수 있어서 좋았다.
