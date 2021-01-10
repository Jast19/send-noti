package co.jast.sendnoti.email;

import java.io.Serializable;
import java.util.List;

public class Mail implements Serializable {

    private static final long serialVersionUID = 8584263439834027993L;
    private String to;
    private String from;
    private String cc;
    private String bcc;
    private String subject;
    private String content;
    private List<String> attachmentsFileNames;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAttachmentsFileNames() {
        return attachmentsFileNames;
    }

    public void setAttachmentsFileNames(List<String> attachmentsFileNames) {
        this.attachmentsFileNames = attachmentsFileNames;
    }

    @Override
    public String toString() {
        return "Mail{" +
            "to='" + to + '\'' +
            ", from='" + from + '\'' +
            ", cc='" + cc + '\'' +
            ", bcc='" + bcc + '\'' +
            ", subject='" + subject + '\'' +
            ", content='" + content + '\'' +
            ", attachmentsFileNames=" + attachmentsFileNames +
            '}';
    }
}

