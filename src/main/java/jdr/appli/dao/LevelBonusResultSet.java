package jdr.appli.dao;

import java.sql.ResultSet;

import jdr.appli.model.characterClass.LevelBonus;

public class LevelBonusResultSet implements ObjectResultSet{
	
	@Override
	public LevelBonus getObjectFromResultSet(ResultSet rs) throws Exception {
		LevelBonus levelBonus = new LevelBonus();
		levelBonus.setIdLevelBonus(rs.getLong("idLevelBonus"));
		levelBonus.setLevel1(rs.getInt("level1"));
		levelBonus.setLevel2(rs.getInt("level2"));
		levelBonus.setLevel3(rs.getInt("level3"));
		levelBonus.setLevel4(rs.getInt("level4"));
		levelBonus.setLevel5(rs.getInt("level5"));
		levelBonus.setLevel6(rs.getInt("level6"));
		levelBonus.setLevel7(rs.getInt("level7"));
		levelBonus.setLevel8(rs.getInt("level8"));
		levelBonus.setLevel9(rs.getInt("level9"));
		levelBonus.setLevel10(rs.getInt("level10"));
		levelBonus.setLevel11(rs.getInt("level11"));
		levelBonus.setLevel12(rs.getInt("level12"));
		levelBonus.setLevel13(rs.getInt("level13"));
		levelBonus.setLevel14(rs.getInt("level14"));
		levelBonus.setLevel15(rs.getInt("level15"));
		levelBonus.setLevel16(rs.getInt("level16"));
		levelBonus.setLevel17(rs.getInt("level17"));
		levelBonus.setLevel18(rs.getInt("level18"));
		levelBonus.setLevel19(rs.getInt("level19"));
		levelBonus.setLevel20(rs.getInt("level20"));
		return levelBonus;
	}

}
