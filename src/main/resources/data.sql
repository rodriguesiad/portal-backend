INSERT INTO user
(`createdAt`, `updatedAt`, `nome`,  `email`, `password`, `profile`)
VALUES
-- Usuário Principal
('2026-05-30', '2026-05-30', 'Caio Alexandre', 'alexandre.caio.ramos@gmail.com',
 '$2a$10$qo5HA3l5CI36aB83YXnqN.X8dPao5BqC1OSAa5PZzUhyPYJ1UEKCa', 'ADMINISTRADOR'),
('2026-05-30', '2026-05-30', 'Avaliador Tocantins', 'avaliador@portal.to.gov.br', 
    '$2a$10$qo5HA3l5CI36aB83YXnqN.X8dPao5BqC1OSAa5PZzUhyPYJ1UEKCa', 'AVALIADOR'),
('2026-05-30', '2026-05-30', 'Proponente Tocantins', 'proponente@portal.to.gov.br', 
 '$2a$10$qo5HA3l5CI36aB83YXnqN.X8dPao5BqC1OSAa5PZzUhyPYJ1UEKCa', 'PROPONENTE'),
('2026-05-30', '2026-05-30', 'Fiscal Tocantins', 'fiscal@portal.to.gov.br',
 '$2a$10$qo5HA3l5CI36aB83YXnqN.X8dPao5BqC1OSAa5PZzUhyPYJ1UEKCa', 'AUDITOR');

INSERT INTO natureza_juridica (`codigo`, `nome`) VALUES
('101-5', 'Órgão Público do Poder Executivo Federal'),
('102-3', 'Órgão Público do Poder Executivo Estadual ou do Distrito Federal'),
('103-1', 'Órgão Público do Poder Executivo Municipal'),
('104-0', 'Órgão Público do Poder Legislativo Federal'),
('105-8', 'Órgão Público do Poder Legislativo Estadual ou do Distrito Federal'),
('106-6', 'Órgão Público do Poder Legislativo Municipal'),
('107-4', 'Órgão Público do Poder Judiciário Federal'),
('108-2', 'Órgão Público do Poder Judiciário Estadual'),
('110-4', 'Autarquia Federal'),
('111-2', 'Autarquia Estadual ou do Distrito Federal'),
('112-0', 'Autarquia Municipal'),
('113-9', 'Fundação Federal'),
('114-7', 'Fundação Estadual ou do Distrito Federal'),
('115-5', 'Fundação Municipal'),
('116-3', 'Órgão Público Autônomo da União'),
('117-1', 'Órgão Público Autônomo Estadual ou do Distrito Federal'),
('118-0', 'Órgão Público Autônomo Municipal'),

('201-1', 'Empresa Pública'),
('203-8', 'Sociedade de Economia Mista'),
('204-6', 'Sociedade Anônima Aberta'),
('205-4', 'Sociedade Anônima Fechada'),
('206-2', 'Sociedade Empresária Limitada'),
('207-6', 'Sociedade Empresária em Nome Coletivo'),
('208-9', 'Sociedade Empresária em Comandita Simples'),
('209-7', 'Sociedade Empresária em Comandita por Ações'),
('210-0', 'Sociedade Mercantil de Capital e Indústria (extinta pelo NCC/2002)'),
('212-7', 'Sociedade Empresária em Conta de Participação'),
('213-5', 'Empresário (Individual)'),
('214-3', 'Cooperativa'),
('215-1', 'Consórcio de Sociedades'),
('216-0', 'Grupo de Sociedades'),
('217-8', 'Estabelecimento, no Brasil, de Sociedade Estrangeira'),
('219-4', 'Estabelecimento, no Brasil, de Empresa Binacional Argentino-Brasileira'),
('220-8', 'Entidade Binacional Itaipu'),
('221-6', 'Empresa Domiciliada no Exterior'),
('222-4', 'Clube/Fundo de Investimento'),
('223-2', 'Sociedade Simples Pura'),
('224-0', 'Sociedade Simples Limitada'),
('225-9', 'Sociedade em Nome Coletivo'),
('226-7', 'Sociedade em Comandita Simples'),
('227-5', 'Sociedade Simples em Conta de Participação'),
('230-5', 'Empresa Individual de Responsabilidade Limitada'),

