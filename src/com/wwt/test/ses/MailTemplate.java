package com.wwt.test.ses;

import java.util.List;

/**
 * @author wwt
 * @date 2018/1/2 17:36
 */
public class MailTemplate {

    public MailTemplate(String from, List<String> to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    private String from;
    private List<String> to;
    private String subject;
    private String body;

    public String getFrom() {
        return from;
    }

    public List<String> getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
