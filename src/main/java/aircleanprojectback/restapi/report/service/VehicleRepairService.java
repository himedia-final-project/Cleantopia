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

    // 차량보고서 등록
    @Transactional
    public String insertVehicleRepair(VehicleRepairDTO vehicleRepairDTO,
                                      MultipartFile beforeImage,
                                      MultipartFile afterImage)
    {
        VehicleRepair insertVehicleRepair = modelMapper.map(vehicleRepairDTO, VehicleRepair.class);

        // before 이미지 저장
        if (beforeImage != null && !beforeImage.isEmpty()) {
            String beforeImageName = UUID.randomUUID().toString().replace("-", "");
            try {
                String beforeReplaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, beforeImageName, beforeImage);
                insertVehicleRepair.setBeforeVehiclePhoto(beforeReplaceFileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store before image", e);
            }
        }

        // after 이미지 저장
        if (afterImage != null && !afterImage.isEmpty()) {
            String afterImageName = UUID.randomUUID().toString().replace("-", "");
            try {
                String afterReplaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, afterImageName, afterImage);
                insertVehicleRepair.setAfterVehiclePhoto(afterReplaceFileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store after image", e);
            }
        }

        vehicleRepairRepository.save(insertVehicleRepair);

        return "차량보고서 등록 성공";
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

        VehicleRepair vehicleRepair = vehicleRepairRepository.findById(String.valueOf(vehicleReportCode)).get();
        VehicleRepairDTO vehicleRepairDTO = modelMapper.map(vehicleRepair, VehicleRepairDTO.class);
        return vehicleRepairDTO;

    }

    // 차량보고서 수정
    public VehicleRepair updateVehicleRepairStatus(int vehicleReportCode, String vehicleReportStatus) {

        VehicleRepair vehicleRepair = vehicleRepairRepository.findById(String.valueOf(vehicleReportCode))
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicleReportCode: " + vehicleReportCode));
        vehicleRepair.setVehicleRepairStatus(vehicleReportStatus);
        return vehicleRepairRepository.save(vehicleRepair);
    }


}
