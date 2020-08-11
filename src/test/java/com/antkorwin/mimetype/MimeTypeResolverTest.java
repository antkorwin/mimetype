package com.antkorwin.mimetype;

import java.io.File;

import com.antkorwin.ioutils.resourcefile.ResourceFile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Korovin Anatoliy
 */
class MimeTypeResolverTest {

	@Test
	void text() {
		// Arrange
		File textFile = new ResourceFile("test.txt").getFile();
		// Act
		String type = new MimeTypeResolver().get(textFile);
		// Assert
		assertThat(type).contains("text/plain");
	}

	@Test
	void pdf() {
		// Arrange
		File textFile = new ResourceFile("document.pdf").getFile();
		// Act
		String type = new MimeTypeResolver().get(textFile);
		// Assert
		assertThat(type).isEqualTo("application/pdf");
	}

	@Test
	void pdfFileWithoutExtension() {
		// Arrange
		File textFile = new ResourceFile("contentpdf").getFile();
		// Act
		String type = new MimeTypeResolver().get(textFile);
		// Assert
		assertThat(type).isEqualTo("application/pdf");
	}

	@Test
	void png() {
		// Arrange
		File textFile = new ResourceFile("image.png").getFile();
		// Act
		String type = new MimeTypeResolver().get(textFile);
		// Assert
		assertThat(type).isEqualTo("image/png");
	}

	@Test
	void pngWithoutExtension() {
		// Arrange
		File textFile = new ResourceFile("image").getFile();
		// Act
		String type = new MimeTypeResolver().get(textFile);
		// Assert
		assertThat(type).isEqualTo("image/png");
	}

	@Test
	void word() {
		// Arrange
		File textFile = new ResourceFile("word.docx").getFile();
		// Act
		String type = new MimeTypeResolver().get(textFile);
		// Assert
		assertThat(type).isEqualTo("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	}


}