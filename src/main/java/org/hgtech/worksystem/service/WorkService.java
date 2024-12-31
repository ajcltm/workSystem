package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.WorkDTO;
import org.hgtech.worksystem.domain.WorkVO;
import org.hgtech.worksystem.repository.WorkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkService {
    @Autowired
    WorkRepository repository;

    ModelMapper mapper = new ModelMapper();

    public int register(WorkDTO workDTO) {
        return repository.insert(mapper.map(workDTO, WorkVO.class));
    }

    public WorkDTO getLast() {
        return mapper.map(repository.selectLast(), WorkDTO.class);
    }

    public List<WorkDTO> getAll() {
        return repository.selectAll().stream().map(vo -> mapper.map(vo, WorkDTO.class)).collect(Collectors.toList());
    }

    public WorkDTO getByWkId(int id) {
        return mapper.map(repository.selectByWkId(id), WorkDTO.class);
    }

    public List<WorkDTO> getByParent(int parent) {
        return repository.selectByParent(parent).stream().map(vo -> mapper.map(vo, WorkDTO.class)).collect(Collectors.toList());
    }

    public int remove(int id) {
        return repository.delete(id);
    }

    public int modify(WorkDTO workDTO) {
        return repository.update(mapper.map(workDTO, WorkVO.class));
    }
}