('303-4', 'Serviço Notarial e Registral (Cartório)'),
('304-2', 'Organização Social'),
('305-0', 'Organização da Sociedade Civil de Interesse Público (Oscip)'),
('306-9', 'Outras Formas de Fundações Mantidas com Recursos Privados'),
('307-7', 'Serviço Social Autônomo'),
('308-5', 'Condomínio Edilícios'),
('309-3', 'Unidade Executora (Programa Dinheiro Direto na Escola)'),
('310-7', 'Comissão de Conciliação Prévia'),
('311-5', 'Entidade de Mediação e Arbitragem'),
('312-3', 'Partido Político'),
('313-1', 'Entidade Sindical'),
('320-4', 'Estabelecimento, no Brasil, de Fundação ou Associação Estrangeiras'),
('321-2', 'Fundação ou Associação Domiciliada no Exterior'),
('399-9', 'Outras Formas de Associação'),

('401-4', 'Empresa Individual Imobiliária'),
('402-2', 'Segurado Especial'),
('408-1', 'Contribuinte Individual'),

('500-2', 'Organização Internacional e Outras Instituições Extraterritoriais');

-- Registros Relacionados à localização
INSERT INTO `estados` VALUES (1,'17',1,'TO','Tocantins');

INSERT INTO `regioes_imediatas` VALUES (1,1,'ARAGUAINA','Araguaína'),(1,2,'ARAGUATINS','Araguatins'),(1,3,'COLINAS_DO_TOCANTINS','Colinas do Tocantins'),(1,4,'DIANOPOLIS','Dianópolis'),(1,5,'GUARAI','Guaraí'),(1,6,'GURUPI','Gurupi'),(1,7,'MIRACEMA_DO_TOCANTINS','Miracema do Tocantins'),(1,8,'PALMAS','Palmas'),(1,9,'PARAISO_DO_TOCANTINS','Paraíso do Tocantins'),(1,10,'PORTO_NACIONAL','Porto Nacional'),(1,11,'TOCANTINOPOLIS','Tocantinópolis');

-- Alguns dos municípios do Tocantins

INSERT INTO `municipio` VALUES (1,8,'Aparecida do Rio Negro'),(2,8,'Lagoa do Tocantins'),(3,8,'Lajeado'),(4,8,'Palmas'),(5,8,'Rio Sono'),(6,10,'Ponte Alta do Tocantins'),(7,10,'Porto Nacional'),(8,10,'Natividade'),(9,9,'Abreulândia'),(10,9,'Araguacema'),(11,9,'Nova Rosalândia'),(12,9,'Paraíso do Tocantins'),(13,9,'Divinópolis do Tocantins'),(14,9,'Lagoa da Confusão'),(15,7,'Dois Irmãos do Tocantins'),(16,7,'Miracema do Tocantins'),(17,7,'Miranorte'),(18,7,'Rio dos Bois'),(19,7,'Tocantínia'),(20,1,'Ananás'),(21,1,'Angico'),(22,1,'Aragominas'),(23,1,'Araguaína'),(24,5,'Guaraí'),(25,3,'Colinas do Tocantins'),(26,3,'Itacajá'),(27,3,'Itapiratins'),(28,11,'Nazaré'),(29,11,'Palmeiras do Tocantins'),(30,11,'Santa Terezinha do Tocantins'),(31,11,'Tocantinópolis'),(32,2,'Araguatins'),(33,2,'Augustinópolis'),(34,6,'Formoso do Araguaia'),(35,6,'Gurupi'),(36,4,'Almas'),(37,4,'Arraias'),(38,4,'Dianópolis'),(39,4,'Lavandeira');


-- Registros para inserção de Editais

INSERT INTO `orgaos_proponentes` VALUES (1,1,1,'SEMARH','SEMARH'),(1,1,2,'NATURATINS','NATURATINS'),(1,1,3,'SEAGRO','SEAGRO');

