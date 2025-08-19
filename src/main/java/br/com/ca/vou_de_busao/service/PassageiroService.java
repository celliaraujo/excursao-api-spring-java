package br.com.ca.vou_de_busao.service;

import br.com.ca.vou_de_busao.exceptions.PassageiroNotFoundException;
import br.com.ca.vou_de_busao.model.Passageiro;
import br.com.ca.vou_de_busao.repository.PassageiroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PassageiroService {
    @Autowired
    private PassageiroRepository passageiroRepository;

    public Passageiro criar(@Valid Passageiro passageiro){
        return passageiroRepository.save(passageiro);
    }

    public List<Passageiro> listarTodos(){
        return passageiroRepository.findAll();
    }

    public Optional<Passageiro> buscarPorId(Long id){
        if(passageiroRepository.findById(id).isEmpty()){
            throw new PassageiroNotFoundException(id);
        }else {
            return passageiroRepository.findById(id);
        }
    }

    public List<Passageiro> buscarPorNome(String nome){
        if(passageiroRepository.findByNomeContainingIgnoreCase(nome).isEmpty()){
            throw new PassageiroNotFoundException(nome);
        }
        return passageiroRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Passageiro atualizar(Long id, Passageiro passageiro){
        Passageiro passageiroAlt = passageiroRepository.findById(id)
                .orElseThrow(() -> new PassageiroNotFoundException(id));
        validarCampos(passageiro);
        passageiroAlt.setCpf(passageiro.getCpf());
        passageiroAlt.setNome(passageiro.getNome());
        passageiroAlt.setTelefone(passageiro.getTelefone());
        return passageiroRepository.save(passageiroAlt);
    }

    public void deletar(Long id){
        if(passageiroRepository.existsById(id)){
            passageiroRepository.deleteById(id);
        }else{
            throw new PassageiroNotFoundException(id);
        }
    }

    private void validarCampos(Passageiro passageiro){
        if(passageiroRepository.existsByCpf(passageiro.getCpf())){
            throw new IllegalArgumentException("Já existe um cadastro vinculado a este cpf.");
        }
        if(passageiro.getTelefone() == null || passageiro.getTelefone().isBlank()){
            throw new IllegalArgumentException("Telefone não pode ser vazio.");
        }
        if(passageiro.getNome() == null || passageiro.getNome().isBlank()){
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }

    }
}
