package com.facpro.rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import com.rabbitmq.client.Channel;

/**
 * Hello world!
 *
 */
public class App 
{
    private final static String QUEUE_NAME = "task_queue";
    private final static String EXCHANGE_NAME = "logs";
    public static void main( String[] args )
    {
        ConnectionFactory factory =  new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()){
                boolean durable = true;
                //channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
                channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
                String message = String.join(" ", args);
                channel.basicPublish(EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
