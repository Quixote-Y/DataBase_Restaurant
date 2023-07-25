package service;

import java.util.ArrayList;
import java.util.List;

import dao_.MenuDao;
import javaBean.Menu;
import utils.Utility;

public class MenuService {

	MenuDao menuDao = new MenuDao();
	List<Menu> list = new ArrayList<>();
	
	//��ʾ�˵�
	public void showMenu() {
		String sql = "select * from menu";
		
		List<Menu> list = menuDao.queryMultiply(sql, Menu.class, null);
		System.out.println(list);
	}
	
	//��ͷ���
	//���̾��ǣ����������ţ���ʾ�˵���Ȼ�������Ʒ��Ӧ��id������һ�����ϴ洢�û����˵Ĳ�
	public void orderMenu() {
		System.out.println("����������:");
		int tableNumber = Utility.readInt();
		System.out.println("=====================�˵�===========================");
		showMenu();
		System.out.println("��ѡ������Ҫ�Ĳ�Ʒ(����-1ʱ����):");
		int menuId= Utility.readInt();
		while(menuId!=-1) {
			String sql = "select * from menu where id =?";
			Menu order=menuDao.querySingle(sql, Menu.class, menuId);
			list.add(order);
			System.out.println("��Ʒ"+order.getName()+"�Ѽ������Ĳ˵�,�����Լ�������:");
			menuId= Utility.readInt();
		}
		System.out.println("���Ĳ˵�:");
		System.out.println(list);
	}
	//�鿴�˵�
	public void showOrderMenu() {
		System.out.println(list);
	}
	//�����ܻ���
	public double sumPrice() {
		double sumPrice = 0D;
		for(int i=0;i<list.size();i++) {
			sumPrice+=list.get(i).getPrice();
		}
		return sumPrice;
	}
	
	//����
	public void checkout() {
	    double sumprice=sumPrice();
		System.out.println("��һ������"+sumprice+"Ԫ");
		System.out.println("��ѡ�����Ľ��˷�ʽ:");
		System.out.println("\t֧����\t\t΢��");
		//������ڿ��ԼӶ�����ȥ������Ӧ��api����ȡ����ֵ
		System.out.println("���˳ɹ���\n������Ϊ������");
		list.clear();
	}
	
	//�˳�
	public boolean leave() {
		if(sumPrice()>0) {
			return false;
		}
		return true;
	}
}
