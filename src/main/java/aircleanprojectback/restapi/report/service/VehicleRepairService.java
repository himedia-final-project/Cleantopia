package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.report.dto.VehicleRepairDTO;
import aircleanprojectback.restapi.report.entity.VehicleRepair;
import aircleanprojectback.restapi.report.repository.VehicleRepairRepository;
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
public class VehicleRepairService {
    private final VehicleRepairRepository vehicleRepairRepository;
    private final ModelMapper modelMapper;

    @Value("${image.image-dir}")
    private  String IMAGE_DIR;

    @Value("${image.image-url}")
    private String IMAGE_URL;

    public VehicleRepairService(VehicleRepairRepository vehicleRepairRepository, ModelMapper modelMapper) {
        this.vehicleRepairRepository = vehicleRepairRepository;
        this.modelMapper = modelMapper;
    }


    // 차량보고서 전체  조회
    public Page<VehicleRepairDTO> getAllVehicleRepair(Criteria vehicleRepairCriteria) {

        Pageable vehicleRepairPageable = PageRequest.of(vehicleRepairCriteria.getPageNum() -1, vehicleRepairCriteria.getAmount());
        Page<VehicleRepair> vehicleRepairPage = vehicleRepairRepository.findAll(vehicleRepairPageable);
        Page<VehicleRepairDTO> vehicleRepairDTO = vehicleRepairPage.map(vehicleRepair -> modelMapper.map(vehicleRepair, VehicleRepairDTO.class));

        return vehicleRepairDTO;
    }


    // 차량보고서 세부조회
    public VehicleRepairDTO detailVehicleRepair(int vehicleReportCode) {

        VehicleRepair vehicleRepair = vehicleRepairRepository.findById(vehicleReportCode).get();
        VehicleRepairDTO vehicleRepairDTO = modelMapper.map(vehicleRepair, VehicleRepairDTO.class);
        return vehicleRepairDTO;

    }

    // 차량보고서 승인/ 반려
    public VehicleRepair updateVehicleRepairStatus(int vehicleReportCode, String vehicleReportStatus, String vehicleRepairApprove) {

        VehicleRepair vehicleRepair = vehicleRepairRepository.findById(vehicleReportCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicleReportCode: " + vehicleReportCode));
        vehicleRepair.setVehicleRepairStatus(vehicleReportStatus);
        vehicleRepair.setVehicleRepairApprove(vehicleRepairApprove);

        VehicleRepair vehicleRepair1 = vehicleRepairRepository.save(vehicleRepair);



        return vehicleRepair1;

    }


    // 차량수리보고서 등록
    @Transactional
    public String insertVehicleReports(VehicleRepairDTO vehicleRepairDTO,
                                       MultipartFile beforeVehiclePhoto,
                                       MultipartFile afterVehiclePhoto) {

        VehicleRepair insertVehicleRepair = modelMapper.map(vehicleRepairDTO, VehicleRepair.class);

        log.info("insertVehicleRepair = {}", insertVehicleRepair);
        System.out.println("insertVehicleRepair = " + insertVehicleRepair);

        // 이미지
        String beforeImageName = UUID.randomUUID().toString().replace("-", "");
        String afterImageName = UUID.randomUUID().toString().replace("-", "");
        String beforeReplaceFileName = null;
        String afterReplaceFileName = null;

        try {
            if (beforeVehiclePhoto != null && !beforeVehiclePhoto.isEmpty()) {
                beforeReplaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, beforeImageName, beforeVehiclePhoto);
                insertVehicleRepair.beforeVehiclePhoto(beforeReplaceFileName);
                System.out.println("여기로 오긴 하니?");
                System.out.println("beforeReplaceFileName = " + beforeReplaceFileName);
            }

            if (afterVehiclePhoto != null && !afterVehiclePhoto.isEmpty()) {
                afterReplaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, afterImageName, afterVehiclePhoto);
                insertVehicleRepair.afterVehiclePhoto(afterReplaceFileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image file", e);
        }

       vehicleRepairRepository.save(insertVehicleRepair);
        vehicleRepairRepository.flush();

        return "보고서 등록성공";
    }



}