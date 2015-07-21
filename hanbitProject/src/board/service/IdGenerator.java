package board.service;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class IdGenerator {

	private JdbcTemplate jdbcTemplate;

	public IdGenerator(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int generateNextId(String sequenceName) throws IdGenerationFailedException, SQLException {

		String sql = "select next_value from id_sequence where sequence_name = ? for update";
		int id = jdbcTemplate.queryForInt(sql, new Object[] { sequenceName });
		id++;
		sql = "update id_sequence set next_value = ? where sequence_name = ?";
		jdbcTemplate.update(sql, new Object[] { id, sequenceName });
		return id;
	}

}
