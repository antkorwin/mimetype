package com.antkorwin.mimetype;

import java.io.File;

/**
 * Created on 30/06/2020
 * <p>
 * Resolve the file size.
 *
 * @author Korovin Anatoliy
 */
public class FileSizeResolver implements MetaDataResolver {

	@Override
	public String get(File file) {
		return String.valueOf(file.length());
	}
}
