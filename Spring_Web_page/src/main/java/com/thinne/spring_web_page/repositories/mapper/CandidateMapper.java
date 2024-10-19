package com.thinne.spring_web_page.repositories.mapper;

import com.thinne.spring_web_page.entities.Candidate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidateMapper implements RowMapper<Candidate> {
    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getInt("candidate_id"));
        candidate.setFullName(rs.getString("last_name") + " " + rs.getString("middle_name") + " " + rs.getString("first_name"));
        candidate.setAddress(rs.getString("address"));
        candidate.setPhone(rs.getString("phone"));
        Date birthDate = rs.getDate("dob");
        if (birthDate != null)
            candidate.setDob(birthDate.toLocalDate());
        else candidate.setDob(null);
        return candidate;
    }
}
