# Introduction #

Tutorial para configurar o pom.xml com a estrutua do eclipse.

## **Atenção:** Artigo tirado da página http://www.urubatan.com.br/to-maven-or-not-to-maven-20/ ##

# Details #

primeiro, configuramos um POM simples:
```
<project xmlns=”http://maven.apache.org/POM/4.0.0″
xmlns:xsi=”http://www.w3.org/2001/XMLSchema-instance”
xsi:schemaLocation=”http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd”>
    <modelVersion>4.0.0</modelVersion>
    <groupId>br.com.techoffice</groupId>
    <artifactId>teste</artifactId>
    <packaging>war</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>Application Test</name>
    <description>Teste de descrição do projeto</description>
    <url>http://test.techoffice.com.br</url>
</project>
```
Depois adicionamos algumas dependencias como em um projeto maven padrão.
```
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>myfaces</groupId>
        <artifactId>tomahawk</artifactId>
        <version>1.1.1</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.4</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
E agora algumas configurações para fazer com que o maven aceite a estrutura de diretórios do projeto como montamos ele:
```
<build>
    <sourceDirectory>src</sourceDirectory>
    <testSourceDirectory>test</testSourceDirectory>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>2.0</version>
            <configuration>
            <warSourceDirectory>
                ${basedir}/WebContent
            </warSourceDirectory>
            <archiveClasses>true</archiveClasses>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.5</source>
                <target>1.5</target>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-site-plugin</artifactId>
            <configuration>
                <siteDirectory>${basedir}/site</siteDirectory>
                <locales>en,pt</locales>
            </configuration>
        </plugin>
    </plugins>
</build>
```
sourceDirectory é o parametro que configura o diretório de fontes java.
testSourceDirectory é o diretório que configura o diretório de fontes de teste.
warSourceDirectory dentro do plugin de geração web (maven-war-plugin) é o parametro que diz onde esta a raiz do site.
siteDirectory dentro do plugin de geração do site de documentação (maven-site-plugin) é o parametro que diz onde estão os recursos do site.
e no plugin de compilação, configuramos ele para Java 5.

prontinho, a unica coisa que não funcionou, foi que o arquivo site.xml tem que obrigatoriamente estar no diretório src/site, pois este caminho esta hard coded no código fonte do plugin do maven.

### Para executar o maven e criar um projeto web, utilize o seguinte comando: ###
```
mvn eclipse:eclipse -Dwtpversion=2.0
```