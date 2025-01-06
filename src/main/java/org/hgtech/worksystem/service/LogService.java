package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.LogDTO;
import org.hgtech.worksystem.DTO.StakeholderDTO;
import org.hgtech.worksystem.domain.LogVO;
import org.hgtech.worksystem.domain.StakeholderVO;
import org.hgtech.worksystem.repository.LogRepository;
import org.hgtech.worksystem.repository.StakeholderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Autowired
    LogRepository repository;

    ModelMapper mapper = new ModelMapper();

    public int register(LogDTO logDTO) {
        logDTO.setLgRegDate(LocalDateTime.now());
        logDTO.setLgModDate(LocalDateTime.now());
        return repository.insert(mapper.map(logDTO, LogVO.class));
    }

    public LogDTO getLast() {
        return mapper.map(repository.selectLast(), LogDTO.class);
    }

    public List<LogDTO> getAll() {
        return repository.selectAll().stream().map(vo -> mapper.map(vo, LogDTO.class)).collect(Collectors.toList());
    }

    public LogDTO getByWfId(int id) {
        return mapper.map(repository.selectByLgId(id), LogDTO.class);
    }

    public List<LogDTO> getByWfWkId(int id) {
        return repository.selectByLgWkId(id).stream().map(vo -> mapper.map(vo, LogDTO.class)).collect(Collectors.toList());
    }

    public int remove(int id) {
        return repository.delete(id);
    }

    public int modify(LogDTO logDTO) {
        logDTO.setLgModDate(LocalDateTime.now());
        return repository.update(mapper.map(logDTO, LogVO.class));
    }
}
