package com.api.campingproject.core.service;

import com.api.campingproject.core.service.form.EmailForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EnviaEmailService {

    private final JavaMailSender javaMailSender;


    public EnviaEmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public ResponseEntity<?> enviar(EmailForm emailForm){
        log.info("Enviando email");

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(emailForm.getPara());

        mensagem.setSubject(emailForm.getTitulo());
        mensagem.setText(emailForm.getConteudo());
        javaMailSender.send(mensagem);
        log.info("Email enviado com sucesso!");

        return ResponseEntity.ok().build();
    }
}
