package br.com.ca.vou_de_busao.service;

import br.com.ca.vou_de_busao.exceptions.ExcursaoNotFoundException;
import br.com.ca.vou_de_busao.model.Excursao;
import br.com.ca.vou_de_busao.repository.ExcursaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class ExcursaoService {
    @Autowired
    private ExcursaoRepository excursaoRepository;

    public Excursao criar(Excursao excursao){
        validarCampos(excursao);
        return excursaoRepository.save(excursao);
    }
    public List<Excursao> listar() {
        if(excursaoRepository.findAll().isEmpty()){
            throw new RuntimeException("Nenhuma excursão cadastrada ainda.");
        }
        return excursaoRepository.findAll();
    }

    public List<Excursao> buscarPorDestino(String destino) {
        List<Excursao> resultados = excursaoRepository.findByDestinoContainingIgnoreCase(destino);
        if (resultados.isEmpty()) {
            throw new ExcursaoNotFoundException(destino);
        }
        return resultados;
    }
    public Excursao atualizar(Long id, Excursao novaExcursao) {
        validarCampos(novaExcursao);
        if(excursaoRepository.existsById(id)){
            Excursao excursao = excursaoRepository.findById(id).get();
            excursao.setData(novaExcursao.getData());
            excursao.setDestino(novaExcursao.getDestino());
            excursao.setPreco(novaExcursao.getPreco());

            return excursaoRepository.save(excursao);
        }else{
            throw new ExcursaoNotFoundException(id);
        }
    }

    public void deletar(Long id){
        if(!excursaoRepository.existsById(id)){
            throw new ExcursaoNotFoundException(id);
        }
        excursaoRepository.deleteById(id);
    }

    private void validarCampos(Excursao excursao){
        if(excursao.getDestino() == null || excursao.getDestino().isBlank()){
            throw new IllegalArgumentException("Destino não pode ser vazio.");
        }
        if(excursao.getPreco() == null || excursao.getPreco().doubleValue() <= 0){
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }
        if(excursao.getData() == null){
            throw new IllegalArgumentException("Data é obrigatória.");
        }
    }


}
