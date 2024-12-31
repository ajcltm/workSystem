package org.hgtech.worksystem.repository;
import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.StakeholderVO;

import java.util.List;

@Mapper
public interface StakeholderRepository {
    int insert(StakeholderVO stakeHolderVO);
    List<StakeholderVO> selectAll();
    StakeholderVO selectLast();
    StakeholderVO selectByShId(int shId);
    List<StakeholderVO> selectByShWkId(int shWkId);
    int delete(int shId);
    int update(StakeholderVO stakeHolderVO);
}
