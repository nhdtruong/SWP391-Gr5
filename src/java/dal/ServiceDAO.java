package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Service;
import model.CategoryServices;
import model.Deparment; // Đảm bảo bạn đã tạo class Department

public class ServiceDAO extends DBContext {

    // Lấy danh sách tất cả dịch vụ
    public List<Service> getAllServices() {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT s.service_id, s.service_name, s.is_bhyt, s.description, "
                + "s.category_service_id, s.department_id, s.fee, s.discount, s.payment_type_id "
                + "FROM [service] s";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Service service = new Service(rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        getCategoryServiceByCategorySrvicId(rs.getInt(5)),
                        getDepartmentByDepartment_id(rs.getInt(6)),
                        rs.getDouble(7), rs.getDouble(8),
                        rs.getInt(9));
                list.add(service);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public CategoryServices getCategoryServiceByCategorySrvicId(int id) {

        String sql = "select c.category_service_id,c.name ,c.img from category_service c where c.category_service_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CategoryServices(rs.getInt(1), rs.getString(2), rs.getString(3));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public Deparment getDepartmentByDepartment_id(int id) {

        String sql = "select d.department_id, d.department_name from department d where d.department_id = ?;";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Deparment(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException e) {
        }

        return null;
    }

    public List<Service> getServicesByDoctorAndCategory(int doctorId, int categoryId) {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT s.* "
                + "FROM service s "
                + "JOIN doctor_service ds ON s.service_id = ds.service_id "
                + "WHERE ds.doctor_id = ? AND s.category_service_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setInt(2, categoryId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Service s = new Service();
                    s.setService_id(rs.getInt("service_id"));
                    s.setService_name(rs.getString("service_name"));
                    s.setIs_bhyt(rs.getBoolean("is_bhyt"));
                    s.setDescription(rs.getString("description"));
                    s.setCategory_service_id(rs.getInt("category_service_id"));
                    s.setDepartment_id(rs.getInt("department_id"));
                    s.setFee(rs.getDouble("fee"));
                    s.setDiscount(rs.getDouble("discount"));
                    s.setPayment_type_id(rs.getInt("payment_type_id"));

                    list.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Service> getAllServicesByDepartmentId(int department_id) {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT s.service_id, s.service_name, s.is_bhyt, s.description, s.category_service_id, "
                + "s.department_id, s.fee, s.discount, s.payment_type_id "
                + "FROM [service] s where s.department_id = ? ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, department_id);
            rs = ps.executeQuery();
            while (rs.next()) {

                Service service = new Service(rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        getCategoryServiceByCategorySrvicId(rs.getInt(5)),
                        getDepartmentByDepartment_id(rs.getInt(6)),
                        rs.getDouble(7), rs.getDouble(8),
                        rs.getInt(9));
                list.add(service);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Service> getServicesByDoctorAndDepartment(int doctorId, int departmentId) {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT DISTINCT s.service_id, s.service_name, s.is_bhyt, s.description, "
                + "s.category_service_id, s.department_id, s.fee, s.discount, s.payment_type_id "
                + "FROM [service] s "
                + "JOIN doctor_service ds ON s.service_id = ds.service_id "
                + "WHERE s.department_id = ? AND ds.doctor_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ps.setInt(2, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Service service = new Service(
                            rs.getInt("service_id"),
                            rs.getString("service_name"),
                            rs.getBoolean("is_bhyt"),
                            rs.getString("description"),
                            getCategoryServiceByCategorySrvicId(rs.getInt("category_service_id")),
                            getDepartmentByDepartment_id(rs.getInt("department_id")),
                            rs.getDouble("fee"),
                            rs.getDouble("discount"),
                            rs.getInt("payment_type_id")
                    );
                    list.add(service);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Service> getAllServiceBySearchName(String search) {
        List<Service> list = new ArrayList<>();
        String sql = "SELECT s.service_id, s.service_name, s.is_bhyt, s.description, "
                + "s.category_service_id, s.department_id, s.fee, s.discount, s.payment_type_id, s.img "
                + "FROM service s WHERE s.service_name LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + search + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service s = new Service(
                        rs.getInt("service_id"),
                        rs.getString("service_name"),
                        rs.getBoolean("is_bhyt"),
                        rs.getString("description"),
                        getCategoryServiceByCategorySrvicId(rs.getInt("category_service_id")),
                        getDepartmentByDepartment_id(rs.getInt("department_id")),
                        rs.getDouble("fee"),
                        rs.getDouble("discount"),
                        rs.getInt("payment_type_id"),
                        rs.getString("img")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Service> getAllServiceByFilter(String categoryId, String departmentId) {
        List<Service> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT s.service_id, s.service_name, s.is_bhyt, s.description, "
                + "s.category_service_id, s.department_id, s.fee, s.discount, s.payment_type_id, s.img "
                + "FROM service s WHERE 1=1 "
        );
        List<Object> params = new ArrayList<>();

        if (!categoryId.equalsIgnoreCase("all")) {
            sql.append(" AND s.category_service_id = ?");
            params.add(categoryId);
        }

        if (!departmentId.equalsIgnoreCase("all")) {
            sql.append(" AND s.department_id = ?");
            params.add(departmentId);
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Service s = new Service(rs.getInt("service_id"),
                        rs.getString("service_name"),
                        rs.getBoolean("is_bhyt"),
                        rs.getString("description"),
                        getCategoryServiceByCategorySrvicId(rs.getInt("category_service_id")),
                        getDepartmentByDepartment_id(rs.getInt("department_id")),
                        rs.getDouble("fee"), rs.getDouble("discount"),
                        rs.getInt("payment_type_id"), rs.getString("img"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Service getServiceById(int service_id) {
        String sql = "SELECT s.service_id, s.service_name, s.is_bhyt, s.description, s.category_service_id, "
                + "s.department_id, s.fee, s.discount, s.payment_type_id, c.name as category_name "
                + "FROM [service] s "
                + "LEFT JOIN category_service c ON s.category_service_id = c.category_service_id "
                + "WHERE s.service_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, service_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Service(rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        getCategoryServiceByCategorySrvicId(rs.getInt(5)),
                        getDepartmentByDepartment_id(rs.getInt(6)),
                        rs.getDouble(7), rs.getDouble(8),
                        rs.getInt(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int addService(String service_name, boolean bhyt, String description, int category_service_id,
            int department_id, double fee, double discount, int payment_type_id, String img) {
        String sql = "INSERT INTO [service] (service_name, is_bhyt, description, category_service_id, "
                + "department_id, fee, discount, payment_type_id, img) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, service_name);
            ps.setBoolean(2, bhyt);
            ps.setString(3, description);
            ps.setInt(4, category_service_id);

            if (department_id == 0) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, department_id);
            }

            ps.setDouble(6, fee);
            ps.setDouble(7, discount);
            ps.setInt(8, payment_type_id);
            ps.setString(9, img);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateService(int serviceId, String service_name, boolean bhyt, String description, int category_service_id, int department_id, double fee, double discount, int payment_type_id, String img) {
        String sql = "UPDATE [service] SET service_name = ?, is_bhyt = ?, description = ?, "
                + "category_service_id = ?, department_id = ?, fee = ?, discount = ?, payment_type_id = ? , img = ? "
                + "WHERE service_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, service_name);
            ps.setBoolean(2, bhyt);
            ps.setString(3, description);
            ps.setInt(4, category_service_id);
            if (department_id == 0) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, department_id);
            }

            ps.setDouble(6, fee);
            ps.setDouble(7, discount);
            ps.setInt(8, payment_type_id);
            ps.setString(9, img);
            ps.setInt(10, serviceId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Xóa dịch vụ
    public void deleteService(int service_id) {
        String sql = "DELETE FROM [service] WHERE service_id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, service_id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Lấy danh sách tất cả category (Thể loại)
    public List<CategoryServices> getAllCategories() {
        List<CategoryServices> categories = new ArrayList<>();
        String sql = "SELECT category_service_id, name, img FROM category_service WHERE status = 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoryServices category = new CategoryServices(
                        rs.getInt("category_service_id"),
                        rs.getString("name"),
                        rs.getString("img")
                );
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    // Lấy danh sách tất cả department (Khoa)
    public List<Deparment> getAllDepartments() {
        List<Deparment> departments = new ArrayList<>();
        String sql = "SELECT department_id, department_name, img FROM department";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Deparment department = new Deparment(
                        rs.getInt("department_id"),
                        rs.getString("department_name"),
                        rs.getString("img")
                );
                departments.add(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return departments;
    }

    public static void main(String[] args) {
        ServiceDAO sdao = new ServiceDAO();

      //  System.out.println(sdao.getAllServices());
     //   System.out.println(sdao.getAllCategories());
      //  System.out.println(sdao.getAllDepartments());
        //   sdao.updateService(17, "ok", true, "ok", 1, 2, 34000, 200, 1, "default");
      //  System.out.println(sdao.getAllServicesByDepartmentId(6));
        System.out.println(sdao.getServicesByDoctorAndDepartment(70, 6));
    }
}
