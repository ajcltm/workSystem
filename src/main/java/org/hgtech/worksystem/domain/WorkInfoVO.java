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
    Integer wkLevel;
    String wkPath;
    List<FileVO> fileVO;
    List<StakeholderVO> stakeholderVO;
}
