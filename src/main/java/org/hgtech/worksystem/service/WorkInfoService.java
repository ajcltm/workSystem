package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.WorkInfoDTO;
import org.hgtech.worksystem.domain.WorkInfoVO;
import org.hgtech.worksystem.domain.WorkVO;
import org.hgtech.worksystem.repository.WorkInfoRepository;
import org.hgtech.worksystem.repository.WorkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkInfoService {

    @Autowired
    WorkInfoRepository workInfoRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<WorkInfoDTO> getAll(int mbId) {
        return workInfoRepository.selectAll(mbId).stream().map(vo -> modelMapper.map(vo, WorkInfoDTO.class)).collect(Collectors.toList());
    }
}
