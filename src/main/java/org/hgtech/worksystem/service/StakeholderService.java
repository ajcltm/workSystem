package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.FileDTO;
import org.hgtech.worksystem.DTO.StakeholderDTO;
import org.hgtech.worksystem.domain.FileVO;
import org.hgtech.worksystem.domain.StakeholderVO;
import org.hgtech.worksystem.repository.FileRepository;
import org.hgtech.worksystem.repository.StakeholderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StakeholderService {

    @Autowired
    StakeholderRepository repository;

    ModelMapper mapper = new ModelMapper();

    public int register(StakeholderDTO stakeholderDTO) {
        stakeholderDTO.setShRegDate(LocalDateTime.now());
        stakeholderDTO.setShModDate(LocalDateTime.now());
        return repository.insert(mapper.map(stakeholderDTO, StakeholderVO.class));
    }

    public StakeholderDTO getLast() {
        return mapper.map(repository.selectLast(), StakeholderDTO.class);
    }

    public List<StakeholderDTO> getAll() {
        return repository.selectAll().stream().map(vo -> mapper.map(vo, StakeholderDTO.class)).collect(Collectors.toList());
    }

    public StakeholderDTO getByWfId(int id) {
        return mapper.map(repository.selectByShId(id), StakeholderDTO.class);
    }

    public List<StakeholderDTO> getByWfWkId(int id) {
        return repository.selectByShWkId(id).stream().map(vo -> mapper.map(vo, StakeholderDTO.class)).collect(Collectors.toList());
    }

    public int remove(int id) {
        return repository.delete(id);
    }

    public int modify(StakeholderDTO stakeholderDTO) {
        stakeholderDTO.setShModDate(LocalDateTime.now());
        return repository.update(mapper.map(stakeholderDTO, StakeholderVO.class));
    }
}
