package com.example.paymentandclientend.dao;

import com.example.paymentandclientend.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@RequestMapping("/payment")
@Repository
public class PaymentRepository {

    @Autowired
    private DataSource dataSource;

    public void pay(Payment payment) {
        String SQL_PAY = "insert into \"lesson56\".payments(client_id, amount, created_date, status, msisdn) values (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_PAY)) {

            preparedStatement.setInt(1, payment.getClientId());
            preparedStatement.setInt(2, payment.getAmount());
            preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
            preparedStatement.setString(4, String.valueOf(payment.getPaymentStatus()));
            preparedStatement.setString(5, payment.getMsisdn());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}

