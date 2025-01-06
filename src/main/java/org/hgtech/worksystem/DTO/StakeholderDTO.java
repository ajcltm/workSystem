package org.hgtech.worksystem.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StakeholderDTO {
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
