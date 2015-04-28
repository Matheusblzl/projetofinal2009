# Introduction #

Exemplo Log4j


# Details #
```
#
# THRESHOLDS = OFF, FATAL, ERROR, WARN, INFO, DEBUG, ALL`
#
 
log4j.threshold=ALL


log4j.logger.org.springframework=OFF
log4j.logger.org.acegisecurity=OFF
log4j.logger.org.ajax4jsf=OFF
log4j.logger.org.apache.jasper.compiler=OFF
log4j.logger.org.apache.catalina.core.ContainerBase=OFF
log4j.logger.org.apache.catalina.session.ManagerBase=OFF
log4j.logger.org.apache.commons.digester=OFF
log4j.logger.org.apache.commons.beanutils=OFF
log4j.logger.net.sf.ehcache=OFF
log4j.logger.com.sun.facelets=OFF
log4j.logger.com.sun.facelets.compiler.TagLibraryConfig=OFF
log4j.logger.org.richfaces.model.ScrollableTableDataModel=OFF
log4j.logger.org.richfaces.renderkit.html.ScrollableDataTableBaseRenderer=OFF
log4j.logger.org.richfaces.component.UIScrollableDataTable=OFF
log4j.logger.org.richfaces.convert.selection.ClientSelectionConverter=OFF
log4j.logger.org.richfaces.model.DataModelCache=OFF
log4j.logger.org.richfaces.skin.SkinFactoryImpl=OFF
log4j.logger.org.hibernate=WARN, CONSOLE, fileout
#log4j.logger.org.jboss.seam=WARN, CONSOLE, fileout

# Path
logsPath=${user.home}${file.separator}Meus documentos${file.separator}Logs${file.separator}

log4j.rootLogger=ALL, CONSOLE, LOG
 
log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
log4j.appender.CONSOLE.layout.ConversionPattern=%d{ISO8601} %-7p [%t/%c] - %m%n
 
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{ISO8601} %-7p [%t/%c] - %m%n
 
log4j.appender.LOG=org.apache.log4j.DailyRollingFileAppender
log4j.appender.LOG.file=${logsPath}logExemplo.log
log4j.appender.LOG.MaxFileSize=1MB
log4j.appender.LOG.datePattern='.'yyyy-MM-dd
log4j.appender.LOG.layout=org.apache.log4j.PatternLayout
log4j.appender.LOG.layout.ConversionPattern=%d{ISO8601} %-7p [%t/%c] - %m%n
 
log4j.appender.fileout=org.apache.log4j.DailyRollingFileAppender
log4j.appender.fileout.file=${logsPath}logExemplo.log
log4j.appender.fileout.MaxFileSize=1MB
log4j.appender.fileout.datePattern='.'yyyy-MM-dd
log4j.appender.fileout.layout=org.apache.log4j.PatternLayout
log4j.appender.fileout.layout.ConversionPattern=%d{ISO8601} %-7p [%t/%c] - %m%n
```