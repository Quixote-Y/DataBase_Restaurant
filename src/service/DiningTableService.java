package service;

import java.util.List;

import dao_.DiningTableDao;
import javaBean.DiningTable;
import utils.Utility;

public class DiningTableService {
	
     private DiningTableDao diningTableDao = new DiningTableDao();
	
     //显示餐桌状态
     public void showDiningTable() {
    	 String sql = "select id, state from diningTable";
    	 List<DiningTable> list=diningTableDao.queryMultiply(sql, DiningTable.class, null);
    	 
    	 System.out.println(list);
     }

     //预定餐桌
     ////先输入要预定的餐桌号，去查这个餐桌空闲与否，返回是空闲，输入 预定人姓名 电话
     public void bookTable() {
    	 System.err.println("请输入要预定的餐桌号:");
    	 int tableNumber = Utility.readInt();
    	 String sql_select = "select state from diningTable where id = ?";
    	 String state=(String)diningTableDao.queryScalar(sql_select, tableNumber);
    	 if("被预定".equals(state)) {
    		 System.out.println("此桌已经被预定，您可以选择别的餐桌：");
    		 showDiningTable();
    	 }else {
    		 System.out.println("请输入您的姓名:");
    		 String name = Utility.readString(32);
    		 System.out.println("请输入您的电话:");
    		 String phone = Utility.readString(20);
    		 
    		 String sql_update = "update diningTable set state = '被预定' ,orderName = ?,orderTel=? where id =?";
    		 diningTableDao.update(sql_update, name,phone,tableNumber);
    		 System.out.println("预定成功");
    	 }
    	 
     }
}
