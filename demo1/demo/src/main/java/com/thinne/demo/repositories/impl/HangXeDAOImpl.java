package vn.edu.iuh.fit.donchung.demo5.repositories.impl;

import jakarta.annotation.Resource;
import vn.edu.iuh.fit.donchung.demo5.entities.HangXe;
import vn.edu.iuh.fit.donchung.demo5.repositories.HangXeDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
public class HangXeDAOImpl implements HangXeDAO {
    private final Logger logger = Logger.getLogger(HangXeDAOImpl.class.getName());
    @Resource(name = "LeDonChungMariaDB")
    DataSource dataSource;

    @Override
    public HangXe getByTenHangXe(String tenHangXe) {
        HangXe hangXe = null;
        try {
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM HangXe WHERE TENHANGXE = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tenHangXe);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                hangXe = HangXe.builder()
                        .maHangXe(rs.getString("MAHANGXE"))
                        .tenHang(rs.getString("TENHANGXE"))
                        .quocGia(rs.getString("QUOCGIA"))
                        .moTa(rs.getString("MOTA"))
                        .build();
            }
        }catch (Exception e) {
            logger.info(e.getMessage());
        }
        return hangXe;
    }
}
