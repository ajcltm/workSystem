package org.hgtech.worksystem.DTO;

import lombok.*;
import org.hgtech.worksystem.domain.FileVO;
import org.hgtech.worksystem.domain.StakeholderVO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkInfoDTO {
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
    int wkLevel;
    String wkPath;
    List<FileVO> fileVO;
    List<StakeholderVO> stakeholderVO;
}
