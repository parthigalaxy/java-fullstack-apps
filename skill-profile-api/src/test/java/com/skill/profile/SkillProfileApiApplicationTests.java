//
//package com.skill.profile;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.assertThat;
//
//import org.cassandraunit.spring.CassandraDataSet;
//import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
//import org.cassandraunit.spring.EmbeddedCassandra;
//import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.datastax.oss.driver.api.core.CqlSession;
//import com.datastax.oss.driver.api.core.cql.ResultSet;
//
//@RunWith(SpringRunner.class)
//
//@TestExecutionListeners({ CassandraUnitTestExecutionListener.class })
//
//@CassandraDataSet(value = "SkillProfile.cql", keyspace = "skilltracker")
//
//@EmbeddedCassandra
//class SkillProfileApiApplicationTests {
//
//	@Before
//	public void setUp() throws Exception {
//		EmbeddedCassandraServerHelper.startEmbeddedCassandra();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
//	}
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Test
//	public void givenEmbeddedCassandraInstance_whenStarted_thenQuerySuccess() throws Exception {
//		EmbeddedCassandraServerHelper.startEmbeddedCassandra();
//		CqlSession session = (CqlSession) EmbeddedCassandraServerHelper.getSession();
//
//		ResultSet result = session.execute("select from person WHERE id=1234");
//		assertThat(result.iterator().next().getString("name"), is("Elango"));
//	}
//
//}