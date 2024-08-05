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

        if (repairPhoto != null && !repairPhoto.isEmpty()) {
            String repairReplaceFileName = UUID.randomUUID().toString().replace("-", "");
            try {
                String savedFileName = FileUploadUtils.saveFile(IMAGE_DIR, repairReplaceFileName, repairPhoto);
                repair.setRepairPhoto(savedFileName);
                System.out.println("Saved File Name: " + savedFileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image file", e);
            }
        }

        return repairRepository.save(repair);
    }


    // 지점 수리보고서 수정
    @Transactional
    public Repair updateRepair(int repairReportCode, RepairDTO repairDTO, MultipartFile repairPhoto) {
        Repair repair = repairRepository.findById(repairReportCode)
                .orElseThrow(() -> new RuntimeException("수리보고서를 찾을 수 없습니다. ID: " + repairReportCode));

        repair.setRepairReportStatus(repairDTO.getRepairReportStatus());
        repair.setRepairContent(repairDTO.getRepairContent());
        repair.setFacilityCount(repairDTO.getFacilityCount());
        repair.setFacilityType(repairDTO.getFacilityType());

        if (repairPhoto != null && !repairPhoto.isEmpty()) {
            String repairReplaceFileName = UUID.randomUUID().toString().replace("-", "");
            try {
                String savedFileName = FileUploadUtils.saveFile(IMAGE_DIR, repairReplaceFileName, repairPhoto);
                repair.setRepairPhoto(savedFileName);
                System.out.println("Saved File Name: " + savedFileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save image file", e);
            }
        }

        Repair repair1 = repairRepository.save(repair);
        repairRepository.flush();

        return repair1;
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
        repair.setRepairReportStatus(repairReportStatus);
        return repairRepository.save(repair);
    }


}
