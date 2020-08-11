package com.antkorwin.mimetype;

import java.io.File;

import com.antkorwin.ioutils.resourcefile.ResourceFile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Korovin Anatoliy
 */
class Sha256ResolverTest {

	@Test
	void getSha256() {
		File file = new ResourceFile("document.pdf").getFile();
		String sha256 = new Sha256Resolver().get(file);
		assertThat(sha256).isEqualTo("bbf9546ec49f2bfe384a91cb5a33831c67a69fb8a7197346f261df0aa8f7a048");
	}
}