package com.example.paymentandclientend.dao;

import com.example.paymentandclientend.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ClientRepository {

    @Autowired
    private DataSource dataSource;

    public void saveClient(Client client) {
        String SQL_SAVE_CLIENT = "insert into \"lesson56\".clients (fio, balance, msisdn) values (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_CLIENT)) {

            preparedStatement.setString(1, client.getFio());
            preparedStatement.setInt(2, client.getBalance());
            preparedStatement.setString(3, client.getMsisdn());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void updateClientBalanceCredit(Integer id, Integer balance) {
        String SQL_BALANCE_CREDIT = "update \"lesson56\".clients set balance = balance - ? where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_BALANCE_CREDIT)) {

            preparedStatement.setInt(1, balance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void updateClientBalanceDebit(Integer id, Integer balance) {
        String SQL_BALANCE_DEBIT = "update \"lesson56\".clients set balance = balance + ? where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_BALANCE_DEBIT)) {

            preparedStatement.setInt(1, balance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Client getClientByMsisdn(String msisdn) {
        String SQL_CLIENT_MSISDN = "select * from \"lesson56\".clients where msisdn = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CLIENT_MSISDN)) {

            preparedStatement.setString(1, msisdn);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Client client = new Client();
                    client.setId(resultSet.getInt("ID"));
                    client.setFio(resultSet.getString("FIO"));
                    client.setMsisdn(resultSet.getString("MSISDN"));
                    client.setActive(resultSet.getBoolean("ACTIVE"));
                    client.setBalance(resultSet.getInt("BALANCE"));
                    return client;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    public void blockClientById(Integer id) {
        String SQL_BLOCk = "update \"lesson56\".clients set active = false where id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_BLOCk)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}

