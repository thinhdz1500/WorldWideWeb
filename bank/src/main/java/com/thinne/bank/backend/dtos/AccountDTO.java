package com.thinne.bank.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private String accountNumber;
    private String name;
    private Integer cardNumber;
    private String address;
    private Double amount;
}
