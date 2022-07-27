# sparta

## 3-1 주특기 기본주차
  * 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
    * 수정 : 게시글 id 값을 @pathvariable로 받고, 수정할 게시글 데이터를 @requestbody로 받아 해당 id 값을 가진 데이터를 db에서 찾아 @requestbody로 받은 데이터로 수정해주었다.
    리턴은 수정한 내용으로 받는다.
    * 삭제 : 게시글 id 값을 @pathvariable로 받아, 해당 id값을 가진 데이터를 db에서 찾아 삭제해주었다. 리턴은 삭제한 게시글의 id로 받는다.
    
  * 어떤 상황에 어떤 방식의 request를 써야하나요?
    * GET : 리소스를 조회
    * POST : 요청 데이터를 처리, 주로 데이터 등록에 사용
    * PUT : 리소스를 대체, 리소스가 없다면 생성
    * PATCH : 리소스 일부만 변경
    * DELETE : 리소스 삭제
    
  * RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
    * 

  * 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
    * controller를 통해 requestmapping을 수행하도록 했다.
    * repository를 통해 JPA를 이용해 데이터베이스에 접근할 수 있도록 했다.
    * service를 통해 controller로 들어온 요청을 수행할 수 있도록 했다.
  
  * 작성한 코드에서 빈(Bean)을 모두 찾아보세요!
    * PostingController, PostingService, Posting, PostingRequestDto, PasswordRequestDto, PostingRepository, timestamped, Week3application
  
  * API 명세서
    * Method | URL | Request | Response
      ---|---|---|---
      GET|/api/postings||
      GET|/api/postings/{id}||
      POST|/api/postings|{”title”:”title”, ”writer”:”writer”, ”password”:”password”, ”contents”:”contents”}|{"createdAt":"createdAt", "modifiedAt": "modifiedAt", "id": 1, "title": "title", "writer": "writer", "password": "password", "contents": "contents"}
      POST|/api/postings/{id}|{"password":"password"}|"true"
      PUT|api/postings/{id}|{”title”:”title2”, ”writer”:”writer2”, ”password”:”password2”, ”contents”:”contents2”}|{"createdAt":"createdAt", "modifiedAt": "modifiedAt", "id": 1, "title": "title2", "writer": "writer2", "password": "password2", "contents": "contents2"}
      DELETE|api/postings/{id}||"true"
