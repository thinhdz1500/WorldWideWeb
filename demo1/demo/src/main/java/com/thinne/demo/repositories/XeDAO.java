package vn.edu.iuh.fit.donchung.demo5.repositories;

import vn.edu.iuh.fit.donchung.demo5.entities.Xe;

import java.util.List;

public interface XeDAO {
    List<Xe> getAll();
    Xe insert(Xe xe, String tenHangXe);
    List<Xe> getByTenXeOrGiaXe(String tenXe, Double giaXe);
}
