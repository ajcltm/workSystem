package org.hgtech.worksystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.WorkInfoVO;
import org.hgtech.worksystem.domain.WorkVO;

import java.util.List;

@Mapper
public interface WorkRepository {

    int insert(WorkVO workVO);
    List<WorkVO> selectAll();
    WorkVO selectLast();
    WorkVO selectByWkId(Integer wkId);
    List<WorkVO> selectByParent(Integer wkParent);
    int delete(int wkId);
    int update(WorkVO workVO);

    WorkVO selectLastAbove(Integer parent, Integer rank);
    WorkVO selectFirstBelow(Integer parent, Integer rank);
}
