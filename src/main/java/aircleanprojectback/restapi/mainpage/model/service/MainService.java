package aircleanprojectback.restapi.mainpage.model.service;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.member.dto.BranchDTO;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.report.dto.ExpenseDTO;
import aircleanprojectback.restapi.report.entity.Expense;
import aircleanprojectback.restapi.report.repository.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final ExpenseRepository expenseRepository;
    public MainService(BranchRepository branchRepository, ModelMapper modelMapper,
                       ExpenseRepository expenseRepository){
        this.branchRepository =branchRepository;
        this.modelMapper = modelMapper;
        this.expenseRepository = expenseRepository;
    }


    public List<BranchDTO> getBranch() {

        List<Branch> result = branchRepository.findAll();

        List<BranchDTO> branchList = result.stream().map(branch -> modelMapper.map(branch,BranchDTO.class)).collect(Collectors.toList());

        return branchList;
    }

    public List<ExpenseDTO> getUtilityCost(String month) {

        String year = month.split("-")[0];
        month = month.split("-")[1];

        List<Expense> result = expenseRepository.findAllExpense(year,month,"Y");


        return result.stream().map(expense -> modelMapper.map(expense,ExpenseDTO.class)).collect(Collectors.toList());
    }
}
