package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
    int wfId;
    LocalDateTime wfRegDate;
    LocalDateTime wfModDate;
    String wfName;
    String wfPath;
    String wfType;
    int wfWkId;
}
