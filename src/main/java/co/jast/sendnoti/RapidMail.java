package co.jast.sendnoti;

import co.jast.sendnoti.s3.UploadS3;
import co.jast.sendnoti.email.Mail;
import co.jast.sendnoti.email.SendEmail;
import co.jast.sendnoti.exc.RapidMailException;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class RapidMail {

    private String pathS3;
    private File[] files;
    private Mail mail;

    private UploadS3 uploadS3;
    private SendEmail sendEmail;

    private RapidMail() {
        this.uploadS3 = new UploadS3();
        this.sendEmail = new SendEmail();
    }

    public static RapidMail builder() {
        return new RapidMail();
    }

    public RapidMail pathS3(String pathS3) {
        this.pathS3 = pathS3;
        return this;
    }

    public RapidMail files(File... files) {
        this.files = files;
        return this;
    }

    public RapidMail mail(Mail mail) {
        this.mail = mail;
        return this;
    }

    public String build() {
        this.validateData();
        return this.sendEmail.sendEmailToQueue(mail);
    }

    private void validateData() {
        boolean pathExist = Objects.nonNull(this.pathS3);
        boolean fileExist = Objects.nonNull(this.files);
        if (pathExist && fileExist) {
            List<String> path = this.uploadS3.uploadFileS3(this.pathS3, this.files);
            this.mail.setAttachmentsFileNames(path);
        } else if (pathExist || fileExist) {
            throw new RapidMailException(
                String.format("Unable to upload files to AmazonS3, pathS3[%s] and files[%s]", pathExist, fileExist));
        }
    }

}
