package org.hgtech.worksystem.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    Integer wfId;
    LocalDateTime wfRegDate;
    LocalDateTime wfModDate;
    String wfName;
    String wfPath;
    String wfType;
    Integer wfWkId;
    Integer wfLgId;
}
