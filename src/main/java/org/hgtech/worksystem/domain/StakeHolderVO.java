package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StakeHolderVO {
    int shId;
    LocalDateTime shRegDate;
    LocalDateTime shModDate;
    String shName;
    String shCompany;
    String shMobile;
    String shPhone;
    String shEmail;
    String shAddress;
    String shWkId;
}
