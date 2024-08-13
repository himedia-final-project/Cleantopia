package aircleanprojectback.restapi.mainpage.model.service;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.mainpage.model.dao.BranchSalesAndBranchRepository;
import aircleanprojectback.restapi.mainpage.model.dao.ExpenseAndBranchRepository;
import aircleanprojectback.restapi.mainpage.model.dao.RankingRepository;
import aircleanprojectback.restapi.mainpage.model.dto.BranchSalesAndBranchDTO;
import aircleanprojectback.restapi.mainpage.model.dto.ExpenseAndBranchDTO;
import aircleanprojectback.restapi.mainpage.model.dto.RankingDTO;
import aircleanprojectback.restapi.mainpage.model.entity.BranchSalesAndBranch;
import aircleanprojectback.restapi.mainpage.model.entity.ExpenseAndBranch;
import aircleanprojectback.restapi.mainpage.model.entity.Ranking;
import aircleanprojectback.restapi.member.dto.BranchDTO;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.report.dto.ExpenseDTO;
import aircleanprojectback.restapi.report.dto.VehicleRepairDTO;
import aircleanprojectback.restapi.report.entity.Expense;
import aircleanprojectback.restapi.report.entity.VehicleRepair;
import aircleanprojectback.restapi.report.repository.BranchSalesRepository;
import aircleanprojectback.restapi.report.repository.ExpenseRepository;
import aircleanprojectback.restapi.report.repository.VehicleRepairRepository;
import aircleanprojectback.restapi.stock.dto.StockExpenseDTO;
import aircleanprojectback.restapi.stock.entity.StockExpense;
import aircleanprojectback.restapi.stock.repository.StockExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final ExpenseRepository expenseRepository;
    private final BranchSalesRepository branchSalesRepository;
    private final BranchSalesAndBranchRepository branchSalesAndBranchRepository;
    private final ExpenseAndBranchRepository expenseAndBranchRepository;
    private final StockExpenseRepository stockExpenseRepository;
    private final VehicleRepairRepository vehicleRepairRepository;
    private final RankingRepository rankingRepository;

    @Value("${image.image-url}")
    private String IMAGE_URL;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;



    public MainService(BranchRepository branchRepository, ModelMapper modelMapper,
                       ExpenseRepository expenseRepository, BranchSalesRepository branchSalesRepository, BranchSalesAndBranchRepository branchSalesAndBranchRepository, ExpenseAndBranchRepository expenseAndBranchRepository, StockExpenseRepository stockExpenseRepository, VehicleRepairRepository vehicleRepairRepository, RankingRepository rankingRepository){
        this.branchRepository =branchRepository;
        this.modelMapper = modelMapper;
        this.expenseRepository = expenseRepository;
        this.branchSalesRepository = branchSalesRepository;
        this.branchSalesAndBranchRepository = branchSalesAndBranchRepository;
        this.expenseAndBranchRepository = expenseAndBranchRepository;
        this.stockExpenseRepository = stockExpenseRepository;
        this.vehicleRepairRepository = vehicleRepairRepository;
        this.rankingRepository = rankingRepository;
    }


    public List<BranchDTO> getBranch() {

        List<Branch> result = branchRepository.findAll();

        List<BranchDTO> branchList = result.stream().map(branch -> modelMapper.map(branch,BranchDTO.class)).collect(Collectors.toList());

        return branchList;
    }

    public List<ExpenseAndBranchDTO> getUtilityCost(String date , String branchCode) {

        String year = date.split("-")[0];
        String month = date.split("-")[1];

        List<ExpenseAndBranch> expenses = expenseAndBranchRepository.findAllWithDateAndStatus(year,month,"Y");

        return expenses.stream().map(e->modelMapper.map(e,ExpenseAndBranchDTO.class)).collect(Collectors.toList());

    }

    public Map<String,Object> getRevenue(String branchCode, String month, String year) {

        List<BranchSalesAndBranch> branchSales = null;
        long annual = 0;
        long monthRevenue = 0;

        if(branchCode.equals("Total")){
            branchSales= branchSalesAndBranchRepository.findAllByBranchSubmissionDateIn(month, year , "Y");
            annual = branchSalesAndBranchRepository.findAnnualRevenue(year, "Y");
            monthRevenue = branchSalesAndBranchRepository.findMonthRevenue(month,year,"Y");
        }else{
            branchSales = branchSalesAndBranchRepository.findAllByMonthAndBranchCode(month,year, branchCode, "Y");
            annual= branchSalesAndBranchRepository.findAnnualRevenueAndBranchCode(year,"Y",branchCode);
            monthRevenue = branchSalesAndBranchRepository.findMonthRevenueWithBranchCode(month,year,"Y",branchCode);
        }

        Map<String,Object> result = new HashMap<>();

        result.put("month",branchSales.stream().map(sales->modelMapper.map(sales, BranchSalesAndBranchDTO.class)).collect(Collectors.toList()));
        result.put("annual",annual);
        result.put("monthRevenue",monthRevenue);

        System.out.println("branchSales = " + branchSales);
        System.out.println("annual = " + annual);

        return result;



    }

    public Map<String,Object> getFacilityCost(String branchCode) {

        List<StockExpense> result = new ArrayList<>();

        Map<String,Object> resultMap = new HashMap<>();

        LocalDate today = LocalDate.now();

        String month = String.valueOf(today.getMonth());

        int monthNumber = Month.valueOf(month.toUpperCase(Locale.ROOT)).getValue();

        System.out.println("month = " + month);
        String year = String.valueOf(today.getYear());

        if(branchCode.equals("Total")){
            result = stockExpenseRepository.findAllByBranchCode("head", String.valueOf(monthNumber),year);
            List<StockExpense> total = stockExpenseRepository.findTotalToday("head",year);

            resultMap.put("result",result.stream().map(r->modelMapper.map(r, StockExpenseDTO.class)).collect(Collectors.toList()));
            resultMap.put("total",total.stream().map(t->modelMapper.map(t,StockExpenseDTO.class)).collect(Collectors.toList()));
        }else{
            result = stockExpenseRepository.findAllByBranchCode(branchCode, String.valueOf(monthNumber),year);

            List<StockExpense> total = stockExpenseRepository.findTotalToday("head",year);

            resultMap.put("result",result.stream().map(r->modelMapper.map(r, StockExpenseDTO.class)).collect(Collectors.toList()));
            resultMap.put("total",total.stream().map(t->modelMapper.map(t,StockExpenseDTO.class)).collect(Collectors.toList()));
        }

        return resultMap;
    }

    public Map<String, Object> getCarCost() {

        List<VehicleRepair> result = new ArrayList<>();
        List<VehicleRepair> total = new ArrayList<>();

        LocalDate today = LocalDate.now();

        String month = String.valueOf(today.getMonth());

        int monthNumber = Month.valueOf(month.toUpperCase(Locale.ROOT)).getValue();

        System.out.println("month = " + month);
        String year = String.valueOf(today.getYear());


            result = vehicleRepairRepository.getDate(monthNumber,year,"Y");
            total = vehicleRepairRepository.getYear(year,"Y");

            Map<String ,Object> resultMap = new HashMap<>();

            resultMap.put("result",result.stream().map(r->modelMapper.map(r, VehicleRepairDTO.class)).collect(Collectors.toList()));
            resultMap.put("total",total.stream().map(t->modelMapper.map(t,VehicleRepairDTO.class)).collect(Collectors.toList()));

        return resultMap;
    }