INSERT INTO `frentes_atuacao` VALUES (1,1,'AGRICULTURA','Agricultura'),(1,2,'QUILOMBOLA','Quilombola'),(1,3,'RESTAURACAO','Restauração'),(1,4,'CONSERVACAO','Conservação'),(1,5,'POVOS_INDIGENAS','Povos Indígenas'),(1,6,'RECURSOS_HIDRICOS','Recursos Hídricos'),(1,7,'EDUCACAO_AMBIENTAL','Educação Ambiental'),(1,8,'PREVENCAO_QUEIMADAS','Prevenção de Queimadas'),(1,9,'CARBONO','Carbono'),(1,10,'BIODIVERSIDADE','Biodiversidade');

-- Editais
INSERT INTO editais (
    estado_id,
    frente_atuacao_id,
    regiao_imediata_id,
    orgao_proponente_id,
    titulo,
    resumo,
    valorMinimo,
    valorMaximo,
    inicioRecebimentoPropostas,
    fimRecebimentoPropostas,
    criadoEm,
    atualizadoEm,
    status
)
VALUES

(
    1,
    9,
    8,
    1,
    'Programa Estadual de Créditos de Carbono REDD+ 2026',
    'Seleção de parceiros para execução de projetos voltados à certificação, monitoramento e comercialização de créditos de carbono oriundos de iniciativas REDD+ no Estado do Tocantins.',
    500000.00,
    15000000.00,
    '2026-03-01',
    '2026-08-31',
    NOW(),
    NOW(),
    'EM_AVALIACAO'
),

(
    1,
    3,
    10,
    2,
    'Recuperação de Áreas Degradadas do Cerrado Tocantinense',
    'Apoio a projetos de restauração ecológica, recuperação de APPs e recomposição de vegetação nativa em áreas prioritárias para conservação.',
    100000.00,
    2500000.00,
    '2026-05-15',
    '2026-07-15',
    NOW(),
    NOW(),
    'EM_AVALIACAO'
),

(
    1,
    6,
    9,
    1,
    'Proteção de Nascentes e Recursos Hídricos',
    'Fomento a iniciativas de conservação de nascentes, recuperação de matas ciliares e melhoria da qualidade dos recursos hídricos.',
    50000.00,
    1200000.00,
    '2026-04-01',
    '2026-05-30',
    NOW(),
    NOW(),
    'EM_AVALIACAO'
),

(
    1,
    5,
    11,
    2,
    'Fortalecimento da Gestão Territorial Indígena',
    'Financiamento de ações de proteção territorial, vigilância comunitária, manejo sustentável e valorização de conhecimentos tradicionais.',
    200000.00,
    5000000.00,
    '2026-03-01',
    '2026-04-30',
    NOW(),
    NOW(),
    'EM_AVALIACAO'
),

(
    1,
    8,
    1,
    2,
    'Prevenção e Combate a Incêndios Florestais',
    'Seleção de projetos para capacitação de brigadas comunitárias, aquisição de equipamentos e ações preventivas contra queimadas.',
    75000.00,
    1800000.00,
    '2026-01-15',
    '2026-03-15',
    NOW(),
    NOW(),
    'ABERTO'
),

(
    1,
    1,
    6,
    3,
    'Agricultura Sustentável de Baixa Emissão de Carbono',
    'Apoio a produtores rurais interessados em implementar práticas agrícolas sustentáveis e sistemas produtivos resilientes.',
    30000.00,
    800000.00,
    '2026-07-01',
    '2026-09-30',
    NOW(),
    NOW(),
    'RASCUNHO'
),

(
    1,
    10,
    2,
    2,
    'Conservação da Biodiversidade do Bico do Papagaio',
    'Projetos voltados à proteção da fauna e flora nativas, monitoramento ambiental e conservação de ecossistemas prioritários.',
    100000.00,
    3000000.00,
    '2026-06-15',
    '2026-09-15',
    NOW(),
    NOW(),
    'ABERTO'
);

