package portal.editais.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import portal.editais.entity.Estado;
import portal.editais.entity.FrenteAtuacao;
import portal.editais.entity.Municipio;
import portal.editais.entity.OrgaoProponente;
import portal.editais.entity.RegiaoImediata;
import portal.editais.repository.EstadoRepository;
import portal.editais.repository.FrenteAtuacaoRepository;
import portal.editais.repository.MunicipioRepository;
import portal.editais.repository.OrgaoProponenteRepository;
import portal.editais.repository.RegiaoImediataRepository;

@Component
public class EditaisDataSeeder implements CommandLineRunner {

    private final EstadoRepository estadoRepository;
    private final OrgaoProponenteRepository orgaoProponenteRepository;
    private final FrenteAtuacaoRepository frenteAtuacaoRepository;
    private final RegiaoImediataRepository regiaoImediataRepository;
    private final MunicipioRepository municipioRepository;

    public EditaisDataSeeder(
            EstadoRepository estadoRepository,
            OrgaoProponenteRepository orgaoProponenteRepository,
            FrenteAtuacaoRepository frenteAtuacaoRepository,
            RegiaoImediataRepository regiaoImediataRepository,
            MunicipioRepository municipioRepository) {
        this.estadoRepository = estadoRepository;
        this.orgaoProponenteRepository = orgaoProponenteRepository;
        this.frenteAtuacaoRepository = frenteAtuacaoRepository;
        this.regiaoImediataRepository = regiaoImediataRepository;
        this.municipioRepository = municipioRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        Estado tocantins = criarTocantins();
        criarOrgaosProponentes(tocantins);
        criarFrentesAtuacao();
        criarRegioesImediatas();
        criarMunicipios();
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
                OrgaoProponente.builder().codigo("SEAGRO").nome("SEAGRO").estado(tocantins).ativo(true).build());

