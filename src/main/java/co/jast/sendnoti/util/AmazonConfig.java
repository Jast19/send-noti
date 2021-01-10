package co.jast.sendnoti.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class AmazonConfig {

    private AmazonKeys amazonKeys;

    private AmazonConfig() {
        this.amazonKeys = AmazonKeys.getInstance();
    }

    public static AmazonConfig getInstance() {
        return AmazonConfigHelper.INSTANCE;
    }

    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(
                this.awsStaticCredentialsProvider(this.amazonKeys.sqsAccessKey(), this.amazonKeys.sqsSecretKey()))
            .build();
    }

    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
            .withRegion(Regions.US_EAST_1.getName())
            .withCredentials(
                this.awsStaticCredentialsProvider(this.amazonKeys.s3AccessKey(), this.amazonKeys.s3SecretKey()))
            .build();
    }

    private AWSStaticCredentialsProvider awsStaticCredentialsProvider(String accessKey, String secretKey) {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
    }

    private static class AmazonConfigHelper {

        private static final AmazonConfig INSTANCE = new AmazonConfig();
    }

}
