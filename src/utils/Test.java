package utils;

import java.sql.Connection;

public class Test {

	public static void main(String[] args){
		
		//�ǵü�鵼��jar����ȫ��û��©��mysql�����Ӱ������ǰ��Ҳ���Driver
		Connection connection = JDBCUtilsByDruid.getConnection();
		System.out.println(connection);
		int i = Utility.readInt();
		System.out.println("i="+i);
		JDBCUtilsByDruid.close(null, null, connection);
	}
}
