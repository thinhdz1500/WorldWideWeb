package vn.edu.iuh.fit.donchung.demo5.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Xe {
    private String maXe;
    private String tenXe;
    private double giaXe;
    private int namSanXuat;
    private HangXe hangXe;
}
