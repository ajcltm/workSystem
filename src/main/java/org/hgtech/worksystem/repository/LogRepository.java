package org.hgtech.worksystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.LogVO;
import org.hgtech.worksystem.domain.WorkVO;

import java.util.List;

@Mapper
public interface LogRepository {
    int insert(LogVO LogVO);
    List<LogVO> selectAll();
    LogVO selectLast();
    LogVO selectByLgId(int lgId);
    List<LogVO> selectByLgWkId(int wkId);
    int delete(int lgId);
    int update(LogVO logVO);
}
