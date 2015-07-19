package blog;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Test {
	
	public void indexOf1(int[] arr1, int[] arr2){
		
		System.out.println("Calculating");
		//int[] list1 = {1, 3, 6};
		//int[] list2 = {1, 3, 5, 8, 12, 1, 3, 17, 1, 3, 6, 9, 1, 3, 6};

	    for(int i = 0; i < arr2.length; i++){
	        for(int j = 0; j < arr1.length; j++){
	            if((i + j) < arr2.length && arr1[j] != arr2[i + j]){
	                break; 
	            }
	            if(j == arr1.length -1){
	                System.out.println(i);
	                break;
	            }
	        }
	    }
	}
	
	public int factorialize(int num) {
		
		if(num == 0) {
			return 1;
		}
		  num = num * factorialize(num - 1);
		  
		  return num;
		}
	
		
	public void printPersonsWithPredicate(
		    List<Person> roster, Predicate<Person> tester) {
		    for (Person p : roster) {
		        if (tester.test(p)) {
		            p.printPerson();
		        }
		    }
		}
	public static void main(String[] args) {
		System.out.println("Calculating");
		int[] list1 = {1, 3, 6};
		int[] list2 = {1, 3, 5, 8, 12, 1, 3, 17, 1, 3, 6, 9, 1, 3, 6};

		Test t = new Test();
		//t.indexOf1(list1, list2);
		System.out.println(t.factorialize(5));
		 
		List<Person> roster = new ArrayList<Person>();
		
		//test.strea
		
		t.printPersonsWithPredicate( roster,
			    p -> p.getGender() == Person.Sex.MALE
			        && p.getAge() >= 18
			        && p.getAge() <= 25
			);
		
	}
	

}
