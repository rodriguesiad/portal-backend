package portal.editais.controller;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import portal.editais.config.exception.ApiException;
import portal.editais.dto.edital.MunicipioResponseDTO;
import portal.editais.service.municipio.MunicipioService;

@RestController
@RequestMapping("/municipios")
@Tag(name = "Municípios")
public class MunicipioController {

    private MunicipioService service;

    public MunicipioController(MunicipioService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MunicipioResponseDTO> findById(@PathVariable Integer id) throws ApiException {
        return ResponseEntity.ok(MunicipioResponseDTO.toResponse(service.findById(id)));
    }

    @GetMapping()
    @PageableAsQueryParam
    public ResponseEntity<Page<MunicipioResponseDTO>> findAll(Pageable pageable) throws ApiException {
        return ResponseEntity.ok(service.findAll(pageable).map(MunicipioResponseDTO::toResponse));
    }

}
