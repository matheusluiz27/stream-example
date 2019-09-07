package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//file path
		String path = "/home/user/in.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			System.out.print("Enter with a value: ");
			Double value = sc.nextDouble();
			
			List<Employee> employees = new ArrayList<>();
			
			String line = br.readLine();		
			while (line != null) {
				String[] fields = line.split(",");
				employees.add(new Employee(fields[0],fields[1], Double.parseDouble(fields[2])));
				
				line = br.readLine();
			}
			
			// Emails' list oderned alphabetics of employees with salary biggest than this value 
			List<String> emails = employees.stream()
				.filter(e -> e.getSalary() > value)
				.map(e -> e.getEmail())
				.sorted((email1, email2) -> email1.toLowerCase().compareTo(email2.toLowerCase())).collect(Collectors.toList());
			
			System.out.println(emails);
			
			// sum of all employees' salary. the your names' first letter shoud be 'M'
			Double sumSalary = employees.stream()
				.filter(e -> e.getName().charAt(0) == 'M')
				.map(e -> e.getSalary())
				.reduce(0.0, (x, y) -> x + y);
			
			System.out.println(sumSalary);
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		sc.close();
	}

}
