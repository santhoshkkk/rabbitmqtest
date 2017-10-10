package in.test.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestProducer {

	private static final String Q_NAME = "new";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory cfac = new ConnectionFactory();
		cfac.setHost("localhost");
		Connection conn = cfac.newConnection();
		Channel channel = conn.createChannel();

		channel.queueDeclare(Q_NAME, true, false, false, null);
		String message = "message1";
		channel.basicPublish("", Q_NAME, null, message.getBytes());
		System.out.println("sent " + message);

		Channel channel2 = conn.createChannel();
		//channel2.queueDeclarePassive(Q_NAME);
		channel2.queueDeclare(Q_NAME, true, false, false, null);
		String message2 = "message2";
		channel2.basicPublish("", Q_NAME, null, message.getBytes());
		System.out.println("sent " + message2);

		conn.close();
	}

}
