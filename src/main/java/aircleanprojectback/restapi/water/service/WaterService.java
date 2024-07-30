package aircleanprojectback.restapi.water.service;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.water.dao.WaterRepository;
import aircleanprojectback.restapi.water.entity.WaterCondition;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WaterService {

    private final BranchRepository branchRepository;
    private final WaterRepository waterRepository;

    public WaterService(BranchRepository branchRepository,WaterRepository waterRepository){
        this.branchRepository=branchRepository;
        this.waterRepository = waterRepository;
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
}
