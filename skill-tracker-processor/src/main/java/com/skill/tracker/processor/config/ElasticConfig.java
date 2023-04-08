package com.skill.tracker.processor.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.net.ssl.SSLContext;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchClients;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.support.HttpHeaders;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableElasticsearchRepositories(basePackages = "com.skill.tracker.processor")
@Slf4j
public class ElasticConfig extends ElasticsearchConfiguration {

	@Value("${elasticsearch.username}")
	private String userName;

	@Value("${elasticsearch.password}")
	private String password;

	@Override
	public ClientConfiguration clientConfiguration() {

		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
		ClientConfiguration clientConfiguration = null;
		try {
			SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true);
			final SSLContext sslContext = sslBuilder.build();

			clientConfiguration = ClientConfiguration.builder() //
					.connectedTo("localhost:9200") //
					.usingSsl().withBasicAuth(userName, password).withHeaders(() -> {
						HttpHeaders headers = new HttpHeaders();
						headers.add("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
						return headers;
					}).withClientConfigurer(
							ElasticsearchClients.ElasticsearchHttpClientConfigurationCallback.from(clientBuilder -> {
								clientBuilder.setSSLContext(sslContext);
								clientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
								clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
								return clientBuilder;
							}))
					.build();
			log.info("elasticsearch client created");
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			log.error("ElasticConfig >> ", e);
		}
		return clientConfiguration;

	}


}
