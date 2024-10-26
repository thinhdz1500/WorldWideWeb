package vn.edu.iuh.fit.donchung.demo5.repositories.impl;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import vn.edu.iuh.fit.donchung.demo5.entities.HangXe;
import vn.edu.iuh.fit.donchung.demo5.entities.Xe;
import vn.edu.iuh.fit.donchung.demo5.repositories.HangXeDAO;
import vn.edu.iuh.fit.donchung.demo5.repositories.XeDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Logger;

public class XeDAOImpl implements XeDAO {

    private final Logger logger = Logger.getLogger(HangXeDAOImpl.class.getName());
    @Resource(name = "LeDonChungMariaDB")
    DataSource dataSource;

    @Inject
    private HangXeDAO hangXeDAO;

    @Override
    public List<Xe> getAll() {
        List<Xe> xes = new ArrayList<>();
        try {

            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM Xe xe JOIN HangXe hx ON xe.MAHANGXE = hx.MAHANGXE";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Xe xe = Xe.builder()
                        .maXe(rs.getString("MAXE"))
                        .tenXe(rs.getString("TENXE"))
                        .giaXe(rs.getDouble("GIAXE"))
                        .namSanXuat(rs.getInt("NAMSANXUAT"))
                        .hangXe(
                                HangXe.builder()
                                        .maHangXe(rs.getString("MAHANGXE"))
                                        .tenHang(rs.getString("TENHANGXE"))
                                        .build()
                        )
                        .build();

                xes.add(xe);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return xes;
    }

    @Override
    public Xe insert(Xe xe, String tenHangXe) {
        try {
            HangXe hangXe = hangXeDAO.getByTenHangXe(tenHangXe);

            Connection connection = dataSource.getConnection();
            String maXe = UUID.randomUUID().toString();
            String sql = "INSERT INTO Xe (MAXE, TENXE, GIAXE, NAMSANXUAT, MAHANGXE) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, maXe);
            preparedStatement.setString(2, xe.getTenXe());
            preparedStatement.setDouble(3, xe.getGiaXe());
            preparedStatement.setInt(4, xe.getNamSanXuat());
            preparedStatement.setString(5, hangXe.getMaHangXe());

            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                xe.setMaXe(maXe);
                xe.setHangXe(hangXe);
                return xe;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Xe> getByTenXeOrGiaXe(String tenXe, Double giaXe) {
        List<Xe> xes = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM Xe xe JOIN HangXe hx ON xe.MaHangXe = hx.MaHangXe");
            if(tenXe != null && !tenXe.isBlank()) {
                sql.append(String.format(" WHERE TENXE LIKE '%%%s%%'", tenXe));
            }
            if(giaXe != null) {
                sql.append(String.format(" WHERE GIAXE = %f", giaXe));
            }

            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql.toString());
            while (rs.next()) {
                Xe xe = Xe.builder()
                        .maXe(rs.getString("MAXE"))
                        .tenXe(rs.getString("TENXE"))
                        .giaXe(rs.getDouble("GIAXE"))
                        .namSanXuat(rs.getInt("NAMSANXUAT"))
                        .hangXe(
                                HangXe.builder()
                                        .maHangXe(rs.getString("MAHANGXE"))
                                        .tenHang(rs.getString("TENHANGXE"))
                                        .build()
                        )
                        .build();

                xes.add(xe);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return xes;
    }

}
