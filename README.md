# [MUSINSA] Java(Kotlin) Backend Engineer - 과제

<br>
카테고리에서 상품을 하나씩 구매하여 코디를 완성하는 서비스를 제공하기 위한 API 를 구현합니다.
<br>

## 기술 스택

* Java 17
* Spring Boot 3.3.2
    * Spring Data JPA
* QueryDSL 5.0.0
* Embedded H2

## 요구 사항
제공된 데이터를 활용하여 아래의 API 를 구현합니다.
1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API 
2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
4. 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API

## DB 구조

* **categories**

|        | **COLUMN** | **TYPE**     | **COMMENT** |
|--------|------------|--------------|-------------|
| **PK** | id         | bigint       | 카테고리 ID     |
|        | name       | varchar(255) | 카테고리 이름     |
|        | deleted    | char(1)      | 삭제 여부       |
|        | created_at | timestamp    | 생성 일시       |
|        | updated_at | timestamp    | 수정 일시       |

* **brands**

|        | **COLUMN** | **TYPE**     | **COMMENT** |
|--------|------------|--------------|-------------|
| **PK** | id         | bigint       | 브랜드 ID      |
|        | name       | varchar(255) | 브랜드 이름      |
|        | deleted    | char(1)      | 삭제 여부       |
|        | created_at | timestamp    | 생성 일시       |
|        | updated_at | timestamp    | 수정 일시       |

* **products**

|        | **COLUMN**  | **TYPE**  | **COMMENT** |
|--------|-------------|-----------|-------------|
| **PK** | id          | bigint    | 상품 ID       |
|        | brand_id    | bigint    | 브랜드 ID      |
|        | category_id | bigint    | 카테고리 ID     |
|        | price       | bigint    | 가격          |
|        | deleted     | char(1)   | 삭제 여부       |
|        | created_at  | timestamp | 생성 일시       |
|        | updated_at  | timestamp | 수정 일시       |

## 패키지 구조
``` text
src/main/java/com/assignment/musinsacatalog
│ 
├── application
│   ├── brand
│   ├── category 
│   ├── product 
│   └── recommendation
│ 
├── common
│   ├── exception
│   └── response
│ 
├── config
│
├── domain
│   ├── brand
│   ├── category 
│   ├── product 
│   └── recommendation
│
├── infrastructure
│   ├── brand
│   ├── category 
│   └── product
│
└── interfaces
    ├── brand
    ├── category 
    ├── product 
    └── recommendation
```

## 클래스 다이어그램
* 주요한 클래스 위주로 다이어그램을 작성하였습니다.

``` mermaid
classDiagram

    class BrandApiController
    class CategoryApiController
    class ProductApiController
    class RecommendationApiController

    class BrandFacade
    class CategoryFacade
    class ProductFacade
    class RecommendationFacade

    class BrandService
    <<interface>> BrandService
    class BrandServiceImpl
    class CategoryService
    <<interface>> CategoryService
    class CategoryServiceImpl
    class ProductService
    <<interface>> ProductService
    class ProductServiceImpl

    class BrandReader
    <<interface>> BrandReader
    class BrandReaderImpl
    class BrandRepository
    <<interface>> BrandRepository
    class BrandQueryDslRepository
    class CategoryReader
    <<interface>> CategoryReader
    class CategoryReaderImpl
    class CategoryRepository
    <<interface>> CategoryRepository
    class ProductReader
    <<interface>> ProductReader
    class ProductReaderImpl
    class ProductRepository
    <<interface>> ProductRepository
    class ProductQueryDslRepository
    
    class BrandStore
    <<interface>> BrandStore
    class BrandStoreImpl
    class CategoryStore
    <<interface>> CategoryStore
    class CategoryStoreImpl
    class ProductStore
    <<interface>> ProductStore
    class ProductStoreImpl
    
    BrandApiController --> BrandFacade
    CategoryApiController --> CategoryFacade
    ProductApiController --> ProductFacade
    RecommendationApiController --> RecommendationFacade

    BrandFacade --> BrandService
    CategoryFacade --> CategoryService
    ProductFacade --> ProductService
    RecommendationFacade --> ProductService
    RecommendationFacade --> BrandService
    RecommendationFacade --> CategoryService

    BrandService <|-- BrandServiceImpl
    CategoryService <|-- CategoryServiceImpl
    ProductService <|-- ProductServiceImpl

    BrandServiceImpl --> BrandReader
    BrandServiceImpl --> BrandStore
    CategoryServiceImpl --> CategoryReader
    CategoryServiceImpl --> CategoryStore
    ProductServiceImpl --> ProductReader
    ProductServiceImpl --> ProductStore

    BrandReader <|-- BrandReaderImpl
    BrandReaderImpl --> BrandRepository
    BrandReaderImpl --> BrandQueryDslRepository
    CategoryReader <|-- CategoryReaderImpl
    CategoryReaderImpl --> CategoryRepository
    ProductReader <|-- ProductReaderImpl
    ProductReaderImpl --> ProductRepository
    ProductReaderImpl --> ProductQueryDslRepository
    
    BrandStore <|-- BrandStoreImpl
    CategoryStore <|-- CategoryStoreImpl
    ProductStore <|-- ProductStoreImpl
```

