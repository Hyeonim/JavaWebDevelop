package com.yi.spring.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter @Setter
public class ChatDTO {
    Long id;
    Long chatRoomId;
    Long memberId;
    String message;
    String region;
    String nickname;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime regDate;
}