package org.hgtech.worksystem.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO {
    int wkId;
    LocalDateTime wkRegDate;
    LocalDateTime wkModDate;
    LocalDateTime wkRepDate;
    String wkTtl;
    String wkDsc;
    LocalDateTime wkDueDate;
    int wkUser;
    int wkResUser;
    String wkTag;
    String wkImp;
    int wkParent;
    int wkRank;
}
