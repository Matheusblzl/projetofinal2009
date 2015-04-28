# Introduction #

Automatizando seus projetos com o Maven 2

### Atenção, artigo tirado de http://www.pbjug.org/jugs/documentos/maven_2. ###


# Details #

O Maven é uma ferramenta de gerência e compreensão de projetos. Mas o que seriam a gerência e compreensão de projetos? O Maven gerencia projetos desde a sua criação (com a geração do esqueleto inicial do sistema) até a sua implantação em um servidor (remoto ou não).

E a compreensão do projeto? O Maven mantém todas as informações do projeto em um único lugar, o Project Object Model (POM), que é o arquivo de configuração do projeto onde são definidas todas as suas características. No POM são definidas desde informações básicas do projeto, como nome, desenvolvedores, repositórios de código fonte (sistemas de controle de versão, como CVS e Subversion), como suas dependências em bibliotecas externas e até mesmo plugins do próprio Maven que são utilizados para facilitar a vida dos desenvolvedores, como um servidor web embutido que executa diretamente de dentro do projeto.

Além de tudo isso, o Maven é uma ferramenta que prega a padronização dos projetos. Se você conhece a estrutura de um projeto básico do Maven, não vai ter problemas para entender outro projeto que também siga a mesma estrutura e isso diminui drasticamente o tempo que o desenvolvedor vai levar para “entrar” no novo sistema, pois ele não vai precisar entender uma nova estrutura ou aprender novos conceitos.

O Maven também segue a premissa da “convenção sobre configuração”, onde se você segue o padrão, não é necessário dizer ao Maven que você está fazendo isso. Um exemplo clássico disso é a estrutura de diretórios, se você segue toda a estrutura de diretórios padrão do Maven no seu projeto, não vai precisar dizer ao plugin do compilador javac onde ficam os seus arquivos de código fonte nem pra onde ele deve copiar os arquivos “.class” resultantes, ele já sabe exatamente onde procurar por tudo.

Ele foi desenvolvido originalmente pela equipe do projeto Jakarta Turbine com o objetivo de simplificar os “build files” do Ant utilizados no projeto. Eles estavam procurando por uma maneira de deixar todos os projetos seguindo uma mesma estrutura padronizada e também queriam não ter mais que enviar os arquivos “.jar” das dependências para os sistemas de controle de versão, foi então que surgiu a primeira versão da ferramenta, que em 2005 foi completamente reescrita, resultando no Maven 2, que foi construído sobre toda a experiência dos desenvolvedores e usuários da primeira versão.

## Iniciando o projeto ##

A primeira parte na criação de um projeto para o Maven é a definição do seu arquivo de configuração, o POM, é nesse arquivo que ficam todas as informações sobre o projeto que o Maven utiliza para fazer a sua gerência. Seguindo a estrutura de diretórios e arquivos padrão, esse arquivo tem que estar na pasta raiz do projeto e deve se chamar “pom.xml”, vejamos o nosso POM inicial:

Listagem 1 – Primeira versão do “pom.xml”
```
<?xml version="1.0" encoding="UTF-8"?>
<project>
	<modelVersion>4.0.0</modelVersion>
	<groupId>br.com.guj</groupId>
	<artifactId>aprendendo-maven</artifactId>
	<packaging>war</packaging>
	<version>0.0.1</version>
	<name>Aprendendo o Maven 2</name>
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.0</version>
			<scope>test</scope>
		</dependency>
	</dependencies>
</project>
```
O nó raiz da configuração é o `<project/>`, dentro dele ficam todas as informações do projeto. O primeiro nó interno, `<modelVersion/>`, contém a versão do arquivo de configuração do Maven, que no nosso caso, para o Maven 2, é “4.0.0”, esse nó é obrigatório para que a ferramenta saiba qual a estrutura do documento.

O nó seguinte, `<groupId/>`, indica a qual grupo de projetos este projeto faz parte, é uma maneira de se organizar de maneira mais simples os vários projetos dentro de uma mesma empresa ou grupo de desenvolvedores. Se você tem várias aplicações, cada uma com vários módulos diferentes, poderia definir um `<groupId/>` diferente para cada aplicação. O nó `<artifactId/>` caracteriza o projeto atual, ele seria o módulo dentro de cada aplicação no exemplo anterior. Você poderia, por exemplo, ter um `<groupId/>` “com.bi.app” com um `<artifactId/>` “persistência”, outro “web”, outro “swing” e etc. A importância na definição dos nomes dos seus projetos vai ser abordada mais a frente na parte de repositórios do Maven.

