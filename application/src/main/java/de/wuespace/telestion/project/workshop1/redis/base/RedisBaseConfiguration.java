package de.wuespace.telestion.project.workshop1.redis.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;

public interface RedisBaseConfiguration extends TelestionConfiguration {
	@JsonProperty String connectionString();
	@JsonProperty int reconnectAttempts();
}
