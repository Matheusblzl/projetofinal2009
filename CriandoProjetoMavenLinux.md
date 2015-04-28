# Introduction #

Criando um projeto Maven no linux

### Artigo tirado da página : http://fabriciojf.wordpress.com/2009/03/22/criando-um-projeto-maven-no-linux/ ###

### Ver também : http://hotwork.sourceforge.net/hotwork/manual/maven/maven-user-guide.html#CONFIGURAÇÕES ###

# Details #

**O que é Maven?**

Maven é uma ferramenta da Apache Software Foundation usada para gerenciamento e automação de projetos em Java. Com ele você consegue gerenciar com mais facilidade as dependências do seu projeto. Ele é similar ao Ant, também da apache porém é mais fácil de configurar.

O Maven centraliza as informações do projeto em um arquivo chamado pom.xml (Project Object Model). Neste arquivo são descritos dados como: nome do projeto, informações dos desenvolvedores, dados do repositório e sistema de controle de versão, dependências de bibliotecas externas entre outros…

**Instalando o Maven**

Para instalar o maven em seu ambiente de trabalho digite o seguinte comando no console:

sudo apt-get install maven2

Ao instalar o maven2, ele cria um repositório em sua máquina que pode ser acessado através do comando:
```
cd ~/.m2/repository
```
Neste repositório ele colocará todas as bibliotecas que você declarar nos pom.xml das suas aplicações. O interessante é que estas aplicações ficam minúsculas no controle de versão, já que as bibliotecas estão centralizadas no maven e não precisam mais acompanhar os fontes dos projetos.

No maven, toda vez que você compila a aplicação ele executa o download daquelas libs declaradas que não constam no repositório local e, por isso, se você baixar os fontes do projeto em outra máquina, basta instalar o maven e ele resolverá todas as dependências para você.

**Agora que o ambiente está preparado, vamos criar a pasta do projeto?**

Crie e acesse a pasta que receberá nosso projeto maven. ex:
```
mkdir helloworld
cd helloworld
```

**O que são archetypes?**

O Maven trabalha com archetypes ou arquétipos que tem por significado, segundo o site http://www.eon.com.br/unilae/unil351.htm, padrão original ou modelo, a partir do qual outras coisas da mesma natureza são feitas. Protótipo Ideal das coisas, idéia que serve de modelo em relação a outras. Ou seja, você cria projetos utilizando modelos pré-definidos segundo a necessidade e  o archetype se encarrega de organizar toda a estrutura inicial. Para um projeto web, por exemplo, o archetype montaria a seguinte estrutura de pastas:
```
/pom.xml
src
main
java
resources
webapp
test
java
resources
webapp
readme.txt
license.txt
```

Esta estrutura é um padrão para projetos java web. Dentro da pasta raiz ‘/’ não deve existir nada além do pom.xml, o src, o readme.txt e o license.txt. Todas as classes java devem ficar dentro de /src/main/java. Todos os arquivos relativos a web (jsp, html, xhtml) devem ficar dentro de /webapp e os demais arquivos, como os .properties, devem ficar dentro de /resources.

Se o seu projeto for utilizar mais de uma linguagem de programação, como por exemplo, Java e Ruby, você deve criar uma pasta para os códigos ruby no mesmo nível da pasta Java. ex:
```
/pom.xml
src
main
java
ruby
resources
webapp
test
java
ruby
resources
webapp
readme.txt
license.txt
```

A pasta test contém a mesma estrutura da pasta main, mas, como o próprio nome sugere, é utilizada apenas para testes.

Quando você compila um projeto no maven com o mvn compile, ele cria uma pasta target no nível do /src para publicar a aplicação, por isso, quando for remeter seu projeto para o repositório do controle de versão, não envie esta pasta, execute o comando mvn clean e ele limpará o projeto deletando os arquivos desnecessários.

**Agora que você conhece o archetype crie o projeto Maven através do comando:**
```
mvn archetype:generate
```
Este comando, archetype:generate, irá iniciar um wizard para a criação do projeto. O processo é muito intuitivo basta apenas entender o que significam os termos que serão solicitados durante a criação. São eles:

**groupId :**  domínio do fabricante ex: br.com.fabriciojf;

**artifactId :**  nome do projeto ex: helloworld;

**versao :**  versão do projeto. O maven utiliza o padrão ‘1.0 SNAPSHOT‘ para a versão corrente em desenvolvimento, por isso, é bom utilizarmos o mesmo para o nosso projeto;

**package :**  nome do pacote java que deverá ser gerado inicialmente ex: `br.com.fabriciojf.helloworld`;

Após esses passos, serão listados os archetypes disponíveis no repositório oficial do maven. Você deve escolher a opção desejada e pressionar Y. Pronto, o projeto já está criado. Agora podemos acrescentar suas dependências no pom.xml e criar os arquivos da aplicação em sua ide de preferência, respeitando a hierarquia de pastas descrita acima. O interessante é que o projeto criado pelo maven já é reconhecido pelo Netbeans e pelo Eclipse, por isso, não é necessário criar um novo projeto com fontes existentes basta abri-los normalmente.

Se você não encontrou um archetype que sirva às suas necessidades, baixe-o de outro repositório, basta descobrir sua url e informá-la para o archetypeCatalog. Ex:  Para baixar um archetype JSF/JSP de outro repositório, digite na linha de comando:
```
mvn archetype:generate -DarchetypeCatalog=http://myfaces.apache.org
```
Onde -D é o atributo que define o novo valor para a variável archetypeCatalog. Este comando altera o catálogo do maven apenas neste momento, após sua execução ele retornará ao repositório default.