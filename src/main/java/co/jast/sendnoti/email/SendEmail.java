package co.jast.sendnoti.email;

import co.jast.sendnoti.sqs.AmazonQueues;
import co.jast.sendnoti.util.AmazonKeys;
import co.jast.sendnoti.util.GeneralConstants;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageResult;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SendEmail {

    private final AmazonKeys amazonKeys;
    private final AmazonQueues amazonQueues;

    public SendEmail() {
        this.amazonKeys = AmazonKeys.getInstance();
        this.amazonQueues = AmazonQueues.getInstance();
    }

    public String sendEmailToQueue(Mail mail) {
        Map<String, MessageAttributeValue> queueAttributes = this.buildAttributes(mail);
        SendMessageResult resultQueue = this.amazonQueues.queuesService(queueAttributes);
        return resultQueue.getMessageId();
    }

    private Map<String, MessageAttributeValue> buildAttributes(Mail mail) {
        Map<String, MessageAttributeValue> attributes = new HashMap<>();
        attributes.put(GeneralConstants.MAIL_FROM.getValue(), this.get(mail.getFrom()));
        attributes.put(GeneralConstants.MAIL_SUBJECT.getValue(), this.get(mail.getSubject()));
        attributes.put(GeneralConstants.MAIL_TEXT.getValue(), this.get(mail.getContent()));
        attributes.put(GeneralConstants.MAIL_BUCKET.getValue(), this.get(this.amazonKeys.s3BucketName()));
        attributes.put(GeneralConstants.MAIL_TO.getValue(), this.get(mail.getTo()));

        Optional.ofNullable(mail.getCc())
            .ifPresent(cc -> attributes.put(GeneralConstants.MAIL_CC.getValue(), this.get(cc)));
        Optional.ofNullable(mail.getBcc())
            .ifPresent(bcc -> attributes.put(GeneralConstants.MAIL_BCC.getValue(), this.get(bcc)));

        Optional.ofNullable(mail.getAttachmentsFileNames()).ifPresent(files -> {
            attributes.put(GeneralConstants.MAIL_IS_ATTACHMENT.getValue(), this.get("true"));
            attributes.put(GeneralConstants.MAIL_ATTACHMENT.getValue(),
                get(String.join(",", files)));
        });
        return attributes;
    }

    private MessageAttributeValue get(String value) {
        return new MessageAttributeValue().withStringValue(value)
            .withDataType(GeneralConstants.WITH_DATA_TYPE.getValue());
    }

}


