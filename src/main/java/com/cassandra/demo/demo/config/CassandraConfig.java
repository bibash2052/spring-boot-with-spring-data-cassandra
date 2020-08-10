package com.cassandra.demo.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    private static final String KEYSPACE = "cassandraTest";
    private static final String NODES = "127.0.0.1";
    private static final Integer PORT = 9042;

    @Override
    public String getContactPoints() {
        return NODES;
    }

    @Override
    public String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public int getPort() {
        return PORT;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification
                .createKeyspace(getKeyspaceName()).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true).withSimpleReplication();
        return Arrays.asList(specification);
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.cassandra.demo.demo.entity"};
    }

    @Override
    public String getLocalDataCenter() {
        return "datacenter1";
    }
}
