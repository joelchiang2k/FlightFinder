package com.synergisticit.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

public class PassengerRowMapper implements RowMapper<Passenger> {

    @Override
    public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(rs.getLong("passengerId"));
        passenger.setPassengerFirstName(rs.getString("passengerFirstName"));
        passenger.setPassengerLastName(rs.getString("passengerLastName"));
        passenger.setPassengerEmail(rs.getString("passengerEmail"));
        passenger.setPassengerPhoneNo(rs.getString("passengerPhoneNo"));
        Date dobDate = rs.getDate("passengerDOB");
        // Convert Date to LocalDate
        LocalDate dob = ((java.sql.Date) dobDate).toLocalDate();
        passenger.setPassengerDOB(dob);
        passenger.setPassengerLastName(rs.getString("passengerDOB"));
        String genderStr = rs.getString("passengerGender");
        // Convert string to Gender enum
        Gender gender = Gender.valueOf(genderStr);
        passenger.setPassengerGender(gender);
        
        return passenger;
    }
}
