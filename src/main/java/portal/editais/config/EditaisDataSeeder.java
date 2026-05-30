package portal.editais.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import portal.editais.entity.Estado;
import portal.editais.entity.FrenteAtuacao;
import portal.editais.entity.OrgaoProponente;
import portal.editais.entity.RegiaoImediata;
import portal.editais.repository.EstadoRepository;
import portal.editais.repository.FrenteAtuacaoRepository;
import portal.editais.repository.OrgaoProponenteRepository;
import portal.editais.repository.RegiaoImediataRepository;

@Component
public class EditaisDataSeeder implements CommandLineRunner {

    private final EstadoRepository estadoRepository;
    private final OrgaoProponenteRepository orgaoProponenteRepository;
    private final FrenteAtuacaoRepository frenteAtuacaoRepository;
    private final RegiaoImediataRepository regiaoImediataRepository;

    public EditaisDataSeeder(
        EstadoRepository estadoRepository,
        OrgaoProponenteRepository orgaoProponenteRepository,
        FrenteAtuacaoRepository frenteAtuacaoRepository,
        RegiaoImediataRepository regiaoImediataRepository
    ) {
        this.estadoRepository = estadoRepository;
        this.orgaoProponenteRepository = orgaoProponenteRepository;
        this.frenteAtuacaoRepository = frenteAtuacaoRepository;
        this.regiaoImediataRepository = regiaoImediataRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Estado tocantins = criarTocantins();
        criarOrgaosProponentes(tocantins);
        criarFrentesAtuacao();
        criarRegioesImediatas();
    }

    private Estado criarTocantins() {
        return estadoRepository.findBySiglaAndAtivoTrue("TO")
            .orElseGet(() -> estadoRepository.save(Estado.builder()
                .nome("Tocantins")
                .sigla("TO")
                .codigoIbge("17")
                .ativo(true)
                .build()));
    }

    private void criarOrgaosProponentes(Estado tocantins) {
        List<OrgaoProponente> orgaos = List.of(
            OrgaoProponente.builder().codigo("SEMARH").nome("SEMARH").estado(tocantins).ativo(true).build(),
            OrgaoProponente.builder().codigo("NATURATINS").nome("NATURATINS").estado(tocantins).ativo(true).build(),
            OrgaoProponente.builder().codigo("SEAGRO").nome("SEAGRO").estado(tocantins).ativo(true).build()
        );

        orgaos.stream()
            .filter(orgao -> !orgaoProponenteRepository.existsByCodigo(orgao.getCodigo()))
            .forEach(orgaoProponenteRepository::save);
    }

    private void criarFrentesAtuacao() {
        List<FrenteAtuacao> frentes = List.of(
            FrenteAtuacao.builder().codigo("AGRICULTURA").nome("Agricultura").ativo(true).build(),
            FrenteAtuacao.builder().codigo("QUILOMBOLA").nome("Quilombola").ativo(true).build(),
            FrenteAtuacao.builder().codigo("RESTAURACAO").nome("Restauração").ativo(true).build(),
            FrenteAtuacao.builder().codigo("CONSERVACAO").nome("Conservação").ativo(true).build(),
            FrenteAtuacao.builder().codigo("POVOS_INDIGENAS").nome("Povos Indígenas").ativo(true).build(),
            FrenteAtuacao.builder().codigo("RECURSOS_HIDRICOS").nome("Recursos Hídricos").ativo(true).build(),
            FrenteAtuacao.builder().codigo("EDUCACAO_AMBIENTAL").nome("Educação Ambiental").ativo(true).build(),
            FrenteAtuacao.builder().codigo("PREVENCAO_QUEIMADAS").nome("Prevenção de Queimadas").ativo(true).build(),
            FrenteAtuacao.builder().codigo("CARBONO").nome("Carbono").ativo(true).build(),
            FrenteAtuacao.builder().codigo("BIODIVERSIDADE").nome("Biodiversidade").ativo(true).build()
        );

        frentes.stream()
            .filter(frente -> !frenteAtuacaoRepository.existsByCodigo(frente.getCodigo()))
            .forEach(frenteAtuacaoRepository::save);
    }

    private void criarRegioesImediatas() {
        List<RegiaoImediata> regioes = List.of(
            RegiaoImediata.builder().codigo("ARAGUAINA").nome("Araguaína").ativo(true).build(),
            RegiaoImediata.builder().codigo("ARAGUATINS").nome("Araguatins").ativo(true).build(),
            RegiaoImediata.builder().codigo("COLINAS_DO_TOCANTINS").nome("Colinas do Tocantins").ativo(true).build(),
            RegiaoImediata.builder().codigo("DIANOPOLIS").nome("Dianópolis").ativo(true).build(),
            RegiaoImediata.builder().codigo("GUARAI").nome("Guaraí").ativo(true).build(),
            RegiaoImediata.builder().codigo("GURUPI").nome("Gurupi").ativo(true).build(),
            RegiaoImediata.builder().codigo("MIRACEMA_DO_TOCANTINS").nome("Miracema do Tocantins").ativo(true).build(),
            RegiaoImediata.builder().codigo("PALMAS").nome("Palmas").ativo(true).build(),
            RegiaoImediata.builder().codigo("PARAISO_DO_TOCANTINS").nome("Paraíso do Tocantins").ativo(true).build(),
            RegiaoImediata.builder().codigo("PORTO_NACIONAL").nome("Porto Nacional").ativo(true).build(),
            RegiaoImediata.builder().codigo("TOCANTINOPOLIS").nome("Tocantinópolis").ativo(true).build()
        );

        regioes.stream()
            .filter(regiao -> !regiaoImediataRepository.existsByCodigo(regiao.getCodigo()))
            .forEach(regiaoImediataRepository::save);
    }
}
