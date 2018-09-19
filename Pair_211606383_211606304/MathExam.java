import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MathExam {

	public static char RnadomOpes() {
		int Temp = Random(1, 4);
		switch (Temp) {
		case 1: {
			return '+';
		}
		case 2: {
			return '-';
		}
		case 3: {
			return '��';
		}
		case 4: {
			return '��';
		}
		}
		return ' ';

	}

	public static int Random(int Min, int Max) {
		return (int) (Min + Math.random() * (Max - Min + 1));
	}

	public static int Calc(String Question) {
		Stack<Integer> nums = new Stack<Integer>(); // ջ ��������
		Stack<Character> opes = new Stack<Character>(); // ջ ���������
		String string = Question.replace(" ", "");// ��ȡ��ʱ�ַ���
		char symbol;
		int n = 0; // ����ÿһ������
		char[] cs = string.toCharArray();// �ַ���ת��Ϊchar����
		for (int i = 0; i < cs.length; i++) {// ����char����
			char temp = cs[i];
			if (Character.isDigit(cs[i])) {// �жϵ�ǰchar�Ƿ�Ϊ����
				n = 10 * n + Integer.parseInt(String.valueOf(temp)); // ��ʱ�������10������
			} else {// ������
				if (n != 0) {
					nums.push(n);// ������ջ
					n = 0;
				}
				if (temp == '(') {// �������������
					opes.push(temp);// ��ջ
				} else if (temp == ')') {// ���������������
					while (opes.peek() != '(') { // ��������������(�鿴��ջ�����Ķ��󣬵����Ӷ�ջ���Ƴ���)
						symbol = opes.pop();
						int t = cal(nums.pop(), nums.pop(), symbol);// ������������
						if (t < 0 && symbol == '-') {
							return -1;
						}
						if (t == 0 && symbol == '��') {
							return -1;
						}
						if (t < 0 && symbol == '��') {
							return -1;
						}
						nums.push(t);// ��������ջ
					}
					opes.pop();
				} else if (isType(temp) > 0) {
					if (opes.isEmpty()) { // ջΪ��ֱ����ջ
						opes.push(temp);
					} else {
						// ��ջ��Ԫ�����ȼ����ڻ����Ҫ��ջ��Ԫ��,��ջ��Ԫ�ص���������,Ȼ����ջ
						if (isType(opes.peek()) >= isType(temp)) {
							symbol = opes.pop();
							int t = cal(nums.pop(), nums.pop(), symbol);// ������������
							if (t < 0 && symbol == '-') {
								return -1;
							}
							if (t == 0 && symbol == '��') {
								return -1;
							}
							if (t < 0 && symbol == '��') {
								return -1;
							}
							nums.push(t);
						}
						opes.push(temp);
					}
				}
			}
		}
		// ���һ���ַ���������,δ��ջ
		if (n != 0) {
			nums.push(n);
		}
		while (!opes.isEmpty()) {
			symbol = opes.pop();
			int t = cal(nums.pop(), nums.pop(), symbol);// ������������
			if (t < 0 && symbol == '-') {
				return -1;
			}
			if (t == 0 && symbol == '��') {
				return -1;
			}
			if (t < 0 && symbol == '��') {
				return -1;
			}
			nums.push(t);
		}
		return nums.pop();
	}

	// ���ص�������������ȼ�,���ֺ�()����Ҫ����
	public static int isType(char c) {
		if (c == '+' || c == '-') {
			return 1;
		} else if (c == '��' || c == '��') {
			return 2;
		} else {
			return 0;
		}
	}

	// ��������Ƿ���,����ջ��ջ�����й�
	public static int cal(int m, int n, char c) {
		int sum = -1;
		if (c == '+') {
			sum = n + m;
		} else if (c == '-') {
			sum = n - m;
		} else if (c == '��') {
			sum = n * m;
		} else if (c == '��') {
			if (n % m != 0) {
				return -1;
			}
			sum = n / m;
		}
		return sum;
	}

	// �ж��Ƿ�Ϊ����������
	public static boolean isdigit(char chr) {
		if (chr < 48 || chr > 57) {
			return false;
		} else {
			return true;
		}
	}

	// �ж��Ƿ�Ϊ������
	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	// ȡһ�������
	public static int GetRandomNum() {
		// (��������)(��Сֵ+Math.random()*(���ֵ-��Сֵ+1))
		return (int) (0 + Math.random() * (100 - 0 + 1));
	}

	// ������Ŀ(����꼶����)
	public static void GetQuestion(int num, int Grade) {

		switch (Grade) {

		case 1: {
			String TempQuestion = "";
			String TempAnswer = "";
			for (int i = 0; i < num; i++) {
				int Temp = GetRandomNum();
				int Temp2;
				if (GetRandomNum() > 49) {
					Temp2 = (int) (0 + Math.random() * ((100 - Temp) - 0 + 1));
					TempQuestion = TempQuestion + "(" + String.valueOf(i + 1) + ") " + String.valueOf(Temp) + " + "
							+ String.valueOf(Temp2) + " =" + "\r\n";
					TempAnswer = TempAnswer + "(" + String.valueOf(i + 1) + ") " + String.valueOf(Temp) + " + "
							+ String.valueOf(Temp2) + " = " + String.valueOf(Temp + Temp2) + "\r\n";
				} else {
					Temp2 = (int) (0 + Math.random() * (Temp - 0 + 1));
					TempQuestion = TempQuestion + "(" + String.valueOf(i + 1) + ") " + String.valueOf(Temp) + " - "
							+ String.valueOf(Temp2) + " =" + "\r\n";
					TempAnswer = TempAnswer + "(" + String.valueOf(i + 1) + ") " + String.valueOf(Temp) + " - "
							+ String.valueOf(Temp2) + " = " + String.valueOf(Temp - Temp2) + "\r\n";
				}
			}
			System.out.println(TempQuestion + "\r\n" + TempAnswer);
			OutAnswer(TempQuestion + "\r\n" + TempAnswer);
			break;
		}

		case 2: {
			String Question = "";
			String Answer = "";
			for (int j = 0; j < num; j++) {
				int Ismul = GetRandomNum();
				int mul1 = (int) (0 + Math.random() * (10 - 0 + 1));
				if (Ismul > 49) {
					int mul2 = (int) (0 + Math.random() * (10 - 0 + 1));
					Question = Question + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " �� "
							+ String.valueOf(mul2) + "\r\n";
					Answer = Answer + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " �� "
							+ String.valueOf(mul2) + " = " + String.valueOf(mul1 * mul2) + "\r\n";
				} else {
					int roo;
					// ���ݷ�Χ��Ҫע��
					int mul2 = (int) (1 + Math.random() * (mul1 - 1 + 1));
					roo = mul1 % mul2;
					if (roo == 0) {
						Question = Question + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " �� "
								+ String.valueOf(mul2) + "\r\n";
						Answer = Answer + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " �� "
								+ String.valueOf(mul2) + " = " + String.valueOf(mul1 / mul2) + "\r\n";
					} else {
						Question = Question + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " �� "
								+ String.valueOf(mul2) + "\r\n";
						Answer = Answer + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " �� "
								+ String.valueOf(mul2) + " = " + String.valueOf((int) mul1 / mul2) + "..."
								+ String.valueOf(roo) + "\r\n";
					}

				}
			}
			System.out.println(Question + "\r\n" + Answer);
			OutAnswer(Question + "\r\n" + Answer);
			break;

		}

		case 3: {
			String Questions = "";
			String Answers = "";
			String TempQuestion = "";
			String TempAnswer = "";
			for (int k = 0; k < num; k++) {
				int Answer = -1;
				while (Answer < 0) {
					int Num = Random(2, 4);
					if (Num == 2) {
						int TempNum1 = Random(0, 100);
						int TempNum2 = Random(0, 100);
						int TempNum3 = Random(0, 100);
						char Tempopes1 = ' ';
						char Tempopes2 = ' ';
						while (Tempopes1 == Tempopes2) {
							Tempopes1 = RnadomOpes();
							Tempopes2 = RnadomOpes();
						}
						boolean isBracket = false;
						if (Random(1, 2) == 2) {
							isBracket = true;
						}
						if (isBracket == false) {
							TempQuestion = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(TempNum3);
							TempAnswer = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(TempNum3) + " = " + String.valueOf(Calc(TempQuestion));
						} else {
							if (isType(Tempopes1) != isType(Tempopes2)) {
								if (isType(Tempopes1) < isType(Tempopes2)) {
									TempQuestion = "( " + String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1)
											+ " " + String.valueOf(TempNum2) + " ) " + String.valueOf(Tempopes2) + " "
											+ String.valueOf(TempNum3);
									TempAnswer = "( " + String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
											+ String.valueOf(TempNum2) + " ) " + String.valueOf(Tempopes2) + " "
											+ String.valueOf(TempNum3) + " = " + String.valueOf(Calc(TempQuestion));
								} else {
									TempQuestion = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " ( "
											+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
											+ String.valueOf(TempNum3) + " )";
									TempAnswer = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " ( "
											+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
											+ String.valueOf(TempNum3) + " )" + " = "
											+ String.valueOf(Calc(TempQuestion));
								}
							} else {
								TempQuestion = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
										+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
										+ String.valueOf(TempNum3);
								TempAnswer = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
										+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
										+ String.valueOf(TempNum3) + " = " + String.valueOf(Calc(TempQuestion));
							}
						}
						Answer = Calc(TempQuestion);
						TempQuestion = "(" + String.valueOf(k + 1) + ") " + TempQuestion + "\r\n";
						TempAnswer = "(" + String.valueOf(k + 1) + ") " + TempAnswer + "\r\n";
					} else {
						if (Num == 3) {
							char Tempopes = ' ';
							char Tempopes1 = ' ';
							char Tempopes2 = ' ';
							char Tempopes3 = ' ';
							while (Tempopes1 == Tempopes2 && Tempopes1 == Tempopes3) {
								Tempopes1 = RnadomOpes();
								Tempopes2 = RnadomOpes();
								Tempopes3 = RnadomOpes();
							}
							boolean isBracket = false;
							if (Random(1, 2) == 2) {
								isBracket = true;
							}
							TempQuestion = String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes3) + " "
									+ String.valueOf(Random(0, 100));
							TempAnswer = String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes3) + " "
									+ String.valueOf(Random(0, 100)) + " = " + String.valueOf(Calc(TempQuestion));
							Answer = Calc(TempQuestion);
							TempQuestion = "(" + String.valueOf(k + 1) + ") " + TempQuestion + "\r\n";
							TempAnswer = "(" + String.valueOf(k + 1) + ") " + TempAnswer + "\r\n";

						} else {
							char Tempopes = ' ';
							char Tempopes1 = ' ';
							char Tempopes2 = ' ';
							char Tempopes3 = ' ';
							char Tempopes4 = ' ';
							while (Tempopes1 == Tempopes2 && Tempopes1 == Tempopes3 && Tempopes1 == Tempopes4) {
								Tempopes1 = RnadomOpes();
								Tempopes2 = RnadomOpes();
								Tempopes3 = RnadomOpes();
								Tempopes4 = RnadomOpes();
							}
							// Question = "(" + String.valueOf(k+1) + ") "
							TempQuestion = String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes3) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes4) + " "
									+ String.valueOf(Random(0, 100));
							TempAnswer = String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes3) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes4) + " "
									+ String.valueOf(Random(0, 100)) + " = " + String.valueOf(Calc(TempQuestion));
							Answer = Calc(TempQuestion);
							TempQuestion = "(" + String.valueOf(k + 1) + ") " + TempQuestion + "\r\n";
							TempAnswer = "(" + String.valueOf(k + 1) + ") " + TempAnswer + "\r\n";

						}

					}
				}
				Questions = Questions + "\r\n" + TempQuestion;
				Answers = Answers + "\r\n" + TempAnswer;
			}
			System.out.println(Questions + "\r\n" + Answers);
			OutAnswer(Questions + "\r\n" + Answers);
			break;
		}

		}

	}

	// ����ļ�
	// ���±�����Ҫ/r/n�ſ��Ի���
	public static void OutAnswer(String str) {
		try {
			PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
			writer.println(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ������
	public static void main(String[] args) {
		String Path;
		String Grade;
		int n = 0;
		// System.out.println("GetPath:"+args.length);
		// �ж������в�������
		if (args.length != 4) {
			System.out.println("Error:001");
			return;
		}
		if(args[0].equals("-n") == false && args[0].equals("-Grade") == false) {
			System.out.println("Error:002");
			return;
		}
		if(args[2].equals("-n") == false && args[2].equals("-Grade") == false) {
			System.out.println("Error:003");
			return;
		}
		if(args[0].equals("-n")) {
			Grade = args[3];
			Path = args[1];
		}else
		{
			Grade = args[1];
			Path = args[3];
		}
		// �ж���Ŀ�����Ƿ�Ϊ������
		if (!isNum(Path)) {
			System.out.println("Error:004");
			return;
		}
		// �ж��꼶�Ƿ���ȷ
		if (Integer.valueOf(Grade) != 1 && Integer.valueOf(Grade) != 2 && Integer.valueOf(Grade) != 3) {
			System.out.println("Error:005");
			return;
		}
		n = Integer.valueOf(Path);
		// �ж���������Ƿ�Ϊ0
		if (n == 0) {
			System.out.println("Error:006");
			return;
		}
		GetQuestion(n, Integer.valueOf(Grade));
	}

}
