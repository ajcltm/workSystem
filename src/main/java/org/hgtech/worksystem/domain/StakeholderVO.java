package org.hgtech.worksystem.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StakeholderVO {
    Integer shId;
    LocalDateTime shRegDate;
    LocalDateTime shModDate;
    String shName;
    String shCompany;
    String shMobile;
    String shPhone;
    String shEmail;
    String shAddress;
    Integer shWkId;
}
