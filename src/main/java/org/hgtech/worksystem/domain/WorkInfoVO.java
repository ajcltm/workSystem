package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkInfoVO {
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
    List<FileVO> fileVO;
    List<StakeholderVO> stakeholderVO;
}
