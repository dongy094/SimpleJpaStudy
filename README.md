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
- Session을 활용하여 사용자 정보 유지 기능
- Interceptor를 활용하여 게시판 접근관리(비로그인 시 특정 게시판 접근불가)
#### version 1.2
- SingIn 기능
#### version 1.2
- 댓글 기능

</br>

___
언어 : `Java`

tool : `intellij`

DataBase : `H2 DataBase`

관련 기술 : `JPA`  `MVC`  `ORM`  `Hibernate`  `Thymeleaf` </br>
　　　　　`paging`  `Interceptor`  `Session`  `spring boot` </br>
　　　　　

</br>

### 🔘 프로젝트 구현

|회원가입|글작성|
|---|---|
|<img src="https://github.com/dongy094/SimpleJpaStudy/blob/main/file1/signup.gif?raw=true" width="450" height="400">|<img src="https://github.com/dongy094/SimpleJpaStudy/blob/main/file1/writeboard.gif?raw=true" width="450" height="400">|

|글수정|글삭제|
|---|---|
|<img src="https://github.com/dongy094/SimpleJpaStudy/blob/main/file1/update_board.gif?raw=true" width="450" height="400">|<img src="https://github.com/dongy094/SimpleJpaStudy/blob/main/file1/delete_board.gif?raw=true" width="450" height="400">|
|자신이 작성한 글만 수정|자신이 작성한 글만 삭제|

|페이징처리|인터셉터처리|
|---|---|
|<img src="https://github.com/dongy094/SimpleJpaStudy/blob/main/file1/paging.gif?raw=true" width="450" height="400">|<img src="https://github.com/dongy094/SimpleJpaStudy/blob/main/file2/interceptor.gif?raw=true" width="450" height="400">|
||비로그인시 인터셉터 처리|

|로그인||
|---|---|
|<img src="https://github.com/dongy094/SimpleJpaStudy/blob/main/file2/singin.gif?raw=true" width="450" height="400">||


</br>

### 🔘 마무리
- `JPArepository`를 상속받은 인터페이스를 활용한다면 간단한 `CRUD`는 구현필요없이 간단하게 가져다 쓸 수 있다는 것을 알았다.
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

- 데이터 수정할때 `변경감지` 이용해서 데이터 수정하기! </br>
  => 객체를 새로 만들어서 값을 세팅하고 그 객체를 merge를 통해서 업데이트 한다면??? </br>
그때 만약 값 변경이 없어서 세팅하지 않은 변수가 있다면 객체의 해당 변수는 null이되고 merge를 하면 싹 다 갈아 엎어서 결국 null값이 들어가는 위험한 상황이 발생한다.
- `thymeleaf`를 사용하여 front단을 작성하는 것이 익숙하지 않아서 작성하는데 좀 힘들었다.
- `spring boot`를 사용하니까 개발을 위한 환경설정을 하는데 `spring framework`를 사용할때 보다 빠르게 세팅할 수 있어서 좋았다.