INSERT INTO criterios_avaliacao (
    edital_id,
    ordem,
    nome,
    descricao
)
VALUES
(1,1,'Capacidade Técnica','Experiência comprovada em projetos de carbono, REDD+ ou serviços ambientais.'),
(1,2,'Capacidade Financeira','Demonstração de capacidade econômico-financeira para execução do projeto.'),
(1,3,'Abrangência de Atuação','Experiência nacional ou internacional em mercados regulados ou voluntários de carbono.'),
(1,4,'Governança e Compliance','Existência de programa de integridade, compliance e anticorrupção.'),
(1,5,'Benefícios Ambientais','Potencial de geração de benefícios ambientais para o Estado do Tocantins.');

INSERT INTO edital_avaliadores (
    edital_id,
    usuario_id
)
VALUES
(1,2),
(2,2),
(3,2),
(4,2),
(5,2),
(6,2),
(7,2);

-- Projetos
-- REPRESENTANTES LEGAIS

INSERT INTO representante_legal
(id,nomeCompleto,email,telefone,cpf, cargo)
VALUES
(1,'Maria Silva Santos','maria@institutocerrado.org','63999990001','11111111111', 'Diretora'),
(2,'João Pereira Lima','joao@ecotocantins.org','63999990002','22222222222', 'Gestor'),
(3,'Ana Souza Costa','ana@aguaviva.org','63999990003','33333333333', 'Gestora'),
(4,'Carlos Oliveira Rocha','carlos@indigena.org','63999990004','44444444444', 'Diretora'),
(5,'Fernanda Almeida','fernanda@brigadas.org','63999990005','55555555555', 'Presidente'),
(6,'Ricardo Mendes','ricardo@agroverde.org','63999990006','66666666666', 'Diretor'),
(7,'Juliana Barbosa','juliana@biodiversidade.org','63999990007','77777777777', 'Diretor');

INSERT INTO representante_legal
(id,nomeCompleto,email,telefone,cpf,cargo)
VALUES
(8,'Paulo Carvalho','paulo@carbonoto.org','63999990008','88888888888','Diretor Executivo'),
(9,'Luciana Ramos','luciana@appsvivas.org','63999990009','99999999999','Presidente'),
(10,'Marcos Ferreira','marcos@nascentes.org','63999990010','10101010101','Coordenador'),
(11,'Patricia Sousa','patricia@guardioes.org','63999990011','11111111112','Diretora');

-- INSTITUIÇÕES

INSERT INTO instituicao
(
 id,
 createdAt,
 cnpj,
 razaoSocial,
 nomeFantasia,
 dataFundacao,
 id_natureza_juridica,
 areaAtuacao,
 site,
 situacao,
 id_representante_legal
)
VALUES
(
 1,
 NOW(),
 '12.345.678/0001-01',
 'Instituto Cerrado Sustentável',
 'ICS',
 '2014-03-10',
 56,
 'Projetos REDD+, carbono e restauração',
 'https://ics.org.br',
 'ATIVA',
 1
),
(
 2,
 NOW(),
 '12.345.678/0001-02',
 'Instituto Eco Tocantins',
 'EcoTO',
 '2016-06-20',
 56,
 'Conservação ambiental',
 'https://ecoto.org.br',
 'ATIVA',
 2
),
(
 3,
 NOW(),
 '12.345.678/0001-03',
 'Associação Água Viva',
 'Água Viva',
 '2012-01-15',
 56,
 'Recursos hídricos',
 'https://aguaviva.org.br',
 'ATIVA',
 3
),
(
 4,
 NOW(),
 '12.345.678/0001-04',
 'Instituto Terras Indígenas',
 'ITI',
 '2010-09-22',
 56,
 'Gestão territorial indígena',
 'https://iti.org.br',
 'ATIVA',
 4
),
(
 5,
 NOW(),
 '12.345.678/0001-05',
 'Associação Brigadas do Cerrado',
 'ABC',
 '2018-05-12',
 56,
 'Prevenção de queimadas',
 'https://abc.org.br',
 'ATIVA',
 5
),
(
 6,
 NOW(),
 '12.345.678/0001-06',
 'Cooperativa Agroverde',
 'Agroverde',
 '2015-04-18',
 29,
 'Agricultura sustentável',
 'https://agroverde.coop.br',
 'ATIVA',
 6
),
(
 7,
 NOW(),
 '12.345.678/0001-07',
 'Instituto Biodiversidade Tocantins',
 'IBT',
 '2013-11-08',
 56,
 'Fauna e flora',
 'https://ibt.org.br',
 'ATIVA',
 7
);

