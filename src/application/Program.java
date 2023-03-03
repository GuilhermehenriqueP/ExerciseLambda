package application;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {
	
	public static void main(String[] args) throws IOException {
	
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		System.out.print("Enter salary: ");
		double salary = sc.nextDouble();
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			
			List<Employee> list = new ArrayList<>();
			
			String line = br.readLine();
			
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1],Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s1.toUpperCase());
			
			List<String> emails = list.stream()
					.filter(p -> p.getSalary() > salary)
					.map(p -> p.getEmail()).sorted()
					.collect(Collectors.toList());
			
			double sum = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (x,y) -> x+y);
			
			System.out.println("Email of people whose salary is more than" + String.format("%.2f", salary) + ":");
			emails.forEach(System.out::println);
			System.out.println("Sum of salary of people whose name starts with 'M' : " + sum);
		
		
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}finally {
			   br.close();
		}
		
		
		
		sc.close();
		
		
	}
}
