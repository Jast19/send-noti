SendNoti
========
Library send-noti to send mail with amazons3

Config
------
add amazon.properties in src/main/resources
```properties
amazonS3.accessKey:xxxxxxxxxx
amazonS3.secretKey:xxxxxxxxxx
amazonS3.bucketName:xxxxxxxxxx
amazonSQS.accessKey:xxxxxxxxxxxx
amazonSQS.secretKey:xxxxxxxxxxxx
amazonSQS.queueName:xxxxxxxxxxxx
```

Example
-------
```java
void rapidMailSendWithAttachmentsTest(){
    String path="pathS3";
    File file=new File("File.txt");
    Mail mail=new Mail();

    String res=RapidMail.builder()
    .pathS3(path)
    .files(file)
    .mail(mail).build();
    }
````