-- INSTITUIÇÕES

INSERT INTO instituicao
(
id,
createdAt,
cnpj,
razaoSocial,
nomeFantasia,
dataFundacao,
id_natureza_juridica,
areaAtuacao,
site,
situacao,
id_representante_legal
)
VALUES

(
8,
NOW(),
'12.345.678/0001-08',
'Instituto Carbono Tocantins',
'ICT',
'2018-02-01',
56,
'Mercado de carbono',
'https://ict.org.br',
'ATIVA',
8
),

(
9,
NOW(),
'12.345.678/0001-09',
'Associação APPs Vivas',
'APPs Vivas',
'2016-04-20',
56,
'Restauração ecológica',
'https://appsvivas.org.br',
'ATIVA',
9
),

(
10,
NOW(),
'12.345.678/0001-10',
'Instituto Nascentes do Cerrado',
'INC',
'2015-06-15',
56,
'Recursos hídricos',
'https://inc.org.br',
'ATIVA',
10
),

(
11,
NOW(),
'12.345.678/0001-11',
'Associação Guardiões Apinajé',
'AGA',
'2017-08-10',
56,
'Proteção territorial indígena',
'https://aga.org.br',
'ATIVA',
11
);

-- LOCALIZAÇÕES

INSERT INTO localizacao
(id,id_municipio,latitude,longitude,comunidade)
VALUES
(1,4,'-10.184','-48.333','Região Norte de Palmas'),
(2,7,'-10.708','-48.417','Comunidade Ribeirinha Porto Nacional'),
(3,14,'-10.790','-49.620','Bacia do Javaés'),
(4,31,'-6.324','-47.421','Terra Indígena Apinajé'),
(5,23,'-7.191','-48.207','Zona Rural Araguaína'),
(6,35,'-11.729','-49.068','Assentamentos Gurupi'),
(7,32,'-5.646','-48.123','Bico do Papagaio');

INSERT INTO localizacao
(id,id_municipio,latitude,longitude,comunidade)
VALUES
(8,4,'-10.210','-48.310','Comunidade São João'),
(9,7,'-10.680','-48.430','Projeto Ribeirinho Tocantins'),
(10,12,'-10.790','-48.880','Assentamento Esperança'),
(11,31,'-6.280','-47.390','Terra Indígena Apinajé Norte');

-- PÚBLICO BENEFICIADO

INSERT INTO publico_beneficiado
(
 id,
 mulheresQuant,
 homensQuant,
 criancasQuant,
 jovensQuant,
 idososQuant,
 povosIndigenasQuant,
 quilombolasQuant,
 agricultoresFamiliarQuant,
 comunidadesTradicionaisQuant,
 rendaMedia,
 fonteRendaPrincipal,
 descricaoAplicacaoBeneficio
)
VALUES
(1,220,180,120,140,40,0,0,80,20,1800,'Serviços Ambientais','Capacitação e geração de renda por carbono'),
(2,150,170,90,100,30,0,0,60,10,1500,'Agricultura','Recuperação de áreas degradadas'),
(3,120,130,80,70,20,0,0,50,30,1400,'Pesca','Proteção de nascentes'),
(4,140,150,110,90,25,250,0,0,40,1200,'Extrativismo','Proteção territorial indígena'),
(5,90,110,40,60,10,0,0,30,0,1600,'Agricultura','Prevenção de queimadas'),
(6,200,210,130,120,35,0,0,220,0,1900,'Agricultura Familiar','Transição produtiva sustentável'),
(7,180,190,90,110,20,0,0,40,20,1700,'Turismo de Natureza','Conservação da biodiversidade');

