package portal.editais.dto.documento;

public record DocumentoDownloadDTO(
    String nomeOriginal,
    String tipoConteudo,
    byte[] conteudo
) {
}
