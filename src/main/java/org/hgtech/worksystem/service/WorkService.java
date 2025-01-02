package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.WorkDTO;
import org.hgtech.worksystem.domain.WorkVO;
import org.hgtech.worksystem.repository.WorkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WorkService {
    @Autowired
    WorkRepository repository;

    ModelMapper mapper = new ModelMapper();

    public int register(WorkDTO workDTO) {
        workDTO.setWkRegDate(LocalDateTime.now());
        workDTO.setWkModDate(LocalDateTime.now());
        repository.insert(mapper.map(workDTO, WorkVO.class));
        workDTO.setWkRank(repository.selectLast().getWkId());
        repository.update(mapper.map(workDTO, WorkVO.class));
        return 1;
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
        workDTO.setWkModDate(LocalDateTime.now());
        return repository.update(mapper.map(workDTO, WorkVO.class));
    }

    public void changeRank(int rank, WorkVO workVO) {
        workVO.setWkRank(rank);
        System.out.println("changeRank : " + workVO.getWkRank()+ " ->" + rank  + workVO);
        repository.update(workVO);
    }

    public void changeParent(int parent, int wkId) {
        WorkVO currentVO = repository.selectByWkId(wkId);
        currentVO.setWkParent(parent);
        repository.update(currentVO);
        int currentRank = currentVO.getWkRank();

        List<WorkVO> child = repository.selectByParent(parent);
        if (child != null) {
            Map<Integer, List<WorkVO>> map = new HashMap<>();
            int forChangeNum = 0;
            int maxNum = 0;
            for (WorkVO vo:child) {
                int rank = vo.getWkRank();
                if (rank > maxNum) {
                    maxNum = rank;
                }
                if (forChangeNum != 0) {
                    map.put(forChangeNum, repository.selectByParent(vo.getWkRank()));
                    changeRank(forChangeNum, vo);
                    forChangeNum = rank;
                }
                if (rank > currentRank && forChangeNum == 0) {
                    map.put(currentRank, repository.selectByParent(vo.getWkRank()));
                    changeRank(currentRank, vo);
                    forChangeNum = rank;
                }
            }
            if (maxNum > currentRank) {
                map.put(maxNum, repository.selectByParent(currentVO.getWkRank()));
                changeRank(maxNum, currentVO);
            }

            for (Map.Entry<Integer, List<WorkVO>> entry : map.entrySet()) {
                changParentBath(entry.getKey(), entry.getValue());
            }
        }
    }

    public void changParentBath(int rank, List<WorkVO> child) {
        if (child != null) {
            for (WorkVO vo: child) {
                vo.setWkParent(rank);
                repository.update(vo);
            }
        }
    }
}
