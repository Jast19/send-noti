package co.jast.sendnoti.util;

public enum AmazonProperties {

    FILE_NAME_AMAZON_PROPERTIES("amazon.properties"),
    S3_ACCESS_KEY("amazonS3.accessKey"),
    S3_SECRET_KEY("amazonS3.secretKey"),
    S3_BUCKET_NAME("amazonS3.bucketName"),
    SQS_ACCESS_KEY("amazonSQS.accessKey"),
    SQS_SECRET_KET("amazonSQS.secretKey"),
    SQS_QUEUE_NAME("amazonSQS.queueName"),
    ;

    private final String value;

    AmazonProperties(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
