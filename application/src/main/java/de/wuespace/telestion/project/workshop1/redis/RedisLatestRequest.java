package de.wuespace.telestion.project.workshop1.redis;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;

import java.util.List;

@SuppressWarnings("unused")
public record RedisLatestRequest(
		@JsonProperty List<String> fields
) implements JsonMessage {
	public RedisLatestRequest() {
		this(List.of());
	}
}
