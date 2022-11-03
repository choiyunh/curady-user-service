package com.curady.userservice.domain.user.service;

import com.curady.userservice.global.advice.exception.EmailSenderException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(String email, String authToken) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        StringBuffer emailcontent = new StringBuffer();
        emailcontent.append("<!DOCTYPE html>");
        emailcontent.append("<html>");
        emailcontent.append("<head>");
        emailcontent.append("</head>");
        emailcontent.append("<body>");
        emailcontent.append(
                " <div" +
                        "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #0054FD; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">" +
                        "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">" +
                        "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">curady</span><br />" +
                        "		<span style=\"color: #0054FD\">메일 인증</span> 안내입니다." +
                        "	</h1>\n" +
                        "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">" +
                        email +
                        "		님 안녕하세요.<br />" +
                        "		curady에 가입해 주셔서 진심으로 감사드립니다.<br />" +
                        "		아래 <b style=\"color: #0054FD\">'메일 인증'</b> 버튼을 클릭하여 회원가입을 완료해 주세요.<br />" +
                        "		감사합니다." +
                        "	</p>" +
                        "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\"" +
                        "	href=\"http://curady.kr/signup-confirmemail?email=" + email + "&authToken=" + authToken + "\" target=\"_blank\">" +
                        "		<p" +
                        "			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #0054FD; line-height: 45px; vertical-align: middle; font-size: 16px;\">" +
                        "			메일 인증</p>" +
                        "	</a>" +
                        "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>" +
                        " </div>"
        );
        emailcontent.append("</body>");
        emailcontent.append("</html>");

        try {
            helper.setFrom("ROMANTICISM");
            helper.setTo(email);
            helper.setSubject("curady 인증 메일");
            helper.setText(emailcontent.toString(), true);
        } catch (MessagingException e) {
            throw new EmailSenderException();
        }

        javaMailSender.send(mimeMessage);
    }
}
