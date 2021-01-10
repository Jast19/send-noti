package co.jast.sendnoti.util;

public enum GeneralConstants {

    MAIL_FROM("from"),
    MAIL_SUBJECT("subject"),
    MAIL_TEXT("text"),
    MAIL_IS_ATTACHMENT("isAttachment"),
    MAIL_ATTACHMENT("attachment"),
    MAIL_TO("to"),
    MAIL_BCC("bcc"),
    MAIL_CC("cc"),
    MAIL_BUCKET("bucket"),
    WITH_DATA_TYPE("String"),
    FOLDER_SUFFIX("/"),
    DATE_FORMATTER("yyyy/MM/dd"),
    ;

    private final String value;

    GeneralConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
