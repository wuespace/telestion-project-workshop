package de.wuespace.telestion.project.workshop1;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.wuespace.telestion.api.message.JsonMessage;
import de.wuespace.telestion.api.verticle.TelestionConfiguration;
import de.wuespace.telestion.api.verticle.TelestionVerticle;
import de.wuespace.telestion.api.verticle.trait.WithEventBus;
import de.wuespace.telestion.project.workshop1.message.IMUData;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.Json;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

public class TCPReceiver extends TelestionVerticle<TCPReceiver.Configuration> implements WithEventBus {

	public record Configuration(
			@JsonProperty String outAddress,
			@JsonProperty String host,
			@JsonProperty int port
	) implements TelestionConfiguration {
		public Configuration() {
			this(null, "0.0.0.0", 0);
		}
	}

	@Override
	public void onStart() throws Exception {
		var options = new NetClientOptions().setConnectTimeout(0).setReconnectAttempts(0).setReconnectInterval(3000);
		NetClient client = vertx.createNetClient(options);

		client.connect(getConfig().port(), getConfig().host())
				.onSuccess(this::onConnected)
				.onFailure(err -> logger.error("Cannot connect to {}:{}", getConfig().host(), getConfig().port(), err));
	}

	private void onConnected(NetSocket socket) {
		logger.info("Connected to {}:{}", getConfig().host(), getConfig().port());

		socket.handler(buffer -> {
			var message = JsonMessage.from(buffer, IMUData.class);
			var receiveTime = System.currentTimeMillis();
			var options = new DeliveryOptions()
					.addHeader("receive-time", Json.encode(receiveTime))
					.addHeader("time", Json.encode(receiveTime));

			publish(getConfig().outAddress(), message, options);
		});
	}
}
