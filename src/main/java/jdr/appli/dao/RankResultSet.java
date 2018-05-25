package jdr.appli.dao;

import java.sql.ResultSet;

import jdr.appli.model.characterClass.Rank;

public class RankResultSet implements ObjectResultSet {
	
	@Override
	public Rank getObjectFromResultSet(ResultSet rs) throws Exception {
		Rank rank = new Rank();
		rank.setIdRank(rs.getLong("idRank"));
		rank.setLevel1(rs.getString("level1"));
		rank.setLevel2(rs.getString("level2"));
		rank.setLevel3(rs.getString("level3"));
		rank.setLevel4(rs.getString("level4"));
		rank.setLevel5(rs.getString("level5"));
		rank.setLevel6(rs.getString("level6"));
		rank.setLevel7(rs.getString("level7"));
		rank.setLevel8(rs.getString("level8"));
		rank.setLevel9(rs.getString("level9"));
		rank.setLevel10(rs.getString("level10"));
		rank.setLevel11(rs.getString("level11"));
		rank.setLevel12(rs.getString("level12"));
		rank.setLevel13(rs.getString("level13"));
		rank.setLevel14(rs.getString("level14"));
		rank.setLevel15(rs.getString("level15"));
		rank.setLevel16(rs.getString("level16"));
		rank.setLevel17(rs.getString("level17"));
		rank.setLevel18(rs.getString("level18"));
		rank.setLevel19(rs.getString("level19"));
		rank.setLevel20(rs.getString("level20"));
		return rank;
	}

}
