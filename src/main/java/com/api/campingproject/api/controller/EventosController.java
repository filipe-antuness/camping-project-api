package com.api.campingproject.api.controller;

import com.api.campingproject.api.vo.EventosVO;
import com.api.campingproject.core.repository.EventosRepository;
import com.api.campingproject.core.service.EventosService;
import com.api.campingproject.core.service.form.EventosForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController()
@RequestMapping("/eventos")
public class EventosController {

    private static String caminhoImagens = "C:\\Users\\Filipe Antunes\\Documents\\GitHub\\camping-project-api\\imagens\\";
    private String imagem = "";

    @Autowired
    EventosService eventosService;

    @Autowired
    EventosRepository eventosRepository;

    @PostMapping("/salvarImagem")
    public void salvarImagem(@RequestParam("file") MultipartFile arquivo){

        try{
            if(!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImagens+arquivo.getOriginalFilename());
                Files.write(caminho, bytes);

                this.imagem = arquivo.getOriginalFilename();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @GetMapping("/recuperarImagem/{imagem}")
    @ResponseBody
    public byte[] recuperarImagem(@PathVariable("imagem") String imagem) throws IOException {
        System.out.println(imagem);
        File imagemArquivo = new File(caminhoImagens+imagem);
        if(imagem!=null || imagem.trim().length()>0) {
            return Files.readAllBytes(imagemArquivo.toPath());
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<EventosForm> cadastrar(@RequestBody EventosForm eventosForm, UriComponentsBuilder uriComponentsBuilder) {
        eventosForm.setCaminhoImagem(this.imagem);
        this.imagem = "";
        return eventosService.cadastrar(eventosForm, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventosVO> buscarEventoPordId(@PathVariable Integer id){
        return eventosService.buscaEventoPorId(id);
    }

    @GetMapping
    public ResponseEntity<List<EventosVO>> Lista(){
        return eventosService.Listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Integer id){
        return eventosService.deletarEvento(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventosForm> atualizar (@RequestBody EventosForm eventosForm, @PathVariable Integer id){
        if(this.imagem.equals("")){
            return eventosService.atualizarEvento(eventosForm, id);
        }else {
            eventosForm.setCaminhoImagem(this.imagem);
            this.imagem = "";
            return eventosService.atualizarEvento(eventosForm, id);
        }
    }

    @PutMapping("/inscricao/evento/{idEvento}/usuario/{idUsuario}")
    public ResponseEntity<EventosForm> inscricaoEvento (@PathVariable Integer idEvento, @PathVariable Integer idUsuario){
        return eventosService.inscricaoEvento(idEvento, idUsuario);
    }

    @PutMapping("/removerInscricao/evento/{idEvento}/usuario/{idUsuario}")
    public ResponseEntity<EventosForm> removerInscricaoEvento (@PathVariable Integer idEvento, @PathVariable Integer idUsuario){
        return eventosService.removerInscricaoEvento(idEvento, idUsuario);
    }

    @PutMapping("/pagamento/evento/{idEvento}/usuario/{idUsuario}")
    public ResponseEntity<EventosForm> confirmarPagamento (@PathVariable Integer idEvento, @PathVariable Integer idUsuario){
        return eventosService.confirmarPagamento(idEvento, idUsuario);
    }

}
