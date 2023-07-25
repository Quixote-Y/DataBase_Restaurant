package service;

import java.util.ArrayList;
import java.util.List;

import dao_.MenuDao;
import javaBean.Menu;
import utils.Utility;

public class MenuService {

	MenuDao menuDao = new MenuDao();
	List<Menu> list = new ArrayList<>();
	
	//显示菜单
	public void showMenu() {
		String sql = "select * from menu";
		
		List<Menu> list = menuDao.queryMultiply(sql, Menu.class, null);
		System.out.println(list);
	}
	
	//点餐服务
	//流程就是：先输入桌号，显示菜单，然后输入餐品对应的id，创建一个集合存储用户点了的菜
	public void orderMenu() {
		System.out.println("请输入桌号:");
		int tableNumber = Utility.readInt();
		System.out.println("=====================菜单===========================");
		showMenu();
		System.out.println("请选择您需要的餐品(输入-1时结束):");
		int menuId= Utility.readInt();
		while(menuId!=-1) {
			String sql = "select * from menu where id =?";
			Menu order=menuDao.querySingle(sql, Menu.class, menuId);
			list.add(order);
			System.out.println("菜品"+order.getName()+"已加入您的菜单,您可以继续输入:");
			menuId= Utility.readInt();
		}
		System.out.println("您的菜单:");
		System.out.println(list);
	}
	//查看菜单
	public void showOrderMenu() {
		System.out.println(list);
	}
	//计算总花费
	public double sumPrice() {
		double sumPrice = 0D;
		for(int i=0;i<list.size();i++) {
			sumPrice+=list.get(i).getPrice();
		}
		return sumPrice;
	}
	
	//结账
	public void checkout() {
	    double sumprice=sumPrice();
		System.out.println("您一个消费"+sumprice+"元");
		System.out.println("请选择您的结账方式:");
		System.out.println("\t支付宝\t\t微信");
		//这里后期可以加东西，去调用相应的api，获取返回值
		System.out.println("结账成功！\n很荣幸为您服务");
		list.clear();
	}
	
	//退出
	public boolean leave() {
		if(sumPrice()>0) {
			return false;
		}
		return true;
	}
}
