package com.amigoscode.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Configuration {

//    @Value("${aws.access-key}")
    private String accessKey = "test";

//    @Value("${aws.secret-access-key}")
    private String secretAccessKey = "test";
    private final Region region = Region.EU_CENTRAL_1;

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretAccessKey);
        AWSStaticCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(region.toString())
                .build();
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(region)
                .httpClient(ApacheHttpClient.builder().build())
                .build();
    }
}
