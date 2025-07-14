package config;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.text.DecimalFormat;
public class EmailSender {

    public static void sendRefundRequestEmail(String toEmail,
            String appointmentCode,
            String patientName,
            String doctorName,
            String workingDate,
            String slotTime,
            String amount,
            String refundReason) throws MessagingException, UnsupportedEncodingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("doctris123@gmail.com", "lbfqpmndsfbyuwwk");
            }
        });

        DecimalFormat currencyFormat = new DecimalFormat("#,###");
        String formattedAmount = currencyFormat.format(Double.parseDouble(amount)) + " VND";

        String htmlContent = "<html>"
                + "<body style='font-family: Arial, sans-serif;'>"
                + "<h2 style='color: #c0392b;'>Yêu cầu hoàn tiền phiếu khám</h2>"
                + "<p>Hệ thống vừa nhận được một yêu cầu hoàn tiền từ người dùng.</p>"
                + "<table style='border-collapse: collapse; width: 40%; font-size: 14px;'>"
                + "  <tr><td><strong>Mã phiếu:</strong></td><td>" + appointmentCode + "</td></tr>"
                + "  <tr><td><strong>Bệnh nhân:</strong></td><td>" + patientName + "</td></tr>"
                + "  <tr><td><strong>Bác sĩ:</strong></td><td>" + doctorName + "</td></tr>"
                + "  <tr><td><strong>Ngày khám:</strong></td><td>" + workingDate + "</td></tr>"
                + "  <tr><td><strong>Khung giờ:</strong></td><td>" + slotTime + "</td></tr>"
                + "  <tr><td><strong>Số tiền:</strong></td><td>" + formattedAmount + "</td></tr>"
                + "  <tr><td><strong>Lý do:</strong></td><td>" + (refundReason == null || refundReason.isEmpty() ? "(Không có)" : refundReason) + "</td></tr>"
                + "</table>"
                + "<p style='margin-top:20px;'>Vui lòng xử lý yêu cầu này trong thời gian sớm nhất.</p>"
                + "</body></html>";

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("doctris123@gmail.com", "Doctris System"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Yêu cầu hoàn tiền phiếu khám", "UTF-8");
        message.setContent(htmlContent, "text/html; charset=UTF-8");

        Transport.send(message);
    }

    public static void sendVerificationEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        Date expirationTime = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String formattedExpirationTime = dateFormat.format(expirationTime);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("doctris123@gmail.com", "lbfqpmndsfbyuwwk");
            }
        });

        String verificationLink = "/verify?email=" + toEmail + "&token=";

        String htmlContent = "<html>"
                + "<body style='font-family: Arial, sans-serif;'>"
                + "<h2 style='color: #333;'>Xác minh tài khoản người dùng</h2>"
                + "<p>Mã OTP của bạn có hiệu lực sau 5 phút:</p>"
                + "<p href='" + "" + "' style='background-color: #4CAF50; color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 10px 0; border-radius: 5px;'>Click Here</p>"
                + "<p>Token sẽ hết hạn vào lúc: <strong>" + formattedExpirationTime + "</strong></p>"
                + "</body>"
                + "</html>";

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("doctris123@gmail.com", "Building Management"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Đổi Mật Khẩu", "UTF-8");

        message.setContent(htmlContent, "text/html; charset=UTF-8");
        Transport.send(message);
    }

    public static void sendOTP(String OTP, String toEmail) throws MessagingException, UnsupportedEncodingException {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        Date expirationTime = calendar.getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        String formattedExpirationTime = dateFormat.format(expirationTime);

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("doctris123@gmail.com", "lbfqpmndsfbyuwwk");
            }
        });

        String htmlContent = "<html>"
                + "<body style='font-family: Arial, sans-serif;'>"
                + "<h2 style='color: #333;'>Xác minh tài khoản người dùng</h2>"
                + "<p>Mã OTP của bạn có hiệu lực trong 5 phút:</p>"
                + "<p style='background-color: #4CAF50; color: white; padding: 10px 20px; "
                + "text-align: center; text-decoration: none; display: inline-block; "
                + "font-size: 24px; margin: 10px 0; border-radius: 5px;'>"
                + OTP + "</p>"
                + "<p>Token sẽ hết hạn vào lúc: <strong>" + formattedExpirationTime + "</strong></p>"
                + "</body>"
                + "</html>";

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("doctris123@gmail.com", "Doctris Management"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Mã OTP xác minh tài khoản", "UTF-8");

        message.setContent(htmlContent, "text/html; charset=UTF-8");
        Transport.send(message);
    }

    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
        EmailSender e = new EmailSender();
        e.sendVerificationEmail("huydvhe186208@fpt.edu.vn");

    }

}
