package com.antkorwin.mimetype;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.antkorwin.throwable.functions.WrappedException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

/**
 * Created on 30/06/2020
 * <p>
 * Resolve the MimeType by the content of a file.
 *
 * @author Korovin Anatoliy
 */
public class MimeTypeResolver implements MetaDataResolver {

	private final Parser parser;
	private final ContentHandler contenthandler;

	public MimeTypeResolver() {
		contenthandler = new BodyContentHandler(Integer.MAX_VALUE);
		parser = new AutoDetectParser();
	}

	@Override
	public String get(File file) {
		Metadata metadata = new Metadata();
		metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());
		try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
			parser.parse(inputStream,
			             contenthandler,
			             metadata,
			             new ParseContext());
			return metadata.get(Metadata.CONTENT_TYPE);
		} catch (Exception e) {
			throw new WrappedException(e);
		}
	}
}
