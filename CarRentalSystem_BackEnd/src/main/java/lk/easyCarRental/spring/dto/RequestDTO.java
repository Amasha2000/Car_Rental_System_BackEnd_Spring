package lk.easyCarRental.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDTO {
    private String rid;
    private CustomerDTO customer;
    private double total_fee;
    private String bank_slip;
    private List<RequestDetailDTO> request_detail_list;
}