//    public List<RankingDTO> getRanking() {
//
//        LocalDate today = LocalDate.now();
//
//        String month = String.valueOf(today.getMonth());
//
//        int monthNumber = Month.valueOf(month.toUpperCase(Locale.ROOT)).getValue();
//
//        System.out.println("month = " + month);
//        String year = String.valueOf(today.getYear());
//
//
//
//        List<RankingDTO> result = rankingRepository.getRanking(String.valueOf(monthNumber),year,"Y");
//
//        return result.stream().map(r->modelMapper.map(r,RankingDTO.class)).collect(Collectors.toList());
//    }
public List<RankingDTO> getRanking() {
            LocalDate today = LocalDate.now();

        String month = String.valueOf(today.getMonth());

        int monthNumber = Month.valueOf(month.toUpperCase(Locale.ROOT)).getValue();

        System.out.println("month = " + month);
        String year = String.valueOf(today.getYear());
    List<Object[]> results = rankingRepository.getRankingRaw(monthNumber, Integer.parseInt(year), "Y");

    List<RankingDTO> list = results.stream().map(this::convertToDTO).collect(Collectors.toList());

    for(int i =0 ;i<list.size() ;i++){
        list.get(i).setMemberImage(IMAGE_URL+list.get(i).getMemberImage());
    }

    return list;
}

    private RankingDTO convertToDTO(Object[] result) {
        RankingDTO dto = new RankingDTO();
        dto.setRankIndex(((Number) result[0]).intValue());
        dto.setMemberName((String) result[1]);
        dto.setMemberImage((String) result[2]);
        dto.setBranchName((String) result[3]);
        dto.setTotalRevenue(((Number) result[4]).longValue());
        return dto;
    }


//    public List<Ranking> getRankingWithIndex(String month, String year, String status) {
//        List<Ranking> rankings = rankingRepository.getRanking(month, year, status);
//        for (int i = 0; i < rankings.size(); i++) {
//            rankings.get(i).setRankIndex(i + 1);  // 인덱스를 1부터 부여
//        }
//        return rankings;
//    }
}
