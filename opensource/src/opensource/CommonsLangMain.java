package opensource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;

class DTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int num;
	private String name;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	//hashCode 메소드와 equals 메소드 재정의
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTO other = (DTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	 */

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object obj) {
		EqualsBuilder builder = new EqualsBuilder();
		DTO otherDTO  = (DTO)obj;
		
		builder.append(getNum(), otherDTO.getNum());
		builder.append(getName(), otherDTO.getName());
		return builder.isEquals();
	}

	@Override
	public String toString() {
		return "DTO [num=" + num + ", name=" + name + "]";
	}

}

public class CommonsLangMain {

	public static void main(String[] args) {
		String text = null;

		//빈 문자열 확인
		if (text == null || text.length() == 0) {
			System.out.println("text는 비었다");
		}
		//isEmpty 메서드의 부정형인 isNotEmpty 메서드
		//공백만의 문자열도 빈 것으로 판정하는 isBlank 메서드
		if (StringUtils.isEmpty(text)) {
			System.out.println("text는 비었다");
		}
		
		DTO dto1 = new DTO();
		dto1.setNum(1);
		dto1.setName("아담");
		
		DTO dto2 = new DTO();
		dto2.setNum(2);
		dto2.setName("류시아");
		
		System.out.println(dto1.hashCode());
		System.out.println(dto1.equals(dto2));
		
		//깊은 복사
		DTO dto3 = SerializationUtils.clone(dto1);
		System.out.println(dto3);
	}
}
