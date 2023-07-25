package dao_;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.JDBCUtilsByDruid;


/*使用dao的意义在何：
 *    对于jdbc包里的sql操作，十分僵硬，每一条sql语句都必须要去单独的调用一次query方法，
 *       对每一张表的操作，当业务逻辑复杂时显得不灵活 ，一次使用了 Dao 来理清业务逻辑
 *       对于每一张表 ，我们都可以书写相应的 Dao去实现对这张表的增删改查，通过方法，传递sql语句，
 *       相同类型的sql用一个方法，传入不同内容的sql语句，大大降低了程序的重复操作，也提高了灵活性
 *          
 *     这个类BasicDao 设计为所有 专用Dao的父类 ，它实现所有Dao都会用到的基本增删改查操作，专用道
 *     通过继承它，然后扩展的方式来实现对数据库特点数据表的操作
 *     减少代码冗余
 */
public class BasicDao<T> {//泛型指定具体类型，指定子类将来操作什么样类型的JavaBean

	static QueryRunner qr = new QueryRunner();
	
	
	/**通用的dml方法,针对任意表
	 * 只要是调用qeryRunner.update的sql都是用的这个，那就是增删改操作
	 * @param sql     传入sql更新数据（dml）类语句
	 * @param parameters  传入sql语句的里占位符对于的参数 ，可变参数
	 * @return   返回语句对数据表的影响行数
	 */
	public int update(String sql,Object...parameters) {
		Connection connection=null;
		try {
			//1.通过工具类先获取Druid数据库连接池的连接
			connection = JDBCUtilsByDruid.getConnection();
            //执行sql跟新语句，返回 
			 return qr.update(connection, sql, parameters);
		} catch (SQLException e) {
			//老方法，抛出运行时异常，调用者可选择不处理向后抛
			throw new RuntimeException(e);
		}finally {//关闭
			JDBCUtilsByDruid.close(null, null, connection);
		}
	}
	/**
	 *  返回多个对象，查询结果是多行的查询，针对所有数据表
	 * @param sql        sql查询语句
	 * @param clazz      返回结果对应类的Class，也就是操作目标表对应的javaBean对象的Class对象，这个参数用于在BeanListHandler（）方法中将查询结束传回来的ResultSet转换为存有对应Clss类对象的对象
	 * @param parameters  可变参数，占位符的值
	 * @return
	 */
	public List<T> queryMultiply(String sql,Class<T> clazz ,Object...parameters ){
		Connection connection = null;
		try {
			connection = JDBCUtilsByDruid.getConnection();
			return qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
		} catch (SQLException e) {
			//转换抛出
			throw new RuntimeException(e);
		}finally{
			JDBCUtilsByDruid.close(null, null, connection);
		}
	
	}
	//返回单行查询结果
	public T querySingle(String sql,Class<T> clazz,Object...parameters) {
		Connection connection=null;
		try {
			connection = JDBCUtilsByDruid.getConnection();
			return qr.query(connection, sql, new BeanHandler<T>(clazz), parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JDBCUtilsByDruid.close(null, null, connection);
		}
		
	}
	//返回一个对象，查询结果只有一行一列
	public Object queryScalar(String sql,Object...paramenters) {
		Connection connection = null;
		try {
			connection = JDBCUtilsByDruid.getConnection();
			return qr.query(connection, sql, new ScalarHandler(), paramenters);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JDBCUtilsByDruid.close(null, null, connection);
		}
		
	}
	
	
}
