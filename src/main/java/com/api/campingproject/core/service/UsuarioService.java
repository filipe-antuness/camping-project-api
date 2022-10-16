package com.api.campingproject.core.service;

import com.api.campingproject.api.model.UsuarioEntity;
import com.api.campingproject.core.service.form.UsuarioForm;
import com.api.campingproject.api.vo.UsuarioVO;
import com.api.campingproject.core.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public ResponseEntity cadastrar (UsuarioForm usuarioForm, UriComponentsBuilder uriComponentsBuilder) {
        UsuarioEntity usuarioEntity =  usuarioForm.converter();

        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByEmail(usuarioEntity.getEmail());

        if(usuarioExistente.isPresent()){
            throw new Error("Esse usuário já existe!");
        }

        usuarioEntity.setSenha(passwordEncoder().encode(usuarioEntity.getSenha()));
        usuarioRepository.save(usuarioEntity);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioForm(usuarioEntity));
    }

    public ResponseEntity buscaUsuarioPorId (Integer id){
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if(usuarioEntity.isPresent()){
            UsuarioVO usuarioVO = new UsuarioVO(usuarioEntity.get());
            return ResponseEntity.ok(usuarioVO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<UsuarioVO>> Listar (){
        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();
        List<UsuarioVO> usuarioVOS = UsuarioVO.converterListadeUsuarios(usuarioEntities);
        return ResponseEntity.ok(usuarioVOS);
    }

    public ResponseEntity<?> deletarUsuario (Integer id){
        Optional<UsuarioEntity> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity atualizarUsuario(UsuarioForm usuarioForm, Integer id) {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findById(id);

        if(usuarioEntityOptional.isPresent()){

            usuarioEntityOptional.get().setNome(usuarioForm.getNome());
            usuarioEntityOptional.get().setSobrenome(usuarioForm.getSobrenome());
            usuarioEntityOptional.get().setCpf(usuarioForm.getCpf());
            usuarioEntityOptional.get().setEmail(usuarioForm.getEmail());
            usuarioEntityOptional.get().setDataNascimento(usuarioForm.getDataNascimento());
            usuarioEntityOptional.get().setSenha(usuarioForm.getSenha());
            usuarioEntityOptional.get().setTelefone(usuarioForm.getTelefone());
            usuarioEntityOptional.get().setBairro(usuarioForm.getBairro());
            usuarioEntityOptional.get().setCep(usuarioForm.getCep());
            usuarioEntityOptional.get().setCidade(usuarioForm.getCidade());
            usuarioEntityOptional.get().setEstado(usuarioForm.getEstado());
            usuarioEntityOptional.get().setRua(usuarioForm.getRua());
            usuarioEntityOptional.get().setNumero(usuarioForm.getNumero());

            usuarioRepository.save(usuarioEntityOptional.get());

            return ResponseEntity.ok(new UsuarioForm(usuarioEntityOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
