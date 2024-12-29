package org.hgtech.worksystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.WorkVO;

import java.util.List;

@Mapper
public interface WorkRepository {

    int insert(WorkVO workVO);
    List<WorkVO> selectAll();
    WorkVO selectLast();
    WorkVO selectById(int wkId);
    List<WorkVO> selectByParentId(int wkParent);
    int delete(int wkId);
    int update(WorkVO workVO);
}