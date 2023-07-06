package com.example.numbergenerator;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarNumberVariationsToH2Database {
    public static void main(String[] args) {
        String[] letters = {"А", "Е", "Т", "О", "Р", "Н", "У", "К", "Х", "С", "В", "М"};

        try (Connection connection = DriverManager.getConnection("jdbc:h2:/Users/bagano/IdeaProjects/number-generator/db/numder", "bv", "bvcxz")) {
            // Создание таблицы для хранения номеров
//            String createTableQuery = "CREATE TABLE car_numbers (number VARCHAR(20))";
            String createTableQuery = "CREATE TABLE car_numbers (id INT AUTO_INCREMENT PRIMARY KEY, number VARCHAR(20))";
            PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
            createTableStatement.executeUpdate();

            // Вставка всех возможных номеров в базу данных
//            for (String letter1 : letters) {
//                for (int digit1 = 0; digit1 <= 9; digit1++) {
//                    for (int digit2 = 0; digit2 <= 9; digit2++) {
//                        for (int digit3 = 0; digit3 <= 9; digit3++) {
//                            for (String letter2 : letters) {
//                                for (String letter3 : letters) {
//                                    String carNumber = letter1 + digit1 + digit2 + digit3 + letter2 + letter3 + " 116RUS";
//
//                                    String insertQuery = "INSERT INTO car_numbers (number) VALUES (?)";
//                                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
//                                    insertStatement.setString(1, carNumber);
//                                    insertStatement.executeUpdate();
//                                }
//                            }
//                        }
//                    }
//                }
//            }
            int digit = 0;
            for (String letter1 : letters) {
                for (String letter2 : letters) {
                    for (String letter3 : letters) {
                        String carNumber = letter1 + String.format("%03d", digit) + letter2 + letter3 + " 116RUS";

                        String insertQuery = "INSERT INTO car_numbers (number) VALUES (?)";
                        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                        insertStatement.setString(1, carNumber);
                        insertStatement.executeUpdate();

                        digit++;
                    }
                }
            }
            System.out.println("Все номера успешно добавлены в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


