package com.PEA.webAsset.Share.Generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConsiderationGeneratorId implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String preFix = "consider-";
        Connection connection = session.connection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(consider_id) as id from tb_consideration");

            if (resultSet.next()) {
                int num = resultSet.getInt(1) + 10000000;
                String generateNum = preFix + new Integer(num);
                return generateNum;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
