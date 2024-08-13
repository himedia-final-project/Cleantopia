package aircleanprojectback.restapi.report.service;


import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.report.dto.BranchSalesDTO;
import aircleanprojectback.restapi.report.entity.BranchSales;
import aircleanprojectback.restapi.report.repository.BranchSalesRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class BranchSalesService {

    private final BranchSalesRepository branchSalesRepository;
    private final ModelMapper modelMapper;

    public BranchSalesService(BranchSalesRepository branchSalesRepository, ModelMapper modelMapper) {
        this.branchSalesRepository = branchSalesRepository;
        this.modelMapper = modelMapper;
    }

    // 매출보고서 전체조회
    public Page<BranchSalesDTO> getAllBranchSales(Criteria branchSalesCriteria) {

        Pageable branchSalesPageable = PageRequest.of(branchSalesCriteria.getPageNum() -1, branchSalesCriteria.getAmount(), Sort.by(Sort.Direction.DESC, "branchReportCode"));

        Page<BranchSales> branchSalesList = branchSalesRepository.findAll(branchSalesPageable);
        Page<BranchSalesDTO> branchSalesDTO = branchSalesList.map(branchSales -> modelMapper.map(branchSales, BranchSalesDTO.class));

        System.out.println("branchSalesList.getContent() = " +  branchSalesList.getContent());
        System.out.println("branchSalesDTO.getContent() = " +  branchSalesDTO.getContent());
        return branchSalesDTO;


    }

    // 매출보고서 필터링조회
    public Page<BranchSalesDTO> findBranchSalesMemberName(Criteria branchSalesCriteriaMemberName, String memberName) {

        Pageable branchSalesMemberNamePageable = PageRequest.of(branchSalesCriteriaMemberName.getPageNum() -1, branchSalesCriteriaMemberName.getAmount(), Sort.by(Sort.Direction.DESC, "branchReportCode"));
        Page<BranchSales> branchSalesMemberNameList = branchSalesRepository.findByMemberName(memberName, branchSalesMemberNamePageable);
        Page<BranchSalesDTO> branchSalesMemberNameDTO = branchSalesMemberNameList.map(branchSales -> modelMapper.map(branchSales, BranchSalesDTO.class));

        return branchSalesMemberNameDTO;

    }


    // 매출 보고서 등록
    @Transactional
    public String insertBranchSales(BranchSalesDTO branchSalesDTO) {

        BranchSales insertBranchSales = modelMapper.map(branchSalesDTO, BranchSales.class);
        branchSalesRepository.save(insertBranchSales);

        return "보고서 등록 성공";
    }


    // 매출보고서 수정
    @Transactional
    public BranchSales updateBranchSales(BranchSalesDTO branchSalesDTO, int branchReportCode) {

        BranchSales branchSales = branchSalesRepository.findById(branchReportCode)
                .orElseThrow(() -> new RuntimeException("보고서를 찾을수 없습니다. ID: " + branchReportCode));

        branchSales = branchSales
                .officeSales(branchSalesDTO.getOfficeSales())
                .detergent(branchSalesDTO.getDetergent())
                .fabricSoftener(branchSalesDTO.getFabricSoftener())
                .bleach(branchSalesDTO.getBleach())
                .stainRemover(branchSalesDTO.getStainRemover())
                .washerCleaner(branchSalesDTO.getWasherCleaner())
                .dryerSheet(branchSalesDTO.getDryerSheet())
                .totalBranchSalesCost(branchSalesDTO.getTotalBranchSalesCost());

        branchSalesRepository.save(branchSales);
        return branchSales;
    }

    // 매출 보고서 삭제
    public String deleteBranchSales(int branchReportCode) {

        branchSalesRepository.deleteById(branchReportCode);
        return "삭제 성공";
    }

    // /company 매출보고서 세부 조회
    public BranchSalesDTO detailBranchSales(int branchCode) {

        BranchSales branchSales = branchSalesRepository.findById(branchCode).get();
        BranchSalesDTO branchSalesDTO = modelMapper.map(branchSales, BranchSalesDTO.class);
        return branchSalesDTO;

    }

    // 매출보고서 승인/반려
    @Transactional
    public BranchSales updateBranchSalesState(int branchReportCode, String branchReportApprove, String branchReportStatus) {

        BranchSales branchSales = branchSalesRepository.findById(branchReportCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid branch report code: " + branchReportCode));
        branchSales.setBranchReportStatus(branchReportStatus);
        branchSales.setBranchReportApprove(branchReportApprove);
        return branchSalesRepository.save(branchSales);
    }





}