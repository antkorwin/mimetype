package com.antkorwin.mimetype;

import java.io.File;

/**
 * Created on 30/06/2020
 * <p>
 * Resolve any meta-information from the file.
 *
 * @author Korovin Anatoliy
 */
public interface MetaDataResolver {

	/**
	 * Resolve meta-information from file
	 *
	 * @param file to get a meta information
	 * @return meta information as String value
	 */
	String get(File file);
}
