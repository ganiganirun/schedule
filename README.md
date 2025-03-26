# schedule

1. 프로젝트 소개
    - 해당 프로젝트는 일정 관리 앱 서버를 5가지 level에 거쳐 구현하는 프로젝트이다.
    - 프론트는 제작하지 않기 때문에 API 실행 및 테스트는 Postman을 활용하도록 한다.
2. 요구사항 정의
    - 공통 요구사항
        - Entity를 그대로 반환하지 않고, DTO에 담아 반환하도록 한다.
        - 일정 작성, 수정, 조회 시 반환 받은 일정 정보에는 비밀번호는 제외한다.
        - 일정 수정, 삭제 시 선택 일정의 비밀번호와 요청할 때 함께 보낸 비밀번호가 일치 시에만 가능하다.
            - 비밀번호가 불일치 시 적절 오류 코드 및 메시지를 반환한다.
        - 3 Layer Architecture 에 따라 각 Layer의 목적에 맞게 개발하도록 한다.
        - CRUD 필수 기능은 모두 데이터베이스 연결 및 JDBC를 사용해서 개발해야 한다.
3. API 명세서
    -  일정 생성, 전체 일정 조회, 선택일정 조회, 선택 일정 조정, 선택 일정 삭제
    - id, 할일(todo), 작성자명(name), 비밀번호(password), 최초작성일(created_at), 최근수정일(update_at)
![스크린샷 2025-03-26 오후 12 26 03](https://github.com/user-attachments/assets/4a697d9c-f8e5-440d-a9e7-f6480ec8e184)


4. ERD
![schedule](https://github.com/user-attachments/assets/32795c57-2a12-4eef-8b19-a7cfcf0ec78c)

