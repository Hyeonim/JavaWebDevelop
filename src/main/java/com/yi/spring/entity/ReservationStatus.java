package com.yi.spring.entity;

// 예약 상태에 따라 분기가 필요
public enum ReservationStatus {
    NONE(""), // 아무것도 입력되지 않았을 경우 들어가는 기본값. 현장 손님으로 가정
    WAIT("대기"), // 예약을 신청한 직후 (예약 승인을 기다리는 상태)
    USER_CANCEL("취소됨"), // 사용자가 예약을 취소한 상태
    REST_CANCEL("예약거절"), // 식당에서 사용자의 예약을 거절한 상태
    RESERVE_COMPLETED("예약완료"), // 예약 완료 상태
    NO_SHOW("노쇼"), // 사용자가 예약 시간이 되었음에도 오지 않은 상태(노쇼) 수동으로 조작
    EXPIRED("이용완료"), // 식당에서 예약자의 이용을 확인 한 경우와 예약수락 후 시간이 만료된 경우(현재 시간이 예약한 시간을 넘어간 경우)
    // 이후의 상태는 시간으로 판단
    REVIEW("리뷰작성완료");

    final private String name;

    private ReservationStatus( String name ) {
        this.name = name;
    }
    public String getName() { return name; }
}