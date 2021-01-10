package co.jast.sendnoti;

import co.jast.sendnoti.email.Mail;
import co.jast.sendnoti.exc.RapidMailException;
import co.jast.sendnoti.util.DataUtil;
import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RapidMailTest {

    @Test
    void rapidMailSendWithoutAttachmentTest() {

        String to = DataUtil.TO;
        String subjectEmail = "RapidMail Proof Without Attachments";
        String content = DataUtil.content();
        Mail mail = DataUtil.mailDefault(to, subjectEmail, content);

        String res = RapidMail.builder().mail(mail).build();

        Assertions.assertNotNull(res);
    }

    @Test
    void rapidMailSendWithAttachmentsTest() {

        String path = DataUtil.PATH_S3;
        File file1 = DataUtil.loadFile("File1.txt");
        File file2 = DataUtil.loadFile("File2.csv");

        String to = DataUtil.TO;
        String subjectEmail = "RapidMail Proof With Attachments";
        String content = DataUtil.content();
        Mail mail = DataUtil.mailDefault(to, subjectEmail, content);

        String res = RapidMail.builder()
            .pathS3(path)
            .files(file1, file2)
            .mail(mail).build();

        Assertions.assertNotNull(res);
    }

    @Test
    void rapidMailSendOnlyWithPathTest() {

        String path = DataUtil.PATH_S3;
        String to = DataUtil.TO;
        String subjectEmail = "This throws an error";
        String content = DataUtil.content();
        Mail mail = DataUtil.mailDefault(to, subjectEmail, content);

        RapidMail res = RapidMail.builder()
            .pathS3(path)
            .mail(mail);

        RapidMailException exception = Assertions
            .assertThrows(RapidMailException.class, () -> res.build());

        Assertions.assertTrue(exception.getMessage().startsWith("Unable"));
    }

}
