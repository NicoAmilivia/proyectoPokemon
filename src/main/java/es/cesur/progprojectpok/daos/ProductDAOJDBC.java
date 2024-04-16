package es.cesur.progprojectpok.daos;

import es.cesur.progprojectpok.database.ConfigDB;
import es.cesur.progprojectpok.model.Product;

import java.sql.*;

public class ProductDAOJDBC {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConfigDB.URL, ConfigDB.USERNAME, ConfigDB.PASSWORD);
    }

    // Guardar un producto en la base de datos
    public boolean saveProduct(int id, String name, String description, boolean storable, Integer maxStock, Double price) {
        String sql = "INSERT INTO producto (ID_PRODUCTO, NOMBRE, DESCRIPCION, ALMACENABLE, MAXIMO_STOCK, PRECIO) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, description);
            pstmt.setBoolean(4, storable);
            if (maxStock != null) {
                pstmt.setInt(5, maxStock);
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            if (price != null) {
                pstmt.setDouble(6, price);
            } else {
                pstmt.setNull(6, Types.DOUBLE);
            }

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cargar un producto por ID
    public Product loadProduct(int id) {
        String sql = "SELECT * FROM producto WHERE ID_PRODUCTO = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("ID_PRODUCTO"),
                        rs.getString("NOMBRE"),
                        rs.getString("DESCRIPCION"),
                        rs.getBoolean("ALMACENABLE"),
                        rs.getInt("MAXIMO_STOCK"),
                        rs.getDouble("PRECIO")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar un producto en la base de datos
    public boolean updateProduct(int id, String name, String description, boolean storable, Integer maxStock, Double price) {
        String sql = "UPDATE producto SET NOMBRE = ?, DESCRIPCION = ?, ALMACENABLE = ?, MAXIMO_STOCK = ?, PRECIO = ? WHERE ID_PRODUCTO = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setBoolean(3, storable);
            if (maxStock != null) {
                pstmt.setInt(4, maxStock);
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            if (price != null) {
                pstmt.setDouble(5, price);
            } else {
                pstmt.setNull(5, Types.DOUBLE);
            }
            pstmt.setInt(6, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un producto de la base de datos
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM producto WHERE ID_PRODUCTO = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

