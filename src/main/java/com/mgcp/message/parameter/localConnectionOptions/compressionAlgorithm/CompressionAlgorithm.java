package com.mgcp.message.parameter.localConnectionOptions.compressionAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class CompressionAlgorithm extends BaseLocalOptionValue {
	private ArrayList<String> algorithmNames = new ArrayList<>();

	public CompressionAlgorithm(String... algorithmNames) {
		this.algorithmNames = new ArrayList<>(Arrays.asList(algorithmNames));
	}

	public CompressionAlgorithm(ArrayList<String> algorithmNames) {
		this.algorithmNames = new ArrayList<>(algorithmNames);
	}

	public ArrayList<String> getAlgorithmNames() {
		return algorithmNames;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(algorithmNames.get(0));
		for (int i = 1; i < algorithmNames.size(); i++) {
			builder.append(";" + algorithmNames.get(i));
		}

		return builder.toString();
	}

	public static CompressionAlgorithm parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split(";");
		return new CompressionAlgorithm(parts);
	}
}
