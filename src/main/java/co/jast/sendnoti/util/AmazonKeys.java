package co.jast.sendnoti.util;

import co.jast.sendnoti.exc.RapidMailException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class AmazonKeys {

    private Properties properties;

    private AmazonKeys() {
        this.loadConfig();
    }

    public static AmazonKeys getInstance() {
        return AmazonConfigHelper.INSTANCE;
    }

    public String s3AccessKey() {
        return this.getValueProperties(AmazonProperties.S3_ACCESS_KEY.getValue());
    }

    public String s3SecretKey() {
        return this.getValueProperties(AmazonProperties.S3_SECRET_KEY.getValue());
    }

    public String s3BucketName() {
        return this.getValueProperties(AmazonProperties.S3_BUCKET_NAME.getValue());
    }

    public String sqsAccessKey() {
        return this.getValueProperties(AmazonProperties.SQS_ACCESS_KEY.getValue());
    }

    public String sqsSecretKey() {
        return this.getValueProperties(AmazonProperties.SQS_SECRET_KET.getValue());
    }

    public String sqsQueueName() {
        return this.getValueProperties(AmazonProperties.SQS_QUEUE_NAME.getValue());
    }

    public String getValueProperties(String nameProperties) {
        var value = properties.getProperty(nameProperties);
        Objects.requireNonNull(value, String.format("Property not found [%s]", nameProperties));
        return value;
    }

    private void loadConfig() {
        var fileName = AmazonProperties.FILE_NAME_AMAZON_PROPERTIES.getValue();
        try {
            InputStream inputStream = AmazonKeys.class.getClassLoader()
                .getResourceAsStream(fileName);
            Objects.requireNonNull(inputStream, String.format("File not found [%s]", fileName));
            this.properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RapidMailException(String.format("Error load file [%s]", fileName), e);
        }
    }

    private static class AmazonConfigHelper {

        private static final AmazonKeys INSTANCE = new AmazonKeys();
    }
}
