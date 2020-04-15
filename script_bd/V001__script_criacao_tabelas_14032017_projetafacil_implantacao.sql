CREATE DATABASE projetafacil;
USE projetafacil;


CREATE TABLE usuario (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  ativo bit(1) DEFAULT NULL,
  email varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  senha varchar(255) DEFAULT NULL,
  tipo_usuario varchar(255) NOT NULL,
  PRIMARY KEY (id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE perfil (
  id bigint(20) NOT NULL,
  inscricao_estadual varchar(255) DEFAULT NULL,
  inscricao_municipal varchar(255) DEFAULT NULL,
  nome_fantasia varchar(255) DEFAULT NULL,
  razao_social varchar(255) DEFAULT NULL,
  cargo varchar(255) DEFAULT NULL,
  contato varchar(255) DEFAULT NULL,
  cpf varchar(255) DEFAULT NULL,
  cnpj varchar(255) DEFAULT NULL,
  tipo_pessoa varchar(255) DEFAULT NULL,
  ddd_fixo varchar(255) DEFAULT NULL,
  telefone_fixo varchar(255) DEFAULT NULL,
  ddd_celular varchar(255) DEFAULT NULL,
  celular varchar(255) DEFAULT  NULL,
  cep varchar(255) DEFAULT NULL,
  cidade varchar(255) DEFAULT NULL,
  UF varchar(255) DEFAULT NULL,
  logradouro varchar(255) DEFAULT NULL,
  bairro varchar(255) DEFAULT NULL,
  numero varchar(255) DEFAULT NULL,
  complemento varchar(255) DEFAULT NULL,
  id_usuario bigint(20) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_fornecedor_usuario (id_usuario),
  CONSTRAINT fk_fornecedor_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE grupo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE usuario_grupo (
  id_usuario bigint(20) NOT NULL,
  id_grupo bigint(20) NOT NULL,
  PRIMARY KEY (id_grupo,id_usuario),
  KEY FKcx5f61jsftmpnlu4ec8fyndg3 (id_usuario),
  CONSTRAINT FK4yweq9u2sokki6o060mejfw8r FOREIGN KEY (id_grupo) REFERENCES grupo (id),
  CONSTRAINT FKcx5f61jsftmpnlu4ec8fyndg3 FOREIGN KEY (id_usuario) REFERENCES usuario (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE permissao (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE grupo_permissao (
  id_grupo bigint(20) NOT NULL,
  id_permissao bigint(20) NOT NULL,
  KEY FKfp14wb9mt832y4jlw2rx3pf6p (id_permissao),
  KEY FKh1lvrl72de4u5xhr1u3jvo0rq (id_grupo),
  CONSTRAINT FKfp14wb9mt832y4jlw2rx3pf6p FOREIGN KEY (id_permissao) REFERENCES permissao (id),
  CONSTRAINT FKh1lvrl72de4u5xhr1u3jvo0rq FOREIGN KEY (id_grupo) REFERENCES grupo (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO grupo VALUES (1, 'ADMINISTRADOR');
INSERT INTO grupo VALUES (2, 'ORCAMENTISTA');


INSERT INTO usuario(id, ativo, email, nome, senha, tipo_usuario) VALUES (1,true,'admin@projetafacil.com','admin','$2a$06$azDV5oprJJjUhtDQgnqhZ.k8BqCWD4S9kyhyEx7CbHeYlYQgHc1Bi', 'ADMINISTRADOR');
INSERT INTO perfil(id, tipo_pessoa, cargo, id_usuario) VALUES (1,'FISICA', 'CTO na Projeta Fácil', 1);


INSERT INTO usuario(id, ativo, email, nome, senha, tipo_usuario) VALUES (2,true,'constru@yes.com','ConstruYes Material','$2a$06$azDV5oprJJjUhtDQgnqhZ.k8BqCWD4S9kyhyEx7CbHeYlYQgHc1Bi', 'ORCAMENTISTA');
INSERT INTO perfil(id, tipo_pessoa, id_usuario) VALUES (2,'JURIDICA', 2);



INSERT INTO usuario_grupo VALUES (1,1);
INSERT INTO usuario_grupo VALUES (2,2);




-- CODIFICACAO (CATEGORIZACAO DOS INSUMOS)


CREATE TABLE etapa (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  codigo varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE subetapa (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  codigo varchar(255) DEFAULT NULL,
  nome varchar(255) DEFAULT NULL,
  id_etapa bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKoigue97uy6g9x49hc0oeukh47 (id_etapa),
  CONSTRAINT FKoigue97uy6g9x49hc0oeukh47 FOREIGN KEY (id_etapa) REFERENCES etapa (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- INSUMOS E SEUS RELACIONAMENTOS COM A CODIFICACAO

CREATE TABLE insumo (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  codigo_bim varchar(255) DEFAULT NULL,
  nome varchar(999) NOT NULL,
  unidade varchar(255) NOT NULL,
  codigo_sinapi varchar(255) DEFAULT NULL,
  tipo varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE insumo_etapa (
  id_insumo bigint(20) NOT NULL,
  id_etapa bigint(20) NOT NULL,
  PRIMARY KEY (id_insumo,id_etapa),
  KEY FK3m074y0e4wfqj55rd9r19ejef (id_etapa),
  CONSTRAINT FK3m074y0e4wfqj55rd9r19ejef FOREIGN KEY (id_etapa) REFERENCES etapa (id),
  CONSTRAINT FKduvu082flmcmrgrtkxc4oemhx FOREIGN KEY (id_insumo) REFERENCES insumo (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE insumo_subetapa (
  id_insumo bigint(20) NOT NULL,
  id_sub_etapa bigint(20) NOT NULL,
  PRIMARY KEY (id_insumo,id_sub_etapa),
  KEY FKa8hb7fsihdpqmxnfg67c6k3u6 (id_sub_etapa),
  CONSTRAINT FKa8hb7fsihdpqmxnfg67c6k3u6 FOREIGN KEY (id_sub_etapa) REFERENCES subetapa (id),
  CONSTRAINT FKtf4buq6e2hl6x9mbr8ixgg2de FOREIGN KEY (id_insumo) REFERENCES insumo (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- COMPOSICAO E ITENS DA COMPOSICAO

CREATE TABLE composicao_servico (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  unidade varchar(255) DEFAULT NULL,
  referencia varchar(255) DEFAULT NULL,
  codigo_bim varchar(255) DEFAULT NULL,
  id_usuario bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKsyyr2ql56vuvyq3poe7ys7b5n (id_usuario),
  CONSTRAINT FKsyyr2ql56vuvyq3poe7ys7b5n FOREIGN KEY (id_usuario) REFERENCES usuario (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE itens_insumo_composicao (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  valor_coeficiente decimal(19,2) DEFAULT NULL,
  id_composicao_servico bigint(20) DEFAULT NULL,
  id_insumo bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKbdt8sju9q8scvj5u2jlylm81a (id_composicao_servico),
  KEY FKcnnt808n5i7k7f3ilkbdk78i7 (id_insumo),
  CONSTRAINT FKbdt8sju9q8scvj5u2jlylm81a FOREIGN KEY (id_composicao_servico) REFERENCES composicao_servico (id),
  CONSTRAINT FKcnnt808n5i7k7f3ilkbdk78i7 FOREIGN KEY (id_insumo) REFERENCES insumo (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE itens_composicao_insumo_composicao (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  valor_coeficiente decimal(19,2) DEFAULT NULL,
  id_composicao_servico bigint(20) DEFAULT NULL,
  id_insumo_composicao bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_composicao_composicao (id_composicao_servico),
  KEY fk_insumo_composicao (id_insumo_composicao),
  CONSTRAINT fk_composicao_composicao FOREIGN KEY (id_composicao_servico) REFERENCES composicao_servico (id),
  CONSTRAINT fk_insumo_composicao FOREIGN KEY (id_insumo_composicao) REFERENCES composicao_servico (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- ORCAMENTO

CREATE TABLE orcamento (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  descricao varchar(255) NOT NULL,
  preco_total decimal(19,2) DEFAULT NULL,
  valorbdi decimal(10,2) DEFAULT NULL,
  id_usuario bigint(20) DEFAULT NULL,
  criterio varchar(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_usuario_orcamento (id_usuario),
  CONSTRAINT fk_usuario_orcamento FOREIGN KEY (id_usuario) REFERENCES usuario (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE item_orcamento (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  quantidade decimal(19,2) DEFAULT NULL,
  valor_material decimal(19,2) DEFAULT NULL,
  valor_mao_de_obra decimal(19,2) DEFAULT NULL,
  valor_equipamento decimal(19,2) DEFAULT NULL,
  valor_unitario decimal(19,2) DEFAULT NULL,
  valor_total decimal(19,2) DEFAULT NULL,
  id_composicao_servico bigint(20) DEFAULT NULL,
  id_orcamento bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_id_composicao_item_orc (id_composicao_servico),
  KEY fk_id_orcamento_item_orc (id_orcamento),
  CONSTRAINT fk_id_orcamento_item_orc FOREIGN KEY (id_orcamento) REFERENCES orcamento (id),
  CONSTRAINT fk_id_composicao_item_orc FOREIGN KEY (id_composicao_servico) REFERENCES composicao_servico (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








-- Fornecedor
CREATE TABLE material_fornecedor (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  codigo_barra varchar(255) NOT NULL,
  codigo_ncm varchar(255) DEFAULT NULL,
  codigo_sinapi varchar(255) DEFAULT NULL,
  codigo_tcpo varchar(255) DEFAULT NULL,
  codigo_bim varchar(255) DEFAULT NULL,
  nome varchar(600) NOT NULL,
  preco decimal(19,2) NOT NULL,
  unidade varchar(255) NOT NULL,
  id_usuario bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY id_usuario_material_forncedor (id_usuario),
  CONSTRAINT id_usuario_material_forncedor FOREIGN KEY (id_usuario) REFERENCES usuario (id)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8;








CREATE TABLE estado (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  uf varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE cidade (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(255) DEFAULT NULL,
  id_estado bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_estado (id_estado),
  CONSTRAINT fk_estado FOREIGN KEY (id_estado) REFERENCES estado (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;











