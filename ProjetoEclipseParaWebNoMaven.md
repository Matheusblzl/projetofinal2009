# Introduction #

Projeto Eclipse para web no maven2


# Details #

Para passar o projeto web criado no maven2 para um projeto eclipse utiliza o comando:

```
mvn eclipse:eclipse -Dwtpversion=2.0
```

É recomendado utilizar os seguintes plugins:
```
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.0.2</version>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>2.0.2</version>
				<configuration>
					<source>1.6</source>
					<target>1.6</target>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-assembly-plugin</artifactId>
				<version>2.1</version>
				<configuration>
					<descriptorRefs>
						<descriptorRef>
							jar-with-dependencies
						</descriptorRef>
					</descriptorRefs>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-deploy-plugin</artifactId>
				<version>2.4</version>
			</plugin>
```