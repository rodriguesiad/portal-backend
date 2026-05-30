package portal.editais.service.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    ArquivoArmazenado salvar(MultipartFile arquivo, String contexto, String donoId);
}
