package com.thinne.spring_web_page.repositories;

import com.thinne.spring_web_page.entities.Candidate;
import com.thinne.spring_web_page.repositories.mapper.CandidateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class candidateRepository {
    private DataSource ds;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource ds) {
        this.ds = ds;
        this.jdbcTemplate = new JdbcTemplate(ds);
    }
    public List<Candidate> getAllCandidates() {
        String sql = "SELECT * FROM candidate";
        return jdbcTemplate.query(sql, new CandidateMapper());
    }
    public boolean insert(Candidate candidate) {
        String sql = "insert into candidate (last_name,middle_name, first_name, dob, email, address, phone) value(?,?,?,?,?,?,?)";
        String fullName = candidate.getFullName();
        String[] name = fullName.split(" ");
        switch (name.length) {
            case 1:
                jdbcTemplate.update(sql, name[0],"","", candidate.getDob(), candidate.getEmail(), candidate.getAddress(), candidate.getPhone());
                break;
            case 2:
                jdbcTemplate.update(sql, name[0],"" ,name[1], candidate.getDob(), candidate.getEmail(), candidate.getAddress(), candidate.getPhone());
                break;
            case 3:
                jdbcTemplate.update(sql, name[0], name[1], name[2], candidate.getDob(), candidate.getEmail(), candidate.getAddress(), candidate.getPhone());
                break;
            case 4:
                jdbcTemplate.update(sql, name[0], name[1]+" "+name[2], name[3], candidate.getDob(), candidate.getEmail(), candidate.getAddress(), candidate.getPhone());
                break;
            case 5:
                jdbcTemplate.update(sql, name[0], name[1]+" "+ name[2]+" "+ name[3], name[4], candidate.getDob(), candidate.getEmail(), candidate.getAddress(), candidate.getPhone());
                break;
            default:
                return false;
        }
        System.out.println("insert candidate successfully");
        return true;
    }
}
