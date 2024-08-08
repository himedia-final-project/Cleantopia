package aircleanprojectback.restapi.car.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LaundryAndDriverDTO {

    private int laundryCode;
    private String laundryCollectionStatus;
    private String laundryArriveStatus;
    private String laundryCustomerName;
    private int laundryWeight;
    private String laundryFabricType;
    private Date laundryCustomerRegistDate;
    private String laundryWashingInstructionStatus;
    private Date laundryApprovalDate;
    private String laundryDryCleaningStatus;
    private int laundryDirtyLevel;
    private String branchCode;
    private String driverLicenseNumber;
    private Date laundryDriverPickupDate;
    private String laundryCompleted;
    private Date laundryCompletedDate;
    private DriverAndMemberDTO pickDriverMember;
    private DriverAndMemberDTO deliveryDriverMember;
    private String bringCustomerStatus;

}
