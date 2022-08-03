# sparta

## 3-1 주특기 기본주차

- 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
  - 수정 : 게시글 id 값을 @pathvariable로 받고, 수정할 게시글 데이터를 @requestbody로 받아 해당 id 값을 가진 데이터를 db에서 찾아 @requestbody로 받은 데이터로 수정해주었다.
    리턴은 수정한 내용으로 받는다.
  - 삭제 : 게시글 id 값을 @pathvariable로 받아, 해당 id값을 가진 데이터를 db에서 찾아 삭제해주었다. 리턴은 삭제한 게시글의 id로 받는다.
- 어떤 상황에 어떤 방식의 request를 써야하나요?
  - GET : 리소스를 조회
  - POST : 요청 데이터를 처리, 주로 데이터 등록에 사용
  - PUT : 리소스를 대체, 리소스가 없다면 생성
  - PATCH : 리소스 일부만 변경
  - DELETE : 리소스 삭제
- RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?

  - URI규칙을 준수 했다.
  - collection와 document의 리소스 관계를 표현했다.
  - header를 설정했다.
  - http 메소드를 활용했다.

- 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)

  -
  -
  -

- 작성한 코드에서 빈(Bean)을 모두 찾아보세요!

  -

- API 명세서
  - | Method | URL                                        | Request                                                                 | Response                                                                                                                                                        |
    | ------ | ------------------------------------------ | ----------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- | ----------- |
    | POST   | /api/member/signup                         | {"username":"username", "password":"password", "repassword":"password"} | {"createdAt":"createdAt", "modifiedAt": "modifiedAt", "id": 1, "username": "username", "password": "password", "role": "USER"}                                  | 회원가입                                                                                                                    |
    | POST   | /user/login                                | {"username":"username", "password":"password"}                          | none                                                                                                                                                            | 로그인                                                                                                                      |
    | GET    | /api/postings                              |                                                                         |                                                                                                                                                                 | 게시글 목록조회                                                                                                             |
    | GET    | /api/postings/{id}                         |                                                                         |                                                                                                                                                                 | 게시글 조회                                                                                                                 |
    | POST   | /api/auth/postings                         | {”title”:”title”, ”contents”:”contents”}                                | {"createdAt":"createdAt", "modifiedAt": "modifiedAt", "postingid": 1, "title": "title", "writer": "username", "password": "password", "contents": "contents"}   | 게시글 작성                                                                                                                 |
    | PUT    | api/auth/postings/{id}                     | {”title”:”title2”, ”contents”:”contents2”}                              | {"createdAt":"createdAt", "modifiedAt": "modifiedAt", "postingid": 1, "title": "title2", "writer": "username", "password": "password", "contents": "contents2"} | 게시글 수정                                                                                                                 |
    | DELETE | api/auth/postings/{id}                     |                                                                         | "Deleted Successfully"                                                                                                                                          |                                                                                                                             | 게시글 삭제 |
    | POST   | /api/auth/comments/{postingId}             | {"contents":"contents"}                                                 | {"createdAt": "createdAt","modifiedAt": "modifiedAt","commentId": 9,"postingId": 7,"username": "koo","comment": "comment3"}                                     | 댓글 작성                                                                                                                   |
    | GET    | /api/comments/{postingId}                  |                                                                         | {"contents":"contents"}                                                                                                                                         | {"createdAt": "createdAt","modifiedAt": "modifiedAt","commentId": 9,"postingId": 7,"username": "koo","comment": "comment3"} | 댓글 조회   |
    | PUT    | /api/auth/comments/{postingId}/{commentId} | {"comment":"comment4"}                                                  | {"contents":"contents"}                                                                                                                                         | {"createdAt": "createdAt","modifiedAt": "modifiedAt","commentId": 9,"postingId": 7,"username": "koo","comment": "comment4"} | 댓글 수정   |
    | DELETE | /api/auth/comments/{postingId}/{commentId} |                                                                         | "Deleted Successfully"                                                                                                                                          | 댓글 삭제                                                                                                                   |
