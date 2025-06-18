/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Position;

/**
 *
 * @author DELL
 */
public class PositionDAO extends DBContext{
    
        public List<Position> getAllPosition() {
        String sql = "select * from position ";
        List<Position> listPo = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Position po =  new Position(rs.getInt(1), rs.getString(2));
                listPo.add(po);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return listPo;
    }
        public static void main(String[] args) {
            PositionDAO d  = new PositionDAO();
            System.out.println(d.getAllPosition());
    }
}
