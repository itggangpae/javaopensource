package opensource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class PersonMarshalling {

    public void marshalTest() {
        try {
            Person person1 = new Person("cyberadam", "ggangpae1@gmail.com");
            Person person2 = new Person("군계", "itstudy@kakao.com");
            List<Person> list = new ArrayList<>();
            list.add(person1);
            list.add(person2);
            
            PersonReport report = new PersonReport(list);
            
            //JAXBContext 생성 & marshaller 생성
            JAXBContext context = JAXBContext.newInstance(PersonReport.class);
            Marshaller marshaller = context.createMarshaller();
            //보기 좋게 출력해주는 옵션
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //표준 출력으로 결과를 보여준다.
            
            marshaller.marshal(report, System.out);
        } catch (Exception e) {
           System.out.println(e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        new PersonMarshalling().marshalTest();
    }
}
