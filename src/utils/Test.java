package utils;

import java.sql.Connection;

public class Test {

	public static void main(String[] args){
		
		//记得检查导入jar包齐全了没，漏了mysql的连接包，老是包找不到Driver
		Connection connection = JDBCUtilsByDruid.getConnection();
		System.out.println(connection);
		int i = Utility.readInt();
		System.out.println("i="+i);
		JDBCUtilsByDruid.close(null, null, connection);
	}
}
