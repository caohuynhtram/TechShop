package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import utils.DBContext;

public class BrandDAO extends DBContext {

    // 1. Lấy tất cả Brand
    public List<Brand> getAllBrand() {
        List<Brand> list = new ArrayList<>();
        String sql = "SELECT brand_id, brand_name, is_active FROM brands";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Brand(
                        rs.getInt("brand_id"),
                        rs.getString("brand_name"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Lấy Brand theo ID
    public Brand getBrandById(int id) {
        String sql = "SELECT brand_id, brand_name, is_active FROM brands WHERE brand_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Brand(
                        rs.getInt("brand_id"),
                        rs.getString("brand_name"),
                        rs.getBoolean("is_active")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 3. Thêm mới Brand
    public void insertBrand(Brand b) {
        String sql = "INSERT INTO brands(brand_name, is_active) VALUES (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getBrandName());
            ps.setBoolean(2, b.isIsActive());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Cập nhật Brand
    public void updateBrand(Brand b) {
        String sql = "UPDATE brands SET brand_name = ?, is_active = ? WHERE brand_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getBrandName());
            ps.setBoolean(2, b.isIsActive());
            ps.setInt(3, b.getBrandId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. Xóa Brand
    public void deleteBrand(int id) {
        String sql = "DELETE FROM brands WHERE brand_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main để test
    public static void main(String[] args) {
        BrandDAO dao = new BrandDAO();

        // Test Insert
        dao.insertBrand(new Brand("Samsung", true));

        // Test Get All
        for (Brand b : dao.getAllBrand()) {
            System.out.println(b);
        }
    }
}
