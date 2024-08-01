package aircleanprojectback.restapi.water.service;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.water.dao.WaterRepository;
import aircleanprojectback.restapi.water.dao.WaterSupplyRepository;
import aircleanprojectback.restapi.water.dao.WaterTankRepository;
import aircleanprojectback.restapi.water.dto.Row;
import aircleanprojectback.restapi.water.dto.WaterSupplyDTO;
import aircleanprojectback.restapi.water.dto.WaterSupplyRequest;
import aircleanprojectback.restapi.water.entity.WaterCondition;
import aircleanprojectback.restapi.water.entity.WaterSupply;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WaterService {

    private final BranchRepository branchRepository;
    private final WaterRepository waterRepository;
    private final WaterTankRepository waterTank;
    private final WaterTankRepository waterTankRepository;
    private final WaterSupplyRepository waterSupplyRepository;

    public WaterService(BranchRepository branchRepository, WaterRepository waterRepository, WaterTankRepository waterTank, WaterTankRepository waterTankRepository, WaterSupplyRepository waterSupplyRepository){
        this.branchRepository=branchRepository;
        this.waterRepository = waterRepository;
        this.waterTank = waterTank;
        this.waterTankRepository = waterTankRepository;
        this.waterSupplyRepository = waterSupplyRepository;
    }


    public String findBranchLocation(String branchCode) {

        Optional<Branch> branch = branchRepository.findById(branchCode);

        return  branch.get().getBranchRegion();

    }

    public Optional<WaterCondition> getWaterCondition(String branchCode) {

        String location = findBranchLocation(branchCode);

        Optional<WaterCondition> waterCondition = Optional.empty();

        switch(location){
            case "중앙" :
                waterCondition =  waterRepository.findById("노량진");
                break;
            case "동부" :
                waterCondition = waterRepository.findById("중랑천");
                break;
            case "서부" :
                waterCondition = waterRepository.findById("안양천");
                break;
            case "남부" :
                waterCondition = waterRepository.findById("탄천");
                break;
            case "북부" :
                waterCondition = waterRepository.findById("선유");
                break;
        }

        return waterCondition;
    }

    @Transactional
    public void registWaterSupply(WaterSupplyRequest request) {

        int waterLevel = request.getWaterLevel();
        Row formattedWaterLevel = request.getFormattedWaterLevel();
        String branchCode = request.getBranchCode(); // branchCode 가져오기
        int supplyAmount = request.getSupplyAmount();

        System.out.println(formattedWaterLevel);
        System.out.println("branchCode = " + branchCode);
        System.out.println(waterLevel);
        System.out.println(supplyAmount);

        int updateWaterCurCapacity = waterLevel + supplyAmount;

        //물탱크 충전
        waterRepository.updateWaterCurCapacity(updateWaterCurCapacity, branchCode);

        // 브런치 코드로 물탱크 번호 조회
        String waterTankNo = waterTankRepository.selectWaterTankNo(branchCode);

        // 수급일지 regist
        WaterSupplyDTO waterSupplyDTO = new WaterSupplyDTO();
        waterSupplyDTO.setWaterTankNo(waterTankNo);
        waterSupplyDTO.setMsrDate(formattedWaterLevel.getMsrDate());
        waterSupplyDTO.setSiteId(formattedWaterLevel.getSiteId());
        waterSupplyDTO.setwTemp(formattedWaterLevel.getWTemp());
        waterSupplyDTO.setwPh(formattedWaterLevel.getWPh());
        waterSupplyDTO.setwDo(formattedWaterLevel.getWDo());
        waterSupplyDTO.setwTn(formattedWaterLevel.getWTn());
        waterSupplyDTO.setwTp(formattedWaterLevel.getWTp());
        waterSupplyDTO.setwPhen(formattedWaterLevel.getWPhen());
        waterSupplyDTO.setwCn(formattedWaterLevel.getWCn());
        waterSupplyDTO.setWaterVolume(String.valueOf(supplyAmount));

        System.out.println("확인하겠습니다람쥐" + waterSupplyDTO);

        WaterSupply waterSupply = waterSupplyDTO.toEntity();
        waterSupplyRepository.save(waterSupply);

    }
}
