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
public class LogInfoDTO {
    Integer lgId;
    LocalDateTime lgRegDate;
    LocalDateTime lgModDate;
    LocalDateTime lgRepDate;
    String lgDsc;
    String lgTag;
    Integer lgWkId;
    List<FileVO> fileVO;
    List<StakeholderVO> StakeholderVO;
}
