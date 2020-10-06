package JDBC;

import JDBC.ConnectionFactory;

import java.sql.Connection;

public class teste {

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        Connection conn = factory.getConnection();

        System.out.println(conn);

    }

}