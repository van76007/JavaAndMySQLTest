package com.algo.processor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.algo.topK.TopKFrequentWords;
import com.algo.topK.dsa.TrieEntry;

/**
 * A class to split big file into chunks.
 * The words of each chunk will be added to a Trie. Eventually, we extract the top K frequent words from this Trie
 * @author dvanvu
 *
 */
public class FileProcessor {
	private final String delimiter;
	private final String fileName;
	/** Number of chunks the file to be splitted **/
	private final int chunks;
	TopKFrequentWords top;

	public FileProcessor(String fileName, int chunks, int k, String delimiter) {
		this.fileName = fileName;
		this.chunks = chunks;
		this.delimiter = delimiter;
		this.top = new TopKFrequentWords(k);
	}
	
	/**
	 * A method to find top K words in a file
	 * @return Top K words with their frequencies
	 * @throws IOException if reading file has IO exception
	 */
	public List<TrieEntry> findTopKWords() throws IOException {
		File file = new File(fileName);
		long len = file.length();

		long[] offsets = splitFile(file);

		for (int i = 0; i < chunks; i++) {
			long start = offsets[i];
			long end = i < chunks - 1 ? offsets[i + 1] : len;

			ChunkProcessor processor = new ChunkProcessor(file, start, end, delimiter, top);
			processor.processChunk();
		}

		List<TrieEntry> result = new ArrayList<TrieEntry>();
		for (TrieEntry entry : top.getTopKWords()) {
			result.add(entry);
		}
		return result;
	}
	
	/**
	 * A method to split text file into non-overlapped chunks, each chunk preserves text lines
	 * @param file A big file to be spitted
	 * @return Offsets of trunks
	 * @throws IOException if reading file has IO Exception
	 */
	private long[] splitFile(File file) throws IOException {
		long[] offsets = new long[chunks];

		RandomAccessFile raf = new RandomAccessFile(file, "r");
		for (int i = 1; i < chunks; i++) {
			raf.seek(i * file.length() / chunks);

			while (true) {
				int read = raf.read();
				if (read == '\n' || read == -1) {
					break;
				}
			}

			offsets[i] = raf.getFilePointer();
		}
		raf.close();

		return offsets;
	}
}
