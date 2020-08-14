package com.antkorwin.mimetype;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.antkorwin.throwable.functions.ThrowableSupplier;
import com.antkorwin.throwable.functions.WrappedException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

/**
 * Created on 30/06/2020
 * <p>
 * Resolve the MimeType by the content of a file.
 *
 * @author Korovin Anatoliy
 */
public class FileTextResolver implements MetaDataResolver {

	@Override
	public String get(File file) {
		return getTextFromInputStream(() -> new FileInputStream(file));
	}

	public String get(byte[] contentOfFile) {
		return getTextFromInputStream(() -> new ByteArrayInputStream(contentOfFile));
	}

	public String get(ThrowableSupplier<InputStream> inputStreamSupplier) {
		return getTextFromInputStream(inputStreamSupplier);
	}

	private String getTextFromInputStream(ThrowableSupplier<InputStream> inputStreamSupplier) {
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		ParseContext context = new ParseContext();
		try (InputStream inputStream = inputStreamSupplier.get()) {
			parser.parse(inputStream, handler, metadata, context);
			return handler.toString();
		} catch (Exception e) {
			throw new WrappedException(e);
		}
	}
}