O nó `<packaging/>` define o tipo de artefato que este projeto deve gerar, no nosso caso, definimos o artefato como um “war”, que é um arquivo de aplicação web, mas poderíamos definir ele como um “jar” comum ou qualquer outro artefato que o Maven venha a suportar. O nó `<version/>` indica a versão na qual este projeto se encontra, por padrão, os desenvolvedores do Maven adotaram o versionamento das aplicações utilizando 3 números separados por “.” (pontos), mas você pode utilizar isso da forma que achar apropriado. O nó `<name/>` é apenas um nome “mais bonito” para o projeto e vai ser utilizado na hora de gerar os logs e na criação da documentação do projeto.

Estes são basicamente os nós que vão ser encontrados em qualquer POM, o último nó, `<dependencies/>`, define uma dependência do projeto e nós vamos entender mais a frente o que isso quer dizer.

## Estrutura de diretórios padrão ##

Como já foi dito, o Maven também tem como objetivo padronizar as estruturas dos projetos para que eles possam ser compreendidos mais facilmente, então ele tem uma estrutura básica de diretórios tida como padrão para os seus projetos, que é a seguinte:
```
  - pom.xml                         -- Arquivo de configuração do projeto
  - src/                            -- Pasta raiz
           - main/                  -- Tronco principal
                   - java/          -- Código fonte Java
                   - resources/     -- Recursos no classpath (arquivos de configuração, imagens, etc)
                   - webapp/        -- Aplicação web Java
           - test/                  -- Tronco de testes
                   - java/          -- Código fonte dos testes
                   - resources/     -- Recursos no classpath dos testes
  - site/                           -- Tronco principal da documentação
```
Seguindo esta estrutura, não é necessário dizer a ferramenta onde ficam os seus arquivos, ele sabe automaticamente onde procurar por eles. Um ponto importante desta estrutura é que ela separa os arquivos da aplicação dos arquivos dos testes da aplicação, assim, quando você gerar um “JAR” ou “WAR” do sistema, os seus testes não vão junto, porque não há necessidade de se empacotar testes unitários junto com o sistema.

Nas pastas “java” você só deve colocar aquivos “.java” (arquivos de código fonte), qualquer outro tipo de arquivo vai ser ignorado pelo Maven e seus plugins, se você precisa adicionar arquivos de configuração no classpath da aplicação (dentro do “JAR”, como arquivos de configuração do Hibernate ou Log4J) eles devem estar dento da pasta “resources”, pois é nela que o Maven procura por arquivos de configuração.

A pasta “webapp” que é vista só é necessária se o projeto em questão for de uma aplicação web, senão ela não precisa ser colocada. A pasta “webapp” contém os arquivos de uma aplicação web Java, como os JSPs, imagens, e as pastas “WEB-INF” e “META-INF”, como é definido na estrutura de diretórios das aplicações web em Java. Os arquivos de configuração da aplicação web “web.xml” não são colocados nas pastas “resources” e sim dentro de “webapp/WEB-INF/web.xml”. Lembre-se sempre que só vão para as pastas “resources” os arquivos de configuração que precisam estar no classpath da aplicação.

A última pasta definida é a pasta “site”, que contém os arquivos de documentação que vão ser utilizados para gerar um “mini-site” do projeto, com informações extraídas dom POM e de outros plugins utilizados, como geradores de relatórios de análise de código.


## Repositórios e Dependências ##

Na introdução nós falamos sobre repositórios e no nosso primeiro arquivo de configuração nós vimos o nó `<dependencies/>` que declara as dependências do projeto, mas o que são as dependências do projeto?

As dependências de um projeto para o Maven são os arquivos ou bibliotecas (arquivos “JAR”) que ele precisa em alguma das fases do seu ciclo de vida. No nosso POM de exemplo, declaramos uma dependência no JAR do JUnit, para que pudéssemos utilizar as classes do JUnit no projeto. Todas as dependências necessárias para o projeto devem ser definidas no POM, para que o Maven possa utilizá-las nas fases do ciclo de vida do projeto que elas são necessárias.

Toda a execução de ações do Maven depende de em qual parte do ciclo de vida o projeto está, em um projeto, existem as fases de preparação, compilação, teste, empacotamento e instalação e as dependências estão intimamente ligadas a este ciclo de vida.

