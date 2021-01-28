package com.facpro.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * Hello world!
 *
 */
public class App 
{
    private final static String QUEUE_NAME = "task_queue";
    private final static String EXCHANGE_NAME = "logs";
    public static void main( String[] args ) throws Exception
    {
        ConnectionFactory factory =  new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        // boolean durable = true;
        // channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        // channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

            try {
                doWork(message);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println(" [x] Done");
                //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        boolean autoAck = true;
        //channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> {});
        channel.basicConsume(queueName, autoAck, deliverCallback, consumerTag -> {});
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }
}
