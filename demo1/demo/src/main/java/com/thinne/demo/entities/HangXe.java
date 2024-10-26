package vn.edu.iuh.fit.donchung.demo5.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class HangXe {
    private String maHangXe;
    private String tenHang;
    private String quocGia;
    private String moTa;
}
