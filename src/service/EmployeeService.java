package service;

import dao_.EmployeeDao;
import javaBean.Employee;

//Service ��ҵ��㣬���ж����ݱ��Ӧ�ò������������
//dao�Ǹ���ʵ�ֶ����ݿ����ݵ���ȡ����ҵ����ṩ�ӿ�
public class EmployeeService {

	EmployeeDao employeeDao = new EmployeeDao();
	
	/**
	 *ְԱ��¼ϵͳ 
	 * @param empId  Ա����
	 * @param pwd     ����
	 * @return   �Ƿ�ɹ�
	 */
	public boolean login(String empId,String pwd) {
		String sql = "select * from employee where empId=? and pwd =md5(?)";
		if(employeeDao.querySingle(sql, Employee.class, empId,pwd)!=null) {
			return true;
		}
		return false;
		
	}
}
