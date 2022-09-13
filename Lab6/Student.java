// Brendan Han
// CSE274

public class Student implements Comparable<Student> {
	
	//====================================================================================== Properties
	private String fName, lName;
	private int age, test1, test2 , test3, test4;
	
	
	//====================================================================================== Constructor
	public Student(String ln) {
		String[] parts = ln.split("\t");
		setfName(parts[0]);
		setlName(parts[1]);
		setAge(Integer.parseInt(parts[2]));
		setTest1(Integer.parseInt(parts[3]));
		setTest2(Integer.parseInt(parts[4]));
		setTest3(Integer.parseInt(parts[5]));
		setTest4(Integer.parseInt(parts[6]));
	}
	
	//======================================================================================  Methods
	public double average() {
		return (test1 + test2 + test3 + test4) / 4.0;
	}
	
	public String fullName() {
		return lName + "," + fName;
	}
	
	public String toString() {
		return String.format("%-25s %8.1f", fullName(), average());
	}

	public int compareTo(Student o) {
		return fullName().compareTo(o.fullName());
	}
	
	//======================================================================================  Getters
	public String getfName() {
		return fName;
	}
	
	public String getlName() {
		return lName;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getTest1() {
		return test1;
	}
	
	public int getTest2() {
		return test2;
	}
	public int getTest3() {
		return test3;
	}
	
	public int getTest4() {
		return test4;
	}
	
	//======================================================================================  Setters
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setTest1(int test1) {
		this.test1 = test1;
	}
	
	public void setTest2(int test2) {
		this.test2 = test2;
	}
	
	public void setTest3(int test3) {
		this.test3 = test3;
	}
	
	public void setTest4(int test4) {
		this.test4 = test4;
	}
}
