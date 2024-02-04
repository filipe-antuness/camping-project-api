package com.api.campingproject.core.service;

import com.api.campingproject.core.service.form.EmailForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EnviaEmailService {

    private final JavaMailSender javaMailSender;


    public EnviaEmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public ResponseEntity<?> enviar(EmailForm emailForm) throws MessagingException {
        log.info("Enviando email");

        MimeMessage mensagem = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(emailForm.getPara());
        helper.setSubject(emailForm.getTitulo());
        helper.setText(emailForm.getConteudo(), true);

        javaMailSender.send(mensagem);
        log.info("Email enviado com sucesso!");


        return ResponseEntity.ok().build();
    }
}
