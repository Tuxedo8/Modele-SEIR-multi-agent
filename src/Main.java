import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		MTRandom random = new MTRandom(41);

		for (int i = 0; i < 100; i++) {

			Monde monde = new Monde(random);
			File csvFile = new File("resultat-" + (i + 1) + ".csv");
			FileWriter fileWriter = new FileWriter(csvFile);

			StringBuilder line = new StringBuilder();
			line.append("S,I,E,R\n");
			fileWriter.write(line.toString());

			for (int j = 0; j < 730; j++) {
				line = new StringBuilder();
				monde.nextday(random);
				line.append(monde.getNbrS()).append(",");
				line.append(monde.getNbrI()).append(",");
				line.append(monde.getNbrE()).append(",");
				line.append(monde.getNbrR()).append("\n");
				fileWriter.write(line.toString());

			}

			fileWriter.close();

			// System.out.println(random.negExp(365));
		}

	}
}
