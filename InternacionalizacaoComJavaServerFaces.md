# Introduction #

Internacionalização com JavaServerFaces.

### Atenção: artigo retirado da página http://thiagoprocaci.blogspot.com/2009/07/internacionalizacao-de-mensagens-com.html ###

### Postado por Thiago Baesso Procaci ###

---

# Details #

Nesta postagem vamos mostrar como fazer a internacionalização de mensagens utilizando JSF. Internacionalizar mensagens é uma maneira de isolar toda a mensageiria de uma aplicação em arquivos. Desta forma, pode-se definir mensagens em idiomas diferentes em arquivos separados sem que a aplicação sofra nenhum impacto.

Para exemplificar a internacionalização com JSF, vamos aproveitar a aplicação exemplo mostrada na postagem “Iniciando com JSF (Tutorial com exemplo prático)”.

Primeiramente deve-se definir os arquivos com as mensagens. Para isso foi criado um pacote com.sample.messages e lá foram colocados dois arquivos com as mensagens da aplicação (um com mensagens em inglês e outro em português).

A listagem 1 e 2 mostram como ficaram os arquivos com as mensagens da aplicação.
```
messages_en_US.properties (mensagens em inglês)
portuguese=Portuguese  
english=English  
save=Save  
delete=Delete  
name=Name  
nationality=Nationality  
title=JSF  
```
### Listagem 1 ###
```
messages_pt_BR.properties (mensagens em português)
portuguese=Portugues  
english=Ingles  
save=Salvar  
delete=Excluir  
name=Nome  
nationality=Nacionalidade  
title=JSF  
```
### Listagem 2 ###

O pacote com os arquivos de mensagens é ilustrado na figura 1:
Figura 1: Pacote mensagens


Agora temos que configurar o faces-config.xml para o funcionamento da mensageiria. Para isso basta adicionar o seguinte trecho no faces-config.xml conforme a listagem 3:
```
<application>     
 <resource-bundle>  
    <base-name>com.sample.messages.messages</base-name>  
    <var>msgs</var>  
 </resource-bundle>  
 <locale-config>  
        <default-locale>pt_BR</default-locale>  
        <supported-locale>en_US</supported-locale>  
 </locale-config>  
 <message-bundle>  
      com.sample.messages.messages  
 </message-bundle>        
</application>  
```
### Listagem 3 ###

Vamos agora criar um controller para gerenciar qual será o locale (idioma) usado na aplicação. Esse controller chamaremos de LocaleController e ficará dentro do pacote com.sample.controller do exemplo. A listagem 4 mostra a implementação do LocaleController.

```
package com.sample.controller;  
  
import java.util.Locale;  
  
import javax.faces.component.UIViewRoot;  
import javax.faces.context.FacesContext;  
  
public class LocaleController {  
 private Locale currentLocale = new Locale("pt", "BR");  
  
 public void englishLocale() {  
  UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();  
  currentLocale = Locale.US;  
  viewRoot.setLocale(currentLocale);  
 }  
  
 public void portugueseLocale() {  
  UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();  
  currentLocale = new Locale("pt", "BR");  
  viewRoot.setLocale(currentLocale);  
 }  
  
 public Locale getCurrentLocale() {  
  return currentLocale;  
 }  
}  
```
### Listagem 4 ###

Vamos também definir um managed-bean no faces-config.xml para o LocaleController (listagem 5).
```
<managed-bean>  
  <managed-bean-name>localeController</managed-bean-name>  
  <managed-bean-class>  
  com.sample.controller.LocaleController  
  </managed-bean-class>  
  <managed-bean-scope>session</managed-bean-scope>     
</managed-bean>  
```
### Listagem 5 ###

