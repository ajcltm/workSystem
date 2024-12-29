package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    int mbId;
    LocalDateTime mbRegDate;
    LocalDateTime mbModDate;
    String mbUserName;
    LocalDate mbBirth;
    String mbUserId;
    String mbUserPw;
}
