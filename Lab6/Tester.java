// Brendan Han
// CSE274

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Tester {

	private static DoublyLinkedCollectionIteration<Student> students = new DoublyLinkedCollectionIteration<>();
	private static ArrayList<Student> alStudents = new ArrayList<>();
	
	public static void main(String[] args) {
		loadData();
		Collections.sort(alStudents);
		System.out.println("\n====================================== Class Average");
		System.out.println(getTotalAverage());
		System.out.println("\n====================================== Top10()");
		showTop10();
		System.out.println("\n====================================== BottomTop10()");
		showBottom10();
	}

	public static void loadData() {
		try (Scanner fin = new Scanner(new File("StudentData.txt"))) {
			fin.nextLine();
			while (fin.hasNextLine()) {
				Student s = new Student(fin.nextLine());
				students.add(s); // dll collection
				alStudents.add(s); // arraylist
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static double getTotalAverage() {
		double sum = 0;
		
		for (Student s : alStudents)
			sum += s.average();
		
		return sum / alStudents.size();
	}
	
	private static void showTop10() {
		Comparator<Student> sortHighest = new Comparator<Student>() {
			
			@Override
			public int compare(Student o1, Student o2) {
				return (int)(o2.average() * 100) - (int)(o1.average() * 100);
			}
		};
		Collections.sort(alStudents, sortHighest);
		
		for (int i = 0; i < 10; i++) {
			System.out.println(alStudents.get(i));
		}
	}
	
	private static void showBottom10() {
		Comparator<Student> sortLowest = new Comparator<Student>() {	
			@Override
			public int compare(Student o1, Student o2) {
				return (int)(o1.average() * 100) - (int)(o2.average() * 100);
			}
		};
		Collections.sort(alStudents, sortLowest);

		for (int i = 0; i < 10; i++) {
			System.out.println(alStudents.get(i));
			
		}
	}
}
