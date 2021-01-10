package co.jast.sendnoti.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import co.jast.sendnoti.exc.RapidMailException;
import co.jast.sendnoti.util.AmazonConfig;
import co.jast.sendnoti.util.AmazonKeys;
import co.jast.sendnoti.util.GeneralConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UploadS3 {

    private final AmazonConfig amazonConfig;
    private final AmazonKeys amazonKeys;

    public UploadS3() {
        this.amazonConfig = AmazonConfig.getInstance();
        this.amazonKeys = AmazonKeys.getInstance();
    }

    public List<String> uploadFileS3(String pathS3, File... files) {
        List<String> paths = new ArrayList<>();
        Arrays.asList(files).forEach(file -> paths.add(this.uploadFileS3(pathS3, file)));
        return paths;
    }

    private String uploadFileS3(String pathS3, File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileContent);

            PutObjectResult resultS3 = this.putAsset(pathS3, file.getName(), byteArrayInputStream);

            Objects.requireNonNullElseGet(resultS3.getETag(),
                () -> new RapidMailException(String.format("Error upload file to S3 [%s]", file.getName())));
            return pathS3.concat(file.getName());
        } catch (IOException e) {
            throw new RapidMailException(String.format("Error upload file to S3 [%s]", file.getName()), e);
        }
    }

    private PutObjectResult putAsset(String pathS3, String assetName, InputStream asset) {
        return this.putAsset(pathS3, assetName, asset, false);
    }

    private PutObjectResult putAsset(String pathS3, String assetName, InputStream asset, boolean appendDateToFilePath) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(((ByteArrayInputStream) asset).available());
        return this.amazonConfig.amazonS3().putObject(new PutObjectRequest(this.amazonKeys.s3BucketName(),
            this.getS3Path(!appendDateToFilePath ? pathS3 : this.appendFullDatePathFormat(pathS3)) + assetName, asset,
            metadata));
    }

    private String appendFullDatePathFormat(String filePath) {
        return new StringBuilder().append(filePath)
            .append(!filePath.substring(filePath.length() - 1).equals(GeneralConstants.FOLDER_SUFFIX.getValue()) ?
                GeneralConstants.FOLDER_SUFFIX.getValue() : "")
            .append(LocalDate.now().format(DateTimeFormatter.ofPattern(GeneralConstants.DATE_FORMATTER.getValue())))
            .toString();
    }

    private String getS3Path(String path) {
        if (path.startsWith(GeneralConstants.FOLDER_SUFFIX.getValue())) {
            path = path.substring(1);
        }
        return path + (!path.substring(path.length() - 1).equals(GeneralConstants.FOLDER_SUFFIX.getValue()) ?
            GeneralConstants.FOLDER_SUFFIX.getValue() : "");
    }

}
