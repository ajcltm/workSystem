package org.hgtech.worksystem.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogDTO {
    Integer lgId;
    LocalDateTime lgRegDate;
    LocalDateTime lgModDate;
    LocalDateTime lgRepDate;
    String lgDsc;
    String lgTag;
    Integer lgWkId;
}
