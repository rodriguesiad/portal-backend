INSERT INTO user
(`createdAt`, `updatedAt`, `nome`,  `email`, `password`, `profile`)
VALUES
-- Usuário Principal
('2026-05-30', '2026-05-30', 'Caio Alexandre', 'alexandre.caio.ramos@gmail.com',
 '$2a$10$qo5HA3l5CI36aB83YXnqN.X8dPao5BqC1OSAa5PZzUhyPYJ1UEKCa', 'ADMINISTRADOR'),
('2026-05-30', '2026-05-30', 'Avaliador Tocantins', 'avaliador@portal.to.gov.br', 
    '$2a$10$qo5HA3l5CI36aB83YXnqN.X8dPao5BqC1OSAa5PZzUhyPYJ1UEKCa', 'AVALIADOR'),
('2026-05-30', '2026-05-30', 'Proponente Tocantins', 'proponente@portal.to.gov.br', 
 '$2a$10$qo5HA3l5CI36aB83YXnqN.X8dPao5BqC1OSAa5PZzUhyPYJ1UEKCa', 'PROPONENTE');

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

INSERT INTO documentos (
    criadoEm,
    tamanhoBytes,
    id,
    tipoConteudo,
    bucket,
    objectKey,
    url,
    nomeOriginal,
    contexto
)
VALUES (
    '2026-05-31 02:08:48.641889',
    3925705,
    UUID_TO_BIN('cbeedde6-0d2d-4cfe-ae67-b1697a70e97f'),
    'application/pdf',
    'portal-editais',
    'edital/7eac097d-c61a-4c20-92e2-a056c04b4b0f/39dbc37a-533d-41e1-9033-b206d477c9f6.pdf',
    'http://localhost:9000/portal-editais/edital/7eac097d-c61a-4c20-92e2-a056c04b4b0f/39dbc37a-533d-41e1-9033-b206d477c9f6.pdf',
    '2022.-Edital-de-Chamamento-Publico-no.-01.2022-DOE-1.pdf',
    'EDITAL'
);

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
    '2026-06-01',
    '2026-08-31',
    NOW(),
    NOW(),
    'ABERTO'
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
    'ABERTO'
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

INSERT INTO edital_documentos (
    edital_id,
    criadoEm,
    documento_id
)
VALUES
(
    1,
    '2026-05-31 02:17:27.104123',
    UUID_TO_BIN('cbeedde6-0d2d-4cfe-ae67-b1697a70e97f')
),
(
    2,
    '2026-05-31 02:17:27.104123',
    UUID_TO_BIN('cbeedde6-0d2d-4cfe-ae67-b1697a70e97f')
),
(
    3,
    '2026-05-31 02:17:27.104123',
    UUID_TO_BIN('cbeedde6-0d2d-4cfe-ae67-b1697a70e97f')
),
(
    4,
    '2026-05-31 02:17:27.104123',
    UUID_TO_BIN('cbeedde6-0d2d-4cfe-ae67-b1697a70e97f')
),
(
    5,
    '2026-05-31 02:17:27.104123',
    UUID_TO_BIN('cbeedde6-0d2d-4cfe-ae67-b1697a70e97f')
),
(
    6,
    '2026-05-31 02:17:27.104123',
    UUID_TO_BIN('cbeedde6-0d2d-4cfe-ae67-b1697a70e97f')
),
(
    7,
    '2026-05-31 02:17:27.104123',
    UUID_TO_BIN('cbeedde6-0d2d-4cfe-ae67-b1697a70e97f')
);