/*
 * Etienne KOA :  18/07/2024
 */

package etienne.springframework.jdbc.dao;

import etienne.springframework.jdbc.domain.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorExtractor implements ResultSetExtractor<Author> {


	@Override
	public Author extractData(ResultSet rs) throws SQLException, DataAccessException {

		rs.next();
		return new AuthorMapper().mapRow(rs, 0);
	}
}
