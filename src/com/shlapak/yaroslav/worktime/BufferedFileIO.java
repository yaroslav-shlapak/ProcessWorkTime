package com.shlapak.yaroslav.worktime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BufferedFileIO <T> {
	
	private Charset charset;
	
	public BufferedFileIO(String charsetName) {
		this.charset = Charset.forName(charsetName);
	}

	public String readFile(Path path, String list) {
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				list += line;
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		return list;
	}
	
	public void writeFile(Path path, List<T> list) {
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			for(T d : list) {
				String s = String.valueOf(d);
				writer.write(s, 0, s.length());
				writer.newLine();
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
}

