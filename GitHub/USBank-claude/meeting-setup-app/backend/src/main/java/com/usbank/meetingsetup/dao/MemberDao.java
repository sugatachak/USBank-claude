package com.usbank.meetingsetup.dao;

import com.usbank.meetingsetup.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Member> rowMapper = (rs, n) -> Member.builder()
            .id(rs.getLong("id"))
            .agenda(rs.getString("agenda"))
            .date(rs.getString("date"))
            .starttime(rs.getString("starttime"))
            .duration(rs.getInt("duration"))
            .agendaitems(rs.getString("agendaitems"))
            .restrictions(rs.getString("restrictions"))
            .status(rs.getString("status"))
            .build();

    public Long save(Member member) {
        String sql = "INSERT INTO Members (agenda, date, starttime, duration, agendaitems, restrictions, status) VALUES (?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, member.getAgenda());
            ps.setString(2, member.getDate());
            ps.setString(3, member.getStarttime());
            ps.setInt(4, member.getDuration());
            ps.setString(5, member.getAgendaitems());
            ps.setString(6, member.getRestrictions());
            ps.setString(7, member.getStatus());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void updateStatus(Long id, String status) {
        jdbcTemplate.update("UPDATE Members SET status = ? WHERE id = ?", status, id);
    }

    public Optional<Member> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM Members WHERE id = ?", rowMapper, id)
                .stream().findFirst();
    }
}
