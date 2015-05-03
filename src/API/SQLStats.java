package API;

import java.sql.ResultSet;
import java.sql.SQLException;




public class SQLStats {

	public static boolean playerExists(String uuid){
	
		try{
			ResultSet rs = Main.mysql.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
			
			if(rs.next()){
				return rs.getString("UUID") !=null;
			}
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createPlayer(String uuid) {
		if(!(playerExists(uuid))) {
		Main.mysql.update("INSERT INTO Stats(UUID, KILLS, DEATHS) VALUES ('" + uuid + "', '0', '0');");
	}
	}


    public static Integer getKills(String uuid) {
    	Integer i = 0;
    	
    	if(playerExists(uuid)) {
    		
    		try{
    			ResultSet rs = Main.mysql.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
    			if((!rs.next()) || (Integer.valueOf(rs.getInt("KILLS")) == null ));
    			
    			i = rs.getInt("KILLS");
    			
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	
    		
    	}else {
    		createPlayer(uuid);
    		getKills(uuid);
    		
    	}
    	return i;
    }

    public static Integer getDeaths(String uuid) {
    	Integer i = 0;
    	
    	if(playerExists(uuid)) {
    		
    		try{
    			ResultSet rs = Main.mysql.query("SELECT * FROM Stats WHERE UUID= '" + uuid + "'");
    			if((!rs.next()) || (Integer.valueOf(rs.getInt("DEATHS")) == null ));
    			
    			i = rs.getInt("DEATHS");
    			
    		}catch(SQLException e) {
    			e.printStackTrace();
    		}
    	
    		
    	}else {
    		createPlayer(uuid);
    		getDeaths(uuid);
    		
    	}
    	return i;
    }
    
    
    



}