INSERT INTO publico_beneficiado
(
id,
mulheresQuant,
homensQuant,
criancasQuant,
jovensQuant,
idososQuant,
povosIndigenasQuant,
quilombolasQuant,
agricultoresFamiliarQuant,
comunidadesTradicionaisQuant,
rendaMedia,
fonteRendaPrincipal,
descricaoAplicacaoBeneficio
)
VALUES
(
8,180,170,90,100,20,0,0,70,10,1850,
'Serviços Ambientais',
'Fortalecimento da economia verde baseada em créditos de carbono'
),
(
9,140,150,70,90,30,0,0,90,15,1450,
'Agricultura Familiar',
'Recuperação produtiva e ambiental de áreas degradadas'
),
(
10,160,180,80,120,25,0,0,60,35,1300,
'Pesca Artesanal',
'Proteção de nascentes e recuperação de matas ciliares'
),
(
11,120,130,100,90,15,220,0,0,30,1100,
'Extrativismo',
'Proteção territorial e fortalecimento cultural indígena'
);

-- PLANOS

INSERT INTO plano_execucao
(
objetivoGeral,
objetivoEspecifico
)
VALUES
(
'Implementar ações de conservação florestal associadas ao mercado de carbono.',
'Mapear áreas prioritárias, capacitar comunidades e monitorar estoques de carbono.'
),
(
'Recuperar áreas degradadas do Cerrado.',
'Restaurar APPs, recompor vegetação nativa e reduzir processos erosivos.'
),
(
'Promover agricultura sustentável.',
'Capacitar produtores e implantar sistemas produtivos de baixa emissão.'
),
(
'Conservar a biodiversidade regional.',
'Monitorar espécies e recuperar habitats prioritários.'
),
(
'Fortalecer a gestão territorial indígena.',
'Capacitar lideranças e apoiar o monitoramento territorial.'
);

INSERT INTO plano_execucao
(id,objetivoGeral,objetivoEspecifico)
VALUES
(
6,
'Expandir iniciativas de carbono jurisdicional.',
'Desenvolver inventário florestal, monitoramento remoto e capacitação comunitária.'
),
(
7,
'Recuperar áreas prioritárias do Cerrado.',
'Restaurar vegetação nativa e promover práticas sustentáveis.'
),
(
8,
'Proteger recursos hídricos.',
'Recuperar nascentes e ampliar áreas de proteção permanente.'
),
(
9,
'Fortalecer territórios indígenas.',
'Promover vigilância territorial e formação de agentes ambientais indígenas.'
);

INSERT INTO atividade
(descricao, responsavel, dataInicio, dataFim, id_plano_execucao)
VALUES

('Mapeamento ambiental da área do projeto','Equipe Técnica','2026-06-01','2026-07-15',1),
('Inventário florestal e de carbono','Equipe Técnica','2026-07-16','2026-09-30',1),

('Diagnóstico das áreas degradadas','Coordenação Ambiental','2026-06-01','2026-07-01',2),
('Plantio de mudas nativas','Equipe de Campo','2026-07-02','2026-12-30',2),

('Capacitação de produtores rurais','Equipe Agrícola','2026-08-01','2026-09-01',3),
('Implantação de unidades demonstrativas','Equipe Agrícola','2026-09-02','2027-03-01',3),

('Levantamento de fauna e flora','Biólogos','2026-06-01','2026-08-30',4),
('Monitoramento da biodiversidade','Biólogos','2026-09-01','2027-02-28',4),

('Capacitação de lideranças indígenas','Equipe Social','2026-06-01','2026-07-30',5),
('Monitoramento territorial participativo','Equipe Indígena','2026-08-01','2027-01-31',5);

INSERT INTO atividade
(descricao,responsavel,dataInicio,dataFim,id_plano_execucao)
VALUES

('Inventário de carbono florestal','Equipe REDD+','2026-06-01','2026-08-01',6),
('Monitoramento remoto','Equipe Geoespacial','2026-08-02','2026-12-01',6),

('Levantamento ambiental','Equipe Técnica','2026-06-01','2026-07-15',7),
('Restauração ecológica','Equipe Campo','2026-07-16','2026-12-30',7),

