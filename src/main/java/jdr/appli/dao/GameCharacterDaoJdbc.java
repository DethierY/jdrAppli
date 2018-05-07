package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdr.appli.model.Appreciation;
import jdr.appli.model.GameCharacter;
import jdr.appli.model.User;
import jdr.appli.model.characterClass.CharacterClass;

@Repository
public class GameCharacterDaoJdbc implements GameCharacterDao {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private DataSource datasource;
	
	@Autowired
	public GameCharacterDaoJdbc(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}
	
	@Override
	public List<GameCharacter> getListGameCharacters() throws Exception {
		Connection con = datasource.getConnection();
		GameCharacter character;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<GameCharacter> aListOfGameCharacters = new ArrayList<GameCharacter>();
		try {
			sql = "SELECT * FROM gamecharacter";
			pstmt = con.prepareStatement(sql);
			logSQL (pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				character = getGameCharacterFromResultSet(rs);
				aListOfGameCharacters.add(character);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return aListOfGameCharacters;
		
	}
	
	@Override
	public GameCharacter getGameCharacter (Long id) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		GameCharacter character = null;
		try {
			String sql = "SELECT * FROM gamecharacter WHERE idGameCharacter = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				character = getGameCharacterFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return character;
	}
	
	private User getUser (Long id) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		User user = null;
		try {
			String sql = "SELECT * FROM user WHERE idUser = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				user = getUserFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return user;
	}
	
	private Appreciation getAppreciation (Long id) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		Appreciation appreciation = null;
		try {
			String sql = "SELECT * FROM appreciation WHERE idAppreciation = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				appreciation = getAppreciationFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return appreciation;
	}
	
	private CharacterClass getCharacterClass (Long id) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		CharacterClass characterClass = null;
		try {
			String sql = "SELECT * FROM characterclass WHERE idCharacterClass = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
			logSQL(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next())
				characterClass = getCharacterClassFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !: " + pstmt.toString(), e);
		} finally {
			pstmt.close();
			con.close();
		}
		return characterClass;
	}
	
	private GameCharacter getGameCharacterFromResultSet(ResultSet rs) throws Exception {
		GameCharacter character = new GameCharacter();
		character.setIdCharacter(rs.getLong("idGameCharacter"));
		character.setUser(getUser(rs.getLong("user")));
		character.setAppreciation(getAppreciation(rs.getLong("appreciation")));
		character.setCharacterName(rs.getString("characterName"));
		character.setCharacterClass(getCharacterClass(rs.getLong("characterClass")));
		character.setLevel(rs.getInt("level"));
		return character;
	}

	private User getUserFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setIdUser(rs.getLong("idUser"));
		user.setName(rs.getString("name"));
		return user;
	}
	
	private Appreciation getAppreciationFromResultSet(ResultSet rs) throws SQLException {
		Appreciation appreciation = new Appreciation();
		appreciation.setIdAppreciation(rs.getLong("idAppreciation"));
		appreciation.setAverageScore(rs.getDouble("averageScore"));
		appreciation.setNumberOfVoters(rs.getInt("numberOfVoters"));
		return appreciation;
	}
	
	private CharacterClass getCharacterClassFromResultSet(ResultSet rs) throws SQLException {
		CharacterClass characterClass = new CharacterClass();
		characterClass.setIdCharacterClass(rs.getLong("idCharacterClass"));
		characterClass.setClassName(rs.getString("className"));
		return characterClass;
	}

	private void logSQL(PreparedStatement pstmt) {
		String sql;
		if (pstmt == null)
			return;
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
	}
	
}
