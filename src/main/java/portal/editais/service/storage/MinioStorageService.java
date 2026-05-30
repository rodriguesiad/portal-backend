package portal.editais.service.storage;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MinioStorageService implements StorageService {

    private static final String PDF_CONTENT_TYPE = "application/pdf";

    private final MinioClient minioClient;
    private final String bucket;
    private final String endpoint;
    private final long tamanhoMaximoBytes;

    public MinioStorageService(
        @Value("${app.minio.endpoint}") String endpoint,
        @Value("${app.minio.access-key}") String accessKey,
        @Value("${app.minio.secret-key}") String secretKey,
        @Value("${app.minio.bucket}") String bucket,
        @Value("${app.documentos.tamanho-maximo-mb:20}") long tamanhoMaximoMb
    ) {
        this.endpoint = endpoint;
        this.bucket = bucket;
        this.tamanhoMaximoBytes = tamanhoMaximoMb * 1024L * 1024L;
        this.minioClient = MinioClient.builder()
            .endpoint(endpoint)
            .credentials(accessKey, secretKey)
            .build();
    }

    @Override
    public ArquivoArmazenado salvar(MultipartFile arquivo, String contexto, String donoId) {
        validarPdf(arquivo);
        criarBucketSeNecessario();

        try {
            String nomeOriginal = StringUtils.cleanPath(arquivo.getOriginalFilename() == null ? "documento.pdf" : arquivo.getOriginalFilename());
            String objectKey = contexto + "/" + donoId + "/" + UUID.randomUUID() + ".pdf";

            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectKey)
                    .stream(arquivo.getInputStream(), arquivo.getSize(), -1)
                    .contentType(PDF_CONTENT_TYPE)
                    .build()
            );

            return new ArquivoArmazenado(
                nomeOriginal,
                PDF_CONTENT_TYPE,
                arquivo.getSize(),
                bucket,
                objectKey,
                endpoint + "/" + bucket + "/" + objectKey
            );
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar documento no MinIO.");
        }
    }

    private void validarPdf(MultipartFile arquivo) {
        if (arquivo == null || arquivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento PDF é obrigatório.");
        }

        String nomeArquivo = arquivo.getOriginalFilename();
        boolean extensaoPdf = nomeArquivo != null && nomeArquivo.toLowerCase().endsWith(".pdf");
        boolean tipoPdf = PDF_CONTENT_TYPE.equalsIgnoreCase(arquivo.getContentType());

        if (!extensaoPdf || !tipoPdf) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Somente arquivos PDF são permitidos.");
        }

        if (arquivo.getSize() > tamanhoMaximoBytes) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O PDF excede o tamanho máximo permitido.");
        }
    }

    private void criarBucketSeNecessario() {
        try {
            boolean existe = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!existe) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao preparar bucket do MinIO.");
        }
    }
}
