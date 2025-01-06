package org.hgtech.worksystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.FileVO;

import java.util.List;

@Mapper
public interface FileRepository {
    int insert(FileVO fileVO);
    List<FileVO> selectAll();
    FileVO selectLast();
    FileVO selectByWfId(int wfId);
    List<FileVO> selectByWfWkId(int wfWkId);
    List<FileVO> selectByWfLgId(int wfLgId);
    int delete(int wfId);
    int update(FileVO fileVO);
}