('Mapeamento de nascentes','Equipe Hidrológica','2026-06-01','2026-07-01',8),
('Recuperação de APPs','Equipe Ambiental','2026-07-02','2027-01-30',8),

('Formação de agentes indígenas','Equipe Social','2026-05-01','2026-07-01',9),
('Monitoramento territorial','Equipe Comunitária','2026-07-02','2027-02-01',9);

-- PROJETOS

INSERT INTO projeto
(
createdAt,
id_autor,
id_edital,
id_instituicao,
id_localizacao,
id_publico_beneficiado,
id_plano_execucao,
auditor_id,
nomeProjeto,
resumo,
justificativaMerito,
status,
declarouVeracidadeInformacoes,
autorizouTratamentoDadosLgpd,
comprometeuPrestacaoContas,
autorizouMonitoramentoAuditoria
)
VALUES

(
NOW(),
3,
1,
1,
1,
1,
1,
2,
'Carbono Cerrado Tocantins',
'Projeto voltado à geração de créditos de carbono REDD+.',
'Contribui para redução do desmatamento e geração de renda.',
'EM_AVALIACAO',
b'1',b'1',b'1',b'1'
),

(
NOW(),
3,
2,
2,
2,
2,
2,
2,
'Restaura Cerrado',
'Recuperação de áreas degradadas.',
'Fortalecimento da restauração ecológica.',
'EM_AVALIACAO',
b'1',b'1',b'1',b'1'
),

(
NOW(),
3,
6,
3,
3,
3,
3,
NULL,
'Agricultura Carbono Neutro',
'Promoção de práticas agrícolas sustentáveis.',
'Redução de emissões e aumento da produtividade.',
'SUBMETIDO',
b'1',b'1',b'1',b'1'
),

(
NOW(),
3,
7,
4,
4,
4,
4,
2,
'Biodiversidade do Bico',
'Conservação de espécies prioritárias.',
'Proteção dos ecossistemas regionais.',
'EM_AVALIACAO',
b'1',b'1',b'1',b'1'
),

(
NOW(),
3,
4,
5,
5,
5,
5,
2,
'Territórios Indígenas Sustentáveis',
'Fortalecimento da gestão territorial indígena.',
'Proteção ambiental e valorização cultural.',
'APROVADO',
b'1',b'1',b'1',b'1'
);

INSERT INTO projeto
(
createdAt,
id_autor,
id_edital,
id_instituicao,
id_localizacao,
id_publico_beneficiado,
id_plano_execucao,
auditor_id,
nomeProjeto,
resumo,
justificativaMerito,
status,
declarouVeracidadeInformacoes,
autorizouTratamentoDadosLgpd,
comprometeuPrestacaoContas,
autorizouMonitoramentoAuditoria
)
VALUES

(
NOW(),
3,
1,
8,
8,
8,
6,
2,
'Inventário Carbono Tocantins',
'Projeto de quantificação e monitoramento de estoques florestais de carbono.',
'Contribui para geração de ativos ambientais e expansão do mercado de carbono.',
'EM_AVALIACAO',
b'1',b'1',b'1',b'1'
),

(
NOW(),
3,
2,
9,
9,
9,
7,
2,
'APPs Vivas do Tocantins',
'Recuperação de áreas degradadas e recomposição de vegetação nativa.',
'Promove restauração ecológica em áreas prioritárias.',
'EM_AVALIACAO',
b'1',b'1',b'1',b'1'
),

(
NOW(),
3,
3,
10,
10,
10,
8,
2,
'Nascentes do Cerrado',
'Proteção e recuperação de nascentes em bacias prioritárias.',
'Melhora a disponibilidade hídrica e reduz processos erosivos.',
'EM_AVALIACAO',
b'1',b'1',b'1',b'1'
),

(
NOW(),
3,
4,
11,
11,
11,
9,
2,
'Guardiões do Território Apinajé',
'Fortalecimento da vigilância territorial indígena.',
'Integra proteção ambiental e valorização cultural.',
'EM_AVALIACAO',
b'1',b'1',b'1',b'1'
);