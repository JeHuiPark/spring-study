package com.example.h2.config;

import com.example.h2.user.domain.UserRegister;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@ComponentScan(includeFilters = @Filter(OptionalComponent.class))
@Profile("local")
public class H2ServerConfiguration {


	/**
	 * @see org.h2.server.TcpServer
	 * @return
	 * @throws SQLException
	 */
	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSource dataSource() throws SQLException {
		//Server server = adviceRun(9093, "external_db_name", "dbname", FilePath.absolute);
		Server server = defaultRun(9093);
		if(server.isRunning(true)){
			log.info("server run success");
		}
		log.info("h2 server url = {}", server.getURL());

		return new org.apache.tomcat.jdbc.pool.DataSource();
	}

	/**
	 *
	 * @param port
	 * @param externalDbName
	 * @param dbname
	 * @param db_store
	 * @return
	 * @throws SQLException
	 */
	private Server adviceRun(int port, String externalDbName, String dbname, FilePath db_store) throws SQLException {
		return Server.createTcpServer(
				"-tcp",
				"-tcpAllowOthers",
				"-ifNotExists",
				"-tcpPort", port+"", "-key", externalDbName, db_store.value2(dbname)).start();
	}

	private Server defaultRun(int port) throws SQLException {
		return Server.createTcpServer(
				"-tcp",
				"-tcpAllowOthers",
				"-ifNotExists",
				"-tcpPort", port+"").start();
	}

	enum FilePath {
		absolute("~/"),
		relative("./");
		String prefix;
		FilePath(String prefix){
			this.prefix = prefix;
		}
		public String value2(String dbname){
			return prefix + dbname;
		}
	}
}

@RequiredArgsConstructor
@Slf4j
@OptionalComponent
class H2DefaultDataInitalizer {

	private final UserRegister userRegister;

	@PostConstruct
	public void run() {
		log.info("H2DefaultDataInitalizer 올라간다");
		userRegister.randUser();
	}
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface OptionalComponent {
}