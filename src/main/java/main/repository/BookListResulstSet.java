package main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class BookListResulstSet implements ResultSetExtractor<String> {

	@Override
	public String extractData(ResultSet rs) throws SQLException, DataAccessException {
		StringBuilder sb = new StringBuilder();
		String semiColumn = ", ";
		while(rs.next()) {
			sb.append(rs.getLong("id"));
			sb.append(semiColumn);
			sb.append(rs.getString("title"));
			sb.append(semiColumn);
			sb.append(rs.getInt("nb_pages"));
			sb.append(semiColumn);
			sb.append(rs.getString("author"));
			sb.append(semiColumn);
			sb.append(rs.getDate("publication_date"));
			sb.append(semiColumn + System.lineSeparator());
		}
		
		return sb.toString();
		
	}

}
