package com.antkorwin.mimetype;

import java.io.File;

import com.antkorwin.ioutils.resourcefile.ResourceFile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Korovin Anatoliy
 */
class FileSizeResolverTest {

	@Test
	void resolveFileSize() {
		File file = new ResourceFile("document.pdf").getFile();
		String size = new FileSizeResolver().get(file);
		assertThat(size).isEqualTo("16666");
	}
}