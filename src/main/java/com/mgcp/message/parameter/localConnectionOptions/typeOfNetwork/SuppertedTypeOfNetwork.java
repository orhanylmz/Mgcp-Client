package com.mgcp.message.parameter.localConnectionOptions.typeOfNetwork;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class SuppertedTypeOfNetwork extends BaseLocalOptionValue {
	private ArrayList<TypeOfNetwork> supportedTypeOfNetworks = new ArrayList<>();

	public SuppertedTypeOfNetwork(TypeOfNetwork... supportedTypeOfNetworks) {
		this.supportedTypeOfNetworks = new ArrayList<>(Arrays.asList(supportedTypeOfNetworks));
	}

	public SuppertedTypeOfNetwork(ArrayList<TypeOfNetwork> typeOfNetworks) {
		this.supportedTypeOfNetworks = new ArrayList<>(supportedTypeOfNetworks);
	}

	public ArrayList<TypeOfNetwork> getSupportedTypeOfNetworks() {
		return supportedTypeOfNetworks;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(supportedTypeOfNetworks.get(0).toString());
		for (int i = 1; i < supportedTypeOfNetworks.size(); i++) {
			builder.append(";" + supportedTypeOfNetworks.get(i).toString());
		}

		return builder.toString();
	}

	public static SuppertedTypeOfNetwork parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		String[] parts = text.split("[;]");

		ArrayList<TypeOfNetwork> typeOfNetworkValues = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			TypeOfNetwork typeOfNetworkValue = TypeOfNetwork.parse(parts[i]);
			typeOfNetworkValues.add(typeOfNetworkValue);
		}

		return new SuppertedTypeOfNetwork(typeOfNetworkValues);
	}
}
