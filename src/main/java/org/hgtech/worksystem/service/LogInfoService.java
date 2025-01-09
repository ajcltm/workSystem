package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.LogInfoDTO;
import org.hgtech.worksystem.DTO.WorkInfoDTO;
import org.hgtech.worksystem.repository.LogInfoRepository;
import org.hgtech.worksystem.repository.WorkInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogInfoService {

    @Autowired
    LogInfoRepository repository;

    ModelMapper modelMapper = new ModelMapper();

    public List<LogInfoDTO> getAll(int wkId) {
        return repository.selectAll(wkId).stream().map(vo -> modelMapper.map(vo, LogInfoDTO.class)).collect(Collectors.toList());
    }
}
