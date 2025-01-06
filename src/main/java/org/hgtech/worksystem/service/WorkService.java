package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.WorkDTO;
import org.hgtech.worksystem.domain.WorkInfoVO;
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
        System.out.println("origin ... "+workDTO);
        workDTO.setWkRegDate(LocalDateTime.now());
        workDTO.setWkModDate(LocalDateTime.now());
        workDTO.setWkRank(getRank());
        return repository.insert(mapper.map(workDTO, WorkVO.class));
    }

    public int getRank() {
        WorkVO workVO = repository.selectLast();
        System.out.println("selectLast ... "+workVO);
        if (workVO != null) {
            return workVO.getWkId()+1;
        }
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

    public void rankUp(int id) {
//      변경하려는 레코드 조회
        WorkVO currentVO = repository.selectByWkId(id);
//        변경하려는 랭크
        int currentRank = currentVO.getWkRank();
        Integer currentParent = currentVO.getWkParent();

//        변경하려는 랭크를 부모로 두고 있는 레코드 조회
        List<WorkVO> currentChildVO = repository.selectByParent(currentRank);

//        변경하려는 랭크의 바로위 레코드 조회
        WorkVO aboveVO = repository.selectLastAbove(currentParent, currentRank);
//        변경하려는 랭크의 바로위 랭크
        if (aboveVO != null) {
            int aboveRank = aboveVO.getWkRank();
            List<WorkVO> aboveChildVO = repository.selectByParent(aboveRank);
//        실제 변경
            currentVO.setWkRank(aboveRank);
            repository.update(currentVO);
            aboveVO.setWkRank(currentRank);
            repository.update(aboveVO);
            if ( currentChildVO != null) {
                for (WorkVO vo : currentChildVO) {
                    vo.setWkParent(aboveRank);
                    repository.update(vo);
                }
            }

            if (aboveChildVO != null) {
                for (WorkVO vo : aboveChildVO) {
                    vo.setWkParent(currentRank);
                    repository.update(vo);
                }
            }
        }

    }

    public void rankDown(int id) {
//      변경하려는 레코드 조회
        WorkVO currentVO = repository.selectByWkId(id);
//        변경하려는 랭크
        int currentRank = currentVO.getWkRank();
        Integer currentParent = currentVO.getWkParent();

//        변경하려는 랭크를 부모로 두고 있는 레코드 조회
        List<WorkVO> currentChildVO = repository.selectByParent(currentRank);

//        변경하려는 랭크의 바로 아래 레코드 조회
        WorkVO belowVO = repository.selectFirstBelow(currentParent, currentRank);
//        변경하려는 랭크의 바로위 랭크

        if (belowVO != null) {
            int belowRank = belowVO.getWkRank();
            List<WorkVO> belowChildVO = repository.selectByParent(belowRank);

//        실제 변경
            currentVO.setWkRank(belowRank);
            repository.update(currentVO);
            belowVO.setWkRank(currentRank);
            repository.update(belowVO);
            if ( currentChildVO != null) {
                for (WorkVO vo : currentChildVO) {
                    vo.setWkParent(belowRank);
                    repository.update(vo);
                }
            }

            if (belowChildVO != null) {
                for (WorkVO vo : belowChildVO) {
                    vo.setWkParent(currentRank);
                    repository.update(vo);
                }
            }

        }

    }
}
