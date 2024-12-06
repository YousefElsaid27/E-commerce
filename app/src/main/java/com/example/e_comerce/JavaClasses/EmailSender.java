package com.example.e_comerce.JavaClasses;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private static final String TAG = "EmailSender";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    // CRITICAL: Use your ACTUAL Gmail and EXACT App Password
    private static final String SENDER_EMAIL = "mobileproject37@gmail.com";
    private static final String SENDER_PASSWORD = "zefqjxaainzuouij";


    public static void sendVerificationCode(String recipientEmail, String verificationCode) {
        new SendEmailTask(SENDER_EMAIL, SENDER_PASSWORD).execute(recipientEmail, verificationCode);
    }

    private static class SendEmailTask extends AsyncTask<String, Void, Boolean> {
        private String senderEmail;
        private String senderPassword;
        private String errorMessage = "";

        public SendEmailTask(String email, String password) {
            this.senderEmail = email;
            this.senderPassword = password;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length < 2) {
                errorMessage = "Insufficient email parameters";
                return false;
            }

            String recipientEmail = params[0];
            String verificationCode = params[1];

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            session.setDebug(true);  // Enable detailed debugging

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recipientEmail));
                message.setSubject("Password Reset Verification Code");
                message.setText("Your verification code is: " + verificationCode +
                        "\n\nThis code will expire in 15 minutes.");

                Transport.send(message);
                return true;
            } catch (AuthenticationFailedException e) {
                errorMessage = "Authentication Failed: Check email and App Password\n" +
                        "Possible reasons:\n" +
                        "1. Incorrect App Password\n" +
                        "2. 2-Step Verification not enabled\n" +
                        "3. App Password not generated correctly\n" +
                        "Error details: " + e.getMessage();
                Log.e(TAG, errorMessage, e);
            } catch (MessagingException e) {
                errorMessage = "Messaging Error: " + e.getMessage();
                Log.e(TAG, errorMessage, e);
            } catch (Exception e) {
                errorMessage = "Unexpected Error: " + e.getMessage();
                Log.e(TAG, errorMessage, e);
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Log.e(TAG, "Email Send Failure Details: " + errorMessage);
            }
        }
    }
}