## API 명세
* 브랜드/카테고리/상품 등록/수정/삭제/조회/목록 조회 API 는 http-test 폴더에 샘플을 작성하였습니다.
1. 공통 정보
* **Response**

| **NAME**  | **TYPE** | **DESCRIPTION** |
|-----------|----------|-----------------|
| result    | String   | 결과              |
| data      | Object   | 데이터             |
| message   | String   | 메시지             |
| errorCode | String   | 에러 코드           |

2. 카테고리 별 최저가격 브랜드와 상품 가격, 총액 조회 API

| **METHOD** | **URL**                                                                             |
|------------|-------------------------------------------------------------------------------------|
| GET        | http://localhost:8080/api/v1/recommendations/lowest-price-products-of-each-category |

- Request

`curl -X GET http://localhost:8080/api/v1/recommendations/lowest-price-products-of-each-category`

- Response

``` json
{
  "result": "SUCCESS",
  "data": {
    "categoryProducts": [
      {
        "categoryName": "상의",
        "brandName": "C",
        "price": 10000
      },
      {
        "categoryName": "아우터",
        "brandName": "E",
        "price": 5000
      },
      {
        "categoryName": "바지",
        "brandName": "D",
        "price": 3000
      },
      {
        "categoryName": "스니커즈",
        "brandName": "G",
        "price": 9000
      },
      {
        "categoryName": "가방",
        "brandName": "A",
        "price": 2000
      },
      {
        "categoryName": "모자",
        "brandName": "D",
        "price": 1500
      },
      {
        "categoryName": "양말",
        "brandName": "I",
        "price": 1700
      },
      {
        "categoryName": "악세서리",
        "brandName": "F",
        "price": 1900
      }
    ],
    "totalPrice": 34100
  },
  "message": null,
  "errorCode": null
}
```

3. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액 조회 API

| **METHOD** | **URL**                                                                                                      |
|------------|--------------------------------------------------------------------------------------------------------------|
| GET        | http://localhost:8080/api/v1/recommendations/http://localhost:8080/api/v1/recommendations/lowest-price-brand |

- Request

`curl -X GET http://localhost:8080/api/v1/recommendations/http://localhost:8080/api/v1/recommendations/lowest-price-brand`

- Response

``` json
{
  "result": "SUCCESS",
  "data": {
    "brandName": "D",
    "lowestPriceCategories": [
      {
        "categoryName": "상의",
        "price": 10100
      },
      {
        "categoryName": "아우터",
        "price": 5100
      },
      {
        "categoryName": "바지",
        "price": 3000
      },
      {
        "categoryName": "스니커즈",
        "price": 9500
      },
      {
        "categoryName": "가방",
        "price": 2500
      },
      {
        "categoryName": "모자",
        "price": 1500
      },
      {
        "categoryName": "양말",
        "price": 2400
      },
      {
        "categoryName": "악세서리",
        "price": 2000
      }
    ],
    "totalPrice": 36100
  },
  "message": null,
  "errorCode": null
}
```


4. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격 조회 API


| **METHOD** | **URL**                                                                                                                    |
|------------|----------------------------------------------------------------------------------------------------------------------------|
| GET        | http://localhost:8080/api/v1/recommendations/http://localhost:8080/api/v1/recommendations/lowest-price-product-by-category |

- Request

`curl -X GET http://localhost:8080/api/v1/recommendations/http://localhost:8080/api/v1/recommendations/lowest-price-product-by-category?categoryName=상의`

- Response

``` json
{
  "result": "SUCCESS",
  "data": {
    "categoryName": "상의",
    "lowest": [
      {
        "brandName": "C",
        "price": 10000
      }
    ],
    "highest": [
      {
        "brandName": "I",
        "price": 11400
      }
    ]
  },
  "message": null,
  "errorCode": null
}
```
