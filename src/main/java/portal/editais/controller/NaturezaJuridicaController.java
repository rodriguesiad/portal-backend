package portal.editais.controller;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import portal.editais.config.exception.ApiException;
import portal.editais.dto.natureza.NaturezaJuridicaResponseDTO;
import portal.editais.service.natureza.juridica.NaturezaJuridicaService;

@RestController
@RequestMapping("/natureza-juridicas")
public class NaturezaJuridicaController {

    private NaturezaJuridicaService service;

    public NaturezaJuridicaController(NaturezaJuridicaService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NaturezaJuridicaResponseDTO> findById(@PathVariable Integer id) throws ApiException {
        return ResponseEntity.ok(NaturezaJuridicaResponseDTO.toResponse(service.findById(id)));
    }

    @GetMapping()
    @PageableAsQueryParam
    public ResponseEntity<Page<NaturezaJuridicaResponseDTO>> findAll(Pageable pageable) throws ApiException {
        return ResponseEntity.ok(service.findAll(pageable).map(NaturezaJuridicaResponseDTO::toResponse));
    }

}
