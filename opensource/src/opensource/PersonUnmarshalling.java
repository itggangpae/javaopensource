package opensource;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class PersonUnmarshalling {
	public static void main(String[] args) {
		try {
			// XML 파일을 읽어온다.
			File file = new File("person.xml");

			// JAXBContext 생성 & unmarshaller 생성
			JAXBContext context = JAXBContext.newInstance(PersonReport.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// XML을 Person 오브젝트로 변환한다.
			PersonReport personReport = (PersonReport)unmarshaller.unmarshal(file);
			List<Person> list = personReport.getList();
			for(Person person : list) {
				System.out.println(person);
			}
		} catch (Exception e) {
			// ... handle exception
		}
	}
}
