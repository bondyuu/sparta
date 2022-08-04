# sparta

## 3-2 주특기 숙련주차

- FormLogin을 활용할 때와 활용하지 않을 때 어떤 차이점이 있었나요?
  - FormLogin을 사용할 때는 EnumUserRole을 설정해 서버 측에서 권한을 확인하고 인가를 했다면, jwt를 사용할 때는 클라이언트가 Authorization 값을 가져왔을 때 jwtAuthFilter을 통해 인가를 해줄 수 있었습니다. 
  
  
- 처음 설계한 API와 ERD에 변경사항이 있었나요? 
변경되었다면 어떤 점 때문일까요? 첫 설계의 중요성에 대해 생각해보세요.
  - Controller에서 @Requestbody로 받은 Dto를 통해 Service 로직을 구현할때, 정확하게 어떤 값들을 받아 어떻게 처리할지 정해두지 않아 Service 로직을 만들 때 Dto변수를 계속 수정해주어햐 하는 문제가 발생했습니다. 처음 설계를 제대로 한다면 서버를 구현하는데 있어 의사소통이 원활하게 이루어져 여러 사람이 함께 작업을 진행해도 세부사항들을 수정할 필요가 없을 것 같습니다.
  
  
- Refresh Token을 사용하는 이유가 뭘까요?
  - AccessToken이 만료되지 않고 계속 접근할 수 있다면 Token이 노출되었을 때, 보안에 취약하게 될 수 있습니다. 그래서 Token의 만료 기간을 설정해두고, 기간 후에 로그인을 시도했을 때 Refresh Token을 통해 Access Token을 재발급해 보안상 이득을 취할 수 있습니다.

- 3번의 이유로 사용한다면, 왜 매번 Access Token과 Refresh Token을 모두 재발급 할까요? 만료 시간과 관련하여 고민/검색 해보세요!
  - 매번 AccessToken과 Refresh Token을 재발급 하기 보다는 AccessToken의 유효기간은 짧게, RefreshToken은 길게 설정하여 AccessToken의 유효기간이 지났을 때만 Refresh Token으로 재발급합니다. 물론 Refresh Token 역시 노출 위험이 있기 때문에 기간을 설정해두고 주기적인 재발급이 필요할 것 같습니다.
  

- API 명세서
  - | Method | URL | Request | Response | 비고  
     ---     | --- |---      | ---      | ---|
    | POST   | /api/member/signup     | {"username":"username", "password":"password", "repassword":"password"} | {"createdAt":"createdAt", "modifiedAt": "modifiedAt", "userId": 1, "username": "username", "password": "password", "role": "USER"}  | 회원가입
    | POST   | /user/login            | {"username":"username", "password":"password"}  | none            | 로그인               
    | GET    | /api/postings         |              |                                | 게시글 목록조회                 
    | GET    | /api/postings/{id}    |              |                                | 게시글 조회       
    | POST   | /api/auth/postings    | {”title”:”title”, ”contents”:”contents”}      | {"createdAt":"createdAt", "modifiedAt": "modifiedAt", "postingid": 1, "title": "title", "writer": "username", "contents": "contents"}   | 게시글 작성   
    | PUT    | api/auth/postings/{postingId} | {”title”:”title2”, ”contents”:”contents2”} | {"createdAt":"createdAt", "modifiedAt": "modifiedAt", "postingid": 1, "title": "title2", "writer": "username", "contents": "contents2"} | 게시글 수정                    
    | DELETE | api/auth/postings/{id}   |                    | "Deleted Successfully"            | 게시글 삭제
    | POST   | /api/auth/comments/{postingId}  | {"contents":"contents"}  | {"createdAt": "createdAt","modifiedAt": "modifiedAt","commentId": 9,"postingId": 7,"username": "koo","comment": "comment3"}         | 댓글 작성                                                        
    | GET    | /api/comments/{postingId} |             |  {"createdAt": "createdAt","modifiedAt": "modifiedAt","commentId": 9,"postingId": 7,"username": "koo","comment": "comment3"} | 댓글 조회  
    | PUT    | /api/auth/comments/{postingId}/{commentId} | {"comment":"comment4"} |{"createdAt": "createdAt","modifiedAt": "modifiedAt","commentId": 9,"postingId": 7,"username": "koo","comment": "comment4"} | 댓글 수정   
    | DELETE | /api/auth/comments/{postingId}/{commentId} |            | "Deleted Successfully"  | 댓글 삭제                                  
