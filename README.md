# Projeta Fácil

## Tenologia para Engenheiros Civis, Orçamentistas e Projetistas

 * Create, Read, Update e Delete ( CRUD )

 CRUD de Etapas e Subetapas (Categorias)
 CRUD de Insumos
 Importação de Insumos
 CRUD de Composição
 CRUD de Orçamento

## Tecnologias e Ferramentas

# IDE
- STS (Spring Tools Suite) ou Intellij Community
- LiveReload no Chrome
- Maven (já vem na IDE) 

# Plataforma
- Java 1.8 (JDK e JRE)
- Spring Framework

#Versionamento e Tarefas
- Git e Site Bitbucket

# Banco de Dados e SGBD 
- Mysql 
- Workbench, dbeaver ou terminal
- nome do BD: projetafacil


## Preparando o ambiente

- 1) Instale o Java na máquina
- 2) Instale a IDE (preferencia o STS que facilita muito)
- 3) Instale o LiveReload no Chrome
- 4) Instale o git e depois crie uma conta no Bitbucket
- 5) Informe o usuário no bitbucket
- 6) Instale o Mysql
- 7) Instale o workbech ou dbeaver ou o client do terminal (linux)

## Primeiros Passos no Projeto
- 1) Faça um Clone do Projeto no Bitbucket -> Branch Sandbox
- 2) Execute esse comando apos o clone: 
	git fetch && git checkout sandbox
- 2) Importe o Projeto na IDE como Maven Project
- 3) Faça um Maven Install (ou baixe as dependencias) pela IDE
- 4) Execute o projeto pela classe ProjetafacilApplication.java (sim é como na faculdade, essa classe é como se fosse o Main.java que tem o método estático principal e a mágica acontece)
- 5) Banco de dados será criado com o nome projetafacil
- 6) Execute o script: projetafacil/script_banco_projetafacil/popula_projetafacil.sql no seu SGBD,
no Terminal pode ser: mysql -u root -p < popula_projetafacil.sql
- 7) Abra o navegador e digite localhost:8085
- 8) Pronto! Vc já esta navegando no site
- 9) Clique em acessar o sitema e entre com usuário:
	Usuário: admin@projetafacil.com
	Senha: senha@123
- 10) Navegue no sistema e implemente ou corrija bugs! =)

## Seja um commiter! =)





