package opensource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.cellprocessor.Optional;

public class SuperCsvMain {

	public static void main(String[] args) {
		// 제약조건을 설정
		CellProcessor[] processors = new CellProcessor[] { new NotNull(), // 이름은 필수
				new ParseInt(new NotNull()), // 정수로 변환
				new ParseDate("yyyy-MM-dd"), // Date 로 변환,
				new Optional(), new Optional() };

		// 데이터를 저장할 List 생성
		List<Player> list = new ArrayList<>();
		// 파일 경로를 생성
		Path path = Paths.get("volleyball.csv");
		
		// 읽기
		try (ICsvBeanReader beanReader = new CsvBeanReader(Files.newBufferedReader(path),
				CsvPreference.STANDARD_PREFERENCE)) {
			// 헤더 가져오기 - 첫번째 행의 데이터
			String[] header = beanReader.getHeader(true);
			System.out.println(Arrays.toString(header));
			String[] header1 = {"name", "age", "birth", "email","nickname"};
			// 두번째 행 부터 읽어오기
			Player player = null;
			// 줄 단위로 읽어서 player 로 만든 후
			// list에 추가
			while ((player = beanReader.read(Player.class, header1, processors)) != null) {
				list.add(player);
			}

			for (Player temp : list) {
				System.out.printf("%s\n", temp);
			}

		} catch (Exception e) {
			System.out.printf("파일 읽기 예외:%s\n", e.getMessage());
			e.printStackTrace();
		}
	}
}
