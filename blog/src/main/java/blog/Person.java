package blog;

public class Person {
	
	   public enum Sex {
	        MALE, FEMALE
	    }	

	int age;
	Sex gender;
	

	public Sex getGender() {
		return gender;
	}

	public void setGender(Sex gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void printPerson() {
		
	}
}

