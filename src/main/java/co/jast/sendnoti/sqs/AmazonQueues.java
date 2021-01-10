package co.jast.sendnoti.sqs;

import co.jast.sendnoti.util.AmazonConfig;
import co.jast.sendnoti.util.AmazonKeys;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import co.jast.sendnoti.util.GeneralConstants;
import java.util.Map;

public class AmazonQueues {

    private AmazonKeys amazonKeys;
    private AmazonConfig amazonConfig;

    private AmazonQueues() {
        this.amazonConfig = AmazonConfig.getInstance();
        this.amazonKeys = AmazonKeys.getInstance();
    }

    public static AmazonQueues getInstance() {
        return AmazonQueuesHelper.INSTANCE;
    }

    public SendMessageResult queuesService(Map<String, MessageAttributeValue> queueAttributes) {
        String body = queueAttributes.get(GeneralConstants.MAIL_TEXT.getValue()).getStringValue();
        queueAttributes.remove(GeneralConstants.MAIL_TEXT.getValue());
        String standardQueueUrl = this.amazonConfig.amazonSQS().getQueueUrl(this.amazonKeys.sqsQueueName())
            .getQueueUrl();
        SendMessageRequest sendMessageStandardQueue = new SendMessageRequest()
            .withQueueUrl(standardQueueUrl)
            .withMessageBody(body)
            .withMessageAttributes(queueAttributes);
        return this.amazonConfig.amazonSQS().sendMessage(sendMessageStandardQueue);
    }

    private static class AmazonQueuesHelper {

        private static final AmazonQueues INSTANCE = new AmazonQueues();
    }

}
