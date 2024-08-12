package aircleanprojectback.restapi.stock.dto;

import java.util.List;

public class BranchStockUpdate {

    private List<LaundrySupplyManagementDTO> detergents;
    private List<LaundryPartManagementDTO> parts;

    public BranchStockUpdate() {
    }

    public BranchStockUpdate(List<LaundrySupplyManagementDTO> detergents, List<LaundryPartManagementDTO> parts) {
        this.detergents = detergents;
        this.parts = parts;
    }

    public List<LaundrySupplyManagementDTO> getDetergents() {
        return detergents;
    }

    public void setDetergents(List<LaundrySupplyManagementDTO> detergents) {
        this.detergents = detergents;
    }

    public List<LaundryPartManagementDTO> getParts() {
        return parts;
    }

    public void setParts(List<LaundryPartManagementDTO> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "BranchStockUpdate{" +
                "detergents=" + detergents +
                ", parts=" + parts +
                '}';
    }
}
