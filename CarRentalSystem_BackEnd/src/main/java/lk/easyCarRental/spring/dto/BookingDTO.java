package lk.easyCarRental.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookingDTO {
    private String bookingId;
    private CustomerDTO customer;
    private String date;
    private double cost;
    private String bank_slip;
    private List<BookingDetailDTO> booking_detail_list;
}
