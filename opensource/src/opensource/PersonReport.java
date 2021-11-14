package opensource;

import java.util.List;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PersonList")
public class PersonReport {
	@XmlElement(name = "Person")
	private List<Person> list;
	
	public PersonReport() {
	}
	public PersonReport(List<Person> list) {
		this.list = list;
	}
	
	public List<Person> getList() {
		return list;
	}
}