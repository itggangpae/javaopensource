package opensource;

public class Main {
	public static void main(String[] args) {
		  System.out.println("MySql JDBC 드라이버 로딩중...");
		  try
		  {
		   Class.forName("com.mysql.jdbc.Driver");
		   System.out.println("MySql JDBC 드라이버 로딩 성공...");
		   
		  }
		  catch(ClassNotFoundException e)
		  {
		   System.err.println("MySql JDBC 드라이버 로딩 실패...");
		   System.err.println(e.getMessage());
		   System.exit(0);
		  }
	}
}
