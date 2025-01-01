package org.hgtech.worksystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.WorkInfoVO;
import org.hgtech.worksystem.domain.WorkVO;

import java.util.List;

@Mapper
public interface WorkInfoRepository {
    public List<WorkInfoVO> selectAll();
    WorkInfoVO selectByRank(int rank);
    List<WorkInfoVO> selectByParent(int parent);
    WorkInfoVO selectLastAbove(int level , int rank);
    WorkInfoVO selectFirstBelow(int level , int rank);
}
