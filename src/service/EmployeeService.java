package service;

import dao_.EmployeeDao;
import javaBean.Employee;

//Service 是业务层，所有对数据表的应用操作都在这个层
//dao是负责实现对数据库数据的提取，给业务层提供接口
public class EmployeeService {

	EmployeeDao employeeDao = new EmployeeDao();
	
	/**
	 *职员登录系统 
	 * @param empId  员工号
	 * @param pwd     密码
	 * @return   是否成功
	 */
	public boolean login(String empId,String pwd) {
		String sql = "select * from employee where empId=? and pwd =md5(?)";
		if(employeeDao.querySingle(sql, Employee.class, empId,pwd)!=null) {
			return true;
		}
		return false;
		
	}
}
