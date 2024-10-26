package vn.edu.iuh.fit.donchung.demo5.repositories;

import vn.edu.iuh.fit.donchung.demo5.entities.HangXe;

public interface HangXeDAO {
    HangXe getByTenHangXe(String tenHangXe);
}
