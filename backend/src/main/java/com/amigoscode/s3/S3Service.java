package com.amigoscode.s3;


import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final S3Buckets s3Buckets;

    public S3Service(S3Client s3Client, S3Buckets s3Buckets) {
        this.s3Client = s3Client;
        this.s3Buckets = s3Buckets;
    }

    public List<String> listBuckets() {
//        return amazonS3.listBuckets()
//                .stream()
//                .map(Bucket::getName)
//                .collect(Collectors.toList());
        return null;
    }

    public List<String> listFilesInBucket() {
//        ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request();
//        listObjectsV2Request.setBucketName(bucketName);
//
//        ListObjectsV2Result listObjectsV2Response = amazonS3.listObjectsV2(listObjectsV2Request);
//        List<S3ObjectSummary> objectSummaries = listObjectsV2Response.getObjectSummaries();
//
//        return objectSummaries.stream()
//                .map(S3ObjectSummary::getKey)
//                .collect(Collectors.toList());
        return null;

    }

    public void putObject(String key, byte[] bytes) {
//        String objectKey = UUID.randomUUID() + "_" + file.getOriginalFilename();
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(s3Buckets.getCustomer())
                .key(key)
                .build();

        s3Client.putObject(objectRequest, RequestBody.fromBytes(bytes));
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(file.getSize());
//        metadata.setContentType(file.getContentType());
//        try {
//            amazonS3.putObject(new PutObjectRequest(bucketName, String.format("images/%s/%s", customerId, objectKey) + objectKey, file.getInputStream(), metadata));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public byte[] getObject(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(s3Buckets.getCustomer())
                .key(key)
                .build();
        ResponseInputStream<GetObjectResponse> res = s3Client.getObject(getObjectRequest);
        try {
            return res.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
