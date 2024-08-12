package aircleanprojectback.restapi.laundry.service;


import aircleanprojectback.restapi.laundry.dto.LaundryWayDTO;
import aircleanprojectback.restapi.laundry.entity.Laundry;
import aircleanprojectback.restapi.laundry.entity.LaundryWay;
import aircleanprojectback.restapi.laundry.repository.LaundryRepository;
import aircleanprojectback.restapi.laundry.repository.LaundryWayRepository;
import aircleanprojectback.restapi.laundry.service.GptApiService;
import jakarta.transaction.Transactional;
import org.json.JSONObject;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private GptApiService gptApiService;

    private final ModelMapper modelMapper;
    @Autowired
    private LaundryWayRepository laundryWayRepository;

    @Autowired
    private LaundryRepository laundryRepository;

    public RegistrationService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Transactional
    public List<LaundryWayDTO> registLaundryWay(Map<String, Object> payload) throws IOException {
        List<Map<String, Object>> selectedItems = (List<Map<String, Object>>) payload.get("selectedItems");


        List<LaundryWayDTO> result = new ArrayList<>();

        for (Map<String, Object> item : selectedItems) {

            System.out.println("item.get(\"laundryDryCleaningStatus\") = " + item.get("laundryDryCleaningStatus"));

            int laundryCode = (Integer) item.get("laundryCode");
            int laundryWeight = (Integer) item.get("laundryWeight");
            String laundryFabricType = (String) item.get("laundryFabricType");
            String laundryDryCleaningStatus = (String) item.get("laundryDryCleaningStatus");
            int laundryDirtyLevel = (Integer) item.get("laundryDirtyLevel");

            laundryRepository.updateWashingInstructionStatus("Y", laundryCode);

            // GPT-3.5 API를 호출하여 최적화된 세탁 데이터를 가져옵니다.
            String optimizedData = gptApiService.getOptimizedLaundryData(
                    laundryCode, laundryWeight, laundryFabricType, laundryDryCleaningStatus, laundryDirtyLevel
            );

            // 응답 데이터가 JSON 형태로 파싱될 수 있도록 변환
            JSONObject jsonResponse = new JSONObject(optimizedData);

            // DTO 객체 생성 및 설정
            LaundryWayDTO dto = new LaundryWayDTO();
            dto.setLaundryCode(jsonResponse.optInt("laundryCode", laundryCode));
            dto.setLaundryTime(jsonResponse.optString("laundryTime", "오류"));
            dto.setLaundryDetergentAmount(jsonResponse.optString("laundryDetergentAmount", "오류"));
            dto.setLaundryWaterAmount(jsonResponse.optString("laundryWaterAmount", "오류"));
            dto.setLaundryDryingTime(jsonResponse.optString("laundryDryingTime", "오류"));
            dto.setLaundryDryCleaningTime(jsonResponse.optString("laundryDryCleaningTime", "오류"));

            // Laundry 엔티티를 조회
            Laundry laundry = laundryRepository.findById(dto.getLaundryCode())
                    .orElseThrow(() -> new RuntimeException("Laundry not found with code: " + dto.getLaundryCode()));

            // DTO를 엔티티로 변환 및 빌더 패턴 사용
            LaundryWay laundryWay = LaundryWay.builder()
                    .laundry(laundry) // 조회한 Laundry 엔티티를 설정
                    .laundryTime(dto.getLaundryTime())
                    .laundryDetergentAmount(dto.getLaundryDetergentAmount())
                    .laundryWaterAmount(dto.getLaundryWaterAmount())
                    .laundryDryingTime(dto.getLaundryDryingTime())
                    .laundryDryCleaningTime(dto.getLaundryDryCleaningTime())
                    .build();

            // 데이터베이스에 저장
            laundryWayRepository.save(laundryWay);

            // 결과 리스트에 DTO 추가
            result.add(dto);
        }

        return result;

    }

    public List<LaundryWay> SelectLaundryWay(String branchCode) {
        List<Integer> laundryCodes = laundryRepository.findLaundryCodesByBranchCode(branchCode);
        List<LaundryWay> laundryWayList = laundryWayRepository.findByLaundryCodes(laundryCodes);

        System.out.println("service 확인"+laundryWayList);

        return laundryWayList;
    }




}
