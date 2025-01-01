package org.hgtech.worksystem.service;

import org.hgtech.worksystem.DTO.WorkInfoDTO;
import org.hgtech.worksystem.domain.WorkInfoVO;
import org.hgtech.worksystem.repository.WorkInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkInfoService {

    @Autowired
    WorkInfoRepository repository;

    ModelMapper modelMapper = new ModelMapper();

    public List<WorkInfoDTO> getAll() {
        return repository.selectAll().stream().map(vo -> modelMapper.map(vo, WorkInfoDTO.class)).collect(Collectors.toList());
    }

    public void rankUp(int rank) {
        WorkInfoVO currentVO = repository.selectByRank(rank);
        List<WorkInfoVO> currentChildVO = repository.selectByParent(rank);
        int currentRank = currentVO.getWkRank();
        int currentLevel = currentVO.getWkLevel();
        WorkInfoVO aboveVO = repository.selectLastAbove(currentLevel, rank);
        int aboveRank = aboveVO.getWkRank();
        List<WorkInfoVO> aboveChildVO = repository.selectByParent(aboveRank);

        currentVO.setWkRank(aboveRank);
        repository.update()
        aboveVO.setWkRank(currentRank);
        for (WorkInfoVO vo : currentChildVO) {
            vo.setWkRank(aboveRank);
        }

        for (WorkInfoVO vo : aboveChildVO) {
            vo.setWkRank(currentRank);
        }
    }
}
