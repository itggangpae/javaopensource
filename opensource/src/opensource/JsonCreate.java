package opensource;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonCreate {

	static class Employee {
		private String name;
		private int age;
		private List<String> licenses;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public List<String> getLicenses() {
			return licenses;
		}
		public void setLicenses(List<String> licenses) {
			this.licenses = licenses;
		}
		
		@Override
		public String toString() {
			return "Employee [name=" + name + ", age=" + age + ", licenses=" + licenses + "]";
		}
	}
	
	public static void main(String[] args) {
		Employee employee1 = new Employee();
		employee1.setName("아담");
		employee1.setAge(50);
		employee1.setLicenses(Arrays.asList("OCP","정보처리 기사"));
		
		Employee employee2 = new Employee();
		employee2.setName("류시아");
		employee2.setAge(48);
		employee2.setLicenses(Arrays.asList("CCNA","사무자동화 산업기사"));
		
		File file = new File("employee.json"); // 쓰기 대상의 JSON 데이터 파일
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(file, Arrays.asList(employee1, employee2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
