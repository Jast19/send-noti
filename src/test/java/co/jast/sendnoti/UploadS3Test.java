package co.jast.sendnoti;

import co.jast.sendnoti.s3.UploadS3;
import co.jast.sendnoti.util.DataUtil;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UploadS3Test {

    @Test
    void uploadFileToS3Test() {
        int numberFiles = 1;
        String path = DataUtil.PATH_S3;
        File file = DataUtil.loadFile("File1.txt");

        UploadS3 uploadS3 = new UploadS3();
        List<String> pathS3 = uploadS3.uploadFileS3(path, file);

        Assertions.assertEquals(numberFiles, pathS3.size());
    }

    @Test
    void uploadFilesToS3Test() {
        int numberFiles = 2;
        String path = DataUtil.PATH_S3;
        File file1 = DataUtil.loadFile("File1.txt");
        File file2 = DataUtil.loadFile("File2.csv");

        UploadS3 uploadS3 = new UploadS3();
        List<String> pathS3 = uploadS3.uploadFileS3(path, file1, file2);

        Assertions.assertEquals(numberFiles, pathS3.size());
    }

}
