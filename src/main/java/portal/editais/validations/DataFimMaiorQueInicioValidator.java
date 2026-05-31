package portal.editais.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import portal.editais.dto.projeto.atividade.AtividadeDTO;

public class DataFimMaiorQueInicioValidator
        implements ConstraintValidator<DataFimMaiorQueInicio, AtividadeDTO> {

    @Override
    public boolean isValid(
            AtividadeDTO dto,
            ConstraintValidatorContext context) {

        if (dto == null
                || dto.dataInicio() == null
                || dto.dataFim() == null) {
            return true;
        }

        return !dto.dataFim().isBefore(dto.dataInicio());
    }
}