package de.wuespace.telestion.project.workshop1.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record IMUData(
		@JsonProperty Vector3D acc,
		@JsonProperty Vector3D gyro,
		@JsonProperty Vector3D mag
) implements JsonMessage {
}
