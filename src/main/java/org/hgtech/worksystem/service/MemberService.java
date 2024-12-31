package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.MemberDTO;
import org.hgtech.worksystem.DTO.WorkDTO;
import org.hgtech.worksystem.domain.MemberVO;
import org.hgtech.worksystem.domain.WorkVO;
import org.hgtech.worksystem.repository.MemberRepository;
import org.hgtech.worksystem.repository.WorkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    MemberRepository repository;

    ModelMapper mapper = new ModelMapper();

    public int register(MemberDTO memberDTO) {
        return repository.insert(mapper.map(memberDTO, MemberVO.class));
    }

    public MemberDTO getLast() {
        return mapper.map(repository.selectLast(), MemberDTO.class);
    }

    public List<MemberDTO> getAll() {
        return repository.selectAll().stream().map(vo -> mapper.map(vo, MemberDTO.class)).collect(Collectors.toList());
    }

    public MemberDTO getByMbId(int id) {
        return mapper.map(repository.selectByMbId(id), MemberDTO.class);
    }


    public int remove(int id) {
        return repository.delete(id);
    }

    public int modify(MemberDTO memberDTO) {
        return repository.update(mapper.map(memberDTO, MemberVO.class));
    }
}
