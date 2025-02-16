package org.hgtech.worksystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.WorkInfoVO;
import org.hgtech.worksystem.domain.WorkVO;

import java.util.List;

@Mapper
public interface WorkInfoRepository {
    public List<WorkInfoVO> selectAll(int mbId);
}
