## 0. 프로젝트 소개
카카오 API를 이용해, 근처의 식당을 자동으로 탐색하고 추천해주는 웹 앱

## 1. 실행 방법
정상적인 실행을 위해서는 백앤드를 설정해줘야 합니다.  
스프링 환경에서 application.yml을 다음과 같이 지정해주면 됩니다.
![yml](https://user-images.githubusercontent.com/19809346/147338207-2f289a5a-481c-49ea-b383-ea1883c1b77c.PNG)  
6번째 줄의 까만색 줄은 카카오 API키를 가린것으로, https://developers.kakao.com/product/map 에서 발급 받으실 수 있습니다.  
카카오의 키 중 REST API Key를 사용하면 됩니다.


## 2. 구현한 기능
식당 추천 기능, 회원 Auth 기능

## 2. 개발 스택, 개발 환경
![개발스택](https://user-images.githubusercontent.com/19809346/147336869-7918aba3-5007-4726-bdc9-0e3e560148e5.PNG)
![개발환경](https://user-images.githubusercontent.com/19809346/147336904-9477ca4d-aa17-4cf6-9913-399c2aa16430.PNG)

## 3. 프로젝트 전체 구조
![프로젝트 구조](https://user-images.githubusercontent.com/19809346/147336961-a8a882d2-c209-4ad3-9ee2-66e5d746e158.PNG)

## 4. 추천 시스템의 흐름 및 설계
![슬라이드8](https://user-images.githubusercontent.com/19809346/147337185-4e298436-ca09-438f-a440-42654491a4a8.PNG)
![슬라이드9](https://user-images.githubusercontent.com/19809346/147337191-7badec71-04b6-406a-95df-c46f33489135.PNG)
![슬라이드10](https://user-images.githubusercontent.com/19809346/147337194-a9ad6d24-5ae1-4123-b917-6483aea1e4c9.PNG)
![슬라이드11](https://user-images.githubusercontent.com/19809346/147337213-cb0d8170-ae3a-4467-bc48-527b559b1240.PNG)
![슬라이드12](https://user-images.githubusercontent.com/19809346/147337215-36df7972-7c37-4d73-bc9b-e810503481c5.PNG)
![슬라이드13](https://user-images.githubusercontent.com/19809346/147337222-67405f21-4a78-431f-9076-195fc8c5ecad.PNG)
![슬라이드14](https://user-images.githubusercontent.com/19809346/147337227-9fb8f47b-ff59-4e91-8165-f5acb29f8f22.PNG)
![슬라이드15](https://user-images.githubusercontent.com/19809346/147337229-a8cb3762-078a-4cf0-adca-15c5745d9264.PNG)
![슬라이드16](https://user-images.githubusercontent.com/19809346/147337236-a05ca28d-60c9-4ad9-804a-fa88250aa53f.PNG)
![슬라이드17](https://user-images.githubusercontent.com/19809346/147337244-71383203-233b-4a72-8e3b-71559870dc08.PNG)
![슬라이드18](https://user-images.githubusercontent.com/19809346/147337290-97e677a0-c58d-4763-bddb-7928a0277cb3.PNG)

## 5. 실행화면
### 메인페이지
![실행화면1](https://user-images.githubusercontent.com/19809346/147337821-d79469c8-1f13-46a4-a64b-3161c309e40c.PNG)
### 회원가입 화면
![실행화면3](https://user-images.githubusercontent.com/19809346/147337825-99ef8c78-9bb9-4bb4-860b-42b9ff6035eb.PNG)
### 회원가입 후 백앤드 DB에 패스워드가 암호화되어 저장
![실행화면4](https://user-images.githubusercontent.com/19809346/147337826-f2a4dc50-0257-4a28-8084-9d8d80fdd13b.PNG)
### 로그인
![실행화면5](https://user-images.githubusercontent.com/19809346/147337828-9521cabd-1780-499d-84b1-4125f893d9a1.PNG)
### 로그인 시 유저 세션에 따른 화면 변화
![실행화면6](https://user-images.githubusercontent.com/19809346/147337830-36fa1893-f71d-4e35-a3a8-a6f00d37a4e9.PNG)
### 식사 추천
![실행화면7](https://user-images.githubusercontent.com/19809346/147337832-9952c809-0a50-4301-8747-7e19731f4b1b.PNG)
### 식사 추천 결과
![실행화면8](https://user-images.githubusercontent.com/19809346/147337834-693e3080-24ff-4bcd-943a-8fcd46a9deeb.PNG)
