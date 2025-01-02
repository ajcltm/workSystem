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

    @Autowired
    WorkRepository workRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<WorkInfoDTO> getAll() {
        return workInfoRepository.selectAll().stream().map(vo -> modelMapper.map(vo, WorkInfoDTO.class)).collect(Collectors.toList());
    }

    public void rankUp(int rank) {
//      변경하려는 랭크 조회
        WorkInfoVO currentVO = workInfoRepository.selectByRank(rank);
//        변경하려는 랭크를 부모로 두고 있는 레코드 조회
        List<WorkInfoVO> currentChildVO = workInfoRepository.selectByParent(rank);
//        변경하려는 랭크
        int currentRank = currentVO.getWkRank();
//        변경하려는 레벨
        int currentLevel = currentVO.getWkLevel();
//        변경하려는 랭크의 바로위 레코드 조회
        WorkInfoVO aboveVO = workInfoRepository.selectLastAbove(currentLevel, rank);
//        변경하려는 랭크의 바로위 랭크
        int aboveRank = aboveVO.getWkRank();
        List<WorkInfoVO> aboveChildVO = workInfoRepository.selectByParent(aboveRank);

//        실제 변경
        currentVO.setWkRank(aboveRank);
        workRepository.update(modelMapper.map(currentVO, WorkVO.class));
        aboveVO.setWkRank(currentRank);
        workRepository.update(modelMapper.map(aboveVO, WorkVO.class));
        if ( currentChildVO != null) {
            for (WorkInfoVO vo : currentChildVO) {
                vo.setWkParent(aboveRank);
                workRepository.update(modelMapper.map(vo, WorkVO.class));
            }
        }

        if (aboveChildVO != null) {
            for (WorkInfoVO vo : aboveChildVO) {
                vo.setWkParent(currentRank);
                workRepository.update(modelMapper.map(vo, WorkVO.class));
            }
        }
    }

    public void rankDown(int rank) {
//      변경하려는 랭크 조회
        WorkInfoVO currentVO = workInfoRepository.selectByRank(rank);
//        변경하려는 랭크를 부모로 두고 있는 레코드 조회
        List<WorkInfoVO> currentChildVO = workInfoRepository.selectByParent(rank);
//        변경하려는 랭크
        int currentRank = currentVO.getWkRank();
//        변경하려는 레벨
        int currentLevel = currentVO.getWkLevel();
//        변경하려는 랭크의 바로 아래 레코드 조회
        WorkInfoVO belowVO = workInfoRepository.selectFirstBelow(currentLevel, rank);
//        변경하려는 랭크의 바로 아래 랭크
        int belowRank = belowVO.getWkRank();
        List<WorkInfoVO> belowChildVO = workInfoRepository.selectByParent(belowRank);

//        실제 변경
        currentVO.setWkRank(belowRank);
        workRepository.update(modelMapper.map(currentVO, WorkVO.class));
        belowVO.setWkRank(currentRank);
        workRepository.update(modelMapper.map(belowVO, WorkVO.class));
        if (currentChildVO != null) {
            for (WorkInfoVO vo : currentChildVO) {
                vo.setWkParent(belowRank);
                workRepository.update(modelMapper.map(vo, WorkVO.class));
            }
        }

        if (belowChildVO != null) {
            for (WorkInfoVO vo : belowChildVO) {
                vo.setWkParent(currentRank);
                workRepository.update(modelMapper.map(vo, WorkVO.class));
            }
        }
    }
}
