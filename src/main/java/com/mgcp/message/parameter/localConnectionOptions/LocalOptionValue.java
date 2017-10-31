package com.mgcp.message.parameter.localConnectionOptions;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.bandWidth.BandWidth;
import com.mgcp.message.parameter.localConnectionOptions.compressionAlgorithm.CompressionAlgorithm;
import com.mgcp.message.parameter.localConnectionOptions.echoCancellation.EchoCancellation;
import com.mgcp.message.parameter.localConnectionOptions.encryptionData.EncryptionData;
import com.mgcp.message.parameter.localConnectionOptions.gainControl.GainControl;
import com.mgcp.message.parameter.localConnectionOptions.localOptionExtension.LocalOptionExtension;
import com.mgcp.message.parameter.localConnectionOptions.packatizationPeriod.PacketizationPeriod;
import com.mgcp.message.parameter.localConnectionOptions.resourceReservation.ResourceReservation;
import com.mgcp.message.parameter.localConnectionOptions.silenceSuppression.SilenceSuppression;
import com.mgcp.message.parameter.localConnectionOptions.typeOfNetwork.SuppertedTypeOfNetwork;
import com.mgcp.message.parameter.localConnectionOptions.typeOfNetwork.TypeOfNetwork;
import com.mgcp.message.parameter.localConnectionOptions.typeOfService.TypeOfService;
import com.noyan.util.NullUtil;

public class LocalOptionValue {
	private LocalOptionValueEnum localOptionValueEnum;
	private BaseLocalOptionValue localOptionValue;

	public LocalOptionValue(LocalOptionValueEnum localOptionValueEnum, BaseLocalOptionValue localOptionValue) {
		this.localOptionValueEnum = localOptionValueEnum;
		this.localOptionValue = localOptionValue;
	}

	@Override
	public String toString() {
		if (localOptionValueEnum.equals(LocalOptionValueEnum.localOptionExtension)) {
			return localOptionValue.toString();
		}

		return localOptionValueEnum.toString() + ":" + localOptionValue.toString();
	}

	public static LocalOptionValue parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (!text.contains(":")) {
			return new LocalOptionValue(LocalOptionValueEnum.localOptionExtension, LocalOptionExtension.parse(text));
		}

		String[] parts = text.split(":");
		try {
			LocalOptionValueEnum localOptionValueEnum = LocalOptionValueEnum.valueOf(parts[0].trim());
			switch (localOptionValueEnum) {
			case p:
				return new LocalOptionValue(localOptionValueEnum, PacketizationPeriod.parse(parts[1]));
			case a:
				return new LocalOptionValue(localOptionValueEnum, CompressionAlgorithm.parse(parts[1]));
			case b:
				return new LocalOptionValue(localOptionValueEnum, BandWidth.parse(parts[1]));
			case e:
				return new LocalOptionValue(localOptionValueEnum, EchoCancellation.parse(parts[1]));
			case gc:
				return new LocalOptionValue(localOptionValueEnum, GainControl.parse(parts[1]));
			case s:
				return new LocalOptionValue(localOptionValueEnum, SilenceSuppression.parse(parts[1]));
			case t:
				return new LocalOptionValue(localOptionValueEnum, TypeOfService.parse(parts[1]));
			case r:
				return new LocalOptionValue(localOptionValueEnum, ResourceReservation.parse(parts[1]));
			case k:
				return new LocalOptionValue(localOptionValueEnum, EncryptionData.parse(parts[1]));
			case nt:
				try {
					return new LocalOptionValue(localOptionValueEnum, TypeOfNetwork.parse(parts[1]));
				} catch (MGCPParseException ignored) {
					return new LocalOptionValue(localOptionValueEnum, SuppertedTypeOfNetwork.parse(parts[1]));
				}
			default:
				break;
			}
		} catch (IllegalArgumentException ignored) {
		}

		return new LocalOptionValue(LocalOptionValueEnum.localOptionExtension, LocalOptionExtension.parse(parts[1]));
	}

	public enum LocalOptionValueEnum {
		p, // packetizationPeriod 1*4(DIGIT) [- 1*4(DIGIT)]
		a, // compressionAlgorithm algorithmName 0*(; algorithmName)
		b, // bandwith 1*4(DIGIT) [- 1*4(DIGIT)]
		e, // echoCancellation on/off
		gc, // gainControl auto/[-] 1*4(DIGIT)
		s, // silenceSuppression on/off
		t, // typeOfService 1*2(HEXDIG)
		r, // resourceReservation g / cl / be
		k, // encryptionData clear : encryptionKey / base64:encodedEncryptionKey
			// / uri: URItoObtainKey / prompt
		nt, // typeOfNetwork/supportedTypeOfNetwork
		localOptionExtension// localOptionExtensionName[:localOptionExtensionValue]
	}
}
