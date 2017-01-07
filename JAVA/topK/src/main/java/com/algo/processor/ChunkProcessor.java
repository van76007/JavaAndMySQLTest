package com.algo.processor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

import com.algo.topK.TopKFrequentWords;

/**
 * A class to add words in a chunk file to a Trie
 * @author dvanvu
 *
 */
public class ChunkProcessor {

	private final File file;
	private final long start;
	private final long end;
	/** Delimiter to split words in a line of text **/
	private final String delimiter;
	private TopKFrequentWords top;

	public ChunkProcessor(File file, long start, long end, String delimiter, TopKFrequentWords top) {
		this.file = file;
		this.start = start;
		this.end = end;
		this.delimiter = delimiter;
		this.top = top;
	}
	
	/**
	 * A method to read all lines in a text file, break them by a delimiter and add to the Trie
	 * @throws IOException if reading file has IO Exception
	 */
	public void processChunk() throws IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		raf.seek(start);

		while (raf.getFilePointer() < end) {
			String line = raf.readLine();
			if (line == null) {
				continue;
			}

			StringTokenizer tokenizer = new StringTokenizer(line, delimiter);
			while (tokenizer.hasMoreElements()) {
				top.add(tokenizer.nextElement().toString().trim());
			}
		}

		raf.close();
	}
}
