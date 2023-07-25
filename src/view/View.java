package view;

import org.junit.jupiter.api.Test;

import service.DiningTableService;
import service.EmployeeService;
import service.MenuService;
import utils.Utility;

public class View {

	private static boolean loop = true;
	private static EmployeeService employeeService = new EmployeeService();
	private static DiningTableService diningTableService = new DiningTableService();
	private static MenuService menuService = new MenuService();

	public static void main(String[] args) {
		Menue();
	}

	// 显示界面
	public static void Menue() {
		while (loop) {
			System.out.println("==============Resturant=====================");
			System.out.println("\t\t 1.登录餐厅系统");
			System.out.println("\t\t 2.退出餐厅系统");
			System.out.println("请输入你的选择:");
			int read = Utility.readInt();
			if (read == 1) {
				// 输入用户名何密码
				System.out.println("请输入员工号:");
				String empId = Utility.readString(32);
				System.out.println("请输入密 码:");
				String password = Utility.readString(32);
				// 去数据库核对，检查用户是否合法

				boolean login = employeeService.login(empId, password);

				if (login == true) {
					System.out.println("================登陆成功==================");
					while (loop) {
						System.out.println("\n================Resturant(二级菜单)==========================");
						System.out.println("\t\t  1.显示餐桌状态");
						System.out.println("\t\t  2.预定餐桌");
						System.out.println("\t\t  3.显示所有菜单");
						System.out.println("\t\t  4.点餐服务");
						System.out.println("\t\t  5.查看菜单");
						System.out.println("\t\t  6.结账");
						System.out.println("\t\t  9.退出餐厅");
						System.out.println("请输入你的选择:");
						int read2 = Utility.readInt();
						switch (read2) {
						case 1:
							// System.out.println("1.显示餐桌状态");
							diningTableService.showDiningTable();
							System.out.println("================显示完成=====================");
							break;
						case 2:
							// System.out.println("2.预定餐桌");
							// 先输入要预定的餐桌号，去查这个餐桌空闲与否，返回是空闲，输入 预定人姓名 电话
							diningTableService.bookTable();
							System.out.println("==================预定完成======================");
							break;
						case 3:
							// System.out.println("3.显示所有菜单");
							menuService.showMenu();
							System.out.println("==================显示完成======================");
							break;
						case 4:
							// System.out.println("4.点餐服务");
							menuService.orderMenu();
							System.out.println("==================点餐完成======================");
							break;
						case 5:
							// System.out.println("5.查看菜单");
							menuService.showOrderMenu();
							break;
						case 6:
							// System.out.println("6.结账");
							menuService.checkout();
							break;
						case 9:
							if (!menuService.leave()) {
								System.out.println("您还有未完的款项，请先结账！");
								break;
							}
							System.out.println("退出餐厅");
							loop = false;
							break;
						default:
							System.out.println("输入异常，请尝试查询输入:");
						}
					}

				}
				// 显示2级餐单

			} else {
				System.out.println("您已经退出系统");
				loop = false;
			}
		}
	}
}