Agora vamos definir a página pessoa.jsp para testar a internacionalização (listagem 6).
```
<%@ page contentType="text/html; charset=UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>  
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>  
<html>  
<f:view locale="#{localeController.currentLocale}">  
 <head>   
 <title><h:outputText value="#{msgs.title}" /></title>  
 </head>  
 <body>  
 <h:form>  
  <table>  
     <tr>  
     <td><h:commandLink value="#{msgs.portuguese}" action="#{localeController.portugueseLocale}" /></td>  
     <td><h:commandLink value="#{msgs.english}" action="#{localeController.englishLocale}" /></td>  
     </tr>     
   <tr>  
    <td><h:outputText value="#{msgs.name}" />:</td>  
    <td><h:inputText value="#{pessoaController.pessoa.nome}" /></td>  
   </tr>  
   <tr>  
    <td><h:outputText value="#{msgs.nationality}" />:</td>  
    <td><h:inputText value="#{pessoaController.pessoa.nacionalidade}" /></td>  
   </tr>  
   <tr>  
    <td><h:commandButton value="#{msgs.save}" action="#{pessoaController.save}" /></td>  
   </tr>  
  </table>  
  <h:dataTable value="#{pessoaController.pessoas}" var="p"   
rendered="#{not empty pessoaController.pessoas}" border="1">  
   <h:column>  
    <f:facet name="header">  
     <h:outputText value="#{msgs.name}" />  
    </f:facet>  
    <h:commandLink value="#{p.nome}" action="#{pessoaController.edit}">  
     <f:param name="id" value="#{p.id}" />       
    </h:commandLink>      
   </h:column>  
   <h:column>  
    <f:facet name="header">  
     <h:outputText value="#{msgs.nationality}" />  
    </f:facet>  
    <h:commandLink value="#{p.nacionalidade}" action="#{pessoaController.edit}">  
     <f:param name="id" value="#{p.id}" />       
    </h:commandLink>     
   </h:column>  
   <h:column>  
    <f:facet name="header">  
     <h:outputText value="#{msgs.delete}" />  
    </f:facet>      
    <h:commandLink value="#{msgs.delete}" action="#{pessoaController.delete}">  
     <f:param name="id" value="#{p.id}" />  
    </h:commandLink>     
   </h:column>  
  </h:dataTable>  
 </h:form>   
 </body>  
</f:view>  
</html>
```

# Locale #

### Brasil ###
_Criar campo estático._
```
public static final Locale BRAZIL = new Locale("pt", "BR");
```

### Padrão SUN ###

```
static public final Locale ENGLISH = createSingleton("en__", "en", "");  
  
static public final Locale FRENCH = createSingleton("fr__", "fr", "");  
  
static public final Locale GERMAN = createSingleton("de__", "de", "");  
  
static public final Locale ITALIAN = createSingleton("it__", "it", "");  
  
static public final Locale JAPANESE = createSingleton("ja__", "ja", "");  
  
static public final Locale KOREAN = createSingleton("ko__", "ko", "");  
  
static public final Locale CHINESE = createSingleton("zh__", "zh", "");  
  
static public final Locale SIMPLIFIED_CHINESE = createSingleton("zh_CN_", "zh", "CN");  
  
static public final Locale TRADITIONAL_CHINESE = createSingleton("zh_TW_", "zh", "TW");  
  
static public final Locale FRANCE = createSingleton("fr_FR_", "fr", "FR");  
  
static public final Locale GERMANY = createSingleton("de_DE_", "de", "DE");  
  
static public final Locale ITALY = createSingleton("it_IT_", "it", "IT");  
  
static public final Locale JAPAN = createSingleton("ja_JP_", "ja", "JP");  
  
static public final Locale KOREA = createSingleton("ko_KR_", "ko", "KR");  
  
static public final Locale CHINA = SIMPLIFIED_CHINESE;  
  
static public final Locale PRC = SIMPLIFIED_CHINESE;  
  
static public final Locale TAIWAN = TRADITIONAL_CHINESE;  
  
static public final Locale UK = createSingleton("en_GB_", "en", "GB");  
  
static public final Locale US = createSingleton("en_US_", "en", "US");  
  
static public final Locale CANADA = createSingleton("en_CA_", "en", "CA");  
  
static public final Locale CANADA_FRENCH = createSingleton("fr_CA_", "fr", "CA");  
```