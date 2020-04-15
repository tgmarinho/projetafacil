
-- Script para inserir as SUB ETAPAS da Etapa no Banco de Dados
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Levantamento topográfico",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Estudos geotécnicos/sondagens",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Consultorias técnicas",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Fiscalização/acompanhamento/gerenciamento",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Projeto arquitetônico",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Projeto estrutural",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Projeto elétrico/telefônico",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Projeto hidrossanitário",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Projeto ar condicionado",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Projeto prevenção contra incêndio",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("011", "Projeto luminotécnico",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("012", "Projeto som ambiental",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("013", "Projeto paisagismo e urbanização",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("014", "Maquete/perspectivas",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("015", "Orçamento/cronograma",(select id from etapa where codigo = '001')); 
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("016", "Fotografias",(select id from etapa where codigo = '001')); 


INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Demolições",(select id from etapa where codigo = '002'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Cópias e plotagens",(select id from etapa where codigo = '002'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Despesas legais",(select id from etapa where codigo = '002'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Licenças, taxas, registros",(select id from etapa where codigo = '002'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Seguros",(select id from etapa where codigo = '002'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Assessorias contábil e jurídica",(select id from etapa where codigo = '002'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Tapumes/cercas",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Depósitos/escritórios/proteção transeuntes",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Placa de obra",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Instalação provisória água",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Entrada provisória de energia",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Instalação provisória unidade sanitária",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Sinalização",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Instalação de bombas",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Bandejas salva-vidas",(select id from etapa where codigo = '003'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Locação da obra",(select id from etapa where codigo = '003'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Gruas",(select id from etapa where codigo = '004'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Elevador com torre, cabine, guincho",(select id from etapa where codigo = '004'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Andaimes fachadeiro e suspenso",(select id from etapa where codigo = '004'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Plataforma metálica com torres e engrenagens",(select id from etapa where codigo = '004'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Guinchos",(select id from etapa where codigo = '004'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Balancins/cadeiras suspensas",(select id from etapa where codigo = '004'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Engenheiro/arquiteto de obra",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Mestre de obra",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Contra-mestres",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Apontador",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Guincheiro",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Vigia",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Pessoal administrativo",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Consumos combustíveis e lubrificantes",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Consumos água, luz, telefone",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Material de escritório",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("011", "Medicamentos de emergência",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("012", "EPI/EPC",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("013", "Bebedouros, extintores",(select id from etapa where codigo = '005'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("014", "PCMAT/PCMSO",(select id from etapa where codigo = '005'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Limpeza permanente da obra",(select id from etapa where codigo = '006'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Retirada de entulho",(select id from etapa where codigo = '006'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Transporte interno",(select id from etapa where codigo = '007'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Transporte externo",(select id from etapa where codigo = '007'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Limpeza do terreno",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Desmatamento e destocamento",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Replantio de árvores",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Escavações manuais",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Escavações mecânicas",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Reaterro",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Compactação de solo",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Desmonte de rocha",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Movimento de terra",(select id from etapa where codigo = '008'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Retirada de terra",(select id from etapa where codigo = '008'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Laudos e despesas com vizinhos",(select id from etapa where codigo = '009'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Outros",(select id from etapa where codigo = '009'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Escoramentos de terrenos de vizinhos",(select id from etapa where codigo = '010'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Esgotamento, rebaixamento lençol d’água e drenagens",(select id from etapa where codigo = '010'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Preparo das fundações: cortes em rochas, lastros",(select id from etapa where codigo = '010'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Fundações superficiais/rasas",(select id from etapa where codigo = '010'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Fundações profundas",(select id from etapa where codigo = '010'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Reforços e consolidação das fundações",(select id from etapa where codigo = '010'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Provas de cargas em estacas",(select id from etapa where codigo = '010'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Provas de carga sobre o terreno de fundação",(select id from etapa where codigo = '010'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Concreto protendido",(select id from etapa where codigo = '011'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Concreto armado",(select id from etapa where codigo = '011'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Estrutura metálica",(select id from etapa where codigo = '011'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Estrutura de madeira",(select id from etapa where codigo = '011'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Estrutura mista",(select id from etapa where codigo = '011'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Alvenarias de tijolos maciços",(select id from etapa where codigo = '012'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Alvenarias de tijolos furados",(select id from etapa where codigo = '012'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Alvenarias de blocos",(select id from etapa where codigo = '012'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Paredes de gesso acartonado",(select id from etapa where codigo = '012'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Divisórias leves",(select id from etapa where codigo = '012'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Elementos vazados",(select id from etapa where codigo = '012'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Alvenarias estruturais",(select id from etapa where codigo = '012'));

				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Esquadrias de madeira",(select id from etapa where codigo = '013'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Esquadrias de ferro",(select id from etapa where codigo = '013'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Esquadrias de alumínio",(select id from etapa where codigo = '013'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Esquadrias plásticas",(select id from etapa where codigo = '013'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Esquadrias mistas",(select id from etapa where codigo = '013'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Persianas e outros",(select id from etapa where codigo = '013'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Ferragens",(select id from etapa where codigo = '013'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Peitoris",(select id from etapa where codigo = '013'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Vidros lisos transparentes",(select id from etapa where codigo = '014'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Vidros fantasia",(select id from etapa where codigo = '014'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Vidros temperados",(select id from etapa where codigo = '014'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Vidros aramados",(select id from etapa where codigo = '014'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Vidros de segurança",(select id from etapa where codigo = '014'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Tijolos de vidro",(select id from etapa where codigo = '014'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Brises",(select id from etapa where codigo = '015'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Estrutura de madeira para cobertura",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Estrutura metálica para cobertura",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Cobertura com telhas fibrocimento",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Cobertura com telhas cerâmicas",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Cobertura com telhas plásticas",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Cobertura com telhas de alumínio",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Cobertura com telhas de aço",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Cobertura com telhas sanduíche",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Outros tipos de coberturas",(select id from etapa where codigo = '016'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Funilaria",(select id from etapa where codigo = '016'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Impermeabilização de fundações",(select id from etapa where codigo = '017'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Impermeabilização de sanitários",(select id from etapa where codigo = '017'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Impermeabilização de cozinhas",(select id from etapa where codigo = '017'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Impermeabilização de terraços e jardins",(select id from etapa where codigo = '017'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Impermeabilização de lajes descobertas",(select id from etapa where codigo = '017'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Impermeabilização de lajes cobertas",(select id from etapa where codigo = '017'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Impermeabilização de lajes de subsolo",(select id from etapa where codigo = '017'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Juntas de dilatação",(select id from etapa where codigo = '017'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Tratamento térmico",(select id from etapa where codigo = '018'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Tratamento acústico",(select id from etapa where codigo = '018'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Outros tratamentos especiais",(select id from etapa where codigo = '018'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Revestimentos de argamassa",(select id from etapa where codigo = '019'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Revestimentos cerâmicos/azulejos",(select id from etapa where codigo = '019'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Revestimentos de mármore e granito",(select id from etapa where codigo = '019'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Revestimentos de pastilhas",(select id from etapa where codigo = '019'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Outros revestimentos",(select id from etapa where codigo = '019'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Peitoris",(select id from etapa where codigo = '019'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Forros de argamassa",(select id from etapa where codigo = '020'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Forros de gesso em placa",(select id from etapa where codigo = '020'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Forros de gesso acartonado",(select id from etapa where codigo = '020'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Forros de madeira mineralizada",(select id from etapa where codigo = '020'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Forros de alumínio",(select id from etapa where codigo = '020'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Forros de plástico",(select id from etapa where codigo = '020'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Forros de madeira",(select id from etapa where codigo = '020'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Outros tipos de forro",(select id from etapa where codigo = '020'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Rodaforros e outros complementos",(select id from etapa where codigo = '020'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Fechamento de shafts",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Alçapão",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Corrimão e guarda-corpo",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Escada de marinheiro	",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Gradis e grades",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Portões de veículos e de pedestres",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Porta corta-fogo",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Grelhas de piso",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Chaminé metálica",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Coifa",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("011", "Balcões de madeira",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("012", "Caixa de correio",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("013", "Escadas metálicas",(select id from etapa where codigo = '021'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("014", "Outros",(select id from etapa where codigo = '021'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Selador paredes",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Selador portas e madeiras",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Massa corrida pva e acrílica",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Pintura PVA",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Pintura acrílica",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Revestimento texturizado",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Pintura a cal",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Pintura esmalte sobre ferro",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Pintura esmalte sobre madeira",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Pintura verniz sobre madeira",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("011", "Pintura verniz sobre alvenaria",(select id from etapa where codigo = '022'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("012", "Outros tipos de pinturas",(select id from etapa where codigo = '022'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Contrapiso",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Pisos cerâmicos",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Pisos de ardósia",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Concreto desempenado",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Cimentados",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Pisos de basalto",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Pisos de madeira",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Pisos de mármore e granito",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Pisos plásticos",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Carpetes e tapetes",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("011", "Pisos de granitina",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("012", "Pisos de blocos",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("013", "Meio-fio",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("014", "Degraus e patamares",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("015", "Asfálticas",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("016", "Aços",(select id from etapa where codigo = '023'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("017", "Outros tipos de pavimentações",(select id from etapa where codigo = '023'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "rodapé cerâmico",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "rodapé cimentado",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "rodapé de ardósia",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "rodapé de madeira",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "rodapé plástico",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "rodapé de granitina",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "rodapés de mármore e granito",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "rodapés de basalto",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "soleira de ardósia",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "soleira de madeira",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("011", "soleira de granitina",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("012", "soleiras de mármore e granito",(select id from etapa where codigo = '024'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("013", "soleiras de basalto",(select id from etapa where codigo = '024'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Registros",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Válvulas",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Ligações flexíveis",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Sifões",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Torneiras",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Bacias sanitárias",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Cubas",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Lavatórios",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Tanques",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Mictórios",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("011", "Tampos",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("012", "Complementos de louça",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("013", "Equipamentos sanitários para deficientes",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("014", "Saboneteira para líquido",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("015", "Secador de mãos elétrico",(select id from etapa where codigo = '025'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("016", "Bebedouros elétricos",(select id from etapa where codigo = '025'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Eletrodutos, conexões, buchas e arruelas",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Fios e cabos",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Caixas e quadros de comando",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Tomadas e interruptores",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Luminárias,acessórios,postes,lâmpadas",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Equipamentos diversos elétricos",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("007", "Entrada de energia",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("008", "Eletrodutos e conexões telefônicas",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("009", "Fios e cabos telefônicos",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("010", "Caixas telefônicas",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("011", "Equipamentos diversos telefônicos",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("012", "Eletrodutos, fios, caixas para lógica e tv a cabo",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("013", "Sistema de proteção contra descargas atmosféricas",(select id from etapa where codigo = '026'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("014", "Mão-de-obra",(select id from etapa where codigo = '026'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Tubos e conexões de água fria",(select id from etapa where codigo = '027'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Tubos e conexões de água quente",(select id from etapa where codigo = '027'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Tubos e conexões de esgoto sanitário",(select id from etapa where codigo = '027'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Tubos e conexões de águas pluviais",(select id from etapa where codigo = '027'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Instalações de GLP",(select id from etapa where codigo = '027'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("006", "Mão-de-obra",(select id from etapa where codigo = '027'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Tubos e conexões",(select id from etapa where codigo = '028'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Válvulas e registros",(select id from etapa where codigo = '028'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Abrigos, hidrantes, mangueiras, extintores",(select id from etapa where codigo = '028'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Mão-de-obra",(select id from etapa where codigo = '028'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Elevadores",(select id from etapa where codigo = '029'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Monta-cargas",(select id from etapa where codigo = '029'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Escadas rolantes",(select id from etapa where codigo = '029'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("004", "Esteiras e planos inclinados",(select id from etapa where codigo = '029'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("005", "Outras instalações mecânicas",(select id from etapa where codigo = '029'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Limpeza final",(select id from etapa where codigo = '030'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Retirada de entulhos",(select id from etapa where codigo = '030'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Desmontagem do canteiro de obras",(select id from etapa where codigo = '030'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Obras artísticas e painéis",(select id from etapa where codigo = '031'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Diversos",(select id from etapa where codigo = '031'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Complementos, acabamentos, acertos finais",(select id from etapa where codigo = '032'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Ligações de água, luz, telefone, gás, etc",(select id from etapa where codigo = '033'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Ligações de redes públicas",(select id from etapa where codigo = '033'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Ensaios gerais nas instalações",(select id from etapa where codigo = '034'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Arremates",(select id from etapa where codigo = '034'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("003", "Habite-se",(select id from etapa where codigo = '034'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Paisagismo",(select id from etapa where codigo = '031'));
				
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("001", "Indenizações a terceiros",(select id from etapa where codigo = '035'));
INSERT INTO subetapa (codigo, nome, id_etapa) VALUES ("002", "Imprevistos diversos",(select id from etapa where codigo = '035'));