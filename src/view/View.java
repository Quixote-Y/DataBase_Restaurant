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

	// ��ʾ����
	public static void Menue() {
		while (loop) {
			System.out.println("==============Resturant=====================");
			System.out.println("\t\t 1.��¼����ϵͳ");
			System.out.println("\t\t 2.�˳�����ϵͳ");
			System.out.println("���������ѡ��:");
			int read = Utility.readInt();
			if (read == 1) {
				// �����û���������
				System.out.println("������Ա����:");
				String empId = Utility.readString(32);
				System.out.println("�������� ��:");
				String password = Utility.readString(32);
				// ȥ���ݿ�˶ԣ�����û��Ƿ�Ϸ�

				boolean login = employeeService.login(empId, password);

				if (login == true) {
					System.out.println("================��½�ɹ�==================");
					while (loop) {
						System.out.println("\n================Resturant(�����˵�)==========================");
						System.out.println("\t\t  1.��ʾ����״̬");
						System.out.println("\t\t  2.Ԥ������");
						System.out.println("\t\t  3.��ʾ���в˵�");
						System.out.println("\t\t  4.��ͷ���");
						System.out.println("\t\t  5.�鿴�˵�");
						System.out.println("\t\t  6.����");
						System.out.println("\t\t  9.�˳�����");
						System.out.println("���������ѡ��:");
						int read2 = Utility.readInt();
						switch (read2) {
						case 1:
							// System.out.println("1.��ʾ����״̬");
							diningTableService.showDiningTable();
							System.out.println("================��ʾ���=====================");
							break;
						case 2:
							// System.out.println("2.Ԥ������");
							// ������ҪԤ���Ĳ����ţ�ȥ���������������񣬷����ǿ��У����� Ԥ�������� �绰
							diningTableService.bookTable();
							System.out.println("==================Ԥ�����======================");
							break;
						case 3:
							// System.out.println("3.��ʾ���в˵�");
							menuService.showMenu();
							System.out.println("==================��ʾ���======================");
							break;
						case 4:
							// System.out.println("4.��ͷ���");
							menuService.orderMenu();
							System.out.println("==================������======================");
							break;
						case 5:
							// System.out.println("5.�鿴�˵�");
							menuService.showOrderMenu();
							break;
						case 6:
							// System.out.println("6.����");
							menuService.checkout();
							break;
						case 9:
							if (!menuService.leave()) {
								System.out.println("������δ��Ŀ�����Ƚ��ˣ�");
								break;
							}
							System.out.println("�˳�����");
							loop = false;
							break;
						default:
							System.out.println("�����쳣���볢�Բ�ѯ����:");
						}
					}

				}
				// ��ʾ2���͵�

			} else {
				System.out.println("���Ѿ��˳�ϵͳ");
				loop = false;
			}
		}
	}
}
