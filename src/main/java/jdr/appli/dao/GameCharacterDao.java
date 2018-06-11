package jdr.appli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdr.appli.model.characterClass.CharacterClass;
import jdr.appli.model.gameCharacter.GameCharacter;
import jdr.appli.model.score.Appreciation;
import jdr.appli.model.score.User;

@Repository
public class GameCharacterDAO extends LogSQL implements InsertOne<GameCharacter> {
	
	private DataSource dataSource;
	
	@Autowired
	private GetOne<User> userDAO;
	
	@Autowired
	private GetOne<Appreciation> appreciationDAO;
	
	@Autowired
	private GetOne<CharacterClass> characterClassDAO;
	
	@Autowired
	public GameCharacterDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}
	
	
	public GameCharacter getOne (Long id) throws Exception {
		Connection con = dataSource.getConnection();
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

	public List<GameCharacter> getList() throws Exception {
		Connection con = dataSource.getConnection();
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
	
	public List<GameCharacter> getListForOneUser(Long id) throws Exception {
		Connection con = dataSource.getConnection();
		GameCharacter character;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList<GameCharacter> aListOfGameCharacters = new ArrayList<GameCharacter>();
		try {
			sql = "SELECT * FROM gamecharacter WHERE user = ?";
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
	
	public ResponseEntity<?> insertOne(GameCharacter gameCharacter) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		int i = 0;
		ResponseEntity<?> response;
		gameCharacter.setIdCharacter(new Long(0));
		try {
			String sql = "INSERT INTO gameCharacter ("
					+ "idGameCharacter,"
					+ " user,"
					+ " characterName,"
					+ " characterClass,"
					+ " alliegeance,"
					+ " level,"
					+ " age,"
					+ " sex,"
					+ " height,"
					+ " weight,"
					+ " strength,"
					+ " dexterity,"
					+ " constitution,"
					+ " intelligence,"
					+ " wisdom,"
					+ " charism,"
					+ " endurance,"
					+ " wealth"
					+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setLong(++i,  gameCharacter.getIdCharacter());
			pstmt.setLong(++i, gameCharacter.getUser().getIdUser());
			pstmt.setString(++i, gameCharacter.getCharacterName());
			pstmt.setLong(++i, gameCharacter.getCharacterClass().getIdCharacterClass());
			pstmt.setString(++i, gameCharacter.getAlliegeance());
			pstmt.setInt(++i, gameCharacter.getLevel());
			pstmt.setInt(++i, gameCharacter.getAge());
			pstmt.setString(++i, gameCharacter.getSex());
			pstmt.setDouble(++i, gameCharacter.getHeight());
			pstmt.setInt(++i, gameCharacter.getWeight());
			pstmt.setInt(++i, gameCharacter.getStrength());
			pstmt.setInt(++i, gameCharacter.getDexterity());
			pstmt.setInt(++i, gameCharacter.getConstitution());
			pstmt.setInt(++i, gameCharacter.getIntelligence());
			pstmt.setInt(++i, gameCharacter.getWisdom());
			pstmt.setInt(++i, gameCharacter.getCharisma());
			pstmt.setInt(++i, gameCharacter.getEndurance());
			pstmt.setInt(++i, gameCharacter.getWealth());
			logSQL(pstmt);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				gameCharacter.setIdCharacter(rs.getLong(1));
			}
			response = ResponseEntity.status(HttpStatus.OK).body("Votre personnage a été créé!");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
									 .body("Il y a eu un problème: votre personnage n'a pas été créé!");
			throw e;
		} finally {
			pstmt.close();
			con.close();
		}
		return response;
	}
	
	public GameCharacter getGameCharacterFromResultSet(ResultSet rs) throws Exception {
		GameCharacter character = new GameCharacter();
		character.setIdCharacter(rs.getLong("idGameCharacter"));
		character.setUser(userDAO.getOne(rs.getLong("user")));
		character.setAppreciation(appreciationDAO.getOne(rs.getLong("appreciation")));
		character.setCharacterName(rs.getString("characterName"));
		character.setCharacterClass(characterClassDAO.getOne(rs.getLong("characterClass")));
		character.setAlliegeance(rs.getString("alliegeance"));
		character.setLevel(rs.getInt("level"));
		character.setAge(rs.getInt("age"));
		character.setSex(rs.getString("sex"));
		character.setHeight(rs.getDouble("height"));
		character.setWeight(rs.getInt("weight"));
		character.setStrength(rs.getInt("strength"));
		character.setDexterity(rs.getInt("dexterity"));
		character.setConstitution(rs.getInt("constitution"));
		character.setIntelligence(rs.getInt("intelligence"));
		character.setWisdom(rs.getInt("wisdom"));
		character.setCharisma(rs.getInt("charism"));
		character.setEndurance(rs.getInt("endurance"));
		character.setWealth(rs.getInt("wealth"));
		return character;
	}
	
}
