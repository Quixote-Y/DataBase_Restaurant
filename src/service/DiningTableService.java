package service;

import java.util.List;

import dao_.DiningTableDao;
import javaBean.DiningTable;
import utils.Utility;

public class DiningTableService {
	
     private DiningTableDao diningTableDao = new DiningTableDao();
	
     //��ʾ����״̬
     public void showDiningTable() {
    	 String sql = "select id, state from diningTable";
    	 List<DiningTable> list=diningTableDao.queryMultiply(sql, DiningTable.class, null);
    	 
    	 System.out.println(list);
     }

     //Ԥ������
     ////������ҪԤ���Ĳ����ţ�ȥ���������������񣬷����ǿ��У����� Ԥ�������� �绰
     public void bookTable() {
    	 System.err.println("������ҪԤ���Ĳ�����:");
    	 int tableNumber = Utility.readInt();
    	 String sql_select = "select state from diningTable where id = ?";
    	 String state=(String)diningTableDao.queryScalar(sql_select, tableNumber);
    	 if("��Ԥ��".equals(state)) {
    		 System.out.println("�����Ѿ���Ԥ����������ѡ���Ĳ�����");
    		 showDiningTable();
    	 }else {
    		 System.out.println("��������������:");
    		 String name = Utility.readString(32);
    		 System.out.println("���������ĵ绰:");
    		 String phone = Utility.readString(20);
    		 
    		 String sql_update = "update diningTable set state = '��Ԥ��' ,orderName = ?,orderTel=? where id =?";
    		 diningTableDao.update(sql_update, name,phone,tableNumber);
    		 System.out.println("Ԥ���ɹ�");
    	 }
    	 
     }
}
