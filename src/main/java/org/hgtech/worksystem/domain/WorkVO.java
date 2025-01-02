package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkVO {
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
