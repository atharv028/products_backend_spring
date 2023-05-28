package com.tare.notificationservice.service

import org.springframework.stereotype.Service
import java.util.*
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class NotificationService {

    private val username = "atharv.tare.03@gmail.com"
    private val password = "lywvrxcxqqbsxsrj"

    fun sendMail(orderNumber: String?) {
        val prop = Properties().apply {
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
        }

        val session = Session.getInstance(prop, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        try {
            val msg = MimeMessage(session)
            msg.setFrom(InternetAddress("atharv.tare.03@gmail.com"))
            msg.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse("atharv.tare.3@gmail.com , sokata143@gmail.com, atharvtare512@gmail.com")
            )
            msg.setSubject("Your order is placed successfully", "utf-8")
            msg.setText(
                "Dear Customer,"
                        + "\n\n Your order with number $orderNumber is placed successfully! Thank you for using our service"
            )

            Transport.send(msg)
            println("Done")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }
}