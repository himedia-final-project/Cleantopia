package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.report.dto.RepairDTO;
import aircleanprojectback.restapi.report.entity.Expense;
import aircleanprojectback.restapi.report.entity.Repair;
import aircleanprojectback.restapi.report.repository.RepairRepository;
import aircleanprojectback.restapi.util.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RepairService {

    private final RepairRepository repairRepository;
    private final ModelMapper modelMapper;

    @Value("${image.image-dir}")
    private  String IMAGE_DIR;

    @Value("${image.image-url}")
    private String IMAGE_URL;

    public RepairService(RepairRepository repairRepository, ModelMapper modelMapper) {
        this.repairRepository = repairRepository;
        this.modelMapper = modelMapper;
    }

    // 지점 수리보고서 전체조회
    public Page<RepairDTO> AllFindRepair(Criteria repairCriteria) {

        Pageable repairPageable = PageRequest.of(repairCriteria.getPageNum() -1, repairCriteria.getAmount());

        Page<Repair> repairs = repairRepository.findAll(repairPageable);
        Page<RepairDTO> repairDTO = repairs.map(repair -> modelMapper.map(repair, RepairDTO.class));

        return repairDTO;
    }

//    public List<RepairDTO> AllFindRepair() {
//
//        List<Repair> repairs = repairRepository.findAll();
//        List<RepairDTO> repairDTO = repairs.stream()
//                .map(repair -> modelMapper.map(repair, RepairDTO.class))
//                .collect(Collectors.toList());
//
//        return repairDTO;
//    }


    // 지점 수리보고서 세부조회
    public RepairDTO FindRepairByReportCode(int repairReportCode) {

        Repair repair = repairRepository.findById(repairReportCode).get();
        RepairDTO repairDTO = modelMapper.map(repair, RepairDTO.class);
        return repairDTO;
    }

    // 지점 수리보고서 등록
    @Transactional
    public Object insertRepair(RepairDTO repairDTO, MultipartFile repairPhoto) {

        Repair repair = modelMapper.map(repairDTO, Repair.class);

        String repairImageName = UUID.randomUUID().toString().replace("-", "");
        String repairReplaceFileName = null;
        try {
            if (repairPhoto != null && !repairPhoto.isEmpty()) {
                repairReplaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, repairReplaceFileName, repairPhoto);
                repair.repairPhoto(repairReplaceFileName);
                System.out.println("여기로 오긴 하니?");
                System.out.println("repairReplaceFileName = " + repairReplaceFileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image file", e);
        }


        repairRepository.save(repair);

        return repair;
    }

    // 지점 수리보고서 수정
    @Transactional
    public Repair updateRepair(int repairReportCode, RepairDTO repairDTO, MultipartFile repairPhoto) {

        Repair repair = repairRepository.findById(repairReportCode)
                .orElseThrow(() -> new RuntimeException("수리보고서를 찾을수 없습니다. ID: " + repairReportCode));

        repair = repair
                .repairReportStatus(repairDTO.getRepairReportStatus())
                .repairContent(repairDTO.getRepairContent())
                .facilityCount(repairDTO.getFacilityCount())
                .facilityCode(repairDTO.getFacilityCode())
                .facilityType(repairDTO.getFacilityType())
                .repairPhoto(repairDTO.getRepairPhoto());

        String repairImageNames = UUID.randomUUID().toString().replace("-", "");
        String repairReplaceFileNames = null;
        try {
            if (repairPhoto != null && !repairPhoto.isEmpty()) {
                repairReplaceFileNames = FileUploadUtils.saveFile(IMAGE_DIR, repairReplaceFileNames, repairPhoto);
                repair.repairPhoto(repairReplaceFileNames);
                System.out.println("여기로 오긴 하니?");
                System.out.println("repairReplaceFileName = " + repairReplaceFileNames);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image file", e);
        }

        repairRepository.save(repair);
        return repair;
    }

    // 시설물수리보고서  삭제
    public String  deleteRepair(int repairReportCode) {

        repairRepository.deleteById(repairReportCode);
        return "삭제성공";
    }

    // 시설물수리보고서 승인/반려
    public Repair updateRepairStatus(int repairReportCode, String repairReportStatus) {

        Repair repair = repairRepository.findById(repairReportCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid repairReportCode: " + repairReportCode));
        repair.repairReportStatus(repairReportStatus);
        return repairRepository.save(repair);
    }


}
