package portal.editais.service.storage;

public record ArquivoArmazenado(
    String nomeOriginal,
    String tipoConteudo,
    Long tamanhoBytes,
    String bucket,
    String objectKey,
    String url
) {
}
