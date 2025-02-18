# Pushing Data to Multiple Storage Locations Pattern

## 패턴 설명

동일한 데이터에 대해 서로 다른 사용 사례가 필요할 때 여러 저장소에 데이터를 저장하는 패턴입니다.

## 사용 사례

금융 시계열 데이터를 다음 두 가지 용도로 저장하고 활용하는 경우:

1. 대규모 SQL 집계 분석 수행
2. 테라바이트 규모 데이터에서 소량의 행을 조회하는 범위 스캔 작업

## 참고

- [원본 문서](https://cloud.google.com/blog/products/data-analytics/guide-to-common-cloud-dataflow-use-case-patterns-part-1)