package aircleanprojectback.restapi.stock.dto;

import java.util.List;

public class HeadStockUpdate {

    private List<LaundrySupplyAndManagementDTO> detergents;
    private List<LaundryPartAndManagementDTO> parts;

    public HeadStockUpdate() {
    }

    public HeadStockUpdate(List<LaundrySupplyAndManagementDTO> detergents, List<LaundryPartAndManagementDTO> parts) {
        this.detergents = detergents;
        this.parts = parts;
    }

    public List<LaundrySupplyAndManagementDTO> getDetergents() {
        return detergents;
    }

    public void setDetergents(List<LaundrySupplyAndManagementDTO> detergents) {
        this.detergents = detergents;
    }

    public List<LaundryPartAndManagementDTO> getParts() {
        return parts;
    }

    public void setParts(List<LaundryPartAndManagementDTO> parts) {
        this.parts = parts;
    }

    @Override
    public String toString() {
        return "HeadStockUpdate{" +
                "detergents=" + detergents +
                ", parts=" + parts +
                '}';
    }
}