package com.antkorwin.mimetype;

import java.io.File;

import com.antkorwin.ioutils.resourcefile.ResourceFile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Korovin Anatoliy
 */
class FileTextResolverTest {

	@Test
	void extractTextFromTxt() {
		// Arrange
		File file = new ResourceFile("test.txt").getFile();
		// Act
		String text = new FileTextResolver().get(file);
		// Assert
		assertThat(text).isEqualTo("q1w2e3r4t5\n1234566789\n");
	}

	@Test
	void extractTextFromPdf() {
		// Arrange
		File file = new ResourceFile("document.pdf").getFile();
		// Act
		String text = new FileTextResolver().get(file);
		// Assert
		assertThat(text).contains("Test\tstring\twith\ta\tlot\tof\tspace")
		                .contains("\t\t\t\t\t\tStart\tfrom\tspace\tstring")
		                .contains("Thats\tall…");
	}

	@Test
	void extractTextFromWord() {
		// Arrange
		File file = new ResourceFile("word.docx").getFile();
		// Act
		String text = new FileTextResolver().get(file);
		// Assert
		assertThat(text).containsSubsequence("Тестовый документ",
		                                     "Много", "пробелов", "между", "словами", "вот",
		                                     "Token1", "Token2", "Token3",
		                                     "This is the end of documents.");
	}

	@Test
	void extractFromByteArray() {
		// Arrange
		byte[] bytes = new ResourceFile("word.docx").readAsByteArray();
		// Act
		String text = new FileTextResolver().get(bytes);
		// Assert
		assertThat(text).containsSubsequence("Тестовый документ",
		                                     "Много", "пробелов", "между", "словами", "вот",
		                                     "Token1", "Token2", "Token3",
		                                     "This is the end of documents.");
	}

	@Test
	void extractFromInputStream() {
		// Act
		String text = new FileTextResolver().get(() -> new ResourceFile("word.docx").getInputStream());
		// Assert
		assertThat(text).containsSubsequence("Тестовый документ",
		                                     "Много", "пробелов", "между", "словами", "вот",
		                                     "Token1", "Token2", "Token3",
		                                     "This is the end of documents.");
	}
}