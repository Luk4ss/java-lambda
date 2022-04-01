package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String path = "./in.txt";

        List<Employee> employeeList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();

            while(line != null){
                String[] fields = line.split(",");
                employeeList.add( new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }


            System.out.print("Enter the salary: ");

            double salary = sc.nextDouble();

            List<String> emails = employeeList.stream()
                                                .filter(employee -> employee.getSalary() > salary)
                                                .map(Employee::getEmail)
                                                .sorted((s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()))
                                                .toList();


            System.out.println("Email of the people whose salary is more than $ " + salary);
            emails.forEach(System.out::println);


            double totalSalary = employeeList.stream()
                    .filter(employee -> employee.getName().charAt(0) == 'M')
                    .map(emp -> emp.getSalary())
                    .reduce(0.0, (salary1, salary2) -> salary1 + salary2);


            System.out.println("Sum of Salary of people whose name starts with 'M': $ " + totalSalary );


        }catch (IOException e){
            System.out.print("Error : " + e.getMessage());
        }




    }
}
