package com.antkorwin.mimetype;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.antkorwin.throwable.functions.WrappedException;
import org.apache.commons.codec.binary.Hex;

/**
 * Created on 10/08/2020
 * <p>
 * Evaluate SHA256 hash of the file.
 *
 * @author Korovin Anatoliy
 */
public class Sha256Resolver implements MetaDataResolver {

	private static final int BUFFER_SIZE = 4096;

	@Override
	public String get(File file) {
		try {
			byte[] hash = getHash(file);
			return Hex.encodeHexString(hash);
		} catch (Exception e) {
			throw new WrappedException(e);
		}
	}

	private byte[] getHash(File file) throws NoSuchAlgorithmException, IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		int count;
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
			while ((count = bis.read(buffer)) > 0) {
				digest.update(buffer, 0, count);
			}
		}
		return digest.digest();
	}
}
