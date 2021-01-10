package co.jast.sendnoti.util;

import co.jast.sendnoti.email.Mail;
import java.io.File;
import java.util.List;

public class DataUtil {

    public static String PATH_S3 = "proof/";
    public static String TO = "jhon.sanchez@techandsolve.com";

    public static Mail mailPathFiles(String to, String subject, String content, List<String> pathFiles) {
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setFrom("micredito@rapicredit.com");
        mail.setCc("micredito@rapicredit.com");
        mail.setSubject(subject);
        mail.setContent(content);
        mail.setAttachmentsFileNames(pathFiles);
        return mail;
    }

    public static Mail mailDefault(String to, String subject, String content) {
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setFrom("micredito@rapicredit.com");
        mail.setCc("micredito@rapicredit.com");
        mail.setSubject(subject);
        mail.setContent(content);
        return mail;
    }

    public static String content() {
        return "<!DOCTYPE html>\n"
            + "<html>\n"
            + "<body>\n"
            + "\n"
            + "<h1 style=\"font-size:300%;\">Email test for api RapidMail</h1>\n"
            + "<p style=\"font-size:160%;\">This is text for proof in html</p>\n"
            + "\n"
            + "</body>\n"
            + "</html>";
    }

    public static File loadFile(String file) {
        String path = "src/test/resources/" + file;
        return new File(path);
    }

}
