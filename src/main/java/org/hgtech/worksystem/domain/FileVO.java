package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {
    Integer wfId;
    LocalDateTime wfRegDate;
    LocalDateTime wfModDate;
    String wfName;
    String wfPath;
    String wfType;
    Integer wfWkId;
}
