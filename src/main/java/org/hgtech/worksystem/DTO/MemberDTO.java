package org.hgtech.worksystem.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    Integer mbId;
    LocalDateTime mbRegDate;
    LocalDateTime mbModDate;
    String mbUserName;
    LocalDate mbUserBirth;
    String mbUserId;
    String mbUserPw;
}
