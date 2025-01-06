package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogVO {
    Integer lgId;
    LocalDateTime lgRegDate;
    LocalDateTime lgModDate;
    LocalDateTime lgRepDate;
    String lgDsc;
    String lgTag;
    Integer lgWkId;
}
