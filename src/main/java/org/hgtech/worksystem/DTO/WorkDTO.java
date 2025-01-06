package org.hgtech.worksystem.DTO;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO {
    Integer wkId;
    LocalDateTime wkRegDate;
    LocalDateTime wkModDate;
    LocalDateTime wkRepDate;
    String wkTtl;
    String wkDsc;
    LocalDateTime wkDueDate;
    Integer wkUser;
    Integer wkResUser;
    String wkTag;
    String wkImp;
    Integer wkParent;
    Integer wkRank;
}
