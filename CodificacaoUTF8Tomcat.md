# Introduction #

> Obtive problemas ao utilizar meu projeto web e banco de dados Postgres em codificação UTF-8. O motivo foi o Tomcat6, que por padrão utiliza a codificação ISO 8859. Nos requests, o Tomcat sempre convertia os caracteres em ISO 8859, mesmo quando a página do projeto estava em codificação UTF-8.

> Para resolver este problema foi criado um filtro para forçar a conversão em UTF-8.


# Details #

## Criar o servelt para o filtro: ##
```
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Classe responsável pelo filtro que passará caracteres fora do padrão utilizado
 * pelo sistema para o padrão UTF-8.
 */
public class EncodingFilter implements Filter {

	/**
	 * O tipo da codificação.
	 */
	public String encoding = "UTF-8"; 
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
```

## Adicionado o filtro no web.xml: ##

```
	<filter>
		<display-name>EncodingFilter</display-name>
		<filter-name>EncodingFilter</filter-name>
		<filter-class>br.com.buyFast.util.EncodingFilter</filter-class>
		<init-param>
			<description>filtro para codificação dos caracteres</description>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>EncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
```