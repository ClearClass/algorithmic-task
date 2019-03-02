package lib.clearclass.tasks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class Well {
	private int size; // длина слова
	private Dictionary dic = new DBdictionary();
	private String[] side = new String[4];
	
	public Well(int size){
		this.size = size;
		build();
	}
	
	private void build(){
		for (int i = 0; i < 10; i++) { // 10 попыток найти решение
			String st0 = dic.getWord();
			Set<String> hs1 = dic.getWords(st0.charAt(1));
			for(String st1 : hs1){
				if (st1.equals(st0)) continue;
				Set<String> hs2 = dic.getWords(st1.charAt(size-2));
				for(String st2 : hs2){
					if (st2.equals(st1) || st2.equals(st0)) continue;
					String st3 = dic.getWord(st0.charAt(size-2), st2.charAt(size-2));
					if(st3!=null && !st3.equals(st2) && !st3.equals(st1) && !st3.equals(st0)){
						side[0] = st0;
						side[1] = st1;
						side[2] = st2;
						side[3] = st3;
						return;
					}
				}
			}
		}
		throw new RuntimeException("Набор слов не найден");
	}
	
	public void draw(){
		System.out.println(sps(2) + side[0].charAt(0) + sps(2*size - 7) + side[2].charAt(0) + sps(2));
		System.out.println(String.join(" ", side[1].split("")));
		for (int i = 2; i < size - 2; i++)
			System.out.println(sps(2) + side[0].charAt(i) + sps(2*size - 7) + side[2].charAt(i) + sps(2));
		System.out.println(String.join(" ", side[3].split("")));
		System.out.println(sps(2) + side[0].charAt(size-1) + sps(2*size - 7) + side[2].charAt(size-1) + sps(2));
	}
	
	private String sps(int num){ //spase function
		return String.format("%" + num + "s", " ");
	}
	
	public String[] getSide(){
		return side;
	}
	
	public Dictionary getDictionary(){ // для целей тестирования
		return dic;
	}
		
	private interface ConnectionFactory {
		Connection getConnection() throws SQLException;
	}
	
	// фабрика соединений для встраиваемой СУБД H2
	private class H2ConnectionFactory implements ConnectionFactory {
		String url = "jdbc:h2:./dictionary";
		String user = "user";
		String password = "pass";
		
		@Override
		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection(url, user, password);
		}
	}
	
	// фабрика соединений на основе SingleConnectionDataSource 
	// (позволяет уменьшить время выполнения на ~30%)
	private class H2SingleConnectionFactory extends H2ConnectionFactory {
		SingleConnectionDataSource ds = new SingleConnectionDataSource();
		
		H2SingleConnectionFactory() {
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			ds.setSuppressClose(true);
		}
		
		@Override
		public Connection getConnection() throws SQLException {
			return ds.getConnection();
		}
	}
	
	// интерфейс словаря
	// доступ по умолчанию использован для целей тестирования
	interface Dictionary {
		String getWord(); // любое слово
		Set<String> getWords(char char2); // задан второй символ
		String getWord(char char2, char char_); // задан второй и предпоследний символы
	}
	
	//реализация словаря на основе абстрактной СУБД
	private class DBdictionary implements Dictionary {
		ConnectionFactory cf = new H2SingleConnectionFactory(); 
		
		//создаем словарь, если его нет
		DBdictionary() {
			try {
				Connection conn = cf.getConnection();
				try(Statement stm = conn.createStatement()){
					stm.executeUpdate("CREATE TABLE IF NOT EXISTS dictionary(word VARCHAR(32) PRIMARY KEY);");
					ResultSet rs = stm.executeQuery("SELECT COUNT(*) FROM dictionary");
					rs.next();
					if(rs.getInt(1)==0){ //если таблица пуста, начинаем заполнение
						conn.setAutoCommit(false);
						try(BufferedReader br = new BufferedReader(
									new InputStreamReader(
									new FileInputStream("dictionary.txt")));
						   PreparedStatement pstm = conn.prepareStatement("INSERT INTO dictionary VALUES(?);")){	
							
							// проверяем строки на уникальность
							Set<String> hs = new HashSet<>(); 
							String word;
							while((word = br.readLine())!=null)
								if(hs.add(word)){
									pstm.setString(1, word);
									pstm.executeUpdate();
								}
						}
						conn.commit();
					}
				} catch(Exception e){
					if(!conn.getAutoCommit()) conn.rollback();
					throw new RuntimeException(e);
				} finally {
					conn.close();
				} 
			} catch(SQLException e){
				throw new RuntimeException(e);
			}
		}
		
		@Override
		public String getWord() {
			try(Connection conn = cf.getConnection();
				Statement stm = conn.createStatement()){
								
				ResultSet rs = stm.executeQuery("SELECT word FROM dictionary "
								+ "WHERE LENGTH(word) = " + size
								+ " ORDER BY RANDOM() LIMIT 1");
				rs.next();
				return rs.getString("word");
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		@Override
		public Set<String> getWords(char char2) {
			String __ = String.format("%" + (size-2) + "s", "_").replace(' ', '_');
			String template = "_" + char2 + __;
			try(Connection conn = cf.getConnection();
				Statement stm = conn.createStatement()){
											
				ResultSet rs = stm.executeQuery("SELECT word FROM dictionary "
								+ "WHERE word LIKE '" + template
								+ "' ORDER BY RANDOM() LIMIT 10");
				HashSet<String> hs = new HashSet<>();
				while(rs.next())
					hs.add(rs.getString("word"));
				return hs;
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public String getWord(char char2, char char_) {
			String __ = String.format("%" + (size-4) + "s", "_").replace(' ', '_');
			String template = "_" + char2 + __ + char_ + "_";
			try(Connection conn = cf.getConnection();
				Statement stm = conn.createStatement()){
											
				ResultSet rs = stm.executeQuery("SELECT word FROM dictionary "
								+ "WHERE word LIKE '" + template
								+ "' ORDER BY RANDOM() LIMIT 1");
				if(rs.next()) 
					return rs.getString("word");
				return null;
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}