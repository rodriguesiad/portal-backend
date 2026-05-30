package portal.editais.config;

import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadResourceConfig implements WebMvcConfigurer {

    private final String diretorioUpload;

    public UploadResourceConfig(@Value("${app.upload.editais.path:uploads/editais}") String diretorioUpload) {
        this.diretorioUpload = diretorioUpload;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String localizacao = Paths.get(diretorioUpload).toAbsolutePath().normalize().toUri().toString();
        registry.addResourceHandler("/uploads/editais/**").addResourceLocations(localizacao);
    }
}