Uma dependência é definida no nó `<dependencies/>` do POM, cada dependência fica dentro de um nó `<dependency/>`, que tem os seguintes nós componentes (existem outros além dos aqui definidos):
```
<groupId/> - O valor do “groupId” do POM da dependência
<artifactId/> - O valor do “artifactId” do POM da dependência
<version/> - O valor da “version” do POM da dependência
<scope/> - O escopo do ciclo de vida do projeto no qual esta dependência vai estar disponível
```
Os nós são todos auto-explicativos, pois já os vimos no POM do projeto, entretanto, existe um nó que ainda não tínhamos visto, `<scope/>`, que define quando uma dependência está ou não disponível para o projeto. Este nó pode assumir os seguintes valores:

  * compile – A dependência fica disponível durante todas as fases do projeto, desde a compilação até a instalação do sistema. Deve ser utilizada para dependências que são chamadas diretamente pelo código. Este é o escopo padrão do Maven quando nenhum escopo é definido (o nó `<scope/>` em uma dependência é opcional).

  * provided – A dependência está disponível para compilação mas em tempo de execução ela deve ser disponibilizada pelo ambiente no qual a aplicação está executando. Um exemplo deste escopo é a API de Servlets, pois nós precisamos utilizar ela para compilar os nossos sistemas Java para a Web, mas não é necessário empacotar a aplicação com ela para rodar em um servidor web, o próprio servidor se encarrega de fornecer uma implementação.

  * runtime – É o contrário de provided, a dependência não está disponível em tempo de compilação mas é enviada junto com o projeto em tempo de execução, normalmente é utizada para bibliotecas que são carregadas dinamicamente (como drivers JDBC) e que não precisam estar disponíveis para que o sistema seja compilado.

  * test – A dependência só vai estar disponível para a execução dos testes do sistema e não vai ser enviada junto com a aplicação. Deve ser utilizada para bibliotecas que são utilizadas apenas para testar o sistema, como a biblioteca do JUnit no nosso exemplo, que só está disponível no escopo “test”.

  * system – Indica que a dependência não estará disponível no repositório do Maven e sua localização deve ser fornecida dentro do POM.

Escolha com cuidado o escopo no qual você quer que a sua dependência esteja, pois definir um escopo errado tanto pode fazer com que o seu projeto contenha bibliotecas inúteis (como adicionar bibliotecas de teste ao ambiente de produção) como também podem fazer com que ele simplesmente não funcione (como deixar uma dependência que deveria ser “runtime” como “provided”).

A gerência de dependências é uma das partes mais importantes do Maven e uma das partes mais importantes desta funcionalidade é a busca automática de dependências em repositórios na sua máquina ou na internet.

Um repositório para o Maven é uma estrutura de diretórios e arquivos na qual ele armazena e busca por todas as dependências dos projetos que ele gerencia. Sempre que você declara uma dependência em um projeto, o Maven sai em busca dessa dependência no seu repositório local (normalmente fica em “sua pasta de usuário”/.m2/repository , no Windows XP seria algo como “C:\\Documents and Settings\seu\_usuário\.m2\repository” ), se ele não encontrar nada no repositório local, vai tentar buscar a dependência em um dos seus repositórios remotos (na internet) que vem configurados automaticamente na ferramenta (você também pode definir outros repositórios além dos padrão).

### Listagem 2: Declarando a dependência no Hibernate ###
```
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate</artifactId>
		<version>3.1.3</version>
		<scope>test</scope>
		<type>jar</type>
	</dependency>
```
A primeira parte da URL é o caminho para o repositório (que neste caso é o repositório remoto do Ibiblio, mas poderia ser o seu repositório local), a segunda parte é o valor do `<groupId/>` onde a cada “.” encontrado, uma nova pasta é criada (“org.hibernate” torna-se “/org/hibernate/”), a terceira parte é o valor do `<artifactId/>` (se houverem pontos aqui eles vão ser inseridos no nome, não são criadas novas pastas como no `<groupId/>`).

A última parte é o próprio arquivo em si, que também tem o seu nome gerado com base nas informações do POM. Na declaração da dependência do JUnit no nosso POM anterior, não havia a tag `<type/>`, porque a definição dela é opcional, quando você não a define, o Maven assume automaticamente que a dependência é um arquivo “.JAR”. O Maven também adiciona um “-“ (hífen) entre o `<artifactId/>` e a `<version/>` na hora de gerar o nome do arquivo.

A capacidade de gerenciar as dependências de forma igual para todos os projetos é uma das características que o diferencia de outras ferramentas de “build” como o Ant, pois quando você utiliza o Ant, precisa definir e guardas as dependências dentro do seu projeto ou apontar diretamente para elas no seu sistema de arquivos, já com o Maven você define todas as dependências de uma única maneira (no POM) e a ferramenta se encarrega de fazer com que ela esteja disponível na sua aplicação.

Outro fator que diferencia o Maven de outras ferramentas é que ele também gerencia as “dependências das dependências” do seu projeto. Frameworks como o Hibernate utilizam várias outras bibliotecas e em outras ferramentas seria necessário definir todas estas dependências direto na sua configuração, com o Maven, ele utiliza a própria descrição da dependência (o seu POM) para descobrir quais são as suas dependências e as traz junto para o projeto, você não precisa mais se preocupar em saber todas as bibliotecas que precisam ser adicionadas ao classpath, o Maven adiciona todas elas automaticamente.

Um repositório segue uma estrutura simples de pastas baseadas nas identificações do próprio projeto, através das informações disponíveis nos nós `<groupId/>`, `<artifactId/>` e `<version/>`.