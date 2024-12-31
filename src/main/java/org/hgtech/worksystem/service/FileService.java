package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.FileDTO;
import org.hgtech.worksystem.domain.FileVO;
import org.hgtech.worksystem.repository.FileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    FileRepository repository;

    ModelMapper mapper = new ModelMapper();

    public int register(FileDTO fileDTO) {
        return repository.insert(mapper.map(fileDTO, FileVO.class));
    }

    public FileDTO getLast() {
        return mapper.map(repository.selectLast(), FileDTO.class);
    }

    public List<FileDTO> getAll() {
        return repository.selectAll().stream().map(vo -> mapper.map(vo, FileDTO.class)).collect(Collectors.toList());
    }

    public FileDTO getByWfId(int id) {
        return mapper.map(repository.selectByWfId(id), FileDTO.class);
    }

    public List<FileDTO> getByWfWkId(int id) {
        return repository.selectByWfWkId(id).stream().map(vo -> mapper.map(vo, FileDTO.class)).collect(Collectors.toList());
    }

    public int remove(int id) {
        return repository.delete(id);
    }

    public int modify(FileDTO fileDTO) {
        return repository.update(mapper.map(fileDTO, FileVO.class));
    }
}
