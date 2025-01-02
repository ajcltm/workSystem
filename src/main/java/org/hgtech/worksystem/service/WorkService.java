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

//    public void changeParent(int parent, int wkId) {
////        변경 대상 레코드 조회
//        WorkVO currentVO = repository.selectByWkId(wkId);
//        int currentRank = currentVO.getWkRank();
////        parent의 자식 레크드 조회
//        List<WorkVO> workVOList = repository.selectByParent(parent);
//        if (workVOList != null) {
//            int maxRank = 0 ;
//            WorkVO maxVO = null;
//            for (WorkVO vo: workVOList) {
//                int rank = vo.getWkRank();
//                if (rank > maxRank) {
//                    maxRank = rank;
//                    maxVO = vo;
//                }
//            }
//            currentVO.setWkRank(maxRank);
//            maxVO.setWkRank(currentRank);
//            repository.update(maxVO);
//
//            List<WorkVO> currentChild = repository.selectByParent(currentRank);
//            if (currentChild != null) {
//                for (WorkVO vo: currentChild) {
//                    vo.setWkParent(maxRank);
//                    repository.update(vo);
//                }
//            }
//
//            List<WorkVO> maxChild = repository.selectByParent(maxRank);
//            if (maxChild != null) {
//                for (WorkVO vo: maxChild) {
//                    vo.setWkParent(currentRank);
//                    repository.update(vo);
//                }
//            }
//        }
//
////        parent 변경
//        currentVO.setWkParent(parent);
//        repository.update(currentVO);
//    }
}
