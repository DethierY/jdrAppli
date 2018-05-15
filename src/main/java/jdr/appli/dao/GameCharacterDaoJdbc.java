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

import jdr.appli.model.GameCharacter;
import jdr.appli.service.AppreciationService;
import jdr.appli.service.CharacterClassService;
import jdr.appli.service.UserService;

@Repository
public class GameCharacterDaoJdbc extends LogSQL implements GameCharacterDao {
	
	private DataSource datasource;
	
	@Autowired
	public GameCharacterDaoJdbc(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CharacterClassService characterClassService;
	
	@Autowired
	private AppreciationService appreciationService;
	
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
	public List<GameCharacter> getListUserGameCharacters(Long id) throws Exception {
		Connection con = datasource.getConnection();
		GameCharacter character;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<GameCharacter> aListOfGameCharacters = new ArrayList<GameCharacter>();
		try {
			sql = "SELECT * FROM gamecharacter"
				+ " JOIN user ON gamecharacter.user = user.idUser"
				+ " WHERE idUser = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, id);
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
	
	@Override
	public GameCharacter insertGameCharacter(GameCharacter gameCharacter) throws Exception {
		Connection con = datasource.getConnection();
		PreparedStatement pstmt = null;
		GameCharacter result = null;
		int i = 0;
		gameCharacter.setIdCharacter(new Long(0));
		try {
			String sql = "INSERT INTO gameCharacter (idGameCharacter, user, characterName, characterClass, level, sex, height, alliegeance) VALUES (?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setLong(++i,  gameCharacter.getIdCharacter());
			pstmt.setLong(++i, gameCharacter.getUser().getIdUser());
			pstmt.setString(++i, gameCharacter.getCharacterName());
			pstmt.setLong(++i, gameCharacter.getCharacterClass().getIdCharacterClass());
			pstmt.setInt(++i, gameCharacter.getLevel());
			pstmt.setString(++i, gameCharacter.getSex());
			pstmt.setDouble(++i,  gameCharacter.getHeight());
			pstmt.setString(++i, gameCharacter.getAlliegeance());
			
			logSQL(pstmt);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				gameCharacter.setIdCharacter(rs.getLong(1));		
				result = gameCharacter;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
			con.close();
		}
		return result;
	}
	
	private GameCharacter getGameCharacterFromResultSet(ResultSet rs) throws Exception {
		GameCharacter character = new GameCharacter();
		character.setIdCharacter(rs.getLong("idGameCharacter"));
		character.setUser(userService.getOneUser(rs.getLong("user")));
		character.setAppreciation(appreciationService.getOneAppreciation(rs.getLong("appreciation")));
		character.setCharacterName(rs.getString("characterName"));
		character.setCharacterClass(characterClassService.getOneCharacterClass(rs.getLong("characterClass")));
		character.setLevel(rs.getInt("level"));
		character.setSex(rs.getString("sex"));
		character.setHeight(rs.getDouble("height"));
		character.setAlliegeance(rs.getString("alliegeance"));
		return character;
	}
	
}
