package org.hgtech.worksystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.LogInfoVO;

import java.util.List;

@Mapper
public interface LogInfoRepository {

    List<LogInfoVO> selectAll(int wkId);
}
