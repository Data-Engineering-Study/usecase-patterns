# 데이터 파이프라인 패턴 학습 프로젝트

실시간 데이터 처리 패턴을 학습하기 위한 실용적인 예제 모음 프로젝트입니다.

## 개요

이 프로젝트는 Google Cloud Dataflow의 일반적인 유스케이스 패턴들을 실제 구현하여 학습할 수 있도록 구성되었습니다. 각 패턴은 Apache Beam과 Apache Flink 두 가지 방식으로 구현되어
있어 비교 학습이 가능합니다.

## 배경

이 프로젝트는 다음 Google Cloud Blog 포스트들에서 소개된 데이터 처리 패턴들을 기반으로 합니다:

- [Guide to common Cloud Dataflow use-case patterns, Part 1](https://cloud.google.com/blog/products/data-analytics/guide-to-common-cloud-dataflow-use-case-patterns-part-1)
- [Guide to common Cloud Dataflow use-case patterns, Part 2](https://cloud.google.com/blog/products/data-analytics/guide-to-common-cloud-dataflow-use-case-patterns-part-2)

> 💡 비록 게시글이 다소 오래되었지만, 이러한 패턴들은 현재 실무에서도 널리 사용되고 있는 검증된 방식들입니다.

## 프로젝트 구조

```text
root
|__pattern1
	 |__beam # Beam 모델로 표현된 파이프라인
	 |__flink # Flink로 표현된 파이프라인
|__pattern2
	 |__beam 
	 |__flink
... etc
```

각 패턴 디렉토리는 다음을 포함합니다:

- 패턴에 대한 상세 설명
- Apache Beam 구현체
- Apache Flink 구현체

## 학습 목표

이 프로젝트를 통해 다음을 달성할 수 있습니다:

1. **실시간 데이터 처리 패턴 이해**
    - 다양한 실제 시나리오에 적용 가능한 패턴 학습
    - 각 패턴의 장단점 및 적용 상황 이해

2. **기술 스택 비교 분석**
    - Apache Beam과 Apache Flink의 특징 비교
    - 각 기술의 장단점 및 적합한 사용 사례 파악
    - 실제 구현을 통한 실무적 직관 획득

3. **실무 적용력 향상**
    - 실제 데이터와 유사한 예제를 통한 실전 경험
    - 다양한 시나리오에 대한 해결 방안 학습

## 시작하기

각 패턴별 디렉토리에는 독립적인 README가 포함되어 있으며, 다음 내용을 담고 있습니다:

- 패턴 설명 및 유스케이스
- 환경 설정 방법
- 실행 방법
- 결과 확인 및 분석 방법

## 기여하기

이 프로젝트는 계속해서 발전하고 있습니다. 새로운 패턴 추가, 기존 구현 개선, 문서 보완 등 어떠한 형태의 기여도 환영합니다.
