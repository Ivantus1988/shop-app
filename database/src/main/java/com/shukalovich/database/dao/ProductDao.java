package com.shukalovich.database.dao;

import com.shukalovich.database.connection.ConnectionPool;
import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.Product;
import com.shukalovich.database.entity.enam.Brand;
import com.shukalovich.database.exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductDao implements Dao<Long, Product> {
    private static final ProductDao INSTANCE = new ProductDao();

    private static final String FIND_ALL_SQL = """
            SELECT id,
                   brand,
                   model,
                   screen_size,
                   screen_resolution,
                   ram,
                   memory_size,
                   price,
                   description
            FROM product;
              """;

    private static final String FIND_BY_ID_SQL = """
            SELECT id,
             brand,
             model,
             screen_size,
             screen_resolution,
             ram,
             memory_size,
             price,
             description
            FROM product
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM product
            WHERE id = ?           
            """;

    private static final String UPDATE_SQL = """
            UPDATE product
            SET brand             = ?,
                model             = ?,
                screen_size       = ?,
                screen_resolution = ?,
                ram               = ?,
                memory_size       = ?,
                price             = ?,
                description       = ?
            WHERE id = ?
             """;

    private static final String SAVE_SQL = """
            INSERT INTO product
                (brand,
                model,
                screen_size,
                screen_resolution,
                ram,
                memory_size,
                price,
                description)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_BY_FILTER_SQL = """
              SELECT id,
                   brand,
                   model,
                   screen_size,
                   screen_resolution,
                   ram,
                   memory_size,
                   price,
                   description
            FROM product
            WHERE screen_size < ?
            AND price < ?
            AND ram < ?
            LIMIT ?
            OFFSET ?            
            """;

       @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (var connection = ConnectionPool.get();
             var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(FIND_ALL_SQL);
            while (resultSet.next()) {
                products.add(buildProduct(resultSet));
            }
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
        return products;
    }

    public List<Product> findByFilter(ProductFilter filter) {
        List<Product> products = new ArrayList<>();
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_FILTER_SQL)) {
            setFilterParameters(filter, preparedStatement);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(buildProduct(resultSet));
            }
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            Product product = null;
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = buildProduct(resultSet);
            }
            return Optional.ofNullable(product);
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    @Override
    public Optional<Product> update(Product product) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            setPrepareStatement(product, preparedStatement);
            preparedStatement.setLong(9, product.getId());
            preparedStatement.executeUpdate();
            return Optional.of(product);
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    @Override
    public Product save(Product product) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setPrepareStatement(product, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
        return product;
    }

    private static Product buildProduct(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getLong("id"))
                .brand(Brand.valueOf(resultSet.getString("brand")))
                .model(resultSet.getString("model"))
                .screenSize(resultSet.getDouble("screen_size"))
                .screenResolution(resultSet.getString("screen_resolution"))
                .ram(resultSet.getInt("ram"))
                .memorySize(resultSet.getInt("memory_size"))
                .price(resultSet.getDouble("price"))
                .description(resultSet.getString("description"))
                .build();
    }

    private static void setFilterParameters(ProductFilter filter, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDouble(1, filter.screenSize());
        preparedStatement.setDouble(2, filter.price());
        preparedStatement.setInt(3, filter.ram());
        preparedStatement.setInt(4, filter.limit());
        preparedStatement.setInt(5, filter.limit() * (filter.page() - 1));
    }

    private static void setPrepareStatement(Product product, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, String.valueOf(product.getBrand()));
        preparedStatement.setString(2, product.getModel());
        preparedStatement.setDouble(3, product.getScreenSize());
        preparedStatement.setString(4, product.getScreenResolution());
        preparedStatement.setInt(5, product.getRam());
        preparedStatement.setInt(6, product.getMemorySize());
        preparedStatement.setDouble(7, product.getPrice());
        preparedStatement.setString(8, product.getDescription());
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}