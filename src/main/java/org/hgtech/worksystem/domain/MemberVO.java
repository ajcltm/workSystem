package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    Integer mbId;
    LocalDateTime mbRegDate;
    LocalDateTime mbModDate;
    String mbUserName;
    LocalDate mbUserBirth;
    String mbUserId;
    String mbUserPw;
}
