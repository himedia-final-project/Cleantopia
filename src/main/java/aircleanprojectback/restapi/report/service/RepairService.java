package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.report.dto.RepairDTO;
import aircleanprojectback.restapi.report.entity.Repair;
import aircleanprojectback.restapi.report.repository.RepairRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RepairService {

    private final RepairRepository repairRepository;
    private final ModelMapper modelMapper;

    public RepairService(RepairRepository repairRepository, ModelMapper modelMapper) {
        this.repairRepository = repairRepository;
        this.modelMapper = modelMapper;
    }

    // 지점 수리보고서 전체조회
    public List<RepairDTO> AllFindRepair() {

        List<Repair> repairs = repairRepository.findAll();
        List<RepairDTO> repairDTO = repairs.stream()
                .map(repair -> modelMapper.map(repair, RepairDTO.class))
                .collect(Collectors.toList());

        return repairDTO;
    }


    // 지점 수리보고서 세부조회
    public RepairDTO FindRepairByReportCode(int repairReportCode) {

        Repair repair = repairRepository.findById(repairReportCode).get();
        RepairDTO repairDTO = modelMapper.map(repair, RepairDTO.class);
        return repairDTO;
    }

    // 지점 수리보고서 등록
    @Transactional
    public Object insertRepair(RepairDTO repairDTO) {
        Repair repair = modelMapper.map(repairDTO, Repair.class);
        repairRepository.save(repair);

        return repair;
    }

    // 지점 수리보고서 수정
    @Transactional
    public Repair updateRepair(int repairReportCode, RepairDTO repairDTO) {

        Repair repair = repairRepository.findById(repairReportCode)
                .orElseThrow(() -> new RuntimeException("수리보고서를 찾을수 없습니다. ID: " + repairReportCode));

        repair = repair
                .repairReportStatus(repairDTO.getRepairReportStatus())
                .repairContent(repairDTO.getRepairContent())
                .facilityCount(repairDTO.getFacilityCount())
                .facilityCode(repairDTO.getFacilityCode());

        repairRepository.save(repair);
        return repair;
    }

    // 지점 수리보고서 삭제
    public String  deleteRepair(int repairReportCode) {

        repairRepository.deleteById(repairReportCode);
        return "삭제성공";
    }
}
