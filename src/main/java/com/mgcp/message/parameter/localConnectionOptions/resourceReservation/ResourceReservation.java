package com.mgcp.message.parameter.localConnectionOptions.resourceReservation;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class ResourceReservation extends BaseLocalOptionValue {
	private ResourceReservationEnum resourceReservationEnum;

	public ResourceReservation(ResourceReservationEnum resourceReservationEnum) {
		this.resourceReservationEnum = resourceReservationEnum;
	}

	public ResourceReservationEnum getResourceReservationEnum() {
		return resourceReservationEnum;
	}

	@Override
	public String toString() {
		return resourceReservationEnum.toString();
	}

	public static ResourceReservation parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}
		
		text=text.trim();

		return new ResourceReservation(ResourceReservationEnum.valueOf(text));
	}

	public enum ResourceReservationEnum {
		g, cl, be
	}

}
