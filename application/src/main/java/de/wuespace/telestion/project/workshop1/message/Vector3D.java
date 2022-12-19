package de.wuespace.telestion.project.workshop1.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

public record Vector3D(
		@JsonProperty double x,
		@JsonProperty double y,
		@JsonProperty double z
) implements JsonMessage {
}
