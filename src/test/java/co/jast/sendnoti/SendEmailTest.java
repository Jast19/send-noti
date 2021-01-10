package co.jast.sendnoti;

import co.jast.sendnoti.email.SendEmail;
import co.jast.sendnoti.email.Mail;
import co.jast.sendnoti.util.DataUtil;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SendEmailTest {

    @Test
    void sendMailWithoutAttachments() {
        String email = DataUtil.TO;
        String subjectEmail = "SendEmail Proof Without Attachments";
        String content = DataUtil.content();
        Mail mail = DataUtil.mailDefault(email, subjectEmail, content);

        SendEmail sendEmail = new SendEmail();
        String res = sendEmail.sendEmailToQueue(mail);

        Assertions.assertNotNull(res);
    }

    @Test
    void sendMailWithAttachments() {
        String email = DataUtil.TO;
        String subjectEmail = "SendEmail Proof With Attachments";
        String content = DataUtil.content();
        String path = DataUtil.PATH_S3 + "File1.txt";
        String path2 = DataUtil.PATH_S3 + "File2.csv";
        Mail mail = DataUtil.mailPathFiles(email, subjectEmail, content, Arrays.asList(path, path2));

        SendEmail sendEmail = new SendEmail();
        String res = sendEmail.sendEmailToQueue(mail);

        Assertions.assertNotNull(res);
    }

}
