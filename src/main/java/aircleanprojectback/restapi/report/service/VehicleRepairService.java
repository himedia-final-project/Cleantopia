package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.car.dto.CarDTO;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.report.dto.VehicleRepairDTO;
import aircleanprojectback.restapi.report.entity.VehicleRepair;
import aircleanprojectback.restapi.report.repository.VehicleRepairRepository;
import aircleanprojectback.restapi.util.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private static final String IMAGE_DIR = "uploads/";

    public VehicleRepairService(VehicleRepairRepository vehicleRepairRepository, ModelMapper modelMapper) {
        this.vehicleRepairRepository = vehicleRepairRepository;
        this.modelMapper = modelMapper;
    }


    // 차량보고서 전체  조회
    public List<VehicleRepairDTO> getAllVehicleRepair() {

        List<VehicleRepair> vehicleRepairs = vehicleRepairRepository.findAll();
        List<VehicleRepairDTO> vehicleRepairDTOList = vehicleRepairs.stream()
                .map(vehicleRepair -> modelMapper.map(vehicleRepair, VehicleRepairDTO.class))
                .collect(Collectors.toList());

        return vehicleRepairDTOList;
    }

    // 차량보고서 세부조회
    public VehicleRepairDTO detailVehicleRepair(int vehicleReportCode) {

        VehicleRepair vehicleRepair = vehicleRepairRepository.findById(vehicleReportCode).get();
        VehicleRepairDTO vehicleRepairDTO = modelMapper.map(vehicleRepair, VehicleRepairDTO.class);
        return vehicleRepairDTO;

    }

    // 차량보고서 수정
    public VehicleRepair updateVehicleRepairStatus(int vehicleReportCode, String vehicleReportStatus) {

        VehicleRepair vehicleRepair = vehicleRepairRepository.findById(vehicleReportCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicleReportCode: " + vehicleReportCode));
        vehicleRepair.setVehicleRepairStatus(vehicleReportStatus);
        return vehicleRepairRepository.save(vehicleRepair);
    }


    // 차량수리보고서 등록
    public String insertVehicleReports(VehicleRepairDTO vehicleRepairDTO,
                                       MultipartFile beforeVehicleRepairImage,
                                       MultipartFile afterVehicleRepairImage) {

        VehicleRepair insertVehicleRepair = modelMapper.map(vehicleRepairDTO, VehicleRepair.class);
        insertVehicleRepair = vehicleRepairRepository.save(insertVehicleRepair);

        // 이미지
        String beforeImageName = UUID.randomUUID().toString().replace("-", "");
        String afterImageName = UUID.randomUUID().toString().replace("-", "");
        String beforeReplaceFileName = null;
        String afterReplaceFileName = null;

        try {
            if (beforeVehicleRepairImage != null && !beforeVehicleRepairImage.isEmpty()) {
                beforeReplaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, beforeImageName, beforeVehicleRepairImage);
                insertVehicleRepair.beforeVehiclePhoto(beforeReplaceFileName);
            }

            if (afterVehicleRepairImage != null && !afterVehicleRepairImage.isEmpty()) {
                afterReplaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, afterImageName, afterVehicleRepairImage);
                insertVehicleRepair.afterVehiclePhoto(afterReplaceFileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image file", e);
        }




        return "보고서 등록성공";
    }
}
