package org.hgtech.worksystem.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkInfoVO {
    WorkVO workVO;
    List<FileVO> fileVO;
    List<StakeholderVO> stakeholderVO;
}
