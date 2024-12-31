package org.hgtech.worksystem.repository;

import org.apache.ibatis.annotations.Mapper;
import org.hgtech.worksystem.domain.MemberVO;
import org.hgtech.worksystem.domain.WorkVO;

import java.util.List;

@Mapper
public interface MemberRepository {
    int insert(MemberVO memberVO);
    List<MemberVO> selectAll();
    MemberVO selectLast();
    MemberVO selectByMbId(int mbId);
    int delete(int mbId);
    int update(MemberVO memberVO);
}
