package org.hgtech.worksystem.domain;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInfoVO {
    Integer lgId;
    LocalDateTime lgRegDate;
    LocalDateTime lgModDate;
    LocalDateTime lgRepDate;
    String lgDsc;
    String lgTag;
    Integer lgWkId;
    List<FileVO> fileVO;
    List<StakeholderVO> stakeholderVO;
}