        orgaos.stream()
                .filter(orgao -> !orgaoProponenteRepository.existsByCodigo(orgao.getCodigo()))
                .forEach(orgaoProponenteRepository::save);
    }

    private void criarFrentesAtuacao() {
        List<FrenteAtuacao> frentes = List.of(
                FrenteAtuacao.builder().codigo("AGRICULTURA").nome("Agricultura Familiar").ativo(true).build(),
                FrenteAtuacao.builder().codigo("QUILOMBOLA").nome("Políticas Públicas Estruturantes").ativo(true).build(),
                FrenteAtuacao.builder().codigo("RESTAURACAO").nome("Produção Sustentável").ativo(true).build(),
                FrenteAtuacao.builder().codigo("CONSERVACAO").nome("Fortalecimento Institucional").ativo(true).build(),
                FrenteAtuacao.builder().codigo("POVOS_INDIGENAS").nome("Territórios Indígenas").ativo(true).build(),
                FrenteAtuacao.builder().codigo("RECURSOS_HIDRICOS").nome("Comunidades Tradicionais").ativo(true).build());

        frentes.stream()
                .filter(frente -> !frenteAtuacaoRepository.existsByCodigo(frente.getCodigo()))
                .forEach(frenteAtuacaoRepository::save);
    }

    private void criarRegioesImediatas() {
        List<RegiaoImediata> regioes = List.of(
                RegiaoImediata.builder().codigo("ARAGUAINA").nome("Araguaína").ativo(true).build(),
                RegiaoImediata.builder().codigo("ARAGUATINS").nome("Araguatins").ativo(true).build(),
                RegiaoImediata.builder().codigo("COLINAS_DO_TOCANTINS").nome("Colinas do Tocantins").ativo(true)
                        .build(),
                RegiaoImediata.builder().codigo("DIANOPOLIS").nome("Dianópolis").ativo(true).build(),
                RegiaoImediata.builder().codigo("GUARAI").nome("Guaraí").ativo(true).build(),
                RegiaoImediata.builder().codigo("GURUPI").nome("Gurupi").ativo(true).build(),
                RegiaoImediata.builder().codigo("MIRACEMA_DO_TOCANTINS").nome("Miracema do Tocantins").ativo(true)
                        .build(),
                RegiaoImediata.builder().codigo("PALMAS").nome("Palmas").ativo(true).build(),
                RegiaoImediata.builder().codigo("PARAISO_DO_TOCANTINS").nome("Paraíso do Tocantins").ativo(true)
                        .build(),
                RegiaoImediata.builder().codigo("PORTO_NACIONAL").nome("Porto Nacional").ativo(true).build(),
                RegiaoImediata.builder().codigo("TOCANTINOPOLIS").nome("Tocantinópolis").ativo(true).build());

        regioes.stream()
                .filter(regiao -> !regiaoImediataRepository.existsByCodigo(regiao.getCodigo()))
                .forEach(regiaoImediataRepository::save);
    }

    private void criarMunicipios() {

        RegiaoImediata palmas = regiaoImediataRepository.findByCodigo("PALMAS").orElseThrow();
        RegiaoImediata portoNacional = regiaoImediataRepository.findByCodigo("PORTO_NACIONAL").orElseThrow();
        RegiaoImediata paraiso = regiaoImediataRepository.findByCodigo("PARAISO_DO_TOCANTINS").orElseThrow();
        RegiaoImediata miracema = regiaoImediataRepository.findByCodigo("MIRACEMA_DO_TOCANTINS").orElseThrow();
        RegiaoImediata araguaina = regiaoImediataRepository.findByCodigo("ARAGUAINA").orElseThrow();
        RegiaoImediata guarai = regiaoImediataRepository.findByCodigo("GUARAI").orElseThrow();
        RegiaoImediata colinas = regiaoImediataRepository.findByCodigo("COLINAS_DO_TOCANTINS").orElseThrow();
        RegiaoImediata tocantinopolis = regiaoImediataRepository.findByCodigo("TOCANTINOPOLIS").orElseThrow();
        RegiaoImediata araguatins = regiaoImediataRepository.findByCodigo("ARAGUATINS").orElseThrow();
        RegiaoImediata gurupi = regiaoImediataRepository.findByCodigo("GURUPI").orElseThrow();
        RegiaoImediata dianopolis = regiaoImediataRepository.findByCodigo("DIANOPOLIS").orElseThrow();

        List<Municipio> municipios = List.of(

                municipio("Aparecida do Rio Negro", palmas),
                municipio("Lagoa do Tocantins", palmas),
                municipio("Lajeado", palmas),
                municipio("Palmas", palmas),
                municipio("Rio Sono", palmas),

                municipio("Ponte Alta do Tocantins", portoNacional),
                municipio("Porto Nacional", portoNacional),
                municipio("Natividade", portoNacional),

                municipio("Abreulândia", paraiso),
                municipio("Araguacema", paraiso),
                municipio("Nova Rosalândia", paraiso),
                municipio("Paraíso do Tocantins", paraiso),
                municipio("Divinópolis do Tocantins", paraiso),
                municipio("Lagoa da Confusão", paraiso),

                municipio("Dois Irmãos do Tocantins", miracema),
                municipio("Miracema do Tocantins", miracema),
                municipio("Miranorte", miracema),
                municipio("Rio dos Bois", miracema),
                municipio("Tocantínia", miracema),

                municipio("Ananás", araguaina),
                municipio("Angico", araguaina),
                municipio("Aragominas", araguaina),
                municipio("Araguaína", araguaina),

                municipio("Guaraí", guarai),

                municipio("Colinas do Tocantins", colinas),
                municipio("Itacajá", colinas),
                municipio("Itapiratins", colinas),

                municipio("Nazaré", tocantinopolis),
                municipio("Palmeiras do Tocantins", tocantinopolis),
                municipio("Santa Terezinha do Tocantins", tocantinopolis),
                municipio("Tocantinópolis", tocantinopolis),

                municipio("Araguatins", araguatins),
                municipio("Augustinópolis", araguatins),

                municipio("Formoso do Araguaia", gurupi),
                municipio("Gurupi", gurupi),

                municipio("Almas", dianopolis),
                municipio("Arraias", dianopolis),
                municipio("Dianópolis", dianopolis),
                municipio("Lavandeira", dianopolis));

        municipios.stream()
                .forEach(municipioRepository::save);
    }

    private Municipio municipio(String nome, RegiaoImediata regiaoImediata) {
        Municipio municipio = new Municipio();
        municipio.setNome(nome);
        municipio.setRegiaoImediata(regiaoImediata);
        return municipio;
    }
}
