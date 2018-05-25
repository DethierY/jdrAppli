package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.CharacterClass;

@Repository
public class CharacterClassDaoJdbc extends LogSQL implements CharacterClassDao {
	
	ResultSetContext rsContext;
	
	@Override
	public List<CharacterClass> getListCharacterClasses(Connection con) throws Exception {
		CharacterClass characterClass;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<CharacterClass> aListOfCharacterClasses = new ArrayList<CharacterClass>();
		try {
			sql = "SELECT * FROM characterclass";
			pstmt = con.prepareStatement(sql);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			System.out.println("dans le try du getListCharacterClass");
			rsContext = new ResultSetContext(new CharacterClassResultSet());
			if (rsContext == null) {
				System.out.println("rsContext = null");
			} else {
				System.out.println("rsContext = pas null");
			}
			while (rs.next()) {
				characterClass = (CharacterClass) rsContext.executeResultSetStrategy(rs);
				aListOfCharacterClasses.add(characterClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return aListOfCharacterClasses;
	}
	
	@Override
	public CharacterClass getCharacterClass(Connection con, Long id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		CharacterClass characterClass = null;
		try {
			String sql = "SELECT * FROM characterclass WHERE idCharacterClass = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			rsContext = new ResultSetContext(new CharacterClassResultSet());
			if (rs.next())
				characterClass = (CharacterClass) rsContext.executeResultSetStrategy(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
		}
		return characterClass;
	}

}